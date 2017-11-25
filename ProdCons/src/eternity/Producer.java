/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eternity;

import data.CntObj;
import data.dataManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clemi
 */
public class Producer extends Thread{

    private dataManager m;
    private int n;
    private CntObj cnt;
    
    public Producer(dataManager m, int n, CntObj cnt)
    {
        this.m = m;
        this.n = n;
        this.cnt = cnt;
    }
    
    
    @Override
    public void run() {
        produce();
    }

    private void produce() {
        String item;
        while(true)
        {
           item = "1string";
           if(cnt.getCnt() == n)
           {
               try {
                    synchronized(this){
                        System.out.println("Prod: sleepin");
                        this.wait();
                    }
               } catch (InterruptedException ex) {
                   System.out.println("Prod: just woke up");;
               }
           }
           m.insert(item);
           cnt.increase();
           if(cnt.getCnt() == 1)
           {
               try {
                   data.dataManager.wakeUp("cons");
               } catch (InterruptedException ex) {
                   Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        }
    
    }
}
