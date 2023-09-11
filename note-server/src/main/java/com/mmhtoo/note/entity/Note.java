package com.mmhtoo.note.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "notes")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;

    @Column( name = "name" )
    private String name;

    @Column( name = "title")
    private String title;

    @Column( name = "content")
    private String content;

    @ManyToOne( cascade = { CascadeType.REMOVE })
    @JoinColumn( name = "directory_id" )
    private Directory directory;

    @ManyToOne( cascade = { CascadeType.REMOVE })
    @JoinColumn( name = "account_id" )
    private Account account;

    @Column( name = "created_date")
    private LocalDateTime createdDate;

    @Column( name =  "updated_date")
    private LocalDateTime updatedDate;

    @Column( name = "is_in_trash" )
    private Boolean isInTrash;

}
