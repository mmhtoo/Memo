package com.mmhtoo.note.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table( name = "accounts" )
@Data
@NoArgsConstructor
public class Account {

    @Id
    private String id;

    @Column( name = "username" , nullable = false , unique = true , length = 20 )
    private String username;

    @Column( name = "email" , unique = true , nullable = false , length = 50 )
    private String email;

    @Column( name = "password" , nullable = false , length =  200 )
    private String password;

    @Column( name = "phone" , length = 20)
    private String phone;

    @Column( name = "is_locked")
    private Boolean isLocked;

    @Column( name = "is_enabled" , nullable = false)
    private Boolean isEnabled;

    @Column( name = "joined_date" , nullable = false)
    private LocalDateTime joinedDate;

    @Column( name = "updated_date" , nullable = false )
    private LocalDateTime updatedDate;

}
