package agriwebb.Model;


import jakarta.persistence.*;

@Entity
@Table(name="Tools")
public class Tools {

    @Id
    @Column(unique = true)
    private String id; // assuming this is a UUID or unique string

    @Column(name="toolName")
    private String toolName;

    @Column
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "owner_email", referencedColumnName = "email")
    private AppUser owner;


    public Tools() {}


    public Tools(String id,String toolName, double quantity){

        this.id=id;
        this.toolName=toolName;
        this.quantity=quantity;
    }




    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getToolName() {
        return toolName;
    }
    public void setToolName(String toolName) {
        this.toolName = toolName;
    }


    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    public AppUser getUser() {
        return owner;
    }
    public void setUser(AppUser user) {
        this.owner = user;
    }
}
