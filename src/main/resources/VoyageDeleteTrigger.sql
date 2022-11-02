use waterways;
delimiter $

create trigger VoyageDeleteTrigger before delete
    on Voyage for each row
begin
    if @ArrivalDate < now() then
        signal sqlstate '45000' set message_text = 'Cannot delete completed Voyages';
    end if;

    insert into Transaction (TransactionDate, Amount, UserId) (
        select now(), -sum(Transaction.Amount), Transaction.UserId
        from Transaction, RoomBooking
        where RoomBooking.TransactionId = Transaction.TransactionId
          and RoomBooking.VoyageId = @VoyageId
        group by Transaction.UserId
    );

    insert into Transaction (TransactionDate, Amount, UserId) (
        select now(), -sum(Transaction.Amount), Transaction.UserId
        from Transaction, FoodBooking
        where Transaction.TransactionId = FoodBooking.TransactionId
          and FoodBooking.VoyageId = @VoyageId
        group by Transaction.UserId
    );

end $
