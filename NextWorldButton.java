/**
 * Write a description of class Player here.
 *
 * @Esteban M
 * @version (a version number or a date)
 */
import mayflower.*;

public class NextWorldButton extends Actor
{
    private MayflowerImage button;
    private boolean locked;
    
    public NextWorldButton(String file, int x, int y, boolean lock) {
        button = new MayflowerImage(file);
        
        locked = lock;
        button.scale(x, y);
        
        setImage(button);
    }
    
    public boolean isLocked() {
        return locked;
    }
    
    public void changedLock(boolean newLock) {
        locked = newLock;
    }
    
    public void act() {}
    
}
