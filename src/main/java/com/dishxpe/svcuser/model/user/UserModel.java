package com.dishxpe.svcuser.model.user;

import com.dishxpe.svcuser.model.role.Role;
import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // In a real application, ensure this is hashed and secured.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Using the Role enum

    @Column(nullable = false, updatable = false)
    private Instant createdAt; // Timestamp for when the user was created
    private Instant updatedAt; // Timestamp for when the user was last updated
}
