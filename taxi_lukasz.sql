DROP TABLE IF EXISTS Reports;
DROP TABLE IF EXISTS Orders;
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

CREATE TABLE Orders (
    idOrder INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    operator VARCHAR(100) NOT NULL,
    numberRegister VARCHAR(20) NOT NULL,
    from_ TEXT NOT NULL,
    to_ TEXT NOT NULL,
    dateAdd DATETIME,
    attention TEXT,
    KEY fk_operator(operator),
    KEY fk_numberRegister2(numberRegister),
CONSTRAINT FOREIGN KEY (`operator`) REFERENCES Users(mailLogin) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`numberRegister`) REFERENCES Taxi(numberRegister) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Reports (
    idReport INT PRIMARY KEY AUTO_INCREMENT,
    idOrder INT UNSIGNED,
    price FLOAT NOT NULL,
    distance FLOAT NOT NULL,
    attention TEXT,
CONSTRAINT FOREIGN KEY (idOrder) REFERENCES Orders (idOrder) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Users (mailLogin, password, firstName, lastName, `type`) 
VALUES ('dominik137@vp.pl', sha2('asdwsx', 256), 'Dominik', 'Zawadzki', 1);

DROP TRIGGER IF EXISTS hashPassword;

CREATE TRIGGER hashPassword BEFORE UPDATE ON Users FOR EACH ROW
SET NEW.password = sha2(NEW.password, 256);

INSERT INTO `Taxi` (`numberRegister`, `make`) VALUES
('TKI1234', 'Audi');

INSERT INTO `Orders` (`idOrder`, `operator`, `numberRegister`, `from_`, `to_`, `dateAdd`, `attention`) VALUES
(1, 'dominik137@vp.pl', 'TKI1234', 'Sandomierska', 'Wrzosowa', '2015-06-27 10:21:10', NULL);
