package vn.loh.dao.implement;

import jakarta.persistence.EntityManager;
import vn.loh.configs.JPAConfig;
import vn.loh.dao.IRoleDao;
import vn.loh.entity.Role;
import vn.loh.entity.User;

public class RoleDaoImpl implements IRoleDao {
    @Override
    public Role findById(int id) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        Role role = entityManager.find(Role.class, id);
        return role;
    }
}
