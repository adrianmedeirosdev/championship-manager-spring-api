CREATE TABLE team_players (
  team_id INT,
  players_id INT,
  PRIMARY KEY (team_id, players_id),
  FOREIGN KEY (team_id) REFERENCES team (id),
  FOREIGN KEY (players_id) REFERENCES player (id)
);