package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepo extends JpaRepository<Directory,String> {
}
