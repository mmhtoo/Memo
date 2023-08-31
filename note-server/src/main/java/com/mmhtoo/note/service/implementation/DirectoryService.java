package com.mmhtoo.note.service.implementation;

import com.auth0.jwt.interfaces.Claim;
import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.entity.Directory;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;
import com.mmhtoo.note.mapper.DirectoryMapper;
import com.mmhtoo.note.repository.DirectoryRepo;
import com.mmhtoo.note.service.IDirectoryService;
import com.mmhtoo.note.service.ITokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DirectoryService implements IDirectoryService {

    private final DirectoryRepo directoryRepo;
    private final HttpServletRequest request;
    private final ITokenService tokenService;

    @Override
    public Directory createNewDirectory(
           DirectoryCreateReqDTO directoryCreateReqDTO
    ) throws DuplicateEntityException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException {

        Map<String, Claim> payload = this.tokenService.getPayloadFromRequest(request);

        if( payload == null || payload.get("userId") == null )
            throw new InvalidDataAccessException("Invalid account id for request!");

        directoryCreateReqDTO.setAccountId(payload.get("userId").asString());

       boolean isDuplicate = this.isDuplicateDir(
               directoryCreateReqDTO.getName() ,
               directoryCreateReqDTO.getAccountId(),
               directoryCreateReqDTO.getParentDirId()
       );

       if( isDuplicate )
           throw new DuplicateEntityException("Directory with "+directoryCreateReqDTO.getName()+" name already exists under current directory!");

       Directory parentDir = directoryCreateReqDTO.getParentDirId() == null
               ? null
               : this.getDirectoryById(directoryCreateReqDTO.getParentDirId());

       if( directoryCreateReqDTO.getParentDirId() != null && parentDir == null )
           throw new InvalidDataAccessException("Invalid parent directory!");

       Directory newDir = DirectoryMapper.directCreateReqDtoToDirectory(
               directoryCreateReqDTO
       );
       newDir.setParentDir(parentDir);

       return this.directoryRepo.save(newDir);
    }

    @Override
    public Directory getDirectoryById(String directoryId) {
        return this.directoryRepo
                .findById(directoryId)
                .orElse(null);
    }

    @Override
    public List<Directory> getDirByAccountIdAndParentDirId(
            String accountId,
            String parentId
    ) {
        return this.directoryRepo
                .findByAccountIdAndParentDirId(accountId,parentId);
    }

    @Override
    public Directory getDirByNameAndAccountIdAndParentDirId(
            String name,
            String accountId,
            String parentDirId
    ) {
        return this.directoryRepo.findByNameAndAccountIdAndParentDirId(
                name ,
                accountId ,
                parentDirId
        ).orElse(null);
    }

    @Override
    public boolean isDuplicateDir(
            String name,
            String accountId,
            String parentDirId
    ) {
        return this.getDirByNameAndAccountIdAndParentDirId(
                name ,
                accountId ,
                parentDirId
        ) != null;
    }

}
