# SHIP_STATUS:
#     1 - OPERATIONAL
#     2 - SUSPENDED
#     3 - MAINTENANCE
#
# VOYAGE_STATUS:
#     1 - OPERATIONAL
#     2 - COMPLETED
#     3 - CANCELLED
#
# ROOM_STATUS:
#     1 - AVAILABLE
#     2 - BOOKED
#     3 - RESERVED
#     4 - MAINTENANCE

use waterways;

insert into ShipModel values(1001,200,"model1");
insert into ShipModel values(1002,220,"model2");
insert into ShipModel values(1003,250,"model3");
insert into ShipModel values(1004,300,"model4");
insert into ShipModel values(1005,330,"model5");


# insert into SHIP_STATUS values(1,"OPERATIONAL");
# insert into SHIP_STATUS values(2,"SUSPENDED");
# insert into SHIP_STATUS values(3,"MAINTAINENCE");


insert into Ship values(3001,1001,1,"2000-01-01 00:00:00");
insert into Ship values(3002,1003,2,"2012-11-12 00:00:00");
insert into Ship values(3003,1001,3,"1998-03-23 00:00:00");
insert into Ship values(3004,1004,1,"1990-08-26 00:00:00");
insert into Ship values(3005,1003,3,"2011-10-12 00:00:00");


insert into Users values(4001,"Priyanshu","priyanshu@gmail.com","p1234");
insert into Users values(4002,"Harsh","harsh@gmail.com","p6969");
insert into Users values(4003,"Yogesh","yogesh@gmail.com","p2345");
insert into Users values(4004,"Gaurav","gaurav@gmail.com","p3456");
insert into Users values(4005,"Kushagra","kushagra@gmail.com","p4567");


insert into Employee values(5001,4001,"2010-10-10 00:00:00");
insert into Employee values(5002,4003,"2012-12-12 00:00:00");
insert into Employee values(5003,4002,"2014-03-17 00:00:00");
insert into Employee values(5004,4005,"2020-07-24 00:00:00");
insert into Employee values(5005,4004,"2022-10-15 00:00:00");


insert into Harbor values(6001,"location1","2000-01-01 00:00:00",5001);
insert into Harbor values(6002,"location2","2010-10-28 00:00:00",5001);
insert into Harbor values(6003,"location3","2002-04-20 00:00:00",5002);
insert into Harbor values(6004,"location4","1990-09-15 00:00:00",5003);
insert into Harbor values(6005,"location5","1991-12-10 00:00:00",5003);


# insert into VOYAGE_STATUS values(1,"OPERATIONAL");
# insert into VOYAGE_STATUS values(2,"COMPLETED");
# insert into VOYAGE_STATUS values(3,"CANCELLED");


insert into Voyage values(8001,3001,40000,6001,6001,"2022-01-01 10:10:00","2022-02-01 15:00:00",1);
insert into Voyage values(8002,3003,45000,6002,6002,"2022-04-10 13:00:00","2022-05-10 19:00:00",2);
insert into Voyage values(8003,3002,49000,6005,6004,"2022-10-03 19:00:00","2022-11-03 00:00:00",3);
insert into Voyage values(8004,3005,70000,6001,6005,"2022-11-25 22:00:00","2022-12-25 17:00:00",1);
insert into Voyage values(8005,3004,69000,6003,6002,"2022-09-01 00:00:00","2022-10-01 18:00:00",2);


insert into Crew values(9001,5001,8001,"role1");
insert into Crew values(9002,5002,8003,"role2");
insert into Crew values(9003,5005,8005,"role1");
insert into Crew values(9004,5001,8004,"role3");
insert into Crew values(9005,5002,8001,"role2");


insert into Transaction values(10001,"2022-11-01 00:00:00",20000,4001);
insert into Transaction values(10002,"2022-10-10 00:00:00",25000,4002);
insert into Transaction values(10003,"2022-05-17 00:00:00",30000,4004);
insert into Transaction values(10004,"2022-08-18 00:00:00",26000,4005);
insert into Transaction values(10005,"2022-06-06 00:00:00",50000,4001);


# insert into ROOM_STATUS values(1,"AVAILABLE");
# insert into ROOM_STATUS values(2,"BOOKED");
# insert into ROOM_STATUS values(3,"RESERVED");
# insert into ROOM_STATUS values(4,"MAINTENANCE");


insert into RoomBooking values(10001,11001,8001,1);
insert into RoomBooking values(10002,11004,8002,2);
insert into RoomBooking values(10003,11005,8003,3);
insert into RoomBooking values(10004,11002,8002,1);
insert into RoomBooking values(10005,11003,8005,2);


insert into FoodItem values(12001,8001,500,"food1","desc1");
insert into FoodItem values(12002,8003,450,"food2","desc2");
insert into FoodItem values(12003,8002,300,"food3","desc3");
insert into FoodItem values(12004,8001,600,"food4","desc4");
insert into FoodItem values(12005,8004,1000,"food5","desc5");


insert into FoodBooking values(10001,12001,8001,11001,1000);
insert into FoodBooking values(10003,12002,8003,11005,1200);
insert into FoodBooking values(10004,12003,8002,11002,900);
insert into FoodBooking values(10002,12004,8001,11001,1100);
insert into FoodBooking values(10005,12002,8003,11005,1000);
