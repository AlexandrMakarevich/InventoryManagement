package com.email;

import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("emailJob")
public class EmailJob {

  @Resource(name = "emailService")
  private EmailService emailService;

  @Scheduled(fixedDelay = 300000)
  public void processPendingEmailJob() {
    emailService.processPendingEmail();
  }
}
