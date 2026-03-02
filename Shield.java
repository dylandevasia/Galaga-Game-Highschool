import mayflower.*;
/**
 * Creates a shield powerup that protects the player
 *
 * @Dyaln D
 * @version (a version number or a date)
 */
public class Shield extends Actor
{
    // instance variables - replace the example below with your own
    private Player player;
    private int shieldCountdown, pos;
    /**
     * Constructor for objects of class Shield
     */
    public Shield(Player p1, int position)
    {
        // initialise instance variables
        player = p1;
        pos = position - 1;
        MayflowerImage bubble = new MayflowerImage("img/bubble.png");
        bubble.scale(80 + pos * 15, 80 + pos * 15);
        setImage(bubble);
        shieldCountdown = 0;
    }

    public void act()
    {
        setLocation(player.getX()-10 - 7*pos, player.getY()-10 - 5*pos);
        shieldCountdown++;
    }
}
