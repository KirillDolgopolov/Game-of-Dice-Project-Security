package cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.developer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = ("username"))})
    public class Developer implements UserDetails {
        @Id
        @GeneratedValue
        private Long id;
        @Column(nullable = false)
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        @Column(nullable = false)
        private String password;
        @Enumerated(EnumType.STRING)
        private Role role;


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(role.name()));
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }


