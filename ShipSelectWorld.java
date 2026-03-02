/**
 * Creates the ship selection world
 *
 * @Esteban M
 * @version (a version number or a date)
 */
import mayflower.*;

public class ShipSelectWorld extends World
{
    private NextWorldButton mainPageButton;
    
    private NextWorldButton tringleSkin;
    private NextWorldButton shipSkin;
    
    private String skin;
    
    public ShipSelectWorld(String s)
    {
        setBackground("img/backgrounds/background1.jpg");
        
        skin = s;
        
        mainPageButton = new NextWorldButton("img/button.png", 200, 100, false);
        tringleSkin = new NextWorldButton("img/tringle.png", 100, 100, false);
        shipSkin = new NextWorldButton("img/spaceship.png", 100, 100, false);
        
        addObject(mainPageButton, 200, 500);
        addObject(tringleSkin, 250, 100);
        addObject(shipSkin, 250, 250);
        
    }

    
    
    public void act()
    {
        if(Mayflower.mouseClicked(mainPageButton) && !mainPageButton.isLocked()) {
            Mayflower.setWorld(new TitleWorld(skin));
        }
        
        if(Mayflower.mouseClicked(tringleSkin)) {
            skin = "img/tringle.png";
        }
        
        if(Mayflower.mouseClicked(shipSkin)) {
            skin = "img/spaceship.png";
        }
        
        showText("Home", 30, 260, 560, Color.WHITE);
    }
}
 