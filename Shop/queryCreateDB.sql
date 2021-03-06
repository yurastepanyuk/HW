-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema dbo_autoparts
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dbo_autoparts
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbo_autoparts` DEFAULT CHARACTER SET utf8  COLLATE utf8_general_ci ;
USE `dbo_autoparts` ;

-- -----------------------------------------------------
-- Table `dbo_autoparts`.`autoparts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_autoparts`.`autoparts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `catalognumber` VARCHAR(30) NOT NULL,
  `categoriya` TINYINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `catalognumber_idx` (`catalognumber` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8  COLLATE utf8_general_ci ;


-- -----------------------------------------------------
-- Table `dbo_autoparts`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_autoparts`.`clients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `inn` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `name_idx` (`name` ASC),
  UNIQUE INDEX `inn_UNIQUE` (`inn` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8  COLLATE utf8_general_ci ;




-- -----------------------------------------------------
-- Table `dbo_autoparts`.`docs_shopping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_autoparts`.`docs_shopping` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `date` DATETIME(0) NOT NULL COMMENT '',
  `doc_num` VARCHAR(12) NOT NULL COMMENT '',
  `clients_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `doc_num_UNIQUE` (`doc_num` ASC)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  INDEX `fk_docs_shopping_clients1_idx` (`clients_id` ASC)  COMMENT '',
  CONSTRAINT `fk_docs_shopping_clients1`
    FOREIGN KEY (`clients_id`)
    REFERENCES `dbo_autoparts`.`clients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbo_autoparts`.`docs_sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_autoparts`.`docs_sales` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `date` DATETIME(0) NOT NULL COMMENT '',
  `doc_num` VARCHAR(12) NOT NULL COMMENT '',
  `clients_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `doc_num_UNIQUE` (`doc_num` ASC)  COMMENT '',
  INDEX `fk_docs_sales_clients1_idx` USING BTREE (`clients_id` ASC)  COMMENT '',
  CONSTRAINT `fk_docs_sales_clients1`
    FOREIGN KEY (`clients_id`)
    REFERENCES `dbo_autoparts`.`clients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbo_autoparts`.`prices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_autoparts`.`prices` (
  `price_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `price` DECIMAL(12,2) NULL COMMENT '',
  `kategoriya_cena` TINYINT UNSIGNED NOT NULL COMMENT '',
  `autoparts_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`price_id`)  COMMENT '',
  UNIQUE INDEX `price_id_UNIQUE` (`price_id` ASC)  COMMENT '',
  INDEX `fk_prices_autoparts_idx` (`autoparts_id` ASC)  COMMENT '',
  CONSTRAINT `fk_prices_autoparts`
    FOREIGN KEY (`autoparts_id`)
    REFERENCES `dbo_autoparts`.`autoparts` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbo_autoparts`.`balance_auto_parts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_autoparts`.`balance_auto_parts` (
  `qty` INT NULL COMMENT '',
  `balance_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `autoparts_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`balance_id`, `autoparts_id`)  COMMENT '',
  INDEX `fk_balance_auto_parts_autoparts__idx` (`autoparts_id` ASC)  COMMENT '',
  CONSTRAINT `fk_balance_auto_parts_autoparts1`
    FOREIGN KEY (`autoparts_id`)
    REFERENCES `dbo_autoparts`.`autoparts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbo_autoparts`.`sales_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_autoparts`.`sales_line` (
  `idsales_line` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `qty` SMALLINT NOT NULL COMMENT '',
  `price` DECIMAL(15,2) NULL COMMENT '',
  `docs_sales_id` INT NOT NULL COMMENT '',
  `autoparts_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idsales_line`)  COMMENT '',
  INDEX `fk_sales_line_docs_sales1_idx` (`docs_sales_id` ASC)  COMMENT '',
  INDEX `fk_sales_line_autoparts1_idx` (`autoparts_id` ASC)  COMMENT '',
  CONSTRAINT `fk_sales_line_docs_sales1`
    FOREIGN KEY (`docs_sales_id`)
    REFERENCES `dbo_autoparts`.`docs_sales` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_line_autoparts1`
    FOREIGN KEY (`autoparts_id`)
    REFERENCES `dbo_autoparts`.`autoparts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbo_autoparts`.`shopping_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_autoparts`.`shopping_line` (
  `idshopping_line` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `qty` SMALLINT NOT NULL COMMENT '',
  `price` DECIMAL(15,2) NULL COMMENT '',
  `docs_shopping_id` INT NOT NULL COMMENT '',
  `autoparts_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idshopping_line`)  COMMENT '',
  INDEX `fk_shopping_line_docs_shopping1_idx` (`docs_shopping_id` ASC)  COMMENT '',
  INDEX `fk_shopping_line_autoparts1_idx` (`autoparts_id` ASC)  COMMENT '',
  CONSTRAINT `fk_shopping_line_docs_shopping1`
    FOREIGN KEY (`docs_shopping_id`)
    REFERENCES `dbo_autoparts`.`docs_shopping` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shopping_line_autoparts1`
    FOREIGN KEY (`autoparts_id`)
    REFERENCES `dbo_autoparts`.`autoparts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbo_autoparts`.`clients_has_autoparts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_autoparts`.`clients_has_autoparts` (
  `clients_id` INT NOT NULL COMMENT '',
  `autoparts_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`clients_id`, `autoparts_id`)  COMMENT '',
  INDEX `fk_clients_has_autoparts_autoparts1_idx` (`autoparts_id` ASC)  COMMENT '',
  INDEX `fk_clients_has_autoparts_clients1_idx` (`clients_id` ASC)  COMMENT '',
  CONSTRAINT `fk_clients_has_autoparts_clients1`
    FOREIGN KEY (`clients_id`)
    REFERENCES `dbo_autoparts`.`clients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clients_has_autoparts_autoparts1`
    FOREIGN KEY (`autoparts_id`)
    REFERENCES `dbo_autoparts`.`autoparts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
