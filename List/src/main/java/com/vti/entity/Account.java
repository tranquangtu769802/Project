package com.vti.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "`Account`")
@Data
@NoArgsConstructor
public class Account {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String userName;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Formula("concat(last_name, ' ', first_name)")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "`password`", length = 800)
    private String password;

    @Column(name = "`role`", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = Role.EMPLOYEE;
        }
        password = new BCryptPasswordEncoder().encode(password);
    }

    @Column(name = "token")
    private String token;

    @Column(name = "token_created")
    private LocalDateTime tokenCreated;

//    @PreUpdate
//    public void preUpdate() {
//        if (role == null) {
//            role = Role.EMPLOYEE;
//        }
//        password = new BCryptPasswordEncoder().encode(password);
//    }
// 123456 - doimk: 11111 => bytecode: a => c


}
