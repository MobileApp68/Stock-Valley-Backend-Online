package agriwebb.Model;

import agriwebb.Model.AppUser;
import jakarta.persistence.*;


@Entity
@Table(name = "Livestock")
public class Livestock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String dbId; // session ID to group livestock entries

    @Column(unique = false, nullable = true)
    private String animalType;

    @Column(unique = false, nullable = true)
    private int quantity;

    @Column(unique = false, nullable = true)
    private String breed;

    @Column(unique = false, nullable = true)
    private String tagId;

    @Column(unique = false, nullable = true)
    private int age;

    @Column(unique = false, nullable = true)
    private String healthStatus;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "owner_email", referencedColumnName = "email")
    private AppUser owner;

    // Constructors
    public Livestock() {}

    public Livestock(String dbId, String animalType, int quantity, String breed, String tagId, int age, String healthStatus, String notes, AppUser owner) {
        this.dbId = dbId;
        this.animalType = animalType;
        this.quantity = quantity;
        this.breed = breed;
        this.tagId = tagId;
        this.age = age;
        this.healthStatus = healthStatus;
        this.notes = notes;
        this.owner = owner;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public AppUser getOwner() {
        return owner;
    }
    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

}
