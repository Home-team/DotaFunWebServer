SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `prem_send_msg`;
CREATE TABLE `prem_send_msg` (
  `id` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sender` (`sender`),
  KEY `receiver` (`receiver`),
  CONSTRAINT `prem_send_msg_ibfk_1` FOREIGN KEY (`sender`) REFERENCES `user` (`id`),
  CONSTRAINT `prem_send_msg_ibfk_2` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `login` varchar(255) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
