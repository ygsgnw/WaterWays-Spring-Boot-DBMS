use waterways;
drop trigger if exists CrewInsertTrigger;

delimiter $

create trigger CrewInsertTrigger before insert
    on Crew for each row
begin
    if exists (
        SELECT dtime, atime FROM (
            SELECT DepartureTime as dtime, ArrivalTime as atime FROM Crew, Voyage
            WHERE Crew.EmployeeId = @EmployeeId AND Crew.VoyageId = Voyage.VoyageId AND Voyage.ArrivalTime > NOW()
        ) as da
        WHERE
            NOT(@ArrivalTime < dtime OR @DepartureTime > atime)
    ) OR exists (
        
        )

        THEN
        signal sqlstate '45000' set message_text = 'Employee serves on another voyage at the same time';
    END IF;
end;

$

delimiter ;