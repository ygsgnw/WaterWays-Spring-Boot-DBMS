use waterways;

drop trigger if exists VoyageInsertTrigger;

delimiter |

create trigger VoyageInsertTrigger after insert
    on Voyage for each row
BEGIN

    if NEW.DepartureTime > NEW.ArrivalTime THEN
        signal sqlstate '45000' set message_text = 'Voyage cannot arrive before it departs';
    end if;

    set @count = (
        select ShipModel.RoomCount from ShipModel, (select ModelId from Ship where Ship.ShipSerialId = NEW.ShipSerialId) as A
        where A.ModelId = ShipModel.ModelId
    );

    set @i = 1;

    label: loop
        insert into RoomBooking (RoomId, VoyageId, TransactionId, RoomStatusCode) values (@i, NEW.VoyageId, null, 1);
        set @i = @i + 1;
        if @i > @count then
            leave label;
        end if ;
        iterate label;
    end loop ;

END;

|

delimiter ;
