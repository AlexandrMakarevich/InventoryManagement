package com.dao;

import com.entity.Role;
import com.entity.User;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDao implements UserDao {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource(name = "roleDaoImpl")
    private RoleDao roleDao;

    @Override
    public List<User> getUserByName(String userName) {
        String query = "select * from user where name = :param_name";
        NativeQuery<User> nativeQuery = getSession().createNativeQuery(query, User.class);
        nativeQuery.setParameter("param_name", userName);
        return nativeQuery.getResultList();
    }

    @Override
    public void saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        Role existRole = roleDao.getRoleByName("USER").get(0);
        user.setRoles(new HashSet<>(Arrays.asList(existRole)));
        getSession().save(user);
    }
}
