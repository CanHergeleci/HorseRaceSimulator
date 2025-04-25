import javax.swing.*;

public class Gui {
    public static void main (String[] args)
    {
        // Create a JFrame
        JFrame frame = new JFrame("Horse Race Track");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        // Race config tab
        tabbedPane.addTab("Race Configuration", RaceConfigPanel());

        // Race tab
        tabbedPane.addTab("Ongoing Race", RacePanel());

        // Statistics & Analytics tab
        tabbedPane.addTab("Statistics", StatisticsPanel());

        // Betting tab
        tabbedPane.addTab("Betting", BettingPanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}
