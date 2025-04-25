import javax.swing.*;
import java.awt.*;

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

        // Horse config tab
        tabbedPane.addTab("Horse Configuration", HorseConfigPanel());

        // Race tab
        tabbedPane.addTab("Ongoing Race", RacePanel());

        // Statistics & Analytics tab
        tabbedPane.addTab("Statistics", StatisticsPanel());

        // Betting tab
        tabbedPane.addTab("Betting", BettingPanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel RaceConfigPanel ()
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        // Top left corner
        panel.add(createSquare("Lane Count:",new JSlider(1, 10, 5)));

        // Top right corner
        panel.add(createSquare("Track Length:",new JSlider(20, 100, 50)));

        // Bottom left corner
        panel.add(createSquare("Track Type:",new JComboBox<>(new String[]{"Straight", "Oval", "Figure 8"})));

        // Bottom right corner
        panel.add(createSquare("Track Condition:",new JComboBox<>(new String[]{"Dry", "Wet", "Icy"})));
        
        return panel;
    }
}
