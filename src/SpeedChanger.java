import java.util.Random;

public class SpeedChanger implements Runnable
{
    private Opponent opponent;
    private Random rnd = new Random();
    private boolean isThreadActive;

    public SpeedChanger(Opponent opponent, boolean isThreadActive)
    {
        this.isThreadActive = isThreadActive;
        this.opponent = opponent;
    }

    @Override
    public void run()
    {
        int randNum = rnd.nextInt(100) + 1;

        if (randNum >= 10)
        {
            opponent.setSpeed(3);
        }
        else
        {
            opponent.setSpeed(2);
        }

        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        opponent.setIsThreadActive(false);
    }
}
