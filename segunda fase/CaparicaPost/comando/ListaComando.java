package comando;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import caparicapost.CaparicaPost;
import exceptions.ExceptionChronicleNotFound;
import exceptions.ExceptionNoEditorLoggedIn;
import java.util.Scanner;

/**
 *
 * @author gil
 */
public class ListaComando extends ComandoManipuladorClasse {

    public ListaComando(Scanner line, String[] comando, CaparicaPost caparica) throws ComandoIlegalException {
        super(line, comando, caparica);
    }

    @Override
    public void preVerificacao() throws ComandoIlegalException {
      
        
        if(getComando().length!=2) 
        
            System.out.println("ERRO");
    }

    
    @Override
    public void posVerificacao() throws ComandoIlegalException {
    }

    @Override
    public void execucao() throws ComandoIlegalException {
    
        String comando = getComando()[1];
        
        if(comando.equalsIgnoreCase(Comando.ALL)) {
        
            try {
                getCaparica().ListAllUsers();
            } catch (ExceptionNoEditorLoggedIn e) {
               System.out.println(e.getMessage());
            }
        
        }
        
        if(comando.equalsIgnoreCase(Comando.REPORTS)) 
                getCaparica().ListReports();
        if(comando.equalsIgnoreCase(Comando.CHRONICLES))
            getCaparica().ListChronicles();
        
        if(comando.equalsIgnoreCase(Comando.COMMENTS)) {
            String titulo=getScanner().nextLine();
        
            try {
                getCaparica().ListComments(titulo);
            } catch (ExceptionChronicleNotFound e) {
                System.out.println(e.getMessage());
            }
        }
        
        if(comando.equalsIgnoreCase(Comando.TOPIC)) {
          String topico = getScanner().nextLine();
          getCaparica().ListTopic(topico);
          
        }
        
        if(comando.equalsIgnoreCase(Comando.THEME)) {
          String tema = getScanner().nextLine();
          getCaparica().ListTheme(tema);
            
        }
    
        System.out.println("");
    
    
    
    
    }
    
}
