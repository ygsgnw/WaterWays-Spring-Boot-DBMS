use waterways;

drop view if exists ShipAdminView;

create view ShipAdminView as
    select ShipSerialId, SM.ModelName as ModelName, MfDate, ShipStatusCode
    from Ship
            inner join ShipModel SM on Ship.ModelId = SM.ModelId
;


drop view if exists VoyageUserView;

create view VoyageUserView as
    select VoyageId,
           Fare,
           Voyage.ShipSerialId as ShipSerialId,
           SM.ModelName as ModelName,
           Hd.Location as DepartureHarborName,
           Ha.Location as ArrivalHarborName,
           DepartureTime,
           ArrivalTime,
           VoyageStatusCode
    from Voyage
            inner join Ship S on Voyage.ShipSerialId = S.ShipSerialId
            inner join ShipModel SM on S.ModelId = SM.ModelId
            inner join Harbor Ha on Voyage.ArrivalHarborId = Ha.HarborId
            inner join Harbor Hd on Voyage.DepartureHarborId = Hd.HarborId
