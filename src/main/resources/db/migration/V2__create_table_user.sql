create table hr_user.user
(
    id         bigserial    not null,
    name       varchar(100) not null,
    email      varchar(100) not null unique,
    password   varchar(100) not null,
    created_at timestamp    not null,
    active     bool         not null,

    primary key (id)
);