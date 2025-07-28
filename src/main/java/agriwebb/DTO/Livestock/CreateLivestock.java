package agriwebb.DTO.Livestock;

import agriwebb.Model.AppUser;

public class CreateLivestock {

    private String dbId;
    private String animalType;
    private int quantity;
    private String breed;
    private String tagId;
    private int age;
    private String healthStatus;
    private String notes;
    private String ownerToken; // JWT passed from frontend to identify user


    public CreateLivestock(String dbId, String animalType, int quantity, String breed, String tagId, int age, String healthStatus, String notes, String ownerToken) {
        this.dbId = dbId;
        this.animalType = animalType;
        this.quantity = quantity;
        this.breed = breed;
        this.tagId = tagId;
        this.age = age;
        this.healthStatus = healthStatus;
        this.notes = notes;
        this.ownerToken = ownerToken;
    }


    // Getters and Setters

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


    public String getOwnerToken() {
        return ownerToken;
    }
    public void setOwnerToken(AppUser owner) {
        this.ownerToken = ownerToken;
    }


}
