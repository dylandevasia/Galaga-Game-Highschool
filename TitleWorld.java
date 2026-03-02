/**
 * Write a description of class Player here.
 *
 * @Esteban M
 * @version (a version number or a date)
 */
import mayflower.*;

public class TitleWorld extends World
{
    
    private NextWorldButton shipSelectButton;
    private NextWorldButton infiniteWorldButton;
   
    private String skin;
    
    public TitleWorld(String s) {
        setBackground("img/backgrounds/background1.jpg");
        
        showText("GALAGA", 100, 100, 200, Color.WHITE);

        skin = s;
        
        infiniteWorldButton = new NextWorldButton("img/button.png", 200, 100, false);
        shipSelectButton = new NextWorldButton("img/button.png", 200, 100, false);
        

        addObject(infiniteWorldButton, 200, 300);
        addObject(shipSelectButton, 200, 500);
        
        showText("Play", 30, 270, 360, Color.WHITE);
        showText("Select Ship", 30, 220, 560, Color.WHITE);
        
    }
    
    
    
    public void act() {
        if(Mayflower.mouseClicked(shipSelectButton) && !shipSelectButton.isLocked()) {
            Mayflower.setWorld(new ShipSelectWorld(skin));
        }
        if(Mayflower.mouseClicked(infiniteWorldButton)) {
            Mayflower.setWorld(new InfiniteLevelWorld(skin));
        }
    }
}

