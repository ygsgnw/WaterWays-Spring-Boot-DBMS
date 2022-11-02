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
                        Fare				int,
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
