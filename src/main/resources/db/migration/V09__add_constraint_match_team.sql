alter table _match add constraint fk_match_hometeam 
    foreign key (hometeam_id) references team (id);

alter table _match add constraint fk_match_awayteam
    foreign key (awayteam_id) references team (id);