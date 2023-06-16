CREATE TABLE championship_teams (
  championship_id INT,
  teams_id INT,
  PRIMARY KEY (championship_id, teams_id),
  FOREIGN KEY (championship_id) REFERENCES championship (id),
  FOREIGN KEY (teams_id) REFERENCES team (id)
);