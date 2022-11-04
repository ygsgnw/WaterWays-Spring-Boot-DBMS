use waterways;
drop trigger if exists CrewInsertTrigger;

delimiter $

create trigger CrewInsertTrigger before insert