package ru.sereda.saurestboot.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.sereda.saurestboot.businesslogic.Role;
import ru.sereda.saurestboot.businesslogic.User;

import java.util.List;

@JsonPropertyOrder({"id","username","description","roles"})
public class UserDTO {
    private final String username;
    private final List<Role> roles;
    private final Long id;
    private final String description;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.roles = user.getRoles();
        this.id = user.getId();
        this.description = user.getDescription();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
