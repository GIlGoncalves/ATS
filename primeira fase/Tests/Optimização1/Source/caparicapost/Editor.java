package caparicapost;

import java.util.ArrayList;

public class Editor extends UserAbstract {

    ArrayList<User> collaboratorList = new ArrayList<User>();

    public Editor(String type, String name, String email, String theme) {
        super(type, name, email, theme);
    }

    public void AssignCollaborator(User collab) {
        collaboratorList.add(collab);
    }
}
