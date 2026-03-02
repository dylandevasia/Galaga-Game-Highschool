import mayflower.*;
/**
 * An EnemyBoss object that extends EnemyShip. EnemyBoss is an EnemyShip with more health, more movement, and more shot potential.
 *
 * @Andrew W
 * @version (a version number or a date)
 */
public class EnemyBoss extends EnemyShip
{
    // instance variables - replace the example below with your own
    public int x, y, finalX, finalY, level;
    public int timesShot;
    /**
     * Constructor for objects of class EnemyBoss
     */

    public EnemyBoss(int x, int y, int finalX, int finalY, int level) {
        super(x, y, finalX, finalY);
        this.level = level;
        MayflowerImage player = new MayflowerImage("img/PurpleHex.png");
        player.scale(100, 100);
        setImage(player);
        timesShot = 0;
    }

    //overridden act method with more shot potential
    public void act() {
        move();
        idleSwayCheck();
        InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
        if(w.checkFormation())
        {
            idleSway();
        }
        ShootingPotential(100);
        checkShot();
        die();
        setLocation(x, y);
    }

    //overrided idle sway method that moves the boss left and right
    public void idleSway() {
        if (movingOut) {
            x += 5; // Adjust the value of slope as per your requirement
        } else {
            x -= 5; // Adjust the value of slope as per your requirement
        }
        if(x < 50)
        {
            movingOut = true;
        }
        if(x > 450)
        {
            movingOut = false;
        }
        setLocation(x, y);
    }

    //overridedn death method that gives the boss health based on the current level, drops more powerups, and gives more score than a normal enemyShip
    public void die(){
        if(timesShot >= level * 1.5) {
            InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
            w.removeShip(this);
            w.removeObject(this);
            w.updateScore();
            w.updateScore();
            w.updateScore();
            w.updateScore();
            w.updateScore();
            timesShot = 0;
            if(Math.random()*100 < 15) {
                w.addObject(new PowerUp(x + 30, y), x + 30, y + 60);
            }
            if(Math.random()*100 < 15) {
                w.addObject(new PowerUp(x + 60, y), x + 60, y + 70);
            }
            if(Math.random()*100 < 15) {
                w.addObject(new PowerUp(x,y), x,y + 50);
            }
            if(Math.random()*100 < 15) {
                w.addObject(new PowerUp(x + 90, y), x + 90, y + 60);
            }
            if(Math.random()*100 < 15) {
                w.addObject(new PowerUp(x + 120, y), x + 120, y + 50);
            }
        }
    }

    //method that keeps track of how many times the boss was shot
    public void checkShot() {
        Bullet bullet = getOneIntersectingObject(Bullet.class);
        if(bullet != null && bullet.shotBy() == Ship.PLAYER) {
            timesShot++;
            InfiniteLevelWorld world = (InfiniteLevelWorld) getWorld();
            world.removeObject(bullet);
        }
    }
    
    //overrided ShootingPotential(int a) method that allows the boss to shoot more than a normal enemyShip
    public void ShootingPotential(int a)
    {
        int num = (int)(Math.random() * a);
        if(num == 0)
        {
            Bullet bullet = new Bullet(Ship.ENEMY, x, y);
            InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
            w.addObject(bullet, x, y);
        }
    }
}

