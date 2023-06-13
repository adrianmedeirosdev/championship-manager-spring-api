alter table result add constraint fk_result_match
  foreign key (match_id) references _match (id);