package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends JpaRepository<Permission,Integer> {
}
