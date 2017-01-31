package comando;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import caparicapost.CaparicaPost;
import exceptions.ExceptionUserLoggedIn;
import java.util.Scanner;

/**
 *
 * @author gil
 */
public class RegisterComando extends ComandoManipuladorClasse {

    public RegisterComando(Scanner line, String[] comando, CaparicaPost caparica) throws ComandoIlegalException {
        super(line, comando, caparica);
    }

   @Override
    public void preVerificacao() throws ComandoIlegalException {
      
        
        if(getComando().length!=3) 
        
            System.out.println("ERRO");
    }

    
    @Override
    public void posVerificacao() throws ComandoIlegalException {
    }

    @Override
    public void execucao() throws ComandoIlegalException {
       String tema =null;
       String email = getScanner().nextLine();
       
       if(getComando()[1].equalsIgnoreCase(Comando.EDITOR) | getComando()[1].equalsIgnoreCase(Comando.JOURNALIST)) {
       
           tema=getScanner().nextLine();
       
       }
       
        try {
            getCaparica().RegisterUser(getComando()[1], getComando()[2], email, tema);
        } catch (ExceptionUserLoggedIn ex) {
            System.out.println(ex.getMessage());
        }
       
        
    
    
    
    
    
    }
    
}
