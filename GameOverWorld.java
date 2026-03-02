/**
 * Creates the game over screen
 *
 * @Esteban M
 * @version (a version number or a date)
 */
import mayflower.*;

public class GameOverWorld extends World
{
    private int finalScore;
    private int finalLevel;
    private boolean win;
    private String skin;
    
    private NextWorldButton homeButton;
    
    public GameOverWorld(int score, int level, boolean result, String s)
    {
        setBackground("img/backgrounds/background1.jpg");
        
        homeButton = new NextWorldButton("img/button.png", 200, 100, false);
        skin = s;
        
        addObject(homeButton, 200, 600);
        
        finalScore = score;
        finalLevel = level;
        win = result;
    }
    
    public void act() 
    {
        if(win) {
            showText("You Win!", 75, 90, 200, Color.WHITE);
        } else {
            showText("You Lose!", 75, 100, 200, Color.WHITE);
        }
        showText("Level: " + finalLevel, 50, 170, 400, Color.WHITE);
        showText("Score: " + finalScore, 50, 150, 500, Color.WHITE);
        showText("Home", 30, 260, 660, Color.WHITE);
        
        if(Mayflower.mouseClicked(homeButton) && !homeButton.isLocked()) {
            Mayflower.setWorld(new TitleWorld(skin));
        }
    }
}
