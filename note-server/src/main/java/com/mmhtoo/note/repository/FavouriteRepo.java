package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepo extends JpaRepository<Favourite,Integer> {
}
