package vn.loh.services.impl;

import vn.loh.dao.ICategoryDao;
import vn.loh.dao.IRoleDao;
import vn.loh.dao.implement.CategoryDaoImpl;
import vn.loh.dao.implement.RoleDaoImpl;
import vn.loh.entity.Role;

public class RoleServiceImpl implements IRoleDao {
    private IRoleDao roleDao = new RoleDaoImpl();

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }
}
