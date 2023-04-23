package maze;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Player;
import entity.monster.Skeleton;
import entity.monster.Zombie;
import objects.SuperObject;
import entity.Lava;

/**
 * The MazeBoard class encapsulates the whole game display,
 * including the character, enemies, objects, etc. It extends
 * the JPanel class and implements the Runnable interface, which
 * includes methods like run, update, and repaint.
 */

public class MazeBoard extends JPanel implements Runnable {

    // Screen Constants
    public static final int ROWS = 14;
    public static final int COLUMNS = 22;
    public static final int CELL_SIZE = 48;

    public static final int SCREEN_WIDTH = CELL_SIZE * COLUMNS;
    public static final int SCREEN_HEIGHT = CELL_SIZE * ROWS;

    // Collision verifier object
    public CollisionChecker cChecker = new CollisionChecker(this);

    // Entities and objects setter
    public ObjectSetter objectSetter = new ObjectSetter(this);

    // World Settings
    public static final int MAX_WORLD_COLUMNS = 50;
    public static final int MAX_WORLD_ROWS = 50;
    public final int worldWidth = CELL_SIZE * MAX_WORLD_COLUMNS;
    public final int worldHeight = CELL_SIZE * MAX_WORLD_ROWS;

    // Graphics Quality
    public static final int FPS = 60;
    public static final double DRAW_INTERVAL = 1e9 / FPS;

    public final CellManager cellManager;

    // CellEntities objects
    public final Player player;
    public final Skeleton[] monster;
    public final Zombie[] zombie;
    public final Lava[] lava;

    // Objects
    public SuperObject obj[] = new SuperObject[10];

    // Sound
    Sound sound = new Sound();
    public final Thread gameThread;

    // Timer
    Timer timer;
    int second;
    int minute;

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int overState = 3;

    /**
     * Constructs the MazeBoard object and initializes the game.
     */
    public MazeBoard() {
        // Timer
        second = 0;
        minute = 0;
        simpleTimer();
        timer.start();
        // Map basics
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.LIGHT_GRAY);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        JLabel jlbl = new JLabel("Question");
        this.add(jlbl);
        jlbl.setBounds(100, 100, 100, 100);

        KeyHandler keyHandler = new KeyHandler(this);

        // Map initialization
        cellManager = new CellManager();

        // Player initialization
        player = new Player(this, keyHandler);

        // Monster initialization
        monster = new Skeleton[3];
        for (int i = 0; i < 3; i++) {
            monster[i] = new Skeleton(this);
        }
        zombie = new Zombie[3];
        for (int i = 0; i < 3; i++) {
            zombie[i] = new Zombie(this);
        }
        lava = new Lava[6];
        for (int i = 0; i < 3; i++) {
            lava[i] = new Lava(this, 40 + i, 21);
        }
        for (int i = 3; i < 6; i++) {
            lava[i] = new Lava(this, 40, 40 - i);
        }
        gameThread = new Thread(this);
        gameThread.start();

        this.addKeyListener(keyHandler);

