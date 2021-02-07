DROP TABLE IF EXISTS gift_certificate;
CREATE TABLE  gift_certificate (
  `certificateId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `duration` INT NOT NULL,
 `createDate` DATETIME NOT NULL,
  `lastUpdateDate` DATETIME NOT NULL);
INSERT into gift_certificate values (1, 'qqq','qqq', 12, 12, '2021-01-18T13:39:01.322', '2021-01-18T13:39:01.322');
INSERT into gift_certificate values (2, 'qqq','qqq', 12, 12, '2021-01-18T13:39:01.322', '2021-01-18T13:39:01.322');
