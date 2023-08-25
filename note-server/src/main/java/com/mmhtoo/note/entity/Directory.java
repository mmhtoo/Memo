package com.mmhtoo.note.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "directories")
@Data
@NoArgsConstructor
public class Directory {

    @Id
    private String id;

    @Column( name = "name" )
    private String name;

    @Column( name = "description")
    private String description;

    @Column( name = "created_date")
    private LocalDateTime createdDate;

    @Column( name = "updated_date")
    private LocalDateTime updatedDate;

    @Column( name = "number_of_notes")
    private Integer numberOfNotes;

    @Column( name = "is_in_trash")
    private Boolean isInTrash;

    @ManyToOne
    @JoinColumn( name = "account_id" )
    private Account account;

}
