package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note,Integer> {
}
