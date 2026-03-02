import mayflower.*;
/**
 * An EnemyShip Object that creates the movement, shooting and formation of the enemies.
 *
 * @Dylan D
 * @version (a version number or a date)
 */
public class EnemyShip extends Actor
{
    protected int arrayRowIndex, arrayColumnIndex, x, y, finalY, finalX, counter;
    protected boolean atRightPosition, movingOut, allAtRightPosition;
    public EnemyShip(int x, int y, int finalX, int finalY)
    {
        this.x = x;
        this.finalX = finalX;
        this.y = y;
        this.finalY = finalY;
        arrayRowIndex = (finalX - 75) / 50;
        arrayColumnIndex = finalY / 50;
        y = 0;
        atRightPosition = false;
        allAtRightPosition = false;
        movingOut = true;
        counter = 0;
        MayflowerImage player = new MayflowerImage("img/BlueSquare.png");
        player.scale(50, 50);
        setImage(player);
    }

    public void act()
    {
        move();
        idleSwayCheck();
        InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
        if(w.checkFormation())
        {
            idleSway();
        }
        ShootingPotential(400);
        Die();
    }
   
    //moves the enemy ship until it is at its final location
    public void move()
    {
        if(!atRightPosition)
        {
            if(Math.abs(finalX - x) < 3)
            {
                x = finalX;
            }
            if(Math.abs(finalY - y) < 2)
            {
                y = finalY;
            }
            if(x<finalX)
            {
                x += 2;
            }
            else if(x>finalX)
            {
                 x -= 2;
            }
           
            if(y<finalY)
            {
                y += 3;
            }
            else if(y>finalY)
            {
                y -= 3;
            }
            else if(y == finalY && x != finalX)
            {

                if(x<finalX)
            {
                x += 2;
            }
            else if(x>finalX)
            {
                 x -= 2;
            }
            }
        }
        setLocation(x,y);
    }
    
    //creates the probability an enemy has to shoot
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
   
    //kills the enemyShip when it is shot and randomly drops a powerup
    public void Die()
    {
        if(isTouching(Bullet.class))
        {
            Object a = getOneIntersectingObject(Bullet.class);
            Bullet c = (Bullet) a;
           
            if(c.shotBy() == Ship.PLAYER)
            {
                       
                InfiniteLevelWorld w = (InfiniteLevelWorld) getWorld();
                w.removeShip(this);
                w.removeObject(this);
                w.removeObject(c);
                w.updateScore();
                if(Math.random()*100 <= 5)
                {
                    w.addObject(new PowerUp(x, y) ,x ,y);
                }
            }
        }
    }
   
    //checks if it it a ship is at the right location
    public boolean idleSwayCheck()
    {
        if(y == finalY && x == finalX)
        {
            atRightPosition = true;
        }
        return atRightPosition;
    }
    
    public boolean checkPosition()
    {
        return atRightPosition;
    }
    public boolean checkAllPosition()
    {
        return allAtRightPosition;
    }
    public void readySignal()
    {
         allAtRightPosition = true;
    }
    
    //moves the ships into formation
    public void idleSway()
    {
        if(movingOut)
        {
            x += (arrayRowIndex - 3);
            y += arrayColumnIndex;
        }
        else
        {
            x -= (arrayRowIndex - 3);
            y -= arrayColumnIndex;
        }
        counter++;
        if(counter > 25)
        {
            counter = 0;
            movingOut = !movingOut;
        }
    }
}

