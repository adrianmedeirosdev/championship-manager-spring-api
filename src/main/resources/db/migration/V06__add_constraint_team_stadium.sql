alter table team add constraint fk_team_satadium
  foreign key (stadium_id) references stadium (id);