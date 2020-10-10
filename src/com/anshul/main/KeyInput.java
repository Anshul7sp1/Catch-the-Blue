package com.anshul.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    
    private Handler handler;
    private Boolean[] keyDown = new Boolean[4];

    public KeyInput(Handler handler){
        this.handler = handler;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.PLAYER){
                switch(key){
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W: tempObject.setVelY(-5);keyDown[0]=true;break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S: tempObject.setVelY(5);keyDown[1]=true;break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A: tempObject.setVelX(-5);keyDown[2]=true;break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D: tempObject.setVelX(5);keyDown[3]=true;break;
                }
            }
        }
        
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.PLAYER){
                switch(key){
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W: keyDown[0]=false;break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S: keyDown[1]=false;break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A: keyDown[2]=false;break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D: keyDown[3]=false;break;
                }
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
        }
    }
    
}