
package comando;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;

/**
 *
 * @author gil
 */
public final class Comando {
    
    private static  HashMap<String,String> comandoClasse = new HashMap<String,String>();
    
    public static final String ADD="ADD";
    public static final String EXIT="EXIT";
    public static final String LOGIN="LOGIN";
    public static final String LOGOUT="LOGOUT";
    public static final String REGISTER="REGISTER";
    public static final String TOP="TOP";
    public static final String LIST="LIST";
    public static final String LIKE="LIKE";
    public static final String ASSIGN="ASSIGN";
    public static final String APPROVE="APPROVE";
    public static final String REPORT = "REPORT";
    public static final String CHRONICLE= "CHRONICLE";
    public static final String COMMENT ="COMMENT";
    public static final String EDITOR = "EDITOR";
    public static final String JOURNALIST ="JOURNALIST";
    public static final String ALL = "ALL";
    public static final String TOPIC = "TOPIC";
    public static final String THEME = "THEME";
    public static final String COMMENTS ="COMMENTS";
    public static final String CHRONICLES ="CHRONICLES";
    public static final String REPORTS = "REPORTS";
    public static final String READER = "READER";
    public static final String COLLABORATOR= "COLLABORATOR";
    
    
    
    
    public static final String USERA="User already exists.\n";
    public static final String NEWU="New user registered.";
    public static final String USERLOG="User already logged in.\n";
    public static final String USERAL ="Another user is logged in.\n";
    public static final String WELCOME ="Welcome ";
    public static final String GOOD = "Goodbye ";
    public static final String ALLUSER= "All users:";
    public static final String NOJORNA = "No journalist is logged in.\n";
    public static final String ANOTHER= "There is another article with that title.\n";
    public static final String NEWREPOR= "New report published.";
    public static final String NO ="No journalist is logged in.";
    public static final String REPORTPUB= "Reports published:";
    public static final String COLLABORATORIS = "No collaborator is logged in.\n";
    public static final String NEWCHRONICLE = "New chronicle added.";
    public static final String NOCOLLABORATOR ="No collaborator is logged in.";
    public static final String CHRONICLESW = "Chronicles written:";
    public static final String NEWEDITOR = "New editor assignment.";
    public static final String NOTEXISTC ="Collaborator does not exist.\n";
    public static final String LIKES = "Likes: ";
    public static final String NOEDITOR = "No editor is logged in.\n";
    public static final String NOTEXISTCH="Chronicle does not exist.\n";
    public static final String NOTAUTH = "Not authorized to approve this chronicle.\n";
    public static final String CHRONICLEAPPR = "Chronicle already approved.\n";
    public static final String NEWCHRO = "New chronicle published.";
    public static final String NOREADER = "No reader is logged in.\n";
    public static final String NEWCOMMENT = "New comment added.";
    public static final String TOPCHRO = "Top chronicles:";
    public static final String ARTICLE = " articles:";
    public static final String COMM="Comments: ";
    public static final String DATE ="dd-MM-yyyy";
    
   
    
    
    
    
    static {
    
        CarregaComando();
    
    }

    
    private static void CarregaComando() {
    
        comandoClasse.put(ADD,"comando.AddComando");
        comandoClasse.put(APPROVE,"comando.AproveComando");
        comandoClasse.put(ASSIGN,"comando.AssignComando");
        comandoClasse.put(LOGOUT,"comando.LogoutComando");
        comandoClasse.put(LOGIN,"comando.LoginComando");
        comandoClasse.put(LIKE,"comando.LikeComando");
        comandoClasse.put(REGISTER,"comando.RegisterComando");
        comandoClasse.put(TOP,"comando.TopComando");
        comandoClasse.put(LIST,"comando.ListaComando");
        
      
    }
    
    
    public static String getClasseFromComando(String comando) {
    
        return comandoClasse.get(comando);
    
    }
    
    
    
    
    
    
    
    
}
