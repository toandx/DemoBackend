package toandx.render.demoBackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    public Authority(String name) {
        this.name = name;
    }

    public Authority() {
    }
}
