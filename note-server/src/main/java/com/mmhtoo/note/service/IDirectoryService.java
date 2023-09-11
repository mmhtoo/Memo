package com.mmhtoo.note.service;

import com.mmhtoo.note.dto.request.DirectoryCreateReqDTO;
import com.mmhtoo.note.dto.request.DirectoryUpdateReqDTO;
import com.mmhtoo.note.entity.Directory;
import com.mmhtoo.note.exception.custom.DuplicateEntityException;
import com.mmhtoo.note.exception.custom.InvalidDataAccessException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface IDirectoryService {

    Directory createNewDirectory(DirectoryCreateReqDTO directoryCreateReqDTO) throws DuplicateEntityException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException;

    Directory getDirectoryById(String directoryId);

    List<Directory> getDirByAccountIdAndParentDirId(String accountId,String parentId);

   Directory getDirByNameAndAccountIdAndParentDirId(String name,String accountId,String parentDirId);

    boolean isDuplicateDir(String name,String accountId,String parentDirId);

    Directory getDirectoryInfoById(String directoryId) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException;

    Directory updateDirectory(DirectoryUpdateReqDTO directoryUpdateReqDTO,String directoryId) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException, DuplicateEntityException;

    List<Directory> getDirectoriesOfAccount() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidDataAccessException;

}
