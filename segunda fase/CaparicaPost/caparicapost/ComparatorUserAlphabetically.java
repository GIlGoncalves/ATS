package caparicapost;

import java.util.Comparator;

public class ComparatorUserAlphabetically implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        String name1 = u1.GetName();
        String name2 = u2.GetName();
        return name1.compareToIgnoreCase(name2);
    }
}
