alter table team add constraint fk_team_satadium
  foreign key (home_stadium_id) references stadium (id);