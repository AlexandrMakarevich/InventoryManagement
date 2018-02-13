package com.dao;

import com.entity.Email;
import java.util.List;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

@Repository("emailDaoImpl")
public class EmailDaoImpl extends BaseDao implements EmailDao {

  @Override
  public void saveOrUpdateEmail(Email email) {
    getSession().saveOrUpdate(email);
  }

  @Override
  public Email getEmailById(Integer id) {
    return getSession().get(Email.class, id);
  }

  @Override
  public List<Email> getAllPendingEmail() {
    String sql = "select * from email where status = 'PENDING' order by id asc";
    NativeQuery<Email> nativeQuery = getSession().createNativeQuery(sql, Email.class);
    return nativeQuery.list();
  }
}
