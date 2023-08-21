package com.mmhtoo.note.entity;

import com.mmhtoo.note.enumeration.PermissionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "permissions" )
@Data
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "owner_account_id")
    private Account ownerAccount;

    @ManyToOne
    @JoinColumn( name = "other_account_id")
    private Account otherAccount;

    @ManyToOne( cascade = { CascadeType.REMOVE })
    @JoinColumn( name = "note_id" )
    private Note note;

    @ManyToOne( cascade = { CascadeType.REMOVE})
    @JoinColumn( name = "directory_id" )
    private Directory directory;

    @Column( name = "created_date")
    private LocalDateTime createdDate;

    @Column( name = "updated_date")
    private LocalDateTime updatedDate;

    @Column( name = "is_delete")
    private Boolean isDelete;

    @Enumerated
    @Column( name = "permission_type" )
    private PermissionType permissionType;

}
