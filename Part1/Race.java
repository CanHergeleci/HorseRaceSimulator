import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.ArrayList;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @version 1.0
 */
public class Race
{
    private int raceLength;
    private ArrayList<Horse> horses;
    private int numberOfLanes;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, int numberOfLanes)
    {
        if (distance < 1 || distance > 50)
        {
            throw new IllegalArgumentException("Distance must be greater than 0");
        }
        if (numberOfLanes < 1 || numberOfLanes > 10)
        {
            throw new IllegalArgumentException("Number of lanes must be greater than 0");
        }
        this.raceLength = distance;
        this.numberOfLanes = numberOfLanes;
        this.horses = new ArrayList<>();
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber < 1 || laneNumber > numberOfLanes)
        {
            throw new IllegalArgumentException("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }

        while (horses.size() < numberOfLanes)
        {
            horses.add(null);
        }

        horses.set(laneNumber - 1, theHorse);
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        boolean finished = false;
        ArrayList<Horse> winners = new ArrayList<>();
        
        for (Horse horse : horses)
        {
            if (horse != null)
            {
                horse.goBackToStart();
            }
        }
                      
        while (!finished)
        {
            for (Horse horse : horses)
            {
                if (horse != null)
                {
                    moveHorse(horse);
                }
            }

            printRace();

            winners.clear();
            
            for (Horse horse : horses)
            {
                if (horse != null && raceWonBy(horse))
                {
                    winners.add(horse);
                }
            }

            if (!winners.isEmpty())
            {
                finished = true;
                System.out.print("And the winner");
                if (winners.size() > 1)
                {
                    System.out.print("s are... ");
                }
                else
                {
                    System.out.print(" is... ");
                }

                for (int i = 0; i < winners.size(); i++)
                {
                    System.out.print(winners.get(i).getName());
                    if (i < winners.size() - 1)
                    {
                        System.out.print(", ");
                    }
                }
                System.out.println("!");
            }
           
            try{ 
                TimeUnit.MILLISECONDS.sleep(150);
            }catch(Exception e){}
        }
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() == raceLength)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window
        
        for (int i = 0; i < raceLength + 3; i++)
        {
            System.out.print("=");
        }

        System.out.println();
        
        for (int i = 0; i < numberOfLanes; i++)
        {
            printLane(horses.get(i));
            System.out.println();
        }
        
        for (int i = 0; i < raceLength + 3; i++)
        {
            System.out.print("=");
        }

        System.out.println();
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        if (theHorse == null)
        {
            System.out.print("|");
            multiplePrint(' ', raceLength);
            System.out.print(" |");
            System.out.print(" (EMPTY LANE)");
            return;
        }


        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('❌');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        if (theHorse.hasFallen())
        {
            multiplePrint(' ',spacesAfter - 1); // Consideration for emoji taking up 2 spaces
        }
        else 
        {
            multiplePrint(' ',spacesAfter);
        }
         
        //print the | for the end of the track
        System.out.print('|');
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
