SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `phonebook` DEFAULT CHARACTER SET utf8 ;
USE `phonebook` ;

-- -----------------------------------------------------
-- Table `phonebook`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`company` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`company` (
  `company_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `active` TINYINT(1) NOT NULL ,
  `code` VARCHAR(10) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(345) NULL DEFAULT NULL ,
  `address1` VARCHAR(45) NULL DEFAULT NULL ,
  `address2` VARCHAR(45) NULL DEFAULT NULL ,
  `city` VARCHAR(45) NULL DEFAULT NULL ,
  `state` VARCHAR(45) NULL DEFAULT NULL ,
  `zip` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`company_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `code` ON `phonebook`.`company` (`code` ASC) ;


-- -----------------------------------------------------
-- Table `phonebook`.`company_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`company_location` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`company_location` (
  `company_location_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `active` TINYINT(1) NOT NULL DEFAULT '1' ,
  `company_id` INT(11) NOT NULL ,
  `address1` VARCHAR(45) NULL DEFAULT NULL ,
  `address2` VARCHAR(45) NULL DEFAULT NULL ,
  `city` VARCHAR(45) NULL DEFAULT NULL ,
  `state` VARCHAR(45) NULL DEFAULT NULL ,
  `zip` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`company_location_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `phonebook`.`position`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`position` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`position` (
  `position_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `active` TINYINT(1) NOT NULL DEFAULT '1' ,
  `code` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`position_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `code_unq` ON `phonebook`.`position` (`code` ASC) ;


-- -----------------------------------------------------
-- Table `phonebook`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`users` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `active` TINYINT(1) NOT NULL DEFAULT '1' ,
  `position_id` INT(11) NOT NULL DEFAULT '2' ,
  `username` VARCHAR(10) NOT NULL ,
  `password` VARCHAR(15) NOT NULL ,
  `firstname` VARCHAR(45) NULL DEFAULT NULL ,
  `lastname` VARCHAR(45) NULL DEFAULT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `security_question_1` VARCHAR(145) NOT NULL ,
  `security_answer_1` VARCHAR(45) NOT NULL ,
  `security_question_2` VARCHAR(145) NOT NULL ,
  `security_answer_2` VARCHAR(45) NOT NULL ,
  `birthdate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`user_id`) ,
  CONSTRAINT `fk_users_1`
    FOREIGN KEY (`position_id` )
    REFERENCES `phonebook`.`position` (`position_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `username_uq` ON `phonebook`.`users` (`username` ASC) ;

CREATE INDEX `fk_users_1` ON `phonebook`.`users` (`position_id` ASC) ;


-- -----------------------------------------------------
-- Table `phonebook`.`contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`contacts` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`contacts` (
  `user_id` INT(11) NOT NULL ,
  `contact_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `prefix` VARCHAR(45) NULL DEFAULT NULL ,
  `first_name` VARCHAR(45) NOT NULL ,
  `middle_name` VARCHAR(45) NULL DEFAULT NULL ,
  `last_name` VARCHAR(45) NOT NULL ,
  `suffix` VARCHAR(45) NULL DEFAULT NULL ,
  `address1` VARCHAR(45) NULL DEFAULT NULL ,
  `address2` VARCHAR(45) NULL DEFAULT NULL ,
  `city` VARCHAR(45) NULL DEFAULT NULL ,
  `state` VARCHAR(2) NULL DEFAULT NULL ,
  `zip` VARCHAR(45) NULL DEFAULT NULL ,
  `company_id` INT(11) NULL DEFAULT NULL ,
  `entered_by` INT(11) NULL DEFAULT NULL ,
  `entered_date` DATETIME NULL DEFAULT NULL ,
  `edited_by` INT(11) NULL DEFAULT NULL ,
  `edited_date` DATETIME NULL DEFAULT NULL ,
  `birthdate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`contact_id`) ,
  CONSTRAINT `fk_contacts_1`
    FOREIGN KEY (`user_id` )
    REFERENCES `phonebook`.`users` (`user_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_contacts_1` ON `phonebook`.`contacts` (`user_id` ASC) ;


-- -----------------------------------------------------
-- Table `phonebook`.`email_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`email_type` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`email_type` (
  `email_type_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `email_type_description` VARCHAR(45) NOT NULL ,
  `email_type_active` TINYINT(1) NOT NULL DEFAULT '1' ,
  PRIMARY KEY (`email_type_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `email_type_description_UNIQUE` ON `phonebook`.`email_type` (`email_type_description` ASC) ;


-- -----------------------------------------------------
-- Table `phonebook`.`contacts_email`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`contacts_email` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`contacts_email` (
  `contacts_email_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `contact_id` INT(11) NOT NULL ,
  `email_type_id` INT(11) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `entered_date` DATETIME NOT NULL ,
  PRIMARY KEY (`contacts_email_id`) ,
  CONSTRAINT `fk_contacts_email_1`
    FOREIGN KEY (`contact_id` )
    REFERENCES `phonebook`.`contacts` (`contact_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contacts_email_2`
    FOREIGN KEY (`email_type_id` )
    REFERENCES `phonebook`.`email_type` (`email_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_contacts_email_1` ON `phonebook`.`contacts_email` (`contact_id` ASC) ;

CREATE INDEX `fk_contacts_email_2` ON `phonebook`.`contacts_email` (`email_type_id` ASC) ;


-- -----------------------------------------------------
-- Table `phonebook`.`link_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`link_type` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`link_type` (
  `link_type_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `link_type_description` VARCHAR(45) NOT NULL ,
  `link_type_active` TINYINT(1) NOT NULL DEFAULT '1' ,
  PRIMARY KEY (`link_type_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `phonebook`.`contacts_link`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`contacts_link` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`contacts_link` (
  `contacts_link_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `contact_id` INT(11) NOT NULL ,
  `link_type_id` INT(11) NOT NULL ,
  `link_description` VARCHAR(80) NOT NULL ,
  `link_url` VARCHAR(245) NOT NULL ,
  `entered_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`contacts_link_id`) ,
  CONSTRAINT `fk_contacts_link_1`
    FOREIGN KEY (`contact_id` )
    REFERENCES `phonebook`.`contacts` (`contact_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contacts_link_2`
    FOREIGN KEY (`link_type_id` )
    REFERENCES `phonebook`.`link_type` (`link_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `index2` ON `phonebook`.`contacts_link` (`link_url` ASC) ;

CREATE INDEX `fk_contacts_link_1` ON `phonebook`.`contacts_link` (`contact_id` ASC) ;

CREATE INDEX `fk_contacts_link_2` ON `phonebook`.`contacts_link` (`link_type_id` ASC) ;


-- -----------------------------------------------------
-- Table `phonebook`.`phone_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`phone_type` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`phone_type` (
  `phone_type_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `phone_type_description` VARCHAR(45) NOT NULL ,
  `phone_type_active` TINYINT(1) NOT NULL DEFAULT '1' ,
  PRIMARY KEY (`phone_type_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `phone_type_description_UNIQUE` ON `phonebook`.`phone_type` (`phone_type_description` ASC) ;


-- -----------------------------------------------------
-- Table `phonebook`.`contacts_phone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`contacts_phone` ;

CREATE  TABLE IF NOT EXISTS `phonebook`.`contacts_phone` (
  `contacts_phone_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `contact_id` INT(11) NOT NULL ,
  `phone_type_id` INT(11) NOT NULL ,
  `phone` VARCHAR(45) NOT NULL ,
  `entered_date` DATETIME NOT NULL ,
  PRIMARY KEY (`contacts_phone_id`) ,
  CONSTRAINT `fk_contacts_phone_1`
    FOREIGN KEY (`contact_id` )
    REFERENCES `phonebook`.`contacts` (`contact_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contacts_phone_2`
    FOREIGN KEY (`phone_type_id` )
    REFERENCES `phonebook`.`phone_type` (`phone_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `phone_unq` ON `phonebook`.`contacts_phone` (`phone` ASC) ;

CREATE INDEX `fk_contacts_phone_1` ON `phonebook`.`contacts_phone` (`contact_id` ASC) ;

CREATE INDEX `fk_contacts_phone_2` ON `phonebook`.`contacts_phone` (`phone_type_id` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
