package com.vti.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Department")
@Data
@NoArgsConstructor
public class Department {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`", length = 30, nullable = false, unique = true)
    private String name;

    @Column(name = "total_member")
    private int totalMember;
    @Column(name = "`type`", nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("Dev")
    private Type type;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;

    @PrePersist
    public void prePersist() {
        if (type == null) {
            type = Type.Dev;
        }
    }

}
