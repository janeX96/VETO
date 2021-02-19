drop table if exists vets;
create table vets(
    id int primary key auto_increment,
    first_name varchar(30) not null,
    last_name varchar(50) not null,
    email varchar(40) not null
)