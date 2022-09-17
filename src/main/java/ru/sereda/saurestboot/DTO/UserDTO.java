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

    public UserDTO(String username, List<Role> roles, Long id, String description) {
        this.username = username;
        this.roles = roles;
        this.id = id;
        this.description = description;
    }

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

    public static User getUserFromDTO (UserDTO userDTO){
        User user = new User();
        user.setRoles(userDTO.getRoles());
        user.setUsername(userDTO.getUsername());
        user.setId(userDTO.getId());
        user.setDescription(userDTO.getDescription());
        return user;
    }
}
