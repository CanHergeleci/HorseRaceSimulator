
/**
 * This Horse class represents a horse in the race simulator.
 * It contains fields for the horse's name, symbol, distance travelled etc.
 * It also contains methods to manipulate and retrieve the horse's state.
 * 
 * @author Can Hergeleci 
 * @version 05.05.2025
 */
public class Horse
{
    //Fields of class Horse
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private double confidence;
    
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
       this.symbol = horseSymbol;
       this.name = horseName;
       this.confidence = horseConfidence;
    }
    
    //Other methods of class Horse
    public void fall()
    {
        this.hasFallen = true;
    }
    
    public double getConfidence()
    {
        return this.confidence;
    }
    
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public char getSymbol()
    {
        return this.symbol;
    }
    
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    
    public boolean hasFallen()
    {
        return this.hasFallen;
    }

    public void moveForward()
    {
        this.distanceTravelled++;
    }

    public void setConfidence(double newConfidence)
    {
        if (newConfidence <= 0 || newConfidence >= 1)
        {
            throw new IllegalArgumentException("Confidence must be between 0 and 1");
        }
        else
        {
            this.confidence = newConfidence;
        }
    }
    
    public void setSymbol(char newSymbol)
    {
        this.symbol = newSymbol;
    }
    
}
