/**
 * Creates a powerup object that can either give the player a shield, double shooting, or extra life.
 *
 * @Dylan D
 * @version (a version number or a date)
 */
import mayflower.*;

public class PowerUp extends Actor
{
    private int power;
    int x, y;
    public PowerUp(int x, int y) {
        MayflowerImage powerUP = new MayflowerImage("img/PowerUp.png");
        powerUP.scale(60, 40);
        this.x = x;
        this.y = y;
        setImage(powerUP);
        power = (int)(Math.random() * 3);
    }

    public void act() {
        setLocation(x,y += 2);
        InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
        if(isTouching(Player.class))
        {
            Object a = getOneIntersectingObject(Player.class);
            Player c = (Player) a;
            w.removeObject(this);
            if(power == 0) {
                c.enableDoubleShot();
            }
            else if(power == 1) {
                c.enableShield();
            }
            else if(power == 2) {
                c.enableExtraLife();
            }
        }
    }
}
