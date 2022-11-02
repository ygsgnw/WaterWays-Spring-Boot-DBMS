drop database if exists waterways;
create database waterways;

USE waterways;


drop table if exists ShipModel;
drop table if exists Ship;
drop table if exists Users;
drop table if exists Employee;
drop table if exists Harbor;
drop table if exists Voyage;
drop table if exists Crew;
drop table if exists Transaction;
drop table if exists RoomFare;
drop table if exists RoomBooking;
drop table if exists FoodItem;
drop table if exists FoodBooking;


create table ShipModel (
                           ModelId 			int primary key auto_increment,
                           RoomCount 			int,
                           ModelName 			varchar(30)
);


create table Ship (
                      ShipSerialId 		int 			primary key auto_increment,
                      ModelId 			int,
                      ShipStatus 			int,
                      MfDate				datetime,
                      foreign key (ModelId) references ShipModel(ModelId) on delete cascade
);


create table Users (
                       UserId				int 			primary key auto_increment,
                       UserName	 		varchar(50),
                       EmailId				varchar(50),
                       UserPassword		varchar(10)
);


create table Employee (
                          EmployeeId			int 			primary key auto_increment,
                          UserId				int,
                          ManagerId			int,
                          foreign key (UserId) references Users(UserId) on delete cascade,
                          foreign key (ManagerId) references Employee(EmployeeId) on delete restrict
);


create table Harbor (
                        HarborId 			int 			primary key auto_increment,
                        Location 			varchar(20),
                        ConstructionDate 	datetime,
                        ManagerId 			int,
                        foreign key (ManagerId) references Employee(EmployeeId) on delete restrict
);


create table Voyage (
                        VoyageId 			int 			primary key auto_increment,
                        ShipSerialId 		int,
                        DepartureHarborId 	int,
                        ArrivalHarborId 	int,
                        DepartureTime 		datetime,
                        ArrivalTime 		datetime,
                        VoyageStatus		int,
                        foreign key(ShipSerialId) references Ship(ShipSerialId) on delete cascade,
                        foreign key (DepartureHarborId) references Harbor(HarborId) on delete cascade,
                        foreign key (ArrivalHarborId) references Harbor(HarborId) on delete cascade
);


create table Crew (
                      CrewId				int,
                      EmployeeId			int,
                      VoyageId			int,
                      CrewRole			varchar(50),
                      foreign key (EmployeeId) references Employee(EmployeeId) on delete restrict,
                      foreign key (VoyageId) references Voyage(VoyageId) on delete cascade,
                      primary key (CrewId, EmployeeId, VoyageId)
    -- Weak Entity Crew
);


create table Transaction (
                             TransactionId		int				primary key auto_increment,
                             TransactionDate		datetime,
                             Amount				int,
                             UserId				int,
                             foreign key (UserId) references Users(UserId) on delete restrict
);


create table RoomBooking (
                             RoomId 				int,
                             VoyageId			int,
                             TransactionId		int,
                             RoomStatus			int,
                             primary key (VoyageId, RoomId),
                             foreign key (VoyageId) references Voyage(VoyageId) on delete cascade,
                             foreign key (TransactionId) references Transaction(TransactionId) on delete restrict
    -- Weak Entity RoomBooking
);


create table RoomFare (
                          VoyageId            int,
                          Fare                int,
                          foreign key (VoyageId) references Voyage(VoyageId) on delete cascade
);


create table FoodItem (
                          FoodItemId			int,
                          VoyageId			int,
                          FoodCost            int,
                          FoodName			varchar(20),
                          FoodDescription		varchar(50),
                          foreign key (VoyageId) references Voyage(VoyageId) on delete cascade,
                          primary key (VoyageId, FoodItemId)
    -- Weak Entity FoodItem
);


create table FoodBooking (
                             FoodItemId			int,
                             VoyageId			int,
                             FoodItemCount		int,
                             TransactionId		int,
                             foreign key (TransactionId) references Transaction(TransactionId) on delete restrict,
                             foreign key (VoyageId, FoodItemId) references FoodItem (VoyageId, FoodItemId) on delete cascade,
                             primary key (FoodItemId, VoyageId, TransactionId)
    -- Weak Entity FoodBooking
);

drop trigger if exists OnVoyageDeletion;
drop trigger if exists OnEmployeeDeletion;
drop trigger if exists OnFoodItemDeletion;


delimiter $

create trigger OnVoyageDeletion before delete
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

end;

create trigger OnEmployeeDeletion before delete
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
end;


create trigger OnFoodItemDeletion before delete
    on FoodItem for each row
begin
    if (
    select ArrivalTime
    from Voyage
    where Voyage.VoyageId = @VoyageId
        ) < now() then
        signal sqlstate '45000' set message_text = 'Cannot delete FoodItem that was served on a completed voyage';
end if;

insert into Transaction (TransactionDate, Amount, UserId) (
    select now(), -sum(Transaction.amount), Transaction.UserId
    from Transaction, FoodBooking
    where FoodBooking.TransactionId = Transaction.TransactionId
      and FoodBooking.FoodItemId = @FoodItemId
      and FoodBooking.VoyageId = @VoyageId
    group by UserId
);

end $