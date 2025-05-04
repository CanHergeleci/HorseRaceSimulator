import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class Gui {
    public static void main (String[] args)
    {
        // Create a JFrame
        JFrame frame = new JFrame("Horse Race Track");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 750);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        // Race config tab
        tabbedPane.addTab("Race Configuration", RaceConfigPanel());

        // Horse config tab
        tabbedPane.addTab("Horse Configuration", HorseConfigPanel());

        // Statistics & Analytics tab
        tabbedPane.addTab("Statistics", StatisticsPanel());

        // Race tab
        tabbedPane.addTab("Ongoing Race", RacePanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel RacePanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea raceDisplay = new JTextArea();
        raceDisplay.setEditable(false);

        JButton start = new JButton("Start Race");
        panel.add(start, BorderLayout.NORTH);
        panel.add(raceDisplay, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel StatisticsPanel ()
    {
        JPanel panel = new JPanel();

        // Table at the top
        String[] columnNames = {"Horse Name", "Average Speed", "Finishing Time", "Win Ratio", "Confidence Change (%)"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 1);
        JTable table = new JTable(model);
        table.setLocation(15, 015);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new java.awt.Dimension(650, 100));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Centre
        JLabel label = new JLabel("18.2 Seconds");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(createSquare("Best Time For Current Track", label, 0));

        return panel;
    }

    private static JPanel HorseConfigPanel ()
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Top left corner
        panel.add(createSquare("Horse Breed:", new JComboBox<>(new String[]{"Thoroughbred", "Arabian", "Quarter Horse"}), 0));

        // Top middle
        panel.add(createSquare("Coat Colour:", new JComboBox<>(new String[]{"Brown", "Blonde", "Ginger"}), 1));

        // Top right corner
        JTextField tf = new JTextField();
        tf.setPreferredSize(new java.awt.Dimension(30, 20));
        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.add(tf);
        textPanel.setBackground(Color.LIGHT_GRAY);
        panel.add(createSquare("Symbol Representation:", textPanel, 0));

        // Bottom left corner
        JPanel equipment = new JPanel(new GridLayout(6, 1, 0, 5));
        equipment.add(new JLabel("Saddle"));
        equipment.add(new JComboBox<>(new String[]{"Red", "Blue", "Yellow"}));
        equipment.add(new JLabel("Horseshoes"));
        equipment.add(new JComboBox<>(new String[]{"Leightweight", "Regular", "Heavy"}));
        equipment.add(new JLabel("Blankets"));
        equipment.add(new JComboBox<>(new String[]{"Red", "Blue", "Yellow"}));
        equipment.setBackground(Color.GRAY);
        panel.add(createSquare("Equipment:", equipment, 1));

        // Bottom middle
        JTextField name = new JTextField();
        name.setPreferredSize(new java.awt.Dimension(150, 20));
        JPanel namePanel = new JPanel(new GridBagLayout());
        namePanel.add(name);
        namePanel.setBackground(Color.LIGHT_GRAY);
        panel.add(createSquare("Horse Name:", namePanel, 0));

        // Bottom Right corner
        JButton createHorse = new JButton("Create Horse");
        panel.add(createSquare("Create Horse", createHorse, 1));

        return panel;
    }

    private static JPanel RaceConfigPanel ()
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        // Top left corner
        JSlider LaneCountSlider = new JSlider(2, 10, 5);
        LaneCountSlider.setPaintLabels(true);
        LaneCountSlider.setPaintTicks(true);
        LaneCountSlider.setMajorTickSpacing(1);
        LaneCountSlider.setSnapToTicks(true);
        panel.add(createSquare("Lane Count:", LaneCountSlider, 1));

        // Top right corner
        JSlider TrackLengthSlider = new JSlider(20, 100, 50);
        TrackLengthSlider.setPaintLabels(true);
        TrackLengthSlider.setPaintTicks(true);
        TrackLengthSlider.setMajorTickSpacing(10);
        TrackLengthSlider.setSnapToTicks(true);
        panel.add(createSquare("Track Length:", TrackLengthSlider, 0));

        // Bottom left corner
        panel.add(createSquare("Track Type:", new JComboBox<>(new String[]{"Straight", "Oval", "Figure 8"}), 0));

        // Bottom right corner
        panel.add(createSquare("Track Condition:", new JComboBox<>(new String[]{"Dry", "Wet", "Icy"}), 1));

        return panel;
    }

    private static JPanel createSquare (String label, JComponent component, int colour)
    {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel Label = new JLabel(label, SwingConstants.CENTER);
        Label.setForeground(Color.white);
        Label.setFont(new Font("Monospaced", Font.BOLD, 20));

        panel.add(Label, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        if (colour == 0)
        {
            panel.setBackground(Color.LIGHT_GRAY);
        }
        else
        {
            panel.setBackground(Color.GRAY);
        }

        return panel;
    }
}
