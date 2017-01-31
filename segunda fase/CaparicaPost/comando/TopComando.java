package comando;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import caparicapost.CaparicaPost;
import java.util.Scanner;

/**
 *
 * @author gil
 */
public class TopComando extends ComandoManipuladorClasse {

    public TopComando(Scanner line, String[] comando, CaparicaPost caparica) throws ComandoIlegalException {
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
      
      getCaparica().TopChronicles();
    }
    
}
