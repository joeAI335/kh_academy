import java.util.*;

/**
 * Created by lizhengning1 on 4/9/16.
 */
public class Relation {
    private int numOfUser;
    private static ArrayList<User> source = new ArrayList<>();
    private HashMap<Integer, User> users = new HashMap<>();

    /*The default maximum students of a coach*/
    private final int studentsPerCoach = 5;
    /*The maximum value of people in a group*/
    private final int maxPeoplePerGroup = 15;

    /**
     * The distinct group number
     */
    private final int groupNum = 30;




    /*Initialize the relation and set the version differently of each group*/
    public Relation() {
        for (int i = 0; i < groupNum; i++) {
            User newuser = new User();
            newuser.setVersion(i);
            source.add(newuser);
            users.put(newuser.getId(), newuser);
            haveStudents(newuser, 1);
        }
    }


    /*Each initial user will be a coach and recursively do this approach to build a graph*/

    /**
     * I suppose that every user could be a coach.
     */
    public void haveStudents(User coach, int studentsNum) {
        /**
         * Initialize the full student number of a coach
         * */
        for (int i = 0; i < studentsPerCoach; i++) {
            User student = new User();
            student.setVersion(coach.getVersion());
            if (++studentsNum >= maxPeoplePerGroup) {
                return;
            }
            coach.addStudents(student);
            student.addCoach(coach);
            users.put(student.getId(), student);
        }
        /**
         * Expand the edge of a single coach.
        * */
        for (User student : coach.getStudents()) {
            haveStudents(student, studentsNum);
        }
    }

    /**
     * The function to get the relation graph of each user.
     * I do it recursively so there is a helper recursive function.
    * */
    public static ArrayList<User> getRelation(User user) {
        HashMap<Integer, User> visited = new HashMap<>();
        if (user != null) {
            Relation_helper(user, visited);
        }
        ArrayList<User> result = new ArrayList<>();
        for (User u : visited.values()) {
            result.add(u);
        }
        return result;
    }

    /**
     * No matter a user is a student or a coach, it will always infects the version of the user connected with it.
     * */
    private static void Relation_helper(User user, HashMap<Integer, User> visited) {
        if (!visited.containsKey(user.getId())) {
            for (User student : user.getStudents()) {
                visited.put(student.getId(), student);
                Relation_helper(student, visited);
            }
            for (User coach : user.getCoach()) {
                visited.put(coach.getId(), coach);
                Relation_helper(coach, visited);
            }
        }
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public static ArrayList<User> getSource() {
        return source;
    }
}
