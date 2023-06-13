create table player (
  id integer not null auto_increment,
  team_id integer not null,
  _name varchar(45) not null,
  birth Date not null,
  gender varchar(15) not null,
  height float not null,
  primary key (id)
);