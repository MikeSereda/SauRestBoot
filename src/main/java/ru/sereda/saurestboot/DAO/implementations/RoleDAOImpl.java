package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.DAO.interfaces.RoleDAO;
import ru.sereda.saurestboot.businesslogic.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @Override
    public Role getRole(String name) {
        return null;
    }
}
