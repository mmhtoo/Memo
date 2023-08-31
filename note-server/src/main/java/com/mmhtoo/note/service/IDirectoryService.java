package com.mmhtoo.note.service;

import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.entity.Directory;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;

import java.util.List;

public interface IDirectoryService {

    Directory createNewDirectory(DirectoryCreateReqDTO directoryCreateReqDTO) throws DuplicateEntityException;

    Directory getDirectoryById(String directoryId);

    List<Directory> getDirByAccountIdAndParentDirId(String accountId,String parentId);

   Directory getDirByNameAndAccountIdAndParentDirId(String name,String accountId,String parentDirId);

    boolean isDuplicateDir(String name,String accountId,String parentDirId);

}
