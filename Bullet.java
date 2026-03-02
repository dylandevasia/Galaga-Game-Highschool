/**
 * Creates the object for the bullet. Inlcudes the bullets position, speed, and shooter.
 *
 * @Dylan D
 * @version (a version number or a date)
 */
import mayflower.*;
public class Bullet extends Actor
{
    private int speed, x, y;
    private Ship ship;
    public Bullet(Ship s, int x, int y) 
    {
        ship = s;
        this.x = x;
        this.y = y;
        speed = 10;
        MayflowerImage bullet = new MayflowerImage("img/bullet.png");
        bullet.scale(50, 50);
    }

    //Continuously moves the bullet up or down the screen depending on who it was shot by
    public void act()
    {
        if(Ship.ENEMY == ship) {
            setLocation(x, y += speed);
        } else {
            setLocation(x, y -= speed);
        }
        //Disappears at the end of the screen
        if(y > 800 || y < 0)
        {
            InfiniteLevelWorld w = (InfiniteLevelWorld)getWorld();
            w.removeObject(this);
        }
    }
    
    public Ship shotBy()
    {
        return ship;
    }
}
