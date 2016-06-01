CREATE TABLE `user` (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `icon` varchar(3) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `league` (
  `id` smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `team` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `short_title` varchar(3) NOT NULL,
  `title` varchar(255) NOT NULL,
  `league_id` smallint(6) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `fk_team_league` (`league_id`),
  CONSTRAINT `fk_team_league` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fixture` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `league_id` smallint(6) unsigned NOT NULL,
  `week` smallint(6) unsigned NOT NULL,
  `home_id` int(11) unsigned NOT NULL,
  `away_id` int(11) unsigned NOT NULL,
  `home_score` smallint(6) DEFAULT NULL,
  `away_score` smallint(6) DEFAULT NULL,
  `fixture_date` datetime NOT NULL,
  `create_by` varchar(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `league_id` (`league_id`),
  KEY `home_id` (`home_id`),
  KEY `away_id` (`away_id`),
  CONSTRAINT `fk_away_team` FOREIGN KEY (`away_id`) REFERENCES `team` (`id`),
  CONSTRAINT `fk_home_team` FOREIGN KEY (`home_id`) REFERENCES `team` (`id`),
  CONSTRAINT `fk_match_league` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `predict` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `week` smallint(6) unsigned NOT NULL,
  `fixture_id` bigint(20) unsigned NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `home_score` smallint(6) unsigned NOT NULL,
  `away_score` smallint(6) unsigned NOT NULL,
  `red_card_flag` tinyint(1) DEFAULT NULL,
  `over_time_flag` tinyint(1) DEFAULT NULL,
  `penalty_flag` tinyint(1) DEFAULT NULL,
  `point` smallint(6) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `match_id` (`fixture_id`),
  CONSTRAINT `fk_predict_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `fk_predict_fixture` FOREIGN KEY (`fixture_id`) REFERENCES `fixture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;