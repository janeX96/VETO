drop table if exists pet_events;
create table pet_events(
    id int primary key auto_increment,
    pet_id int,
    occurrence datetime,
    name varchar(30)
)