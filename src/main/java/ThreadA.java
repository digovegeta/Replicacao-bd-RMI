/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author digo_
 */
public class ThreadA {

     public static void main(String[] args){
      ThreadB b = new ThreadB();
      b.start();

      synchronized(b){
          try{
              System.out.println("Aguardando o b completar...");
              b.wait();
              System.out.println("b completo");              
          }catch(InterruptedException e){
              e.printStackTrace();
          }

          System.out.println("Total é igual a: " + b.total);
          System.exit(0);
      }
  }
}

