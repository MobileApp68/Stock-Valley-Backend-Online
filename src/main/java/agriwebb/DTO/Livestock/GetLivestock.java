package agriwebb.DTO.Livestock;

public class GetLivestock {


    private String dbId;
    private String animalType;
    private int quantity;
    private String breed;
    private String tagId;
    private int age;
    private String healthStatus;
    private String notes;

    public GetLivestock(String dbId, String animalType, int quantity, String breed, String tagId, int age, String healthStatus, String notes) {
        this.dbId = dbId;
        this.animalType = animalType;
        this.quantity = quantity;
        this.breed = breed;
        this.tagId = tagId;
        this.age = age;
        this.healthStatus = healthStatus;
        this.notes = notes;
    }


    public String getDbId() {
        return dbId;}

    public String getAnimalType() {
        return animalType;}


    public int getQuantity() {
        return quantity;}

    public String getBreed() {
        return breed;}


    public String getTagId() {
        return tagId;}


    public int getAge() {
        return age;}


    public String getHealthStatus() {
        return healthStatus;}


    public String getNotes() {
        return notes;
    }



}


