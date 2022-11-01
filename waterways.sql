create database if not exists waterways;

USE waterways;


drop table if exists ShipModel;
drop table if exists Ship;
drop table if exists Users;
drop table if exists Employee;
drop table if exists Harbor;
drop table if exists Voyage;
drop table if exists Crew;
drop table if exists Transaction;
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
	foreign key (ModelId) references ShipModel(ModelId),
    ShipStatus 			int,
    MfDate				date
);


create table Users (
	UserId				int 			primary key auto_increment,
    Name	 			varchar(50),
    EmailId				varchar(50),
    UserPassword		varchar(10)
);


create table Employee (
	EmployeeId			int 			primary key auto_increment,
    UserId				int, 
	foreign key (UserId) references Users(UserId),
    ManagerId			int,
	foreign key (ManagerId) references Employee(EmployeeId)
);


create table Harbor (
	HarborId 			int 			primary key auto_increment,
    Location 			varchar(20),
    ContructionDate 	date,
	ManagerId 			int	,
	foreign key (ManagerId) references Employee(EmployeeId)
);


create table Voyage (
	VoyageId 			int 			primary key auto_increment,
    ShipSerialId 		int		,
	foreign key(ShipSerialId) references Ship(ShipSerialId),
    ArrivalHarborId 	int,
	foreign key (ArrivalHarborId)references Harbor(HarborId),
    DepartureHarborId 	int,
	foreign key (DepartureHarborId)references Harbor(HarborId),
    ArrivalTime 		time,
    DepartureTime 		time,
    VoyageStatus		int
);


create table Crew (
	CrewId				int,
	EmployeeId			int	,
	foreign key(EmployeeId) references Employee(EmployeeId),
	VoyageId			int,
	foreign key (VoyageId)references Voyage(VoyageId),
	Role				varchar(50),
	primary key (CrewId, EmployeeId, VoyageId)	-- Weak Entity Crew
);


create table Transaction (
	TransactionId		int				primary key auto_increment,
	TransactionDate		date,
    Amount				int,
    UserId				int	,
	foreign key (UserId)references Users(UserId) 
);


create table RoomBooking (
	RoomId 				int,
    VoyageId			int,
	foreign key(VoyageId) references Voyage(VoyageId),
    Fare				int,
    TransactionId		int	,
	foreign key(TransactionId) references Transaction(TransactionId),
    RoomStatus			int,
    primary key (VoyageId, RoomId)		-- Weak Entity RoomBooking
);


create table FoodItem (
	FoodItemId			int,
	VoyageId			int	,
	foreign key(VoyageId) references Voyage(VoyageId),
	FoodName			varchar(20),
	FoodDescription		varchar(50),
	primary key (VoyageId, FoodItemId)	-- Weak Entity FoodItem
);


create table FoodBooking (
	FoodItemId			int,
	VoyageId			int,
	FoodItemCount		int,
	TransactionId		int,
	foreign key (TransactionId)references Transaction(TransactionId),
	foreign key ( VoyageId,FoodItemId) references FoodItem ( VoyageId,FoodItemId),
	primary key (FoodItemId, VoyageId, TransactionId)		-- Weak Entity FoodBooking
);
