import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.time.LocalDate;

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
    JButton btnEditTitle;
    JLabel txtContentTitle;
    JTextArea txtContent;

    // Base Backgrounds
    Color bgMain = Color.white;
    Color bgSecondary = new Color(0xFFF2F2F7);
    Color bgTertiary = new Color(0xFFE5E5EA);

    // Text Colors
    Color clrPrimary = new Color(0xFF1C1C1E);
    Color clrSecondary = new Color(0x333333);
    Color clrTertiary = new Color(0x8e8e93);

    Color clrBlue = new Color(0x007aff);
    Color clrRed = new Color(0xff3b30);
    Color clrYellow = new Color(0xffcc00);


    Color clrGreen = new Color(0x34c759);
    Color clrPurple = new Color(0x5856d6);
    Color clrLightGray = new Color(0xf2f2f7);

    // Current date
    LocalDate currentDate = LocalDate.now();

    // User's data
    // not directly edting it in login since it would be redundant to call Login everytime
    int sessionID = Login.sessionID;
    String currentUser = Login.currentUser;
    ArrayList<ArrayList<String>> lsJournalEntriesDate = Login.lsJournalEntriesDate;
    ArrayList<ArrayList<String>> lsJournalContents = Login.lsJournalContents;
    ArrayList<ArrayList<String>> lsJournalTitles = Login.lsJournalTitles;

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
            "“There are only two ways to live your life. One is as though nothing is a miracle. The other is as though everything is a miracle.” \n— Albert Einstein"
    };

    String[] qtCalm = {
            "Trust in the Lord with all your heart and lean not on your own understanding.\n– Proverbs 3:5",
            "“Calmness is the cradle of power.” — Josiah Gilbert Holland"
    };

    String[] qtHopeful = {
            "“There’s no reason to look back when you have so much to look forward to.” — Kobe Bryant",
            "For I know the plans I have for you... plans to give you hope and a future.\n– Jeremiah 29:11",

    };

    String[] qtOverthinking = {
            "“Stop worrying about what you can’t control. Shift your energy to what you can create.” \n— Roy T. Bennett",
    };

    String[] qtDown = {
            "Everything negative—pressure, challenges—is all an opportunity for me to rise.\n– Kobe Bryant",
            "“The Lord is near to the brokenhearted and saves the crushed in spirit.” \n — Psalm 34:18"
    };

    String[] qtOverwhelmed = {
            "Great things come from hard work and perseverance. No excuses.\n– Kobe Bryant",
            "“You don’t rise to the level of your expectations, you fall to the level of your training.” — Coach Kamogawa"
    };


    // MAIN SETUP
    App(){
        // Basic setup the JFrame
        setSize(1150,710);
        setMinimumSize(new Dimension(1150,710));
        getContentPane().setBackground(bgMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ReflectNote: Your mind, beautifully organized.");

        // App logo
        ImageIcon icon = new ImageIcon("src/Icon/logo.png");
        Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        setIconImage(scaledImage);

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
        title.setForeground(clrPrimary);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));

        // Create the buttons
        btnHome = createNavButton("Home", clrSecondary);
        btnJournal = createNavButton("Journal", clrSecondary);
        btnInspire = createNavButton("Inspire", clrSecondary);
        btnAbout = createNavButton("About", clrTertiary);
        btnLogout = createNavButton("Logout", clrRed);

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
        JLabel lbUsername = new JLabel("Welcome, " + currentUser);
        lbUsername.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        lbUsername.setForeground(clrSecondary);
        plHome.add(lbUsername, gbc);

        // Title
        gbc.gridy = 1;
        JLabel lbHome = new JLabel("ReflectNote");
        lbHome.setFont(new Font("Segoe UI", Font.BOLD, 75));
        lbHome.setForeground(clrPrimary);
        plHome.add(lbHome, gbc);

        // Tagline
        gbc.gridy = 2;
        JLabel lbTagline = new JLabel("Your mind, beautifully organized.");
        lbTagline.setFont(new Font("Segoe UI", Font.ITALIC, 25));
        lbTagline.setForeground(clrTertiary);
        plHome.add(lbTagline, gbc);

        // Quote
        gbc.gridy = 3;
        // For selecting random index for quote
        Random random = new Random();
        int randomQuoteInx = random.nextInt(homeQuote.length);
        JLabel lbRandomQuote = new JLabel("\"" + homeQuote[randomQuoteInx] + "\"");
        lbRandomQuote.setFont(new Font("Georgia", Font.ITALIC, 22));
        lbRandomQuote.setForeground(clrSecondary);
        plHome.add(lbRandomQuote, gbc);

        // Date
        gbc.gridy = 4;
        JLabel lbDate = new JLabel(currentDate.toString());
        lbDate.setFont(new Font("Segoe UI", Font.PLAIN, 23));
        lbDate.setForeground(clrBlue);
        plHome.add(lbDate, gbc);

        // Description box for button hover
        gbc.gridy = 5;
        JLabel lbDescription = new JLabel("Hover over a button to learn more.");
        lbDescription.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lbDescription.setForeground(clrSecondary);

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

        gbc.gridy = 7;
        // Create a themed toggle button with text or icons
        JToggleButton changeTheme = new JToggleButton("🌙 Dark Mode");

        // Optional: customize appearance
        changeTheme.setFocusPainted(false);
        changeTheme.setFont(new Font("SansSerif", Font.PLAIN, 14));
        changeTheme.setBackground(clrLightGray);
        changeTheme.setForeground(clrPrimary);
        plHome.add(changeTheme, gbc);

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

        // LEFT side of the gird
        JPanel leftPanel = new JPanel(new BorderLayout(5,3));
        leftPanel.setBackground(bgMain);

        // Top border layout
        JLabel lbJournal = setPageTitle("Journal Page");
        leftPanel.add(lbJournal, BorderLayout.NORTH);

        // Center, The journal list entries
        // Using default list model to make the list resizable and not static
        DefaultListModel<String> listModel = new DefaultListModel<>();
        int listLength = lsJournalTitles.get(sessionID).size();
        for (int i = listLength - 1; i >= 0; i--) {
            listModel.addElement(lsJournalEntriesDate.get(sessionID).get(i) + ": " + lsJournalTitles.get(sessionID).get(i));
        }
        entryList = new JList<>(listModel);
        entryList.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        entryList.setForeground(clrPrimary);
        JScrollPane listScroll = new JScrollPane(entryList);
        listScroll.setBorder(null);
        leftPanel.add(listScroll, BorderLayout.CENTER);

        // Bottom panel, buttons
        JPanel buttonGrid = new JPanel(new GridLayout(0,4,3,3));
        btnNew = createJornalOptionButton("New");
        btnEditTitle = createJornalOptionButton("Rename");
        btnUpdate = createJornalOptionButton("Update");
        btnDelete = createJornalOptionButton("Delete");

        buttonGrid.add(btnNew);
        buttonGrid.add(btnUpdate);
        buttonGrid.add(btnEditTitle);
        buttonGrid.add(btnDelete);

        leftPanel.add(buttonGrid, BorderLayout.SOUTH);

        // RIGHT side of the grid
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(bgMain);

        // title of the content
        txtContentTitle = setPageTitle("There's no selected entry");
        txtContentTitle.setFont(new Font("Segoi UI", Font.PLAIN, 30));
        txtContentTitle.setForeground(clrPrimary);

        txtContent = new JTextArea();
        txtContent.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        txtContent.setBorder(BorderFactory.createLineBorder(clrLightGray, 1));
        txtContent.setBackground(bgMain);
        txtContent.setForeground(clrSecondary);
        txtContent.setLineWrap(true); // makes a new line after reaching end (wrap)
        txtContent.setWrapStyleWord(true); // wrap word by word

        JScrollPane textScroll = new JScrollPane(txtContent); // To make it scrollable

        rightPanel.add(txtContentTitle, BorderLayout.NORTH);
        rightPanel.add(textScroll, BorderLayout.CENTER);

        // The action
        entryListJournalListener();
        btnNewJournalAction();
        btnUpdateJournalAction();
        btnDeleteJournalAction();
        editTitleAction();

        plJournal.add(leftPanel);
        plJournal.add(rightPanel);

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
        quoteArea.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        quoteArea.setPreferredSize(new Dimension(1000, 150));
        quoteArea.setBackground(bgMain);
        quoteArea.setForeground(clrPrimary);
        quoteArea.setLineWrap(true);
        quoteArea.setWrapStyleWord(true);
        quoteArea.setEditable(false);

        quoteCard.add(quoteArea, gbc);

        // Your button logic here (already existing)
        JButton backButton = createJornalOptionButton("Back");
        backButton.setForeground(clrBlue);
        //removing background(changing to bgwhite) and border that come with the create option button
        backButton.setBackground(bgMain);
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
                // For developing purposes
