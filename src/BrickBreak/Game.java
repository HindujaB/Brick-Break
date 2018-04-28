
package BrickBreak;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Hindu
 */
public class Game extends JPanel implements KeyListener,ActionListener{ 
    
    private boolean play = false;
    private int score = 0;
   
    
    private int nBricks = 21;
    private int HScore = 0;
    private Timer timer;
    private int delay =9;
    
    private int playerPOSX = 310 ;
    
    private int ballPOSX = 120;
    private int ballPOSY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    
    private Map mp;
    
    public Game(){
    
        mp = new Map(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
        
    
    }
    
    public void paint(Graphics g){
    
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 792, 692);
        
        //draw map
        mp.draw((Graphics2D)g);
        
        //scores and High score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD,25));
        g.drawString("Score :"+score, 660, 30);
        g.drawString("High Score :"+HScore, 480, 30);
        
        
        //titles
        g.setColor(Color.CYAN);
        g.setFont(new Font("Sans serif", Font.BOLD, 42));
        g.drawString("Brick Breakers", 230, 650);
        
        //borders
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 3, 692);
        g.fillRect(0, 0, 792, 3);
        g.fillRect(791, 0, 3, 692);
    
        //paddle
        g.setColor(Color.BLUE);
        g.fillRect(playerPOSX, 550, 100, 8);
        
        //ball
        g.setColor(Color.RED);
        g.fillOval(ballPOSX, ballPOSY, 20, 20);
        
        if (nBricks <= 0) {
            play =false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.cyan);
            g.setFont(new Font("serif", Font.BOLD,30));
            g.drawString("You won the game!", 250, 350);
            g.drawString("Your Score : "+score, 250, 380);
            
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD,20));
            g.drawString("Press 'Enter' to restart", 250, 410);
        }
        
        
       B: if(ballPOSY>670 )
        {
            
            play=false;
            
                    if(score> HScore)
                        HScore = score;
                    ballXdir = 0;
                    ballYdir = 0;
                    g.setColor(Color.cyan);
                    g.setFont(new Font("serif", Font.BOLD, 30));
                    g.drawString("Game Over!", 250, 350);
                    g.drawString("Your Score : " + score, 250, 380);
                    g.setColor(Color.red);
                    g.setFont(new Font("serif", Font.BOLD, 20));
                    g.drawString("Press 'Enter' to restart", 250, 410);
                    break B;
                
            }
           
            
            
            
        
                
        
        g.dispose();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        
        if (play) {
            if (new Rectangle(ballPOSX,ballPOSY,20,20).intersects(new Rectangle(playerPOSX,550,100,8))) {
                ballYdir = -ballYdir;
            }
         A: for(int i =0;i<mp.map.length;i++){
            for(int j = 0; j<mp.map[0].length;j++){
                if(mp.map[i][j]>0){
                    int brickX = j*mp.bWidth +80;
                    int brickY = i *mp.bHeight+50;
                    int bW = mp.bWidth;
                    int bH = mp.bHeight;
                    
                    Rectangle rect = new Rectangle(brickX,brickY,bW,bH);
                    Rectangle ball = new Rectangle(ballPOSX,ballPOSY,20,20);
                    
                    Rectangle brickRect = rect;
                    
                    if (ball.intersects(brickRect)) {
                        mp.setBrickValue(0, i, j);
                        nBricks--;
                        score += 10;
                        
                        if (ballPOSX+19 <= brickRect.x || ballPOSX+1 >= brickRect.x+brickRect.width)                    
                            ballXdir = -ballXdir;
                        else
                            ballYdir = -ballYdir;
                        
                        break A;
                    }
                }
            }
            }
            ballPOSX += ballXdir;
            ballPOSY += ballYdir;
            if (ballPOSX <0) {
                ballXdir = - ballXdir;
            }
            if (ballPOSY <0) {
                ballYdir = - ballYdir;
            }
            if (ballPOSX > 770) {
                ballXdir = - ballXdir;
            }
        }
        repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && play){
        
            if(playerPOSX>=700)
                playerPOSX = 700;
            else
                moveRight();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT && play){
        
            if(playerPOSX<=10)
                playerPOSX = 10;
            else
                moveLeft();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                
                    play =true;
                    playerPOSX = 310 ;    
                    ballPOSX = 120;
                    ballPOSY = 350;
                    ballXdir = -1;
                    ballYdir = -2;
                    score = 0;
                    
                    nBricks = 21;
                    mp = new Map(3, 7);
                
                    repaint();
                }
                
              
            }
        
        
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    

    public void moveRight(){
    
        play = true;
        playerPOSX +=20;
    }
    
    public void moveLeft(){
    
        play = true;
        playerPOSX -=20;
    }
    

    
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    
}
