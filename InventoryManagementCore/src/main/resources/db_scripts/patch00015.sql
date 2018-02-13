CREATE TABLE `email` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `invoice_id` INT NOT NULL,
  `status` ENUM('PENDING', 'SENT', 'ERROR') NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `index_email_1` (`invoice_id` ASC),
  CONSTRAINT `fk_email_1`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `invoice` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)