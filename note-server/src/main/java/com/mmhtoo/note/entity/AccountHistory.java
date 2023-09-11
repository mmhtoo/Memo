package com.mmhtoo.note.entity;

import com.mmhtoo.note.enumeration.HistoryType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "account_histories" )
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountHistory {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;

    @Column( name = "history_type")
    @Enumerated
    private HistoryType historyType;

    @Column( name = "description")
    private String description;

    @Column( name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne( cascade = { CascadeType.REMOVE } )
    @JoinColumn( name = "account_id" )
    private Account account;

}
