use waterways;

drop trigger if exists VoyageInsertTrigger;

delimiter $

create trigger VoyageInsertTrigger after insert
    on Voyage for each row
begin
    set @count = (
        select RoomCount from ShipModel where ShipModel.ModelId = @ModelId
    );

    set @i = 0;

    while @i < @count do
        insert into RoomBooking (RoomId, VoyageId, TransactionId, RoomStatus)
        values (@i, @VoyageId, null, 0);
        set @i = @i + 1;
    end while;
end $

delimiter ;
