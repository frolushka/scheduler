drop table if exists users;
create table users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  invite VARCHAR(255) NOT NULL,
  active TINYINT default null,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

drop table if exists user_roles;
create table user_roles (
  role_id int(11) NOT NULL AUTO_INCREMENT,
  role varchar(255) default null,
  username VARCHAR(255) not null,
  PRIMARY KEY (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

drop table if exists user_invites;
create table user_invites (
  invite_id int(11) NOT NULL AUTO_INCREMENT,
  invite varchar(255) not null,
  username VARCHAR(255) default null,
  PRIMARY KEY (invite_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


insert into users(username, password, invite, active) values ('admin', '$2a$10$jPCOs6WRaruWufiIPg4zIOKokiezAq5pDlDOVXVnvbHsL28bolG5S', 'admin', 1);

insert into user_roles(role, username) values ('ADMIN', 'admin');

insert into user_invites(invite, username) values ('admin', 'admin');

drop table if exists calendars;
create table calendars (
  calendar_id int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) DEFAULT NULL,
  owner VARCHAR(255) NOT NULL,
  PRIMARY KEY (calendar_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

drop table if exists events;
create table events (
  id int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) DEFAULT NULL,
  calendar_id int(11) NOT NULL,
  start_date int(11) DEFAULT NULL,
  end_date int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT events_ibfk_l FOREIGN KEY (calendar_id) REFERENCES calendars(calendar_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
