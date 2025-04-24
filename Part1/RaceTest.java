public class RaceTest 
{
    public static void main(String[] args)
    {
        Race race = new Race(20, 5);
        race.addHorse(new Horse('♘', "QueenMary Horse", 0.8), 1);
        race.addHorse(new Horse('♞', "KingHorse", 0.7), 2);
        race.addHorse(new Horse('0', "FastHorse", 0.5), 3);
        race.startRace();
    }
}