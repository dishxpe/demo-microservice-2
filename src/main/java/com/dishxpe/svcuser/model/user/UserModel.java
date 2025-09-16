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

    @Column(unique = true)
    private String username;

    @Column(unique = true)

    private String email;

    private String password; // In a real application, ensure this is hashed and secured.
    private Role role; // Using the Role enum

    private Instant createdAt; // Timestamp for when the user was created
    private Instant updatedAt; // Timestamp for when the user was last updated
}
