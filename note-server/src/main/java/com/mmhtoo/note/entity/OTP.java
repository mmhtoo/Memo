package com.mmhtoo.note.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OTP {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;

    @Column( name = "otp" , nullable = false , length = 6 )
    private Integer otp;

    @Column( name = "created_date")
    private LocalDateTime createdDate;

    @Column( name = "expired_date")
    private LocalDateTime expiredDate;

    @Column( name = "updated_date")
    private LocalDateTime updatedDate;

    @Column( name = "has_verified")
    private Boolean hasVerified;

    @ManyToOne
    @JoinColumn( name = "account_id" )
    private Account account;

}
