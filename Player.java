/**
 * Write a description of class Player here.
 *
 * @Andrew W, Dylan D, Esteban M
 * @version (a version number or a date)
 */
import mayflower.*;
import java.util.*;
public class Player extends Actor
{
    // instance variables
    int lives;
    int shotDelayCounter, shieldCountdown, doubleShotCountDown;
    boolean shield;
    boolean doubleShot;
    Shield bubble;
    
    int dmgCount, protCount, hlthCount;
    private String dmg;
    private String prot;
    private String hlth;
    private Stack<Shield> shields;
    /**
     * Constructor for objects of class Player
     */
    public Player(String skin)
    {
        MayflowerImage player = new MayflowerImage(skin);
        player.scale(50, 50);
        setImage(player);
        lives = 3;
        
        //counters
        shotDelayCounter = 0;
        shieldCountdown = 0;
        doubleShotCountDown = 0;
        
        //power up variables
        shield = false;
        bubble = new Shield(this, 0);
        doubleShot = false;
        shields = new Stack<Shield>();
        dmg = "DOUBLE SHOT";
        dmgCount = 0;
        prot = "SHIELD";
        protCount = 0;
        hlth = "EXTRA LIFE";
        hlthCount = 0;
    }

    public void act() {
        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();
        //moving the player
        if(x < (600-w) && Mayflower.isKeyDown(Keyboard.KEY_RIGHT))
            setLocation(x+5,y);
        else if(x > 0 && Mayflower.isKeyDown(Keyboard.KEY_LEFT))
            setLocation(x-5,y);

        //shooting    
        if(Mayflower.isKeyDown(Keyboard.KEY_SPACE) && shotDelayCounter >= 20) {
            shotDelayCounter = 0;
            World world = getWorld();
            if(doubleShot == false)
            {
                world.addObject(new Bullet(Ship.PLAYER, getX() + 20, getY()), getX() + 20, getY());
            }
            else
            {
                world.addObject(new Bullet(Ship.PLAYER, getX(), getY()), getX(), getY());
                world.addObject(new Bullet(Ship.PLAYER, getX() + 40, getY()), getX() + 40, getY());

            }
        }
        shotDelayCounter++;
        checkPowerUps();
        powerUpText();
        Die();
    }

    //gives the player double shooting powerup
    public void enableDoubleShot()
    {
        doubleShot = true;
        InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
        w.showText(dmg, 200, 400, Color.RED);
        dmgCount = 0;
        doubleShotCountDown = 0;
        dmgCount++;
    }

    //gives the player shield powerup
    public void enableShield()
    {
        InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
        w.showText(prot, 200, 450, Color.BLUE);
        protCount = 0;
        shieldCountdown = 0;
        protCount++;
        
        Shield s = new Shield(this, shields.size());
        w.addObject(s, 1000, 1000);
        shields.push(s);
        shield = true;
    }
    
    //gives the player an extra life
    public void enableExtraLife()
    {
        lives++;
        InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
        Heart heart = new Heart();
        w.addLives(heart);
        w.showText(hlth, 200, 500, Color.GREEN);
        hlthCount = 0;
        hlthCount++;
    }
    
    //displays text that tells what powerup the player recieved
    public void powerUpText()
    {
        InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
        if(dmgCount != 0)
        {
            dmgCount++;
            if(dmgCount > 100)
            {
                w.removeText(200, 400);
                dmgCount = 0;
            }
        }
        
        if(protCount != 0)
        {
            protCount++;
            if(protCount > 100)
            {
                w.removeText(200, 450);
                protCount = 0;
            }
        }
        
        if(hlthCount != 0)
        {
            hlthCount++;
            if(hlthCount > 100)
            {
                w.removeText(200, 500);
                hlthCount = 0;
            }
        }
    }
    
    //timer for the doubelshot and shield to wear off
    public void checkPowerUps()
    {
        if(doubleShot)
        {
            doubleShotCountDown++;
            if(doubleShotCountDown >500)
            {
                doubleShotCountDown = 0;
                doubleShot = false;
            }
        }
        if(shield)
        {
            shieldCountdown++;
            if(shieldCountdown >1000)
            {
                shieldCountdown = 0;
                InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
                w.removeObject(shields.pop());
                if(shields.size() == 0) {
                    shield = false;
                }
            }
        }
    }

    public int lives()
    {
        return lives;
    }

    //removes the shield after it is shot
    public boolean useShield()
    {
        if(isTouching(Shield.class))
        {
            Object a = getOneIntersectingObject(Shield.class);
            Shield c = (Shield) a;

            InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
            //w.removeObject(c);
            w.removeObject(shields.pop());
            //shield = false;
            if(shields.size() == 0) {
                shield = false;
            }
            return true;
        }
        return false;
    }

    //removes the player's life when it is shot
    public void Die()
    {
        if(isTouching(Bullet.class))
        {
            Object a = getOneIntersectingObject(Bullet.class);
            Bullet c = (Bullet) a;

            if(c.shotBy() == Ship.ENEMY)
            {

                InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
                w.removeObject(c);
                if(!useShield())
                {
                    setLocation(300, 650);
                    w.updateLives();
                }
            }
        }
    }
}
