drop table if exists treatments;
create table treatments(
    id int primary key auto_increment,
    date timestamp not null,
    description varchar(50) not null
)