create table tb_adm (
    adm_id bigint not null auto_increment,
    email varchar(100) not null unique,
    password varchar(255) not null,
    primary key (adm_id)
);
