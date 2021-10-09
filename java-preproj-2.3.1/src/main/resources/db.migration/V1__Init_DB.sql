drop table if exists roles;
drop table if exists user_roles;
drop table if exists users;
create table roles
(
    id   bigint not null auto_increment,
    role varchar(255),
    primary key (id)
) engine = MyISAM;
create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
) engine = MyISAM;
create table users
(
    id         bigint not null auto_increment,
    last_name  varchar(255),
    login      varchar(255),
    first_name varchar(255),
    password   varchar(255),
    primary key (id)
) engine = MyISAM;
alter table user_roles
    add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references roles (id);
alter table user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users (id)