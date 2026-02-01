package toandx.render.demoBackend.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

// Step 2. Create entity implements UserDetails to use with SpringSecurity
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @ManyToMany(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER) // FetchType.EAGER to fix JwtAuthenticationFilter.doFilterInternal bug
    @JoinTable(name = "user_author", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Authority> authorities;

    public User() {

    }

    public User(String username, String password, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        System.out.println("USER "+username);
        authorities.forEach(auth -> System.out.println(auth.getName()));
        System.out.println("END");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        authorities.forEach(auth -> {
            list.add(new SimpleGrantedAuthority(auth.getName()));
        });

        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password,BCrypt.gensalt(10));;
    }
    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
