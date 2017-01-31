package caparicapost;


public class Collaborator extends UserAbstract {

    User editor;

    public Collaborator(String type, String name, String email) {
        super(type, name, email);
        editor = null;
    }

    public void SetEditor(User editor) {
        this.editor = editor;
    }

    public String GetEditorName() {
        if (HasEditor()) {
            return editor.GetName() + "; ";
        }
        return "No editor; ";
    }

    public String GetSimpleEditorName() {
        return editor.GetName();
    }

    public boolean HasEditor() {
        return (editor != null);
    }
}
