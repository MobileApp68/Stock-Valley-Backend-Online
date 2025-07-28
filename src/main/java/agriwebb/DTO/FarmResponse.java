package agriwebb.DTO;

public class FarmResponse {

    private String name;
    private double latitude;
    private double longitude;


    public FarmResponse(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
