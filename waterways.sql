create database if not exists waterways;

USE waterways;


drop table if exists ShipModel;
drop table if exists Ship;
drop table if exists Users;
drop table if exists Employee;
drop table if exists Harbour;
drop table if exists Voyage;
drop table if exists Crew;
drop table if exists Transaction;
drop table if exists RoomBooking;
drop table if exists FoodItem;
drop table if exists FoodBooking;


create table ShipModel (
	ModelId 			int 			primary key auto_increment,
    RoomCount 			int,
    ModelName 			varchar(30)
);


create table Ship (
	ShipSerialId 		int 			primary key auto_increment,
    ModelId 			int				foreign key references ShipModel(ModelId),
    ShipStatus 			int,
    MfDate				date
);


create table Users (
	UserId				int 			primary key auto_increment,
    Name	 			varchar(50),
    Sex 				int,
    SignUpDate			date,
    Dob					date,
    Phone				char(10),
    EmailId				varchar(50),
    UserPassword		varchar(10)
);


create table Employee (
	EmployeeId			int 			primary key auto_increment,
    UserId				int				foreign key references Users(UserId),
    ManagerId			int				foreign key references Employee(EmployeeId)
);


create table Harbour (
	HarbourId 			int 			primary key auto_increment,
    Location 			varchar(20),
    ContructionDate 	date,
	ManagerId 			int				foreign key references Employee(EmployeeId)
);


create table Voyage (
	VoyageId 			int 			primary key auto_increment,
    ShipSerialId 		int				foreign key references Ship(ShipSerialId),
    ArrivalHarbourId 	int				foreign key references Harbour(HarbourId),
    DepartureHarbourId 	int				foreign key references Harbour(HarbourId),
    ArrivalTime 		time,
    DepartureTime 		time,
    VoyageStatus		int
);


create table Crew (
	CrewId				int,
	EmployeeId			int				foreign key references Employee(EmployeeId),
	VoyageId			int				foreign key references Voyage(VoyageId),
	Role				varchar(50),
	primary key (CrewId, EmployeeId, VoyageId)	-- Weak Entity Crew
);


create table Transaction (
	TransactionId		int				primary key auto_increment,
	TransactionDate		date,
    Amount				int,
    UserId				int				foreign key references Users(UserId) 
);


create table RoomBooking (
	RoomId 				int,
    VoyageId			int				foreign key references Voyage(VoyageId),
    Fare				int,
    TransactionId		int				foreign key references Transaction(TransactionId),
    RoomStatus			int,
    primary key (VoyageId, RoomId)		-- Weak Entity RoomBooking
);


create table FoodItem (
	FoodItemId			int,
	VoyageId			int				foreign key references Voyage(VoyageId),
	FoodName			varchar(20),
	FoodCost			int,
	FoodDescription		varchar(50),
	primary key (VoyageId, FoodItemId)	-- Weak Entity FoodItem
);


create table FoodBooking (
	FoodItemId			int,
	VoyageId			int,
	FoodItemCount		int,
	TransactionId		int				foreign key references Transaction(TransactionId),
	foreign key (FoodItemId, VoyageId) references FoodItem (FoodItemId, VoyageId),
	primary key (FoodItemId, VoyageId, TransactionId)		-- Weak Entity FoodBooking
);
