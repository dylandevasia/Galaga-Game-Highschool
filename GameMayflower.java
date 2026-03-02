import mayflower.*;

public class GameMayflower extends Mayflower
{
    
    /**
     * Constructor for objects of class GameMayflower
     */
    public GameMayflower()
    {
        super("Galga", 600, 800);
    }
    
    public void init()
    {
        Mayflower.setFullScreen(false);

        Mayflower.setWorld(new TitleWorld("img/tringle.png"));
    }
   

}
