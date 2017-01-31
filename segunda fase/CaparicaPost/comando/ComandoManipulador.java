package comando;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author gil
 */
public interface ComandoManipulador {
    void preVerificacao() throws ComandoIlegalException;
    void posVerificacao() throws ComandoIlegalException;
    void execucao () throws ComandoIlegalException;
    
}
