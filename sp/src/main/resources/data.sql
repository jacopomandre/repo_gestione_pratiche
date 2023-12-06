insert into stato_pratica (id ,descrizione, step) values(10001,'CREATA', 1);
insert into stato_pratica (id ,descrizione, step) values(10002,'IN_ELABORAZIONE', 2);
insert into stato_pratica (id ,descrizione, step) values(10003,'COMPLETATA', 3);

insert into risultato_pratica (id ,descrizione) values(10001,'APPROVATA');
insert into risultato_pratica (id ,descrizione) values(10002,'RIFIUTATA');

insert into pratica (codice_Pratica) values('PRIMA');
insert into pratica (codice_Pratica) values('SECONDA');

insert into versione_pratica (stato, pratica, descrizione, numero_Versione) values(10001, 1, 'descrizione-1', 1);
insert into versione_pratica (stato, pratica, descrizione, numero_Versione) values(10002, 1, 'descrizione-1',2);
insert into versione_pratica (stato, risultato, pratica, descrizione, numero_Versione) values(10003, 10001, 1,'descrizione-1', 3);

insert into utente (username, password) values ('1234567890', '$2a$10$sX/8o3k4Y91s2jZ94252c.e0387e597924875a324953322925292579523252');