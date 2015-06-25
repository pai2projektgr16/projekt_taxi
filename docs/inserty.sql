






INSERT INTO `Taxi` (`numberRegister`, `make`) VALUES
('FDG 6432', 'Ford'),
('DGD 4698', 'Fiat'),
('DFG 3425', 'BMW'),
('JHG 6776', 'Audi'),
('KTY 0986', 'Fiat'),
('FTY 4543', 'Audi');

INSERT INTO `Users` (`mailLogin`, `password`, `firstName`, `lastName`, `type`, `lastLogon`) VALUES
('mail9@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Lukasz', 'sfsfdsfd', 2, NULL),
('mail2@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'adadswa', 'fgdgfd', 2, NULL),
('mail3@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'asdasd', 'dfgdfg', 2, NULL),
('mail4@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'asdasd', 'dfgdfg', 2, NULL),
('mail5@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'asdsadsad', 'dfgfdd', 2, NULL),
('mail6@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'dfgfdgfdg', 'dfgdfg', 2, NULL),
('mail7@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'fdxvfdx', 'dfgdfgdf', 2, NULL),
('mail8@mail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'huiuhguj', 'dfgdfg', 2, NULL);


INSERT INTO `UsersTaxi` (`idUsersTaxi`, `mailLogin`, `numberRegister`) VALUES
('', 'mail@mail.com', 'DGD 4698'),
('', 'mail3@mail.com', 'JHG 6776'),
('', 'mail5@mail.com', 'FTY 4543'),
('', 'mail8@mail.com', 'TKI 1234'),
('', 'mail2@mail.com', 'KTY 0986');



INSERT INTO `Order` (`idOrder`, `operator`, `numberRegister`, `from`, `to`, `dateAdd`, `attention`) VALUES
('', 'dominik137@vp.pl', 'DGD 4698', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
('', 'dominik137@vp.pl', 'DGD 4698', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
('', 'dominik137@vp.pl', 'DGD 4698', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
('', 'dominik137@vp.pl', 'FTY 4543', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
('', 'dominik137@vp.pl', 'TKI 1234', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
('', 'dominik137@vp.pl', 'KTY 0986', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL),
('', 'dominik137@vp.pl', 'TKI 1234', 'Sandomierska', 'Wrzosowa', '2015-06-25 13:16:42', NULL);