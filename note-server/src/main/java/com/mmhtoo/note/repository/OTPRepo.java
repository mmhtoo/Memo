package com.mmhtoo.note.repository;

import com.mmhtoo.note.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepo extends JpaRepository<OTP,Integer> {
}
