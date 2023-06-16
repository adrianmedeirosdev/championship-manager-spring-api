create table stadium (
  id integer not null auto_increment,
  team_id integer null,
  _name varchar(45) not null,
  _address varchar(450) not null,
  primary key (id)
);

alter table stadium add constraint fk_stadium_team
foreign key (team_id) references team (id);