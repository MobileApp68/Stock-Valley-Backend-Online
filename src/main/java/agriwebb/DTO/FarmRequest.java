package agriwebb.DTO;

import agriwebb.Model.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class FarmRequest {

    private String name;

    private double latitude;

    private double longitude;

    private String ownertoken;

    public FarmRequest(String name, double latitude, double longitude, String ownertoken){
        this.name=name;
        this.latitude=latitude;
        this.longitude=longitude;
        this.ownertoken=ownertoken;
    }




    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }


    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }


    public String getOwnertoken() { return ownertoken; }
    public void setOwnertoken(String ownertoken) {
        this.ownertoken = ownertoken;
    }

}
