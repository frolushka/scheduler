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


insert into users(username, password, invite, active) values ('admin', '$2a$10$eK8lxfRMZmXhe3gLQgUrBOcAS8.91jffDASZN3EVMiBqRl6ucDr1G', 'admin', 1);

insert into user_roles(role, username) values ('ROLE_ADMIN', 'admin');
insert into user_roles(role, username) values ('ROLE_USER', 'admin');

insert into user_invites(invite, username) values ('admin', 'admin');

ALTER USER sa set PASSWORD '1234'