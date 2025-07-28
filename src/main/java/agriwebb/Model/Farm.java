package agriwebb.Model;

import jakarta.persistence.*;



@Entity
@Table(name = "Farms")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, nullable = false)
    private String name;

    @Column(unique = false, nullable = false)
    private double latitude;

    @Column(unique = false, nullable = false)
    private double longitude;

    @OneToOne
    @JoinColumn(name = "owner_email", referencedColumnName="email" )
    private AppUser owner;


    public Farm(){
    }


    public Farm(String name, double latitude, double longitude, AppUser owner){
        this.name=name;
        this.latitude=latitude;
        this.longitude=longitude;
        this.owner=owner;
    }


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }


    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }


    public AppUser getOwner() { return owner; }
    public void setOwner(AppUser owner) { this.owner = owner; }

}
