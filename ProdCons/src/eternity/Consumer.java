/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eternity;

import data.dataManager;
import data.CntObj;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clemi
 */
public class Consumer extends Thread {
    
    private dataManager m;
    private int n;
    private CntObj cnt;

    public Consumer(dataManager aThis, int n, CntObj cnt) {
        this.m = aThis;
        this.n = n;
        this.cnt = cnt;
    }

    @Override
    public void run() {
        consume();
    }

    private void consume() {
        String item;
        while(true)
        {
            if(cnt.getCnt() == 0)
            {
                try {
                    synchronized(this){
                        System.out.println("Cons: sleepin");
                        this.wait();
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Cons: just woke up");}
            }
            cnt.decrease();
            if(cnt.getCnt() == n-1)
            {
                try {
                    data.dataManager.wakeUp("prod");
                } catch (Exception e) {
                }
            }
        }
    }
    
    
}
