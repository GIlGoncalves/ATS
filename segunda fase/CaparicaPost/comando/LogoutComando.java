package comando;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import caparicapost.CaparicaPost;
import exceptions.ExceptionNoUserLoggedIn;
import java.util.Scanner;

/**
 *
 * @author gil
 */
public class LogoutComando extends ComandoManipuladorClasse{

    public LogoutComando(Scanner line, String[] comando, CaparicaPost caparica) throws ComandoIlegalException {
        super(line, comando, caparica);
    }

    @Override
    public void preVerificacao() throws ComandoIlegalException {
      
        
        if(getComando().length!=1) 
        
            System.out.println("ERRO");
    }

    
    @Override
    public void posVerificacao() throws ComandoIlegalException {
    }

    @Override
    public void execucao() throws ComandoIlegalException {
    
        try {
            getCaparica().LogoutUser();
        } catch (ExceptionNoUserLoggedIn e) {
            System.out.println(e.getMessage());
        }
    
    
    
    }
    


}

