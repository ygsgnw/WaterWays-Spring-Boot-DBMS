drop database if exists waterways;

create database waterways;

USE waterways;

create table ShipModel (
	ModelId int primary key auto_increment,
	RoomCount int not null,
	ModelName varchar(30)
);


create table Ship (
	ShipSerialId int primary key auto_increment,
	ModelId int not null,
	ShipStatusCode int not null,
	MfDate datetime not null,
	foreign key (ModelId) references ShipModel(ModelId) on delete cascade
);


create table Users (
	UserId int primary key auto_increment,
	UserName varchar(50) not null,
	EmailId	varchar(50),
	UserPassword varchar(10) not null
);


create table Employee (
	EmployeeId int primary key auto_increment,
	UserId int not null,
	JoinDate datetime not null,
	EmployeeStatusCode int not null,
	foreign key (UserId) references Users(UserId) on delete cascade
);


create table Harbor (
	HarborId int primary key auto_increment,
	Location varchar(20) not null,
	ConstructionDate datetime not null,
	ManagerId int not null,
	HarborStatusCode int not null,
	foreign key (ManagerId) references Employee(EmployeeId) on delete restrict
);


create table Voyage (
	VoyageId int primary key auto_increment,
	ShipSerialId int not null,
	Fare int not null,
	DepartureHarborId int not null,
	ArrivalHarborId int not null,
	DepartureTime datetime not null,
	ArrivalTime datetime not null,
	VoyageStatusCode int not null,
	foreign key (ShipSerialId) references Ship(ShipSerialId) on delete cascade,
	foreign key (DepartureHarborId) references Harbor(HarborId) on delete cascade,
	foreign key (ArrivalHarborId) references Harbor(HarborId) on delete cascade
);


create table Crew (
    EmployeeId int not null,
    VoyageId int not null,
    CrewRole varchar(50),
    foreign key (EmployeeId) references Employee(EmployeeId) on delete restrict,
    foreign key (VoyageId) references Voyage(VoyageId) on delete cascade,
    primary key (EmployeeId, VoyageId)
    -- Weak Entity Crew
);


create table Transaction (
    TransactionId int primary key auto_increment,
    TransactionDate datetime not null,
    Amount int not null,
    UserId int not null,
    foreign key (UserId) references Users(UserId) on delete restrict
);


create table RoomBooking (
    TransactionId int,
    RoomId int,
    VoyageId int,
    RoomStatusCode int not null,
    primary key (VoyageId, RoomId),
    foreign key (VoyageId) references Voyage(VoyageId) on delete cascade,
    foreign key (TransactionId) references Transaction(TransactionId) on delete restrict
    -- Weak Entity RoomBooking
);


create table FoodItem (
    FoodItemId int,
    VoyageId int,
    FoodCost int not null,
    FoodName varchar(20) not null,
    FoodDescription varchar(50),
    foreign key (VoyageId) references Voyage(VoyageId) on delete cascade,
    primary key (VoyageId, FoodItemId)
    -- Weak Entity FoodItem
);


create table FoodBooking (
    TransactionId int primary key,
    FoodItemId int,
    VoyageId int,
    RoomId int,
    FoodItemCount int not null,
    foreign key (TransactionId) references Transaction(TransactionId) on delete restrict,
    foreign key (VoyageId, FoodItemId) references FoodItem (VoyageId, FoodItemId) on delete cascade,
    foreign key (VoyageId, RoomId) references RoomBooking (VoyageId, RoomId) on delete cascade
    -- Weak Entity FoodBooking
);

source src/main/resources/VoyageInsertTrigger.sql

