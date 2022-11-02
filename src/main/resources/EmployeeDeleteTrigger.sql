use waterways;

delimiter $

create trigger EmployeeDeleteTrigger before delete
    on Employee for each row
begin
    if exists(
            select *
            from Crew, Voyage
            where Crew.EmployeeId = @EmployeeId
              and Crew.VoyageId = Voyage.VoyageId
              and Voyage.ArrivalTime < now()
        ) then
        signal sqlstate '45000' set message_text = 'Cannot delete Employee who has served on a previous voyage';
    end if;
end $