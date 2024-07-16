create table reporters
(
    birth_date date,
    id         varchar(255) not null
        primary key,
    image      varchar(255),
    name       varchar(255),
    surname    varchar(255)
);

create table tele_sales
(
    birth_date date,
    email      varchar(255),
    id         varchar(255) not null
        primary key,
    image      varchar(255),
    name       varchar(255),
    position   varchar(255)
        constraint tele_sales_position_check
            check (("position")::text = ANY
        ((ARRAY ['INTERN'::character varying, 'JUNIOR'::character varying, 'MIDDLE'::character varying, 'SENIOR'::character varying])::text[])),
    surname    varchar(255)
);



create table tasks
(
    created_at  timestamp(6),
    deadline    timestamp(6),
    updated_at  timestamp(6),
    asignee_id  varchar(255)
        constraint fkgl5p7x60b089ly259yjx22xeh
            references tele_sales,
    description varchar(255),
    id          varchar(255) not null
        primary key,
    reporter_id varchar(255)
        constraint fk5oq57y9faguufuqrechubaeca
            references reporters,
    status      varchar(255)
        constraint tasks_status_check
            check ((status)::text = ANY
        ((ARRAY ['TODO'::character varying, 'IN_PROGRESS'::character varying, 'DONE'::character varying, 'EXPIRED'::character varying])::text[])),
    subject     varchar(255)
);



