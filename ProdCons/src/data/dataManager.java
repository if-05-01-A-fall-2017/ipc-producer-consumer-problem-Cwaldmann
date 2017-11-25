/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import eternity.Consumer;
import  eternity.Producer;

/**
 *
 * @author Clemi
 */
public class dataManager {
    
    private String[] shared;
    private CntObj cnt;
    public static Thread producerThread,consumerThread;
    
    public dataManager(int n) {
        cnt = new CntObj();
        shared = new String[n];
        producerThread = new Producer(this, n, cnt);
        consumerThread = new Consumer(this, n, cnt);
        
        startThread();
    }

    public void insert(String item) {
        shared[cnt.getCnt()] = item;
    }
    public static void wakeUp(String in) throws InterruptedException {
        if ("prod".equals(in)) {
            synchronized(producerThread){
                producerThread.notify();
            }
        }
        else{
            synchronized(consumerThread){
                consumerThread.notify();
            }
        }
    }

    private void startThread() {
        producerThread.start();
        consumerThread.start();
    }
    
    
}
