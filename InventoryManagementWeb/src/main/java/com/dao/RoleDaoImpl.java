package com.dao;

import com.entity.Role;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDao implements RoleDao {

    @Override
    public void saveRole(Role role) {
        getSession().save(role);
    }

    @Override
    public List<Role> getRoleByName(String roleName) {
        String sql = "select * from role where role= :paramName";
        NativeQuery<Role> nativeQuery = getSession().createNativeQuery(sql, Role.class);
        nativeQuery.setParameter("paramName", roleName);
        return nativeQuery.getResultList();
    }
}
