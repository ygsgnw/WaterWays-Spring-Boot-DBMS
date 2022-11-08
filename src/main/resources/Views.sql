use waterways;

drop view if exists ShipUserView;

create view ShipAdminView as
    select ShipSerialId, SM.ModelName as ModelName, MfDate, ShipStatusCode
    from Ship
            inner join ShipModel SM on Ship.ModelId = SM.ModelId
;



# drop view if exists VoyageUserView;
#
# create view VoyageUserView as
#     select VoyageId, Fare,
#     from Voyage
#             inner join Ship S on Voyage.ShipSerialId = S.ShipSerialId
#             inner join ShipModel SM on S.ModelId = SM.ModelId
#             inner join Harbor Ha on Voyage.ArrivalHarborId = Ha.HarborId
#             inner join Harbor Hd on Voyage.DepartureHarborId = Hd.HarborId
