drop table if exists pets;
create table pets(
    id int primary key auto_increment,
    name varchar(30) not null,
    type varchar(30) not null,
    owner_first_name varchar(30) not null,
    owner_last_name varchar(50) not null,
    owner_phone_number varchar(9) not null
)