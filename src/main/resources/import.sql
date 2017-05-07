--Insert Role
insert into role (name) values ('ROLE_USER');

--insert two users
--password in bcrypt = $2y$10$RwboXCMf6TZIxkp2IUR4hezyWgUtfPktyPxJJEI1Hgu50I7Mgg0ZS
insert into user(username, enabled, password,role_id) values ('user', true, '$2a$06$4k/KmiUJT6Gsz0OjFEP/UeVTZ2damOGUv0./aLSuBIO7YJzyfUaym',1);
insert into user(username, enabled, password,role_id) values ('user2', true, '$2a$06$4k/KmiUJT6Gsz0OjFEP/UeVTZ2damOGUv0./aLSuBIO7YJzyfUaym',1);

-- Insert tasks
--insert into task (description, diy_id, complete) values ('do this 1-1',1,false);
--insert into task (description, diy_id, complete) values ('do this 2-1',2,false);
--insert into task (description, diy_id, complete) values ('do this 1-2',1,false);


--insert DIY
--insert into DIY (title, task_id) values ('how-to-add-aux-cable',1);
--insert into DIY (title,task_id) values ('how-to-replace-retractable-seat-belt',1);

--insert categories
insert into Category(name) values ('car');
insert into Category(name) values ('computers');
insert into Category(name) values ('furnitures');