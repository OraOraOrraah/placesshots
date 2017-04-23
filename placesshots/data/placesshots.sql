CREATE TABLE `config` (
  `week` int(11) unsigned NOT NULL,
  `round` int(11) unsigned NOT NULL,
  `live` int(11) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `create_by` int(11) unsigned DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` int(11) unsigned DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `userconnection` (
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  `accessToken` varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(255) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`providerId`,`providerUserId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_connect` (
  `user_id` int(11) unsigned NOT NULL,
  `providerId` varchar(255) DEFAULT NULL,
  `providerUserId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_user_connect_user` (`user_id`),
  KEY `FK_user_connect_user_connection` (`providerId`,`providerUserId`,`userId`),
  CONSTRAINT `FK_user_connect_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_user_connect_user_connection` FOREIGN KEY (`providerId`, `providerUserId`, `userId`) REFERENCES `userconnection` (`providerId`, `providerUserId`, `userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `league` (
  `id` smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `team` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `league_id` smallint(6) unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `short_title` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `fk_team_league` (`league_id`),
  CONSTRAINT `fk_team_league` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fixture` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `league_id` smallint(6) unsigned NOT NULL,
  `home_id` int(11) unsigned NOT NULL,
  `away_id` int(11) unsigned NOT NULL,
  `week` smallint(6) unsigned NOT NULL,
  `round` char(2) NOT NULL,
  `fixture_date` datetime NOT NULL,
  `home_score` smallint(6) unsigned DEFAULT NULL,
  `away_score` smallint(6) unsigned DEFAULT NULL,
  `home_extra_time_score` smallint(6) unsigned DEFAULT NULL,
  `away_extra_time_score` smallint(6) unsigned DEFAULT NULL,
  `home_penalty_score` smallint(6) unsigned DEFAULT NULL,
  `away_penalty_score` smallint(6) unsigned DEFAULT NULL,
  `create_by` int(11) unsigned DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` int(11) unsigned DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `league_id` (`league_id`),
  KEY `home_id` (`home_id`),
  KEY `away_id` (`away_id`),
  CONSTRAINT `fk_fixture_away_team` FOREIGN KEY (`away_id`) REFERENCES `team` (`id`),
  CONSTRAINT `fk_fixture_home_team` FOREIGN KEY (`home_id`) REFERENCES `team` (`id`),
  CONSTRAINT `fk_fixture_league` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `predict` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fixture_id` bigint(20) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `home_score` smallint(6) unsigned NOT NULL,
  `away_score` smallint(6) unsigned NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fixture_id` (`fixture_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `fk_predict_fixture` FOREIGN KEY (`fixture_id`) REFERENCES `fixture` (`id`)
  CONSTRAINT `fk_predict_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `predict_champion` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL,
  `team_id` int(11) unsigned DEFAULT NULL,
  `round` char(2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `team_id` (`team_id`),
  CONSTRAINT `fk_predict_champion_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_predict_champion_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
