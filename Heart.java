/**
 * The heart object is displayed on the screen and shows the player's lives
 *
 * @Andrew W
 * @version (a version number or a date)
 */
import mayflower.*;


public class Heart extends Actor
{
    public Heart() {
        MayflowerImage heart = new MayflowerImage("img/heart.png");
        heart.scale(50, 50);
        
        setImage(heart);
    }
    
    
    public void act() {}
    
}
