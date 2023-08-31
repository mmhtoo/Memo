package com.mmhtoo.note.service.implementation;

import com.auth0.jwt.interfaces.Claim;
import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.dto.request.DirectoryUpdateReqDTO;
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
import java.time.LocalDateTime;
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

    @Override
    public Directory getDirectoryInfoById(String directoryId)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException {
        Directory savedDirectory = this.getDirectoryById(directoryId);
        Map<String,Claim> payload = this.tokenService.getPayloadFromRequest(request);

        if( savedDirectory == null || payload == null || !savedDirectory.getAccount().getId().equals(payload.get("userId").asString()))
            throw new InvalidDataAccessException("Invalid account or directory for access!");

        return savedDirectory;
    }

    @Override
    public Directory updateDirectory(DirectoryUpdateReqDTO directoryUpdateReqDTO , String directoryId)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException, DuplicateEntityException {
        Directory savedDir = this.getDirectoryById(directoryId);
        Map<String,Claim> payload = this.tokenService.getPayloadFromRequest(request);

        if( savedDir == null || payload == null || !payload.get("userId").asString().equals(savedDir.getAccount().getId()) )
            throw new InvalidDataAccessException("Invalid directory or account for request!");

        Directory sameNameDir = this.getDirByNameAndAccountIdAndParentDirId(
                directoryUpdateReqDTO.getName() ,
                payload.get("userId").asString() ,
                directoryUpdateReqDTO.getParentDirId()
        );

        if( sameNameDir != null && !sameNameDir.getId().equals(directoryId))
            throw new DuplicateEntityException("Directory with "+directoryUpdateReqDTO.getName()+" name already exists under current directory!");

        Directory parentDir = directoryUpdateReqDTO.getParentDirId() == null
                ? savedDir.getParentDir()
                : this.getDirectoryById(directoryUpdateReqDTO.getParentDirId());

        if( parentDir == null && directoryUpdateReqDTO.getParentDirId() != null )
            throw new InvalidDataAccessException("Invalid parent directory!");

        savedDir.setName(directoryUpdateReqDTO.getName());
        savedDir.setDescription(directoryUpdateReqDTO.getDescription());
        savedDir.setUpdatedDate(LocalDateTime.now());
        savedDir.setParentDir(parentDir);

        return this.directoryRepo.save(savedDir);
    }

}
