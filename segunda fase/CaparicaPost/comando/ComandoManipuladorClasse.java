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
public abstract class ComandoManipuladorClasse implements ComandoManipulador{
    
    private CaparicaPost caparica;
    private Scanner scanner;
    private String comando[];
    
    public ComandoManipuladorClasse (Scanner line ,String comando[],CaparicaPost caparica) throws ComandoIlegalException{
     
        this.caparica=caparica;
        this.scanner=line;
        this.comando=comando;
        
        preVerificacao();
        execucao();
        posVerificacao();
        
        
        
        
    }

   protected CaparicaPost getCaparica() {
        return caparica;
    }

    protected Scanner getScanner() {
        return scanner;
    }

    protected String[] getComando() {
        return comando;
    }
    
    
    
    
    
    
    
    
    
    
    
}
