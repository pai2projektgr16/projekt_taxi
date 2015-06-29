DROP TABLE IF EXISTS Report;
DROP TABLE IF EXISTS `Order`;
DROP TABLE IF EXISTS UsersTaxi;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Taxi;

CREATE TABLE Users (
    mailLogin VARCHAR(100) PRIMARY KEY,
    password	TEXT  NOT NULL, --sha256
    firstName text NOT NULL,
    lastName text NOT NULL,
    `type` TINYINT NOT NULL,
    lastLogon DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Taxi (
    numberRegister VARCHAR(20) PRIMARY KEY,
    make TEXT NOT NULL -- ang. marka
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE UsersTaxi (
    idUsersTaxi INT PRIMARY KEY AUTO_INCREMENT,
    mailLogin VARCHAR(100) NOT NULL,
    numberRegister VARCHAR(20) NOT NULL,
    KEY fk_mailLogin(mailLogin),
    KEY fk_numberRegister(numberRegister),
CONSTRAINT FOREIGN KEY (`mailLogin`) REFERENCES Users(mailLogin) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT FOREIGN KEY (`numberRegister`) REFERENCES Taxi(numberRegister) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Order` (
    idOrder INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    operator VARCHAR(100) NOT NULL,
    numberRegister VARCHAR(20) NOT NULL,
    `from` TEXT NOT NULL,
    `to` TEXT NOT NULL,
    dateAdd DATETIME,
    attention TEXT,
    KEY fk_operator(operator),
    KEY fk_numberRegister2(numberRegister),
CONSTRAINT FOREIGN KEY (`operator`) REFERENCES Users(mailLogin) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`numberRegister`) REFERENCES Taxi(numberRegister) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Report (
    idReport INT PRIMARY KEY AUTO_INCREMENT,
    idOrder INT UNSIGNED,
    price FLOAT NOT NULL,
    distance FLOAT NOT NULL,
    attention TEXT,
CONSTRAINT FOREIGN KEY (idOrder) REFERENCES `Order`(idOrder) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TRIGGER IF EXISTS hashPassword;

CREATE TRIGGER hashPassword BEFORE UPDATE ON Users FOR EACH ROW
SET NEW.password = sha2(NEW.password, 256);

INSERT INTO `Taxi` (`numberRegister`, `make`) VALUES
('FDG 6432', 'Ford'),
('DGD 4698', 'Fiat'),
('DFG 3425', 'BMW'),
('JHG 6776', 'Aui'),
('KTY 0986', 'Fiat'),
('FTY 4543', 'Audi');

INSERT INTO Users (mailLogin, password, firstName, lastName, `type`) 
VALUES ('dominik137@vp.pl', sha2('asdwsx', 256), 'Dominik', 'Zawadzki', 1);
INSERT INTO `Users` (`mailLogin`, `password`, `firstName`, `lastName`, `type`, `lastLogon`) VALUES
('mail9@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Lukasz', 'sfsfdsfd', 2, NULL),
('mail2@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'adadswa', 'fgdgfd', 2, NULL),
('mail3@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'asdasd', 'dfgdfg', 2, NULL),
('mail4@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'asdasd', 'dfgdfg', 2, NULL),
('mail5@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'asdsadsad', 'dfgfdd', 2, NULL),
('mail6@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'dfgfdgfdg', 'dfgdfg', 2, NULL),
('mail7@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'fdxvfdx', 'dfgdfgdf', 2, NULL),
('mail8@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'huiuhguj', 'dfgdfg', 2, NULL);


INSERT INTO `UsersTaxi` (`mailLogin`, `numberRegister`) VALUES
( 'dominik137@vp.pl', 'DGD 4698'),
( 'mail3@mail.com', 'JHG 6776'),
( 'mail5@mail.com', 'FTY 4543'),
( 'mail2@mail.com', 'KTY 0986');



INSERT INTO `Order` ( `operator`, `numberRegister`, `from`, `to`, `dateAdd`, `attention`) VALUES
( 'dominik137@vp.pl', 'DGD 4698', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
( 'dominik137@vp.pl', 'DGD 4698', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
( 'dominik137@vp.pl', 'DGD 4698', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
( 'dominik137@vp.pl', 'FTY 4543', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
( 'dominik137@vp.pl', 'KTY 0986', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL);