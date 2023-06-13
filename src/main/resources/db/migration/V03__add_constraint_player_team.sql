alter table player add constraint fk_player_team
  foreign key(team_id) references team (id);