package com.hatrongtan99.app.entity;

import com.hatrongtan99.enumeration.auth.TypeProvider;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50) default 'LOCAL'")
    private TypeProvider typeProvider = TypeProvider.LOCAL;

    @Column(columnDefinition="boolean default 1")
    private boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<AuthorityEntity> authorities = new HashSet<>();

    public void addRole(RoleEntity role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public boolean removeRole(RoleEntity role) {
        if (!this.roles.contains(role)) {
            return false;
        }
        this.roles.remove(role);
        return true;
    }

    public void addAuthority(AuthorityEntity authority) {
        if (this.authorities == null) {
            this.authorities = new HashSet<>();
        }
        this.authorities.add(authority);
    }

    public boolean removeAuthority(AuthorityEntity authority) {
        if (!this.authorities.contains(authority)) {
            return false;
        }
        this.authorities.remove(authority);
        return true;
    }
}
