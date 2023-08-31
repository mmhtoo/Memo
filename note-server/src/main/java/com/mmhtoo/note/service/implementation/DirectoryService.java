package com.mmhtoo.note.service.implementation;

import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.entity.Directory;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.mapper.DirectoryMapper;
import com.mmhtoo.note.repository.DirectoryRepo;
import com.mmhtoo.note.service.IDirectoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectoryService implements IDirectoryService {

    private final DirectoryRepo directoryRepo;

    @Override
    public Directory createNewDirectory(
           DirectoryCreateReqDTO directoryCreateReqDTO
    ) throws DuplicateEntityException {

       boolean isDuplicate = this.isDuplicateDir(
               directoryCreateReqDTO.getName() ,
               directoryCreateReqDTO.getAccountId(),
               directoryCreateReqDTO.getParentDirId()
       );

       if( isDuplicate )
           throw new DuplicateEntityException("Directory with "+directoryCreateReqDTO.getName()+" name already exists under current directory!");

       Directory newDir = DirectoryMapper.directCreateReqDtoToDirectory(
               directoryCreateReqDTO
       );

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
