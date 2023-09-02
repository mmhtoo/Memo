package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectoryRepo extends JpaRepository<Directory,String> {

    List<Directory> findByAccountId(String accountId);

    List<Directory> findByAccountIdAndParentDirId(String accountId,String parentDirId);

    Optional<Directory> findByNameAndAccountIdAndParentDirId(String name, String accountId, String parentDirId);

}
