package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.Trash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashRepo extends JpaRepository<Trash,Integer> {
}
