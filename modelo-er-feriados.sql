-- -----------------------------------------------------
-- Table `mydb`.`feriado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`feriado` (
  `id_feriado` INT NOT NULL,
  `nome` VARCHAR(80) NOT NULL,
  `descricao` VARCHAR(100) NULL,
  PRIMARY KEY (`id_feriado`));
-- -----------------------------------------------------
-- Table `mydb`.`feriado_dinamico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`feriado_dinamico` (
  `id_feriado_dinamico` INT NOT NULL,
  `id_externo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_feriado_dinamico`),
  CONSTRAINT `fk_feriado_dinamico`
    FOREIGN KEY (`id_feriado_dinamico`)
    REFERENCES `mydb`.`feriado` (`id_feriado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `mydb`.`feriado_recorrente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`feriado_recorrente` (
  `id_feriado_recorrente` INT NOT NULL,
  `dia` INT NOT NULL,
  `mes` INT NOT NULL,
  `ano_inicio` INT NOT NULL,
  `ano_fim` INT NOT NULL,
  PRIMARY KEY (`id_feriado_recorrente`),
  CONSTRAINT `fk_feriado_recorrente`
    FOREIGN KEY (`id_feriado_recorrente`)
    REFERENCES `mydb`.`feriado` (`id_feriado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`feriado_personalizado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`feriado_personalizado` (
  `id_feriado_personalizado` INT NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY (`id_feriado_personalizado`),
  CONSTRAINT `fk_feriado_personalizado`
    FOREIGN KEY (`id_feriado_personalizado`)
    REFERENCES `mydb`.`feriado` (`id_feriado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
