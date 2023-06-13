create table _match (
  id integer not null auto_increment,
  _date Date,
  hometeam_id integer not null,
  awayteam_id integer not null,
  championship_id integer not null,
  primary key(id)
);