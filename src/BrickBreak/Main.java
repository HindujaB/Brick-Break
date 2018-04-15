
package BrickBreak;

import javax.swing.JFrame;

/**
 *
 * @author Hindu
 */
public class Main {
    public static void main(String[] args){
    
        JFrame obj = new JFrame();
        Game game =new Game();
        obj.setBounds(10,10,800,700);
        obj.setTitle("Brick Breakers");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(game);
        
        
        
        
    }
    
}
