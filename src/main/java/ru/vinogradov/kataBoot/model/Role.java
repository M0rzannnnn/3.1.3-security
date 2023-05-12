package ru.vinogradov.kataBoot.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role () {}

    public Role(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
