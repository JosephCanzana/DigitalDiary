import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
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
    //Sample
    String[] homeQuote = {
            "This is cs50",
            "This is HCC",
            "This is IP"
    };


    // MAIN SETUP
    App(){
        // Basic setup the JFrame
        setSize(850,600);
        setMinimumSize(new Dimension(850,600));
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

        // Username
        gbc.gridy = 0;
        JLabel lbUsername = new JLabel("Welcome, username");
        lbUsername.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lbUsername.setForeground(Color.DARK_GRAY);
        plHome.add(lbUsername, gbc);

        // Title
        gbc.gridy = 1;
        JLabel lbHome = new JLabel("ReflectNote");
        lbHome.setFont(new Font("Segoe UI", Font.BOLD, 60));
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

        // === Right side of the gird ===
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

        // === left side of the grid ===
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
        JButton happy = createMoodButton("Happy", new Color(0xFFE066));
        JButton sad = createMoodButton("Sad", new Color(0xA3C4F3));
        JButton angry = createMoodButton("Angry", new Color(0xFF6B6B));
        JButton tired = createMoodButton("Tired", new Color(0xC0C0C0));
        JButton anxious = createMoodButton("Anxious", new Color(0xB19CD9));
        JButton motivated = createMoodButton("Motivated", new Color(0xA8E6CF));

        // Add buttons to the mood buttons panel
        plMoodButtons.add(happy);
        plMoodButtons.add(sad);
        plMoodButtons.add(angry);
        plMoodButtons.add(tired);
        plMoodButtons.add(anxious);
        plMoodButtons.add(motivated);

        //Actions
        btnMoodQuotesAction(happy);
        btnMoodQuotesAction(sad);
        btnMoodQuotesAction(angry);
        btnMoodQuotesAction(tired);
        btnMoodQuotesAction(anxious);
        btnMoodQuotesAction(motivated);

        // Add mood buttons panel and title to the plInspire
        plInspire.add(lbInspire, BorderLayout.NORTH);
        plInspire.add(plMoodButtons, BorderLayout.CENTER);

        mainContentPanel.add(plInspire, "INSPIRE");
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
                java.awt.EventQueue.invokeLater(Login::new);
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

    // INSPIRE BUTTONS
    private void btnMoodQuotesAction(JButton button){
        button.addActionListener(e -> {
            String mood = button.getText();
            JOptionPane.showMessageDialog(null, "You are " + mood, "TODO", JOptionPane.OK_OPTION);
        });
    }

    // HELPER'S METHOD
    private JLabel setPageTitle(String title){
        // Create the label for the home screen
        JLabel label = new JLabel(title, JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        label.setForeground(textPrimary);
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 25));
        button.setBackground(bgColor);
        return button;
    }

    // Journal options button
    private JButton createJornalOptionButton(String text){
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
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


