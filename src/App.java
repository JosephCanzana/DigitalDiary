import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class App extends JFrame{

    // JFrame main panels
    JPanel navigationBar;
    JPanel mainContentPanel;
    JPanel footerPanel;

    // Navbar Buttons
    JButton btnHome;
    JButton btnJournal;
    JButton btnInspire;
    JButton btnAbout;
    JButton btnLogout;

    // Panel inside main content panel(card layouts)
    JPanel plHome;
    JPanel plJournal;
    JPanel plInspire;
    JPanel plAbout;

    // Journal entries buttons/list that need to monitor action
    JList<String> entryList;
    JButton btnNew;
    JButton btnUpdate;
    JButton btnDelete;
    JTextArea txtContent;

    // Base Backgrounds
    Color bgMain = Color.white;
    Color bgSecondary = new Color(0xFFF2F2F7);
    Color bgTertiary = new Color(0xFFE5E5EA);

    // Text Colors
    Color textPrimary = new Color(0xFF1C1C1E);
    Color textSecondary = new Color(0x333333);


    // Arrays that would be in use globally (Sample data)
    String[] titles = {
            "2025-05-07",
            "2025-05-06",
            "2025-05-05"
    };

    String[] contents = {
            "Today I learned how to connect a JList to a JTextArea using arrays.",
            "Started designing the journal UI with simple components.",
            "Practiced using arrays and selection listeners in Java Swing."
    };

    // Quotes
    String[] homeQuote = {
            "God loved the world and gave His only Son, so whoever believes won't perish but have eternal life. - John 3:16",
            "The world's beauty is in the eyes of it's beholder - Sachi",
            "I can do all things through Christ who strengthens me. - Philippians 4:13",
            "Trust the Lord with all your heart, submit to Him, and He will guide your paths. Proverbs 3:5-6",
            "I know the plans I have for you—plans to prosper you and give you hope and a future. - Jeremiah 29:11",
            "You lose because you only consider victory - Miyagi",

    };

    String[] qtGrateful = {
            "Give thanks in all circumstances; for this is God's will for you in Christ Jesus.\n– 1 Thessalonians 5:18",
    };

    String[] qtCalm = {
            "Trust in the Lord with all your heart and lean not on your own understanding.\n– Proverbs 3:5",
    };

    String[] qtHopeful = {
            "For I know the plans I have for you... plans to give you hope and a future.\n– Jeremiah 29:11",

    };

    String[] qtOverthinking = {
            "It’s not about winning or losing. It’s about the effort you put in.\n– Ippo Makunouchi, Hajime no Ippo",
    };

    String[] qtDown = {
            "Everything negative—pressure, challenges—is all an opportunity for me to rise.\n– Kobe Bryant",
    };

    String[] qtOverwhelmed = {
            "Great things come from hard work and perseverance. No excuses.\n– Kobe Bryant",
    };



    // User's data
    int userID = Login.indexID;
    String User = Login.currentUser;

    // MAIN SETUP
    App(){
        // Basic setup the JFrame
        setSize(1150,710);
        setMinimumSize(new Dimension(1150,710));
        getContentPane().setBackground(bgMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ReflectNote: Your mind, beautifully organized.");

        // Set the layout to border type with gap
        setLayout(new BorderLayout(5,5));

        // Creating the panel
        navigationBar = new JPanel();
        mainContentPanel = new JPanel(new CardLayout());
        footerPanel = new JPanel();

        // Set Color to the panel
        navigationBar.setBackground(bgSecondary);
        mainContentPanel.setBackground(bgMain);
        footerPanel.setBackground(bgTertiary);

        // Set Size
        navigationBar.setPreferredSize(new Dimension(100, 65));
        mainContentPanel.setPreferredSize(new Dimension(300, 450));
        footerPanel.setPreferredSize(new Dimension(50, 50));

        // Adding and setting panel border location
        add(navigationBar, BorderLayout.NORTH);
        add(mainContentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Initialize the panels for main content panel
        homeLayout();
        journalLayout();
        inspireLayout();
        aboutLayout();

        // Panel setup
        setNavigationBar();

        setLocationRelativeTo(null); // For Centering
        setVisible(true); // Set the Frame visible
    }


    // NAVIGATION BAR TOP PANEL SETUP
    private void setNavigationBar(){
        // Set navigation bar layout to FlowLayout (left aligned)
        navigationBar.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 10));

        // Create the title
        JLabel title = new JLabel("ReflectNote ||  ");
        title.setForeground(textPrimary);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));

        // Create the buttons
        btnHome = createNavButton("Home", Color.DARK_GRAY);
        btnJournal = createNavButton("Journal", Color.DARK_GRAY);
        btnInspire = createNavButton("Inspire", Color.DARK_GRAY);
        btnAbout = createNavButton("About", Color.GRAY);
        btnLogout = createNavButton("Logout", Color.RED);

        // Calling button action functionality
        btnHomeAction();
        btnJournalAction();
        btnInspireAction();
        btnAboutAction();
        btnLogoutAction();

        // Add title and buttons directly to the navigation bar
        navigationBar.add(title);
        navigationBar.add(btnHome);
        navigationBar.add(btnJournal);
        navigationBar.add(btnInspire);
        navigationBar.add(btnAbout);
        navigationBar.add(btnLogout);

    }

    // LAYOUT PANEL DESIGN
    private void homeLayout() {
        plHome = new JPanel(new GridBagLayout());
        plHome.setBackground(bgMain);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 10, 10); // top, left, bottom, top
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Window size
        int width = plHome.getWidth();
        int height = plHome.getHeight();

        // Username
        gbc.gridy = 0;
        JLabel lbUsername = new JLabel("Welcome, " + User);
        lbUsername.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        lbUsername.setForeground(Color.DARK_GRAY);
        plHome.add(lbUsername, gbc);

        // Title
        gbc.gridy = 1;
        JLabel lbHome = new JLabel("ReflectNote");
        lbHome.setFont(new Font("Segoe UI", Font.BOLD, 75));
        lbHome.setForeground(new Color(30, 30, 30));
        plHome.add(lbHome, gbc);

        // Tagline
        gbc.gridy = 2;
        JLabel lbTagline = new JLabel("Your mind, beautifully organized.");
        lbTagline.setFont(new Font("Segoe UI", Font.ITALIC, 25));
        lbTagline.setForeground(new Color(100, 100, 100));
        plHome.add(lbTagline, gbc);

        // Quote
        gbc.gridy = 3;
        // For selecting random index for quote
        Random random = new Random();
        int randomQuoteInx = random.nextInt(homeQuote.length);
        JLabel lbRandomQuote = new JLabel("\"" + homeQuote[randomQuoteInx] + "\"");
        lbRandomQuote.setFont(new Font("Georgia", Font.ITALIC, 22));
        lbRandomQuote.setForeground(new Color(70, 70, 70));
        plHome.add(lbRandomQuote, gbc);

        // Date
        gbc.gridy = 4;
        LocalDate currentDate = LocalDate.now();
        JLabel lbDate = new JLabel(currentDate.toString());
        lbDate.setFont(new Font("Segoe UI", Font.PLAIN, 23));
        lbDate.setForeground(Color.BLUE);
        plHome.add(lbDate, gbc);

        // Description box for button hover
        gbc.gridy = 5;
        JLabel lbDescription = new JLabel("Hover over a button to learn more.");
        lbDescription.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lbDescription.setForeground(new Color(80, 80, 80));

        plHome.add(lbDescription, gbc);

        // Option buttons
        gbc.gridy = 6;
        JPanel plOptions = new JPanel(new GridLayout(1,3,5,5)); // rows, column, hgap, vgap panning
        plOptions.setBackground(bgMain);

        btnInspire = createJornalOptionButton("Inspire");
        btnJournal = createJornalOptionButton("Journal");
        btnAbout = createJornalOptionButton("About");

        plOptions.add(btnInspire);
        plOptions.add(btnJournal);
        plOptions.add(btnAbout);

        plHome.add(plOptions, gbc);

        // Add hover description behavior
        addHoverDescription(btnInspire, lbDescription, "Find inspiration for your thoughts.");
        addHoverDescription(btnJournal, lbDescription, "Start journaling your day.");
        addHoverDescription(btnAbout, lbDescription, "Learn more about ReflectNote.");

        // Button actions
        btnJournalAction();
        btnInspireAction();
        btnAboutAction();

        mainContentPanel.add(plHome, "HOME");
    }

    private void journalLayout(){

        // Create a JPanel for the journal screen layout
        plJournal = new JPanel(new GridLayout(0,2,5,5));
        plJournal.setBackground(bgMain);

        // Right side of the gird
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(bgMain);
        rightPanel.setBorder(BorderFactory.createLineBorder(new Color(0xDCDCDC), 1));

        // Top border layout
        JLabel lbJournal = setPageTitle("Journal Page");
        rightPanel.add(lbJournal, BorderLayout.NORTH);

        // Center, The journal list entries
        entryList = new JList<>(titles);
        entryList.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JScrollPane listScroll = new JScrollPane(entryList);
        rightPanel.add(listScroll, BorderLayout.CENTER);

        // Bottom panel, buttons
        JPanel buttonGrid = new JPanel(new GridLayout(0,3,3,3));
        btnNew = createJornalOptionButton("New");
        btnUpdate = createJornalOptionButton("Update");
        btnDelete = createJornalOptionButton("Delete");

        buttonGrid.add(btnNew);
        buttonGrid.add(btnUpdate);
        buttonGrid.add(btnDelete);

        rightPanel.add(buttonGrid, BorderLayout.SOUTH);

        // left side of the grid
        txtContent = new JTextArea();
        txtContent.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtContent.setBackground(bgMain);
        txtContent.setForeground(textSecondary);
        txtContent.setBorder(BorderFactory.createLineBorder(new Color(0xDCDCDC), 1));
        txtContent.setLineWrap(true); // makes a new line after reaching end (wrap)
        txtContent.setWrapStyleWord(true); // wrap word by word

        JScrollPane textScroll = new JScrollPane(txtContent); // To make it scrollable

        // The action
        entryListJournalListener();
        btnNewJournalAction();
        btnUpdateJournalAction();
        btnDeleteJournalAction();

        plJournal.add(rightPanel);
        plJournal.add(textScroll);

        mainContentPanel.add(plJournal, "JOURNAL");
    }

    private void inspireLayout(){
        // Create a JPanel for the inspire screen layout
        plInspire = new JPanel(new BorderLayout(5,7)); // height gap, vertical gap
        plInspire.setBackground(bgMain);
        JPanel plMoodButtons = new JPanel(new GridLayout(2, 3, 10, 10));

        // Page title
        JLabel lbInspire = setPageTitle("Inspire Page");

        // Buttons
        JButton grateful = createMoodButton("Grateful", new Color(0xFFD97B));
        JButton calm = createMoodButton("Calm", new Color(0xA3D9FF));
        JButton hopeful = createMoodButton("Hopeful", new Color(0xFF8A80));
        JButton overthinking = createMoodButton("Overthinking", new Color(0xC2C2C2));
        JButton down = createMoodButton("Down", new Color(0xB39DDB));
        JButton overwhelmed = createMoodButton("Overwhelmed", new Color(0xAEDFD9));


        // Add buttons to the mood buttons panel
        plMoodButtons.add(calm);
        plMoodButtons.add(grateful);
        plMoodButtons.add(hopeful);
        plMoodButtons.add(overthinking);
        plMoodButtons.add(down);
        plMoodButtons.add(overwhelmed);

        //Actions
        // To not declare every buttons action and make it reusable
        btnMoodQuotesAction(calm);
        btnMoodQuotesAction(grateful);
        btnMoodQuotesAction(overthinking);
        btnMoodQuotesAction(overwhelmed);
        btnMoodQuotesAction(down);
        btnMoodQuotesAction(hopeful);

        // Add mood buttons panel and title to the plInspire
        plInspire.add(lbInspire, BorderLayout.NORTH);
        plInspire.add(plMoodButtons, BorderLayout.CENTER);

        mainContentPanel.add(plInspire, "INSPIRE");
    }

    private void quoteCardDisplay(String mood) {
        JPanel quoteCard = new JPanel(new GridBagLayout());
        quoteCard.setBackground(bgMain);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Determine quote based on mood
        String quote = "";
        Random random = new Random();
        switch (mood) {
            case "Grateful":
                quote = qtGrateful[random.nextInt(qtGrateful.length)];
                break;
            case "Calm":
                quote = qtCalm[random.nextInt(qtCalm.length)];
                break;
            case "Hopeful":
                quote = qtHopeful[random.nextInt(qtHopeful.length)];
                break;
            case "Down":
                quote = qtDown[random.nextInt(qtDown.length)];
                break;
            case "Overthinking":
                quote = qtOverthinking[random.nextInt(qtOverthinking.length)];
                break;
            case "Overwhelmed":
                quote = qtOverwhelmed[random.nextInt(qtOverwhelmed.length)];
                break;
        }

        JLabel qtTitle = setPageTitle(mood);
        quoteCard.add(qtTitle, gbc);

        gbc.gridy = 1;
        // Quote label
        JTextArea quoteArea = new JTextArea(quote);
        quoteArea.setFont(new Font("Segoi UI", Font.PLAIN, 30));
        quoteArea.setBackground(bgMain);
        quoteArea.setPreferredSize(new Dimension(1000, 150));
        quoteArea.setLineWrap(true);
        quoteArea.setWrapStyleWord(true);
        quoteArea.setEditable(false);

        quoteCard.add(quoteArea, gbc);

        // Your button logic here (already existing)
        JButton backButton = createJornalOptionButton("Back");
        backButton.setForeground(new Color(0x007AF));
        backButton.setBorder(null);
        // Add action listener for the back button if needed
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "INSPIRE");
        });

        // Add back button below quote area
        gbc.gridy = 2;
        quoteCard.add(backButton, gbc);

        // Add this quoteCard JPanel to mainContentPanel
        mainContentPanel.add(quoteCard, "QUOTE");

        // Show the QUOTE panel
        CardLayout cl = (CardLayout) mainContentPanel.getLayout();
        cl.show(mainContentPanel, "QUOTE");
    }


    private void aboutLayout(){
        // Create a JPanel for the about screen layout
        plAbout = new JPanel();
        plAbout.setLayout(new BorderLayout());
        plAbout.setBackground(bgMain);

        JLabel lbAbout = setPageTitle("About Page");
        plAbout.add(lbAbout, BorderLayout.NORTH);

        mainContentPanel.add(plAbout, "ABOUT");
    }

    // BUTTON ACTIONS

    // ====== NAV BAR ====
    private void btnHomeAction(){
        btnHome.addActionListener(e ->{
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "HOME");
        });
    }

    private void btnJournalAction(){
        btnJournal.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "JOURNAL");
        });
    }

    private void btnInspireAction(){
        btnInspire.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "INSPIRE");
        });
    }

    private void btnAboutAction(){
        btnAbout.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "ABOUT");
        });
    }

    private void btnLogoutAction(){
        btnLogout.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(null,"Are you sure?", "Logout", JOptionPane.OK_CANCEL_OPTION);
            if(answer == 0){
                EventQueue.invokeLater(()->{
                    new Login();
                });
                dispose();
            }
        });
    }

    // ==== JOURNAL ENTRIES ====
    private void entryListJournalListener(){
        entryList.addListSelectionListener(e -> {
            int index = entryList.getSelectedIndex();
            if (index >= 0) {
                txtContent.setText(contents[index]);
            }
        });
    }

    private void btnNewJournalAction(){
        btnNew.addActionListener(e->{
            JOptionPane.showMessageDialog(null, "This is new!", "TODO", JOptionPane.OK_OPTION);
        });
    }

    private void btnUpdateJournalAction(){
        btnUpdate.addActionListener(e->{
            JOptionPane.showMessageDialog(null, "This is to be update!", "TODO", JOptionPane.OK_OPTION);
        });
    }

    private void btnDeleteJournalAction(){
        btnDelete.addActionListener(e->{
            JOptionPane.showMessageDialog(null, "I still can't delete it!", "TODO", JOptionPane.OK_OPTION);
        });
    }

    // === INSPIRE BUTTONS ===
    private void btnMoodQuotesAction(JButton button){
        button.addActionListener(e -> {
            String mood = button.getText();
            quoteCardDisplay(mood);
        });
        CardLayout cl = (CardLayout) mainContentPanel.getLayout();
        cl.show(mainContentPanel, "QUOTE");
    }

    // HELPER'S METHOD
    private JLabel setPageTitle(String title){
        // Create the label for the home screen
        JLabel label = new JLabel(title, JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 40));
        label.setForeground(textSecondary);
        return label;
    }


    private JButton createNavButton(String text, Color fgColor) {
        // Creating nav button aesthetics
        JButton button = new JButton(text);
        button.setBackground(bgSecondary);
        button.setForeground(fgColor);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBorder(null);

        // In button hover
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 19));
                button.setFont(new Font("Segoe UI", Font.BOLD, 20));
                if(text == "Logout"){
                    button.setForeground(Color.RED);
                } else {
                    button.setForeground(Color.BLUE);
                }
            }
            public void mouseExited(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 19));
                button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                button.setForeground(fgColor);
            }
        });
        return button;
    }

    // Inspire mood button aesthetics
    private JButton createMoodButton(String text, Color bgColor){
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 30));
        button.setBackground(bgColor);
        return button;
    }

    // Journal options button
    private JButton createJornalOptionButton(String text){
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setBackground(bgMain);
        button.setOpaque(false);
        return button;

    }

    // Helper method to update description on hover
    private void addHoverDescription(JButton button, JLabel descriptionLabel, String text) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                descriptionLabel.setText(text);
            }
            public void mouseExited(MouseEvent e) {
                descriptionLabel.setText("Hover over a button to learn more.");
            }
        });
    }
    }
}
