-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Hibernate_TB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Hibernate_TB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Hibernate_TB` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `Hibernate_TB` ;

-- -----------------------------------------------------
-- Table `Hibernate_TB`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hibernate_TB`.`User` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NULL DEFAULT NULL,
  `username` VARCHAR(250) NULL DEFAULT NULL,
  `password` VARCHAR(250) NULL DEFAULT NULL,
  `email` VARCHAR(250) NULL DEFAULT NULL,
  `roles` VARCHAR(250) NULL DEFAULT NULL,
  `discriminator` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Hibernate_TB`.`Page`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hibernate_TB`.`Page` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NULL DEFAULT NULL,
  `roles` VARCHAR(250) NULL DEFAULT NULL,
  `username` VARCHAR(250) NULL DEFAULT NULL,
  `discriminator` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `page_ibfk_1` (`username` ASC) VISIBLE,
  CONSTRAINT `page_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `Hibernate_TB`.`User` (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Hibernate_TB`.`Section`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hibernate_TB`.`Section` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NULL DEFAULT NULL,
  `discriminator` VARCHAR(250) NULL DEFAULT NULL,
  `page` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `page` (`page` ASC) VISIBLE,
  CONSTRAINT `section_ibfk_1`
    FOREIGN KEY (`page`)
    REFERENCES `Hibernate_TB`.`Page` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Hibernate_TB`.`Component`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hibernate_TB`.`Component` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(250) NULL DEFAULT NULL,
  `discriminator` VARCHAR(250) NULL DEFAULT NULL,
  `img` BLOB NULL DEFAULT NULL,
  `section` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `section` (`section` ASC) VISIBLE,
  CONSTRAINT `component_ibfk_1`
    FOREIGN KEY (`section`)
    REFERENCES `Hibernate_TB`.`Section` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Hibernate_TB`.`UserSession`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hibernate_TB`.`UserSession` (
  `cookie` VARCHAR(250) NOT NULL,
  `user` BIGINT NULL DEFAULT NULL,
  `discriminator` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`cookie`),
  INDEX `user` (`user` ASC) VISIBLE,
  CONSTRAINT `usersession_ibfk_1`
    FOREIGN KEY (`user`)
    REFERENCES `Hibernate_TB`.`User` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
