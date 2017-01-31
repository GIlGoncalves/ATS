

import caparicapost.CaparicaPost;
import java.util.Scanner;
import java.io.PrintWriter;
import caparicapost.CaparicaPostClass;
import comando.Comando;
import comando.ComandoIlegalException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;

public class Main {
	private static CaparicaPost cp = new CaparicaPostClass();
        final static String ENERGY = "EnergyTests/";
        final static String UTF = "UTF-8";
        final static String J = " J\n";
        final static String GPU ="GPU: ";
        final static String CPU="CPU: ";
        final static String PAC = "Package: ";
        final static String TIME = "Time: ";
        final static String S =  " seconds";
        
        public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
        Long timeafter, timebefore = System.nanoTime();
        EnergyCheckUtils.init();
        double [] before = EnergyCheckUtils.getEnergyStats();
        Main m = new Main();
        m.main1();

        StringBuilder s = new StringBuilder();
        s.append(ENERGY).append(args[0]);
        PrintWriter writer = new PrintWriter(s.toString(),UTF);
        double [] after = EnergyCheckUtils.getEnergyStats();
        timeafter = System.nanoTime();
        s = new StringBuilder();
        s.append(GPU).append(after[0]-before[0]).append(J);
        s.append(CPU).append(after[1]-before[1]).append(J);
        s.append(PAC).append(after[2]-before[2]).append(J);
        s.append(TIME).append((double)(timeafter - timebefore)/1000000000.0).append(S);
        writer.println(s.toString());
        
        writer.close();
        
        }
        
        
        
        
        
	public  void main1() {
            Scanner a = new Scanner(System.in);
            String comando [] = a.nextLine().split(" ");
            
	   while(comando[0].equals(Comando.EXIT)==false) {
            
                try{
                  
                    
                    String classeComando = Comando.getClasseFromComando(comando[0]);
                    
                    
                    
                    if(classeComando==null) {
                     
                        throw new ComandoIlegalException("Comando não encontrado");
                        
                    
                    }
                    
                Class c = Class.forName(classeComando);     
                Constructor cons = c.getConstructor(Scanner.class,String[].class,CaparicaPost.class);
                cons.newInstance(a,comando,cp);
                
                }
                catch(Exception e) {
                
                    System.out.println("Comando incorrecto");
                }
                
                
                comando=getComando(a);
               
            }
            System.out.println("Exiting.");
            System.out.println();
            a.close();
            
    
            
            
        }
        
        public static String [] getComando(Scanner in) {
        
            String [] input ;
            input = in.nextLine().split(" ");
            return input;
        }
        
        
}
