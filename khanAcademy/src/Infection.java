import java.util.*;

/**
 * Created by lizhengning1 on 4/9/16.
 */
public class Infection{
        public static void main(String[] args) {
            Total_Infection test = new Total_Infection();
            Relation newRelation = new Relation();
            test.infect(newRelation);
            Limited_Infection testlimited = new Limited_Infection();
            List<User> result = testlimited.limitedInfection(newRelation,14);
            System.out.println(result.size());
        }
}

/**
 * The infection algorithm goes this:
 *
 * Pick the target user to infect randomly.
 * If this user has not been infected yet, then infect all the users in its relation.
 * */
class Total_Infection{
    public static void infect(Relation relation) {
        Random random = new Random();
        ArrayList<User> infected = new ArrayList<>();
        while(infected.size() < User.getNumberOfUser()) {
            User target = relation.getUser(random.nextInt(User.getNumberOfUser()));
            while (infected.contains(target)) {
                target = relation.getUser(random.nextInt(User.getNumberOfUser()));
            }
            if (target != null) {
                ArrayList<User> newInfected = Relation.getRelation(target);
                for (User user : newInfected) {
                    user.setVersion(target.getVersion());
                    System.out.println(user.getId() + " Infected " + user.getVersion());
                }
                infected.addAll(newInfected);
            }
        }
    }
}


/***
 *The limited infection algorithm goes this:
 * Get all the source nodes.
 * Sort the array based on the group size because I would like to infect from the smallest group then go to large ones
 * to get closer to the target number.
 */

class Limited_Infection{
    public static ArrayList<User> limitedInfection(Relation relation, int targetNum) {
        int remaining = targetNum;
        Random random = new Random();
        ArrayList<User> infected = new ArrayList<>();
        ArrayList<User> source = Relation.getSource();
        ArrayList<Head> totalUsers = new ArrayList<>();

        for (User u : source) {
            totalUsers.add(new Head(u));
        }
        /**
        * Sort on ascending order based on group size
        * */
        Collections.sort(totalUsers, new Comparator<Head>() {
            @Override
            public int compare(Head o1, Head o2) {
                return o1.group.size() - o2.group.size();
            }
        });

        for (int i = 0; i < totalUsers.size(); i++) {
            Head target = totalUsers.get(i);
            if (target.group.size() <= targetNum) {
                ArrayList<User> newInfected = new ArrayList<>();
                /**
                 * Sort the infected group based on the version number.
                 * Then update each user in this group of the largest version number.
                 * */
                Collections.sort(newInfected, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o2.getVersion() - o1.getVersion();
                    }
                });
                for (User infectUser : target.getGroup()) {
                    infectUser.setVersion(target.getGroup().get(0).getVersion());
                }
                newInfected.addAll(target.getGroup());
                targetNum -= newInfected.size();
                totalUsers.remove(target);
                infected.addAll(newInfected);
            }
        }
        System.out.println(targetNum);
        return infected;
    }
}

class Head{
    ArrayList<User> group = new ArrayList<>();
    public Head(User head) {
        this.group = Relation.getRelation(head);
    }

    public ArrayList<User> getGroup() {
        return this.group;
    }
}