        setGame();

    }

    /**
     * Starts the game loop and handles the game update cycle.
     */
    @Override
    public void run() {
        System.out.println("Game Loop Alive.\n");

        long lastTime = System.nanoTime();
        double delta = 0;
        double nextDrawTime = System.nanoTime() + DRAW_INTERVAL;

        while (gameThread.isAlive()) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / DRAW_INTERVAL;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            try {
                double remainingTime = nextDrawTime - currentTime;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) (remainingTime / 1e6));
                nextDrawTime += DRAW_INTERVAL;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Updates the game state, checking for collisions and updating entities.
     */
    public void update() {
        if (gameState == playState) {
            if (player.getPoints() < 0) {
                player.setPoints(0);
                player.setAlive(false);
            }
            // Check player collision with enemies
            if (cChecker.isPlayerOnEnemy(player))
                player.setAlive(false);
            if (player.isAlive())
                player.update();
            if (cChecker.isLava(player))
                player.setPoints(player.getPoints() - 0.05);

            // Update enemies
            for (int i = 0; i < 3; i++) {
                monster[i].update();
            }
            for (int i = 0; i < 3; i++) {
                zombie[i].update();
            }
            for (int i = 0; i < 3; i++) {
                lava[i].update();
            }
        } else {
        }
    }

    /**
     * Creates and initializes a simple timer.
     */
    public void simpleTimer() {
        // Timer constructor
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second++;
            }
        });
    }

    /**
     * Sets up the game by creating object (rewards) instances and initializing the
     * enemies.
     */
    public void setGame() {
        // Creates object (rewards) instances
        objectSetter.setObject();
        objectSetter.setEnemies();
        playMusic(0, true);
        gameState = titleState;
    }

    /**
     * Paints the game components on the screen, including the map, objects,
     * characters, and messages.
     *
     * @param graphics The Graphics object used to draw the game components.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        graphics2D.setColor(Color.WHITE);

        if (gameState == titleState) {
            cellManager.draw(graphics2D, this);
            drawTitleScreen(graphics2D);
            graphics2D.dispose();
        } else {
            // Draws map
            cellManager.draw(graphics2D, this);

            // OBJECT
            for (SuperObject superObject : obj) {
                if (superObject != null) {
                    superObject.draw(graphics2D, this);
                }
            }
            // Draws monsters
            for (int i = 0; i < 6; i++) {
                lava[i].draw(graphics2D, this);
            }
            for (int i = 0; i < 3; i++) {
                monster[i].draw(graphics2D, this);
            }
            for (int i = 0; i < 3; i++) {
                zombie[i].draw(graphics2D, this);
            }

            // Draw borders of the map
            cellManager.drawNonPlayableArea(graphics2D);
            player.draw(graphics2D);

            if (gameState == pauseState) {
                drawPauseScreen(graphics2D);
            } else {
                // draw score
                graphics2D.drawString("SCORE: " + (int) player.getPoints(), 0, 40);

                if (!player.isAlive()) {
                    String message = "GAME OVER";
                    graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 75));
                    graphics2D.setColor(Color.RED);

                    // center message on screen
                    // Source:
                    // https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
                    FontMetrics metrics = graphics2D.getFontMetrics();
                    int x = (SCREEN_WIDTH - metrics.stringWidth(message)) / 2;
                    int y = SCREEN_HEIGHT / 2;

                    graphics2D.drawString("GAME OVER", x, y);
                    gameState = overState;
                }
                if (player.getPoints() >= 100 && player.isAlive()) {
                    if (!player.hasWon()) {
                        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 75));
                        FontMetrics metrics = graphics2D.getFontMetrics();
                        int x = (SCREEN_WIDTH - metrics.stringWidth("Find the door")) / 2;
                        int y = SCREEN_HEIGHT / 2;
                        graphics2D.drawString("Find the door", x, y);
                        if (cChecker.checkdoor(player)) {
                            player.setWin(true);
                        }
                    } else {
                        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 75));
                        FontMetrics metrics = graphics2D.getFontMetrics();
                        int x = (SCREEN_WIDTH - metrics.stringWidth("You won")) / 2;
                        int y = SCREEN_HEIGHT / 2;
                        graphics2D.drawString("You won", x, y);
                        gameState = overState;
                    }

                }
                // Timer
                if (player.isAlive() && player.getPoints() < 100 && gameState != pauseState) {
                    int x = SCREEN_WIDTH - 220;
                    int y = SCREEN_HEIGHT - 40;
                    graphics2D.drawString("Time: " + Integer.toString(second), x, y);
                }
                graphics2D.dispose();
            }
        }

    }

    /**
     * Draws the title screen for the game.
     *
     * @param g2 The Graphics2D object used to draw the title screen.
     */
    public void drawTitleScreen(Graphics2D g2) {
        g2.setColor(new Color(59, 44, 43));
        g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g2.setFont(getFont().deriveFont(Font.BOLD, 80F));
        String text = "Steve's Trippy Adventure";
        int x = getXforCenteredText(text, g2);
        int y = CELL_SIZE * 3;

        // Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Draw Character
        x = SCREEN_WIDTH / 2 - (CELL_SIZE * 2) / 2;
        y += CELL_SIZE * 3;
        g2.drawImage(player.down1, x, y, CELL_SIZE * 2, CELL_SIZE * 2, null);

        // Draw menu
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 48F));

        text = "PRESS ENTER TO START";
        x = getXforCenteredText(text, g2);
        y += CELL_SIZE * 5;
        g2.drawString(text, x, y);

    }

    /**
     * Draws the pause screen for the game.
     *
     * @param g2 The Graphics2D object used to draw the pause screen.
     */
    public void drawPauseScreen(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Paused";
        int x = getXforCenteredText(text, g2);
        int y = SCREEN_HEIGHT / 2;
        g2.drawString(text, x, y);
    }

    /**
     * Calculates the x-coordinate for centering the text within the screen.
     *
     * @param text The text to be centered.
     * @param g2   The Graphics2D object used to measure the text width.
     * @return The x-coordinate for centering the text within the screen.
     */
    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = SCREEN_WIDTH / 2 - length / 2;
        return x;
    }

    /**
     * Plays the game music or sound effects.
     *
     * @param i Index of the sound file to play.
     * @param b If true, loops the music; if false, plays it only once.
     */
    public void playMusic(int i, boolean b) {
        sound.setFile(i);
        sound.play();
        if (b) {
            sound.loop();
        }
    }

    /**
     * Stops the game music.
     */
    public void stopMusic() {
        sound.stop();
    }

    /**
     * Plays a sound effect.
     *
     * @param i Index of the sound effect file to play.
     */
    public void playSE(int i) {
        sound.setFile(i);
        sound.play();

    }
}
