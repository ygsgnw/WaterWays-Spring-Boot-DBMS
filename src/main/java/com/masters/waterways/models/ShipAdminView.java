package com.masters.waterways.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipAdminView {
    private int ShipSerialId;
    private String ModelName;
    private LocalDateTime MfDate;
    private int ShipStatusCode;

    public int getShipSerialId() {
        return ShipSerialId;
    }

    public void setShipSerialId(int shipSerialId) {
        ShipSerialId = shipSerialId;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    public LocalDateTime getMfDate() {
        return MfDate;
    }

    public void setMfDate(LocalDateTime mfDate) {
        MfDate = mfDate;
    }

    public int getShipStatusCode() {
        return ShipStatusCode;
    }

    public void setShipStatusCode(int shipStatusCode) {
        ShipStatusCode = shipStatusCode;
    }
}
