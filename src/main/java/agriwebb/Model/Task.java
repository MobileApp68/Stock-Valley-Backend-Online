package agriwebb.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @Column(unique = true)
    private String id;

    @Column
    private String title;

    @Column
    private boolean selected;

    @Column(columnDefinition = "TEXT")
    private String additionalNotes;

    @ManyToOne
    @JoinColumn(name = "owner_email", referencedColumnName = "email")
    private AppUser owner;

    public Task() {}

    public Task(String id, String title, boolean selected, String additionalNotes, AppUser owner) {
        this.id=id;
        this.title = title;
        this.selected = selected;
        this.additionalNotes = additionalNotes;
        this.owner = owner;
    }

    // Getters and setters...


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }



    public String getAdditionalNotes() {
        return additionalNotes;
    }
    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }


    public AppUser getOwner() {
        return owner;
    }
    public void setOwner(AppUser owner) {
        this.owner = owner;
    }



}
