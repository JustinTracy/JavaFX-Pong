package game.threads;

import game.Ball;
import view.GameView;
import view.TwoPlayerGame;

import java.util.Random;

public class NewPoint implements Runnable
{
    private Ball ball;
    private GameView gameView;

    int randNum;

    private TwoPlayerGame twoPlayerGame;

    private int directionX;
    private int directionY;

    boolean multiPlayer = false;

    public NewPoint(Ball ball, GameView gameView)
    {
        this.ball = ball;
        this.gameView = gameView;
    }

    public NewPoint(Ball ball, TwoPlayerGame twoPlayerGame, boolean multiPlayer)
    {
        this.multiPlayer = multiPlayer;
        this.ball = ball;
        this.twoPlayerGame = twoPlayerGame;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        Random rnd = new Random();
        randNum = rnd.nextInt(100) + 1;

        if (randNum <= 25)
        {
            directionX = 1;
            directionY = 1;
        }
        else if (randNum > 25 && randNum <= 50)
        {
            directionX = -1;
            directionY = 1;
        }
        else if (randNum > 50 && randNum <= 75)
        {
            directionX = -1;
            directionY = -1;
        }
        else
        {
            directionX = 1;
            directionY = -1;
        }

        ball.setXSpeed(10 * directionX);
        ball.setYSpeed(8 * directionY);
        if (!multiPlayer)
        {
            gameView.setCooldown(false);
        }
        else
        {
            twoPlayerGame.setCooldown(false);
        }
    }
}
