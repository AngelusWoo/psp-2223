import java.io.Serializable;

public class Skill implements Serializable {

    int id;
    String name;
    long requirement;


    public Skill() {
        super();
    }

    public Skill(int id, String name, long requirement) {
        this.id = id;
        this.name = name;
        this.requirement = requirement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRequirement() {
        return requirement;
    }

    public void setRequirement(long requirement) {
        this.requirement = requirement;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", requirement=" + requirement +
                '}';
    }
}
