alter table _match add constraint fk_match_hometeam 
    foreign key (home_team_id) references team (id);

alter table _match add constraint fk_match_awayteam
    foreign key (away_team_id) references team (id);