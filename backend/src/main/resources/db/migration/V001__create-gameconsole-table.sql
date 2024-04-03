create table tb_gameconsole (
	id bigint not null auto_increment,
    console_name varchar(60) not null,
    release_year int (30) not null,
    units_sold bigint not null,
    
    primary key (id)
);