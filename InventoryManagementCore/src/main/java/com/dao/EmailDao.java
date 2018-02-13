package com.dao;

import com.entity.Email;
import java.util.List;

public interface EmailDao {

  void saveOrUpdateEmail(Email email);

  Email getEmailById(Integer id);

  List<Email> getAllPendingEmail();

}
