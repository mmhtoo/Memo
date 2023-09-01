package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note,Integer> {

    List<Note> findByNameAndDirectoryId(String name,String directoryId);

    List<Note> findByDirectoryId(String dirId);

}
