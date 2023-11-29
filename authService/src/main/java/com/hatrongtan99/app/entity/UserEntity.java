package com.hatrongtan99.app.entity;

import com.hatrongtan99.enumeration.auth.TypeProvider;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50) default 'LOCAL'")
    private TypeProvider typeProvider;

    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities = new HashSet<>();

    public void addRole(Role role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public boolean removeRole(Role role) {
        if (!this.roles.contains(role)) {
            return false;
        }
        this.roles.remove(role);
        return true;
    }

    public void addAuthority(Authority authority) {
        if (this.authorities == null) {
            this.authorities = new HashSet<>();
        }
        this.authorities.add(authority);
    }

    public boolean removeAuthority(Authority authority) {
        if (!this.authorities.contains(authority)) {
            return false;
        }
        this.authorities.remove(authority);
        return true;
    }
}
