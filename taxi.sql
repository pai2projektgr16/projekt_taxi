DROP TABLE IF EXISTS `Report`;
DROP TABLE IF EXISTS `Order`;
DROP TABLE IF EXISTS UsersTaxi;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Taxi;

CREATE TABLE Users (
    mailLogin VARCHAR(100) PRIMARY KEY,
    password	VARCHAR(256)  NOT NULL, --sha256
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

INSERT INTO Users (mailLogin, password, firstName, lastName, `type`) 
VALUES ('dominik137@vp.pl', sha2('asdwsx', 256), 'Dominik', 'Zawadzki', 1);