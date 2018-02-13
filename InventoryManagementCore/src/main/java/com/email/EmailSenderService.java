package com.email;

public interface EmailSenderService {

  void sendMessage(String to, String subject, String text);
}