//                System.out.println("===== From App ====");
//                System.out.println("User: "  + currentUser);
//                System.out.println("User content List: " + lsJournalContents.get(sessionID));
//                System.out.println("All list: \n" + lsJournalContents);
//                System.out.println("===== From App ====");
                EventQueue.invokeLater(()->{
                    // passing the value to Login
                    Login.lsJournalContents = lsJournalContents;
                    Login.lsJournalTitles = lsJournalTitles;
                    Login.lsJournalEntriesDate = lsJournalEntriesDate;
                    new Login();
                });
                dispose();
            }
        });
    }

    // ==== JOURNAL ENTRIES ====
    private void entryListJournalListener(){
        entryList.addListSelectionListener(e -> {
            int guiIndex = entryList.getSelectedIndex();
            if (guiIndex >= 0) {
                // since the list is ascending need to get the last array
                int dataIndex = lsJournalTitles.get(sessionID).size() - 1 - guiIndex;
                txtContentTitle.setText(lsJournalTitles.get(sessionID).get(dataIndex).trim());
                txtContent.setText(lsJournalContents.get(sessionID).get(dataIndex).trim());
            }
        });
    }

    private void btnNewJournalAction(){
        btnNew.addActionListener(e->{

            String txtArea = "";
            // check if text area is blank or there's a content
            if (!txtContent.getText().isBlank() && !(entryList.getSelectedIndex() >= 0)){
                // If theres a text in txtContent
                int confirm = JOptionPane.showConfirmDialog(null, "Do you want to save the text in text area?", "Save text", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    txtArea = txtContent.getText();
                }
            }

            // Input a title
            String lsTitle = JOptionPane.showInputDialog(null, "Enter a Title: ");
            // pressed Cancel
            if (lsTitle == null){
                return;
            }
            // Remove white space at the start and end
            lsTitle = lsTitle.trim();
            if (lsTitle.length() > 30){
                JOptionPane.showMessageDialog(null, "Title should be less than 31", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create a start up text and list title
            String newEntry;
            if(lsTitle.isBlank()){
                newEntry = "Date: " + currentDate;
            }else {
                newEntry = currentDate + ": " + lsTitle;
            }

            // Update temporary data
            lsJournalContents.get(sessionID).add(newEntry + "\n\n" + txtArea);
            lsJournalEntriesDate.get(sessionID).add(String.valueOf(currentDate));
            lsJournalTitles.get(sessionID).add(lsTitle);

            // Append on the default list from JList in journalLayout
            ((DefaultListModel<String>) entryList.getModel()).add(0,newEntry);
        });
    }

    private void btnUpdateJournalAction(){
        btnUpdate.addActionListener(e->{
            // Entry selected
            int guiIndex = entryList.getSelectedIndex();

            if (guiIndex >= 0){
                int dataIndex = lsJournalContents.get(sessionID).size() - 1 - guiIndex;
                // Update the entry
                lsJournalContents.get(sessionID).set(dataIndex, txtContent.getText());
                JOptionPane.showMessageDialog(null, "Journal entry updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "No entry selected to Update.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void editTitleAction(){
        btnEditTitle.addActionListener(e->{
            int guiIndex = entryList.getSelectedIndex();
            System.out.println(guiIndex);
            if(guiIndex >= 0){
                int dataIndex = lsJournalContents.get(sessionID).size() - 1 - guiIndex;
                System.out.println(dataIndex);

                String newTitle = JOptionPane.showInputDialog(null, "Enter a new title");

                // Updating the array
                lsJournalTitles.get(sessionID).set(dataIndex, newTitle);

                // update gui title
                txtContentTitle.setText(newTitle);

                // Using DefaultListModel to reference the entryList and change it
                DefaultListModel<String> model = (DefaultListModel<String>) entryList.getModel();
                String newListTag = lsJournalEntriesDate.get(sessionID).get(dataIndex) + ": " + newTitle;
                model.setElementAt(newListTag, guiIndex);
                JOptionPane.showMessageDialog(null, "Succesfully change the title", "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "There's no selected entry.", "Error", JOptionPane.WARNING_MESSAGE);
            }

        });
    }

    private void btnDeleteJournalAction(){
        btnDelete.addActionListener(e->{
            // Entry Selected
            int guiIndex = entryList.getSelectedIndex();

            if (guiIndex >= 0) {
                int dataIndex = lsJournalContents.size() - 1 - guiIndex;

                // Ask for confirmation before deletion
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this entry?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {

                    // Using dynamic array wouldn't leave blank array slot
                    lsJournalContents.get(sessionID).remove(dataIndex);
                    lsJournalTitles.get(sessionID).remove(dataIndex);
                    ((DefaultListModel<String>) entryList.getModel()).remove(guiIndex);
                    txtContent.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No entry selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        label.setForeground(clrPrimary);
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
                button.setFont(new Font("Segoe UI", Font.BOLD, 19));
                if(text == "Logout"){
                    button.setForeground(clrRed);
                } else {
                    button.setForeground(clrBlue);
                }
            }
            public void mouseExited(MouseEvent e) {
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
        button.setForeground(clrPrimary);
        button.setBackground(bgColor);
        return button;
    }

    // Journal options button
    private JButton createJornalOptionButton(String text){
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        button.setForeground(clrPrimary);
        button.setBackground(bgSecondary);
        return button;

    }

    // Helper method to update description on hover
    private void addHoverDescription(JButton button, JLabel descriptionLabel, String text) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                descriptionLabel.setText(text);
                descriptionLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
                descriptionLabel.setForeground(clrBlue);
                button.setBackground(clrBlue);
            }
            public void mouseExited(MouseEvent e) {
                descriptionLabel.setText("Hover over a button to learn more.");
                descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                descriptionLabel.setForeground(clrTertiary);
                button.setBackground(bgSecondary);
            }
        });
    }

    // Go to login first
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Login();
        });
    }
}
