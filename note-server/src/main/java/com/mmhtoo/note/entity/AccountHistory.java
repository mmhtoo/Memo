package com.mmhtoo.note.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "account_histories" )
@Data
@NoArgsConstructor
public class AccountHistory {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;

    @Column( name = "history_type")
    private String historyType;

    @Column( name = "description")
    private String description;

    @Column( name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne( cascade = { CascadeType.REMOVE } )
    @JoinColumn( name = "account_id" )
    private Account account;

}
