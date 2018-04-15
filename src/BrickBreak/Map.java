
package BrickBreak;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Hindu
 */
public class Map {
    public int map[][];
    public int bWidth;
    public int bHeight;
    
    public Map(int row,int col){
    
        map = new int[row][col];
        for(int i =0;i<map.length;i++){
            for(int j = 0; j<map[0].length;j++){
                map[i][j]=1;           
         }
        }
        
        bWidth = 640/col;
        bHeight = 200/row;
        
    }
    
    public void draw(Graphics2D g){
    
         for(int i =0;i<map.length;i++){
            for(int j = 0; j<map[0].length;j++){
            
                if(map[i][j]>0){
                    g.setColor(Color.yellow);
                    g.fillRect(j*bWidth+80, i*bHeight+50, bWidth, bHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*bWidth+80, i*bHeight+50, bWidth, bHeight);
                }
            }
         }    
    }
    
    public void setBrickValue(int val,int row,int col){
    
        map[row][col] = val;
    
    }
}
