alter table _match add constraint fk_match_championship
  foreign key (championship_id) references championship (id);