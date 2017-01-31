package caparicapost;


public class Collaborator extends UserAbstract {
    final String NO = "No editor; ";
    final String PONTO ="; ";
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
            StringBuilder s = new StringBuilder();
            s.append(editor.GetName()).append(PONTO);
            return s.toString();
        }
        return NO;
    }

    public String GetSimpleEditorName() {
        return editor.GetName();
    }

    public boolean HasEditor() {
        return (editor != null);
    }
}
