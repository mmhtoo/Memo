package com.mmhtoo.note.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "favourites")
@Data
@NoArgsConstructor
public class Favourite {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne( cascade = { CascadeType.REMOVE })
    @JoinColumn( name = "account_id" , nullable = false )
    private Account account;

    @OneToOne( cascade = { CascadeType.REMOVE})
    @JoinColumn( name = "directory_id" )
    private Directory directory;

    @OneToOne( cascade = { CascadeType.REMOVE})
    @JoinColumn( name = "note_id" )
    private Note note;

    @Column( name = "created_date")
    private LocalDateTime createdDate;

    @Column( name = "updated_date")
    private LocalDateTime updatedDate;

    @Column( name = "expired_date" )
    private LocalDateTime expiredDate;

}
