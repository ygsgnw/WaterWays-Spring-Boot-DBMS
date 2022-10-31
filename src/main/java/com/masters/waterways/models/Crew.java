package com.masters.waterways.models;

import java.sql.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crew {

    private int CrewId;
    private int EmployeeId;
    private int VoyageId;
    private String Role;

    public int getCrewId() {
        return CrewId;
    }

    public void setCrewId(int crewId) {
        CrewId = crewId;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public int getVoyageId() {
        return VoyageId;
    }

    public void setVoyageId(int voyageId) {
        VoyageId = voyageId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
