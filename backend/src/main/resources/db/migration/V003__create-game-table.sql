create table tb_game (
	id bigint not null auto_increment,
    game_name varchar(60) not null,
    game_genre varchar(30) not null,
    release_year int (30) not null,
    units_sold bigint not null,
    game_console_id bigint not null,
    
    primary key (id),
    foreign key (game_console_id) references tb_gameconsole(id)
);
