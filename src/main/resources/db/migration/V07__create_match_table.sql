create table _match (
  id integer not null auto_increment,
  home_team_id integer null,
  away_team_id integer null,
  _date Date,
  _status varchar(45) null,
  championship_id integer null,
  primary key(id)
);