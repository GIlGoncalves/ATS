package comando;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import caparicapost.CaparicaPost;
import exceptions.ExceptionChronicleNotFound;
import java.util.Scanner;

/**
 *
 * @author gil
 */
public class AddComando extends ComandoManipuladorClasse{

    public AddComando(Scanner line, String[] commando, CaparicaPost caparica) throws ComandoIlegalException {
        super(line, commando, caparica);
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
        
        
        
        if(getComando()[1].equalsIgnoreCase(Comando.REPORT)) {
        
            String tituloReport = getScanner().nextLine();
            String temaReport = getScanner().nextLine();
            String assuntoReport = getScanner().nextLine();
            String dataReport = getScanner().nextLine();
            String textoReport = getScanner().nextLine();
            
            getCaparica().AddReport(getComando()[1],tituloReport,temaReport,assuntoReport,dataReport,textoReport);
            
        
        }
        
        if(getComando()[1].equalsIgnoreCase(Comando.CHRONICLE)) {
        
            String tituloC = getScanner().nextLine();
            String temaC = getScanner().nextLine();
            String topicoC = getScanner().nextLine();
            String dataC = getScanner().nextLine();
            String textoC = getScanner().nextLine();
            
            getCaparica().AddChronicle(getComando()[1], tituloC, temaC, topicoC, dataC, textoC);
            
        
        
        }  
        
        if(getComando()[1].equalsIgnoreCase(Comando.COMMENT)) {
        
            String tituloCo = getScanner().nextLine();
            String textoCo = getScanner().nextLine();
            try {
                getCaparica().AddComment(tituloCo, textoCo);
            } catch (ExceptionChronicleNotFound ex) {
               System.out.println(ex.getMessage());
            }
            
            
        
        }
        
    
    }
    
    
    
}
