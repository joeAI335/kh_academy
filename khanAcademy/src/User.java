import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhengning1 on 4/9/16.
 */
public class User {
    private List<User> students = new ArrayList<>();
    private List<User> coach = new ArrayList<>();
    private int id;
    private static int numberOfUser;
    /**Current version of the user*/
    private int version;

    public User() {
        this.id = this.numberOfUser++;
    }

    public void addStudents(User student) {
        this.students.add(student);
    }

    public void addCoach(User coach) {
        this.coach.add(coach);
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<User> getStudents() {
        return this.students;
    }

    public List<User> getCoach() {
        return this.coach;
    }

    public int getVersion() {
        return this.version;
    }

    public int getId() {
        return this.id;
    }

    public static int getNumberOfUser() {
        return numberOfUser;
    }
}
