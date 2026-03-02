/**
 * Write a description of class Player here.
 *
 * @Andrew W, Dylan D, Esteban M
 * @version (a version number or a date)
 */
import mayflower.*;
import java.util.*;

public class InfiniteLevelWorld extends World
{
    private int score;
    private int lives;
    private int level;
    private boolean isGameOver;
    private boolean isLevelOver;
    
    private String skin;

    private Player player;

    private Stack<Heart> hearts;
    private Heart heart1;
    private Heart heart2;
    private Heart heart3;

    private ArrayList<EnemyShip> spawnLeft;
    private ArrayList<EnemyShip> spawnTop;
    private ArrayList<EnemyShip> spawnRight;

    private ArrayList<EnemyShip> allEnemyShips;
    public InfiniteLevelWorld(String s)
    {
        // Set primitive variables
        score = 0;
        lives = 3;
        level = 1;
        isGameOver = false;
        isLevelOver = false;

        allEnemyShips = new ArrayList<EnemyShip>();
        
        // Set Player
        skin = s;
        player = new Player(skin);

        // Set Heart variables
        heart1 = new Heart();
        heart2 = new Heart();
        heart3 = new Heart();

        hearts = new Stack<Heart>();
        hearts.push(heart1);
        hearts.push(heart2);
        hearts.push(heart3);

        // Set Background
        setBackground("img/backgrounds/background1.jpg");

        // Add Objects
        addObject(player, 100, 650);

        addObject(heart1, 550,750);
        addObject(heart2, 525,750);
        addObject(heart3, 500,750);    

        spawnEnemies(level);
    }

    public void updateText() {
        showText(" Level: " + level + " Score: " + score, 10, 30, Color.WHITE);
    }

    //takes away a life and some points when the player is shot
    public void updateLives() {
        lives = lives - 1;
        if(hearts.size() != 0) {
            removeObject(hearts.pop());
        }
        score = score - 1000;
    }

    public void addLives(Heart heart) {
        lives++;
        addObject(heart, 550 - hearts.size() * 25, 750);
        hearts.push(heart);
    }

    public void updateScore() {
        score = score + 500;
    }

    public boolean checkLevelOver() {
        return getObjects(EnemyShip.class).size() == 0;
    }

    public void spawnEnemy(int i, int j) {
        int ranx = (int)(Math.random() * 1800) - 600;
        int rany = (int)(Math.random() * 400);
        if(ranx < -50) {
            ranx = -100;
        } else if (ranx > 600) {
            ranx = 600;
        } else {
            rany = -100;
        }
        EnemyShip e = new EnemyShip(ranx, rany, i*50 + 75, 50*j + 50);
        addObject(e, ranx, rany);
        allEnemyShips.add(e);
    }
    
    //what does this do
    public void spawnEnemies(int currentLevel) 
    {
        int enemyNumber = (int)(currentLevel * 1.25);
        int[][] grid = new int[7][7];
        
        if(currentLevel < 10) {
            for(int i = 0; i < currentLevel; i++) {
                spawnEnemy(i, 0);
            }
        }
        
        if(currentLevel % 10 == 0)
        {
            EnemyBoss e = new EnemyBoss(300, 0, 300, 200, currentLevel);
            addObject(e, 300, 0);
            allEnemyShips.add(e);
        }
        
        if (currentLevel < 40 && currentLevel % 10 != 0) {
            for(int i = 0; i < currentLevel / 7; i++) {
                for(int j = 0; j < 7; j++) {
                    spawnEnemy(j, i);
                }
            }
        }
    }
    public void removeShip(EnemyShip e)
    {
        allEnemyShips.remove(e);
    }
    
    //idk what to write here
    public boolean checkFormation()
    {
        boolean inPlace = true;
        for(EnemyShip a: allEnemyShips)
        {
            if(a.checkPosition() == false)
            {
                return false;
            }
        }
        for(int i = 0; i < allEnemyShips.size(); i++)
        {
            if(allEnemyShips.get(i).checkPosition() == false)
            {
                allEnemyShips.get(i).readySignal();
            }
        }
        return inPlace;
    }
    
    public void act() 
    {        
        if(lives == 0 || level == 40) {
            Mayflower.setWorld(new GameOverWorld(score, level, level == 40, skin));
        }

        updateText();

        if(checkLevelOver() && level < 40) {
            level++;
            spawnEnemies(level);
        }

    }

}
