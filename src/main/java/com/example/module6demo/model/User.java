package com.example.module6demo.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 6)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 6)
    private String password;

    @Column(columnDefinition = "nvarchar(500)")
    private String fullName;
    @Column(columnDefinition = "nvarchar(1000)")
    private String avatar;
    @Column(columnDefinition = "nvarchar(800)")
    private String userAddress;
    @Email
    private String email;
    @Pattern(regexp = "(09|03|07|08|05)+([0-9]{8})\\b")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}
