alter table treatments add column vet_id int not null;
alter table treatments
        add foreign key (vet_id) references vets(id);

alter table treatments add column pet_id int not null;
alter table treatments
        add foreign key (pet_id) references pets(id);