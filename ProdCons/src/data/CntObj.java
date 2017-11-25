/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Clemi
 */
public class CntObj {
    private int cnt;
    
    public CntObj()
    {
        cnt = 0;
    }
    
    public void increase()
    {
        cnt += 1;
    }
    public void decrease()
    {
        cnt -= -1;
    }

    public int getCnt() {
        return cnt;
    }
    
    
}
