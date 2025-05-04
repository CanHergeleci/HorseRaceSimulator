import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Gui {

    private static ArrayList<Horse> horses = new ArrayList<>();
    private static JButton start;
    private static JTable raceTable;
    private static DefaultTableModel raceTableModel;
    public static void main (String[] args)
    {
        // Create a JFrame
        JFrame frame = new JFrame("Horse Race Track");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 750);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        // Race tab
        tabbedPane.addTab("Ongoing Race", RacePanel());

        // Race config tab
        tabbedPane.addTab("Race Configuration", RaceConfigPanel());

        // Horse config tab
        tabbedPane.addTab("Horse Configuration", HorseConfigPanel());

        // Statistics & Analytics tab
        tabbedPane.addTab("Statistics", StatisticsPanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel RacePanel()
    {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {""};
        raceTableModel = new DefaultTableModel(columnNames, 0);
        raceTable = new JTable(raceTableModel);
        raceTable.setFont(new Font("Monospaced", Font.PLAIN, 14));
        raceTable.setRowHeight(22);
        raceTable.setShowGrid(false);
        raceTable.setTableHeader(null);
        JScrollPane scrollPane = new JScrollPane(raceTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        start = new JButton("Start Race");
        panel.add(start, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

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
        JComboBox<String> breed = new JComboBox<>(new String[]{"Thoroughbred", "Arabian", "Quarter Horse"});
        panel.add(createSquare("Horse Breed:", breed, 0));

        // Top middle
        JComboBox<String> colour = new JComboBox<>(new String[]{"Brown", "Blonde", "Ginger"});
        panel.add(createSquare("Coat Colour:", colour, 1));

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
        JComboBox<String> saddle = new JComboBox<>(new String[]{"Red", "Blue", "Yellow"});
        equipment.add(saddle);
        equipment.add(new JLabel("Horseshoes"));
        JComboBox<String> shoe = new JComboBox<>(new String[]{"Lightweight", "Regular", "Heavy"});
        equipment.add(shoe);
        equipment.add(new JLabel("Blankets"));
        JComboBox<String> blanket = new JComboBox<>(new String[]{"Red", "Blue", "Yellow"});
        equipment.add(blanket);
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

        // Create Horse object
        createHorse.addActionListener(e -> {
            
            // Limit number of horses to 10
            if (horses.size() >= 10)
            {
                JOptionPane.showMessageDialog(panel, "Maximum of 10 horses reached.");
                return;
            }

            // Convert string into char
            String text = tf.getText();
            if (text.length() != 1 || text.codePointCount(0, text.length()) != 1) {
                JOptionPane.showMessageDialog(panel, "Please enter a single character symbol (most emojis are not supported).");
                return;
            }
            char symbol = text.charAt(0);

            if (symbol == ' ' || name.getText().equals(""))
            {
                JOptionPane.showMessageDialog(panel, "Please ensure all fields are filled before clicking create.");
                return;
            }

            Horse horse = new Horse(symbol, name.getText(), Math.random());
            horses.add(horse);
            JOptionPane.showMessageDialog(panel, "Horse added.");

            // Get horse configuration and apply changes to confidence
            String selectedBreed = (String) breed.getSelectedItem();
            String selectedColour = (String) colour.getSelectedItem();
            String selectedBlanket = (String) blanket.getSelectedItem();
            String selectedSaddle = (String) saddle.getSelectedItem();
            String selectedShoe = (String) shoe.getSelectedItem();

            // Adjust confidence which impacts 'speed' and 'fall rate'
            if (selectedBreed.equals("Thoroughbred"))
            {
                horse.setConfidence(horse.getConfidence() * 1.2); //Increases confidence which increases the chance of moving forward aka speed but also increases change of falling
            }
            else if (selectedBreed.equals("Arabian"))
            {
                horse.setConfidence(horse.getConfidence() * 1.05);
            }
            else if (selectedBreed.equals("Quarter Horse"))
            {
                horse.setConfidence(horse.getConfidence() * 0.8);
            }

            // Smaller impact on confidence compared to breed
            if (selectedColour.equals("Brown"))
            {
                horse.setConfidence(horse.getConfidence() * 1.1); 
            }
            else if (selectedColour.equals("Ginger"))
            {
                horse.setConfidence(horse.getConfidence() * 1.05);
            }
            else if (selectedColour.equals("Blonde"))
            {
                horse.setConfidence(horse.getConfidence() * 0.9);
            }

            // Smaller impact on confidence comapred to colour
            if (selectedBlanket.equals("Red"))
            {
                horse.setConfidence(horse.getConfidence() * 1.05); 
            }
            else if (selectedBlanket.equals("Blue"))
            {
                // No change in confidence
            }
            else if (selectedBlanket.equals("Yellow"))
            {
                horse.setConfidence(horse.getConfidence() * 0.95);
            }

            // Same impact on confidence comapred to blanket
            if (selectedSaddle.equals("Red"))
            {
                horse.setConfidence(horse.getConfidence() * 1.05); 
            }
            else if (selectedSaddle.equals("Blue"))
            {
                // No change in confidence
            }
            else if (selectedSaddle.equals("Yellow"))
            {
                horse.setConfidence(horse.getConfidence() * 0.95);
            }

            // Slightly more impact on confidence compared to blanket and saddle
            if (selectedShoe.equals("Lightweight"))
            {
                horse.setConfidence(horse.getConfidence() * 1.075); 
            }
            else if (selectedShoe.equals("Regular"))
            {
                // No change in confidence
            }
            else if (selectedShoe.equals("Heavy"))
            {
                horse.setConfidence(horse.getConfidence() * 0.925);
            }
        
        });

        return panel;
    }

    private static JPanel RaceConfigPanel ()
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        // Top left corner
        JSlider laneCountSlider = new JSlider(2, 10, 5);
        laneCountSlider.setPaintLabels(true);
        laneCountSlider.setPaintTicks(true);
        laneCountSlider.setMajorTickSpacing(1);
        laneCountSlider.setSnapToTicks(true);
        panel.add(createSquare("Lane Count:", laneCountSlider, 1));

        // Top right corner
        JSlider trackLengthSlider = new JSlider(20, 100, 50);
        trackLengthSlider.setPaintLabels(true);
        trackLengthSlider.setPaintTicks(true);
        trackLengthSlider.setMajorTickSpacing(10);
        trackLengthSlider.setSnapToTicks(true);
        panel.add(createSquare("Track Length:", trackLengthSlider, 0));

        // Bottom left corner
        JComboBox<String> raceTrack = new JComboBox<>(new String[]{"Straight", "Oval", "Figure 8"});
        panel.add(createSquare("Track Type:", raceTrack, 0));

        // Bottom right corner
        JComboBox<String> weather = new JComboBox<>(new String[]{"Dry", "Wet", "Icy"});
        panel.add(createSquare("Track Condition:", weather, 1));

        start.addActionListener(e -> {

            String weatherSelected = (String) weather.getSelectedItem();

            Race race = new Race(trackLengthSlider.getValue(), laneCountSlider.getValue());
            for (Horse horse : horses)
            {
                race.addHorse(horse, laneCountSlider.getValue());
                if (weatherSelected.equals("Wet"))
                {
                    horse.setConfidence(horse.getConfidence() * 1.05);
                }
                else if (weatherSelected.equals("Icy"))
                {
                    horse.setConfidence(horse.getConfidence() * 1.1);
                }
                else if (weatherSelected.equals("Dry"))
                {
                    horse.setConfidence(horse.getConfidence() * 0.95);
                }
            }

            if (horses.isEmpty())
            {
                JOptionPane.showMessageDialog(panel, "You have not added any horses, please go to Horse Configuration Panel and add a Horse.");
                return;
            }

            JOptionPane.showMessageDialog(panel, "Race is being generated. Please wait for results to be displyed.");

            // Redirect text from terminal to gui this allows part 1 to work independently to part 2
            try {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(output));

            race.startRace();

            System.setOut(originalOut);

            String raceOutput = output.toString();
            String[] lines = raceOutput.split("\n");
            raceTableModel.setRowCount(0); 
            
            for (String line : lines) {
                raceTableModel.addRow(new Object[]{line});
            }
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error displaying race: " + ex.getMessage());
        }
    });

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
