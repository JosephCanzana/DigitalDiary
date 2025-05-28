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

    // for different themes
    JButton btnChangeTheme;
    String[] THEMES = {"LIGHT", "DARK", "TWILIGHT", "STARDUST", "DAWN"};
    public static int currentTheme = 0;

    // Base Backgrounds
    public static Color bgMain = Color.white;
    public static Color bgSecondary = new Color(0xFFF2F2F7);
    public static Color bgTertiary = new Color(0xFFE5E5EA);

    // Text Colors
    public static Color clrPrimary = new Color(0xFF1C1C1E);
    public static Color clrSecondary = new Color(0x333333);
    public static Color clrTertiary = new Color(0x8e8e93);

    public static Color clrBlue = new Color(0x007aff);
    public static Color clrRed = new Color(0xff3b30);
    public static Color clrYellow = new Color(0xffcc00);

    public static Color clrGreen = new Color(0x34c759);
    public static Color clrPurple = new Color(0x5856d6);
    public static Color clrLightGray = new Color(0xf2f2f7);

    // Current date
    LocalDate currentDate = LocalDate.now();

    // Current Jornal index
    int lastAccessedIndex = -1;

    // User's data
    // not directly edting it in login since it would be redundant to call Login everytime
    int sessionID = Login.sessionID;
    String currentUser = Login.currentUser;
    ArrayList<ArrayList<String>> lsJournalEntriesDate = Login.lsJournalEntriesDate;
    ArrayList<ArrayList<String>> lsJournalContents = Login.lsJournalContents;
    ArrayList<ArrayList<String>> lsJournalTitles = Login.lsJournalTitles;

    // Quotes
    String[] homeQuote = {
            "God loved the world and gave His only Son, so whoever believes won't perish but have eternal life. John 3:16",
            "The world's beauty is in the eyes of it's beholder - Sachi",
            "I can do all things through Christ who strengthens me. - Philippians 4:13",
            "Trust the Lord with all your heart, submit to Him, and He will guide your paths. Proverbs 3:5-6",
            "I know the plans I have for you—plans to prosper you and give you hope and a future. - Jeremiah 29:11",
            "You lose because you only consider victory - Miyagi",
            "Once we accept our limits, we go beyond them. -Albert Einstein",
            "Anyone who has never made  a mistake has never tried anything new.-Albert Einstein",
            "Great spirits have always encountered violent opposition from mediocre minds.-Albert Einstein",
            "If I have seen further than others, it is by standing upon the shoulders of giants. - Isaac Newton"
    };

    String[] qtGrateful = {
            "\"When one has a grateful heart, life is so beautiful. \n - Dichoso\"",
            "\"Give thanks to the Lord, for he is good; his love endures forever.\" \n– 1 Chronicles 16:34",
            "\"I will give thanks to you, Lord, with all my heart; I will tell of all your wonderful deeds.\" \n– Psalm 9:1",
            "\"Give thanks in all circumstances; for this is God’s will for you in Christ Jesus.\" \n– 1 Thessalonians 5:18",
            "\"Let them give thanks to the Lord for his unfailing love and his wonderful deeds for mankind.\" \n– Psalm 107:8",
            "\"I waited patiently for the Lord; he turned to me and heard my cry… He set my feet on a rock and gave me a firm place to stand.\" \n– Psalm 40:1–2",
            "\"Be thankful for what you have , you’ll end up having more.\" \n- Ferrer",
            "\"Looking forward, I often see obstacles and hindrances. But after I face them and succeed, I realize how grateful I am to God for letting it happen — because He knows these challenges help me grow.  I mean, the game wouldn’t be fun without the challenge.\" \n- Canzana",
            "\"No matter how deep the night, it always turns to day, eventually.\" \n— Brook",
            "\"Acknowledging the good that you already have in your life is the foundation for all abundance.\" \n— Eckhart Tolle"
    };

    String[] qtCalm = {
            "\"The chaos doesn't end, you just become calm. - Dichoso\"",
            "\"Peace I leave with you; my peace I give you. Do not let your hearts be troubled and do not be afraid.\" \n– John 14:27",
            "\"Be still, and know that I am God.\" \n– Psalm 46:10",
            "\"In peace I will lie down and sleep, for you alone, Lord, make me dwell in safety.\" \n– Psalm 4:8",
            "\"The Lord gives strength to his people; the Lord blesses his people with peace.\" \n– Psalm 29:11",
            "\"I took a deep breath and listened to the old bray of my heart\" \n- Ferrer",
            "\"You don’t have to control your thoughts. You just have to stop letting them control you.\" \n— Dan Millman",
            "\"Calmness is something you come to realize you already have. You don’t need to act on what you haven’t practiced, or speak of knowledge you don’t possess. Just perform with what you know, and speak from what you truly own.\" \n- Canzana",
            "\"Fear is not evil. It allows you to know your weaknesses. If you know your weaknesses, you can become stronger and gentler.\" \n— Gildarts Clive",
            "\"Sometimes, the most productive thing you can do is relax.\" \n— Mark Black"
    };

    String[] qtHopeful = {
            "\"The future belongs to those who believe in the beauty of their dreams - Dichoso\"",
            "\"For I know the plans I have for you, declares the Lord, plans to prosper you and not to harm you, plans to give you hope and a future.\" \n– Jeremiah 29:11",
            "\"But those who hope in the Lord will renew their strength. They will soar on wings like eagles.\" \n– Isaiah 40:31",
            "\"May the God of hope fill you with all joy and peace as you trust in him.\" \n– Romans 15:13",
            "\"Be strong and take heart, all you who hope in the Lord.\" \n– Psalm 31:24",
            "\"Now to him who is able to do immeasurably more than all we ask or imagine, according to his power that is at work within us\" \n- Ephesiana 3:20",
            "\"I’ve always thought that a genius is someone who can do everything perfectly. But maybe a genius is someone who can devote themselves to something… and truly believe in it.\" \n- Kita Shinsuke",
            "\"I’m built upon the small things I do everyday, and the end results are no more than a byproduct of that\" \n- Kita Shinsuke",
            "\"Once you choose hope, anything’s impossible.\" \n- Ferrer",
            "\"Be hopeful. If you improve by just 1% each day and make it a habit, it will eventually become second nature. Over time, that consistency builds into something far greater—something even bigger than your original dream. In fact, you might realize that you're not chasing the dream anymore; you're already living it, step by step.\" \n- Canzana",
            "\"For every action, there is an equal and opposite reaction.\" \n- Isaac Newton"
    };

    String[] qtOverthinking = {
            "\"The thief come only to steal, kill and destroy; and I came that they may have life and have it abundantly.\" \n- John 10:10",
            "\"Do not be anxious about anything, but in every situation… present your requests to God.\" \n– Philippians 4:6",
            "\"Set your minds on things above, not on earthly things.\" \n– Colossians 3:2",
            "\"Trust in the Lord with all your heart and lean not on your own understanding.\" \n– Proverbs 3:5",
            "\"Overthinking kills your happiness.\" \n- Ferrer",
            "\"Don’t overthink it. You don’t need to be great for everyone, because everyone has a different opinion of you. And if you start worrying about what others think, don't bother — they’re often too caught up in their own thoughts to focus on you.\" \n- Canzana",
            "\"Don't believe everything you think. Thought is not reality; yet it is through thought our realities are created. Look deeper, question what you believe, and remember: you’re not your thoughts.\" \n- Nguyen Joseph",
            "\"You'll never be criticized by someone doing more than you. You'll always be criticized by someone doing less. Remember that.\" \n- Denzel Washington",
            "\"You can't think of the past. 'Why did I do that?' it'll just turn to anger. You also can't think about the future. 'Will it be okay?' It just turns to worry. In that case hold on for dear life! Stake your entire life in her and now. You will be alive.\""
    };

    String[] qtDown = {
            "\"Why, my soul, are you downcast? Put your hope in God, for I will yet praise him.\" \n– Psalm 42:11",
            "\"He heals the brokenhearted and binds up their wounds.\" \n– Psalm 147:3",
            "\"Even though I walk through the darkest valley, I will fear no evil, for you are with me.\" \n– Psalm 23:4",
            "\"The Lord upholds all who fall and lifts up all who are bowed down.\" \n– Psalm 145:14",
            "\"He who climbes the ladder must begin at the bottom\" \n- Ittetsu takeda",
            "\"Every situation in life is temporary\" \n- Ferrer",
            "\"Everything negative – pressure, challenges – is all an opportunity for me to rise.\" \n- Kobe Bryant",
            "\"It’s okay to feel down — we’re only human. But remember, being down is just another challenge, an opportunity to grow stronger. It humbles you and reminds you that you’re never truly at the top, that there’s always room to improve, and that life’s big giants still stand ahead, waiting for you to surpass them.\" \n- Canzana",
            "\"A person grows up when he's able to overcome hardships. Protection is important, but there are some things a person must learn on his own.\" \n— Jiraiya"
    };

    String[] qtOverwhelmed = {
            "\"It's okay not to be okay\"",
            "\"Have I not commanded you? Be strong and courageous. Do not be frightened, and do not be dismayed, for the Lord your God is with you wherever you go.\" – Job 1:9",
            "\"Come to me, all you who are weary and burdened, and I will give you rest.\" \n– Matthew 11:28",
            "\"Cast your burden on the Lord, and he will sustain you.\" \n– Psalm 55:22",
            "\"The Lord is close to the brokenhearted and saves those who are crushed in spirit.\"   – Psalm 34:18",
            "\"Feeling overwhelmed is often the result of trying to do everything at once.\" \n- Ferrer",
            "\"Not everyone who works hard is rewarded. However! All of those succeed have worked hard!!\" \n- Kamogawa",
            "\"God grant me the Serenity to accept the things I cannot Change; Courage to change the things I Can; And Wisdom to now the Difference.\" \n- Reinhold Neibhur",
            "\"Don't think about what can happen in a month. Don't think about what can happen in a year. Just focus on the 24 hours in front of you and do what you can to get closer to where you want to be.\" \n- Eric Thomas",
            "\"Feeling overwhelmed? Don’t be. Just focus on the next step. If you fall short, let God handle the rest. And even if it doesn’t work out, at least you tried—gained experience, built strength, and leveled up.\" \n- Canzana"
    };


    // MAIN SETUP
    App(){
        // Basic setup the JFrame
        setSize(1250,750);
        setMinimumSize(new Dimension(1250,750));
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
        footerPanel = new JPanel(new BorderLayout());

        // Set Color to the panel
        navigationBar.setBackground(bgSecondary);
        mainContentPanel.setBackground(bgMain);
        footerPanel.setBackground(bgTertiary);

        // Set Size
        footerPanel.setPreferredSize(new Dimension(0, 50));

        // Adding and setting panel border location
        add(navigationBar, BorderLayout.NORTH);
        add(mainContentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Panel setup
        setNavigationBar();

        // Initialize the panels for main content panel
        homeLayout();
        journalLayout();
        inspireLayout();
        aboutLayout();

        // Footer
        JLabel lbFooter = new JLabel("Ferrer - Canzana - Dichoso  |  ReflectNote © 2025", JLabel.CENTER);
        setLabelMaxSize(lbFooter, "SansSerif", Font.PLAIN, 12);
        lbFooter.setForeground(clrTertiary);
        footerPanel.add(lbFooter, BorderLayout.CENTER);

        setLocationRelativeTo(null); // For Centering
        setVisible(true); // Set the Frame visible
    }


    // NAVIGATION BAR TOP PANEL SETUP
    private void setNavigationBar(){
        // Set navigation bar layout to FlowLayout (left aligned)
        navigationBar.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 14));

        // Create the title
        JLabel title = new JLabel("ReflectNote ||  ");
        title.setForeground(clrPrimary);
        setLabelMaxSize(title, "Segoe UI", Font.BOLD, 30);

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

    // LAYOUT PANEL DESIGN (CENTER)
    private void homeLayout() {
        plHome = new JPanel(new GridBagLayout());
        plHome.setBackground(bgMain);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // top, left, bottom, top
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Username
        gbc.gridy = 0;
        JLabel lbUsername = new JLabel("Welcome, " + currentUser);
        setLabelMaxSize(lbUsername, "Segoe UI", Font.PLAIN,  40);
        lbUsername.setForeground(clrSecondary);
        plHome.add(lbUsername, gbc);

        // Title
        gbc.gridy = 1;
        JLabel lbHome = new JLabel("ReflectNote");
        setLabelMaxSize(lbHome, "Segoe UI", Font.BOLD, 85);
        lbHome.setForeground(clrPrimary);
        plHome.add(lbHome, gbc);

        // Tagline
        gbc.gridy = 2;
        JLabel lbTagline = new JLabel("Your mind, beautifully organized.");
        setLabelMaxSize(lbTagline,"Segoe UI", Font.ITALIC, 35);
        lbTagline.setForeground(clrTertiary);
        plHome.add(lbTagline, gbc);

        // Quote
        gbc.gridy = 3;
        // For selecting random index for quote
        Random random = new Random();
        int randomQuoteInx = random.nextInt(homeQuote.length);
        JLabel lbRandomQuote = new JLabel("\"" + homeQuote[randomQuoteInx] + "\"");
        setLabelMaxSize(lbRandomQuote,"Georgia", Font.ITALIC, 25);
        lbRandomQuote.setForeground(clrSecondary);
        plHome.add(lbRandomQuote, gbc);

        // Date
        gbc.gridy = 4;
        JLabel lbDate = new JLabel(currentDate.toString());
        setLabelMaxSize(lbDate, "Segoe UI", Font.PLAIN, 30);
        lbDate.setForeground(clrBlue);
        plHome.add(lbDate, gbc);

        // Description box for button hover
        gbc.gridy = 5;
        JLabel lbDescription = new JLabel("Hover over a button to learn more.");
        lbDescription.setFont(new Font("Segoe UI", Font.PLAIN, 23));
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
        btnChangeTheme = new JButton();
        switch (currentTheme){
            // Unicode emoji
            case 0:
                btnChangeTheme.setText("\u2600 Light mode"); //☀️
                break;
            case 1:
                btnChangeTheme.setText("\uD83C\uDF19 Dark mode"); // 🌙
                break;
            case 2:
                btnChangeTheme.setText("\uD83C\uDF12 Twilight mode"); // 🌒
                break;
            case 3:
                btnChangeTheme.setText("\u2728 Stardust mode"); //✨
                break;
            case 4:
                btnChangeTheme.setText("\uD83C\uDF05 Dawn mode"); // 🌅
                break;
        }
        setButtonMaxSize(btnChangeTheme,"SansSerif", Font.PLAIN, 18);
        btnChangeTheme.setBackground(clrLightGray);
        btnChangeTheme.setForeground(clrPrimary);
        plHome.add(btnChangeTheme, gbc);

        // Button actions
        btnJournalAction();
        btnInspireAction();
        btnAboutAction();
        btnChangeThemeAction();

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
        entryList.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        entryList.setBackground(bgMain);
        entryList.setForeground(clrPrimary);
        JScrollPane listScroll = new JScrollPane(entryList);
        listScroll.setBorder(null);
        leftPanel.add(listScroll, BorderLayout.CENTER);

        // Bottom panel, buttons
        JPanel buttonGrid = new JPanel(new GridLayout(0,4,3,3));
        buttonGrid.setBackground(bgMain);
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
        txtContentTitle = new JLabel("Select an entry", JLabel.CENTER);
        setLabelMaxSize(txtContentTitle, "Segoe UI", Font.PLAIN, 33);
        txtContentTitle.setForeground(clrPrimary);

        txtContent = new JTextArea();
        txtContent.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        txtContent.setBorder(BorderFactory.createLineBorder(clrLightGray, 1));
        txtContent.setBackground(bgMain);
        txtContent.setForeground(clrSecondary);
        txtContent.setLineWrap(true); // makes a new line after reaching end (wrap)
        txtContent.setWrapStyleWord(true); // wrap word by word
        txtContent.setCaretColor(clrPrimary);
        txtContent.setEnabled(false);
        txtContent.setText("Please select a journal entry to view or edit.");

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
        plMoodButtons.setBackground(bgMain);

        // Page title
        JLabel lbInspire = setPageTitle("Inspire Page");

        // Buttons
        JButton calm = createMoodButton("Calm", new Color(0xA3D9FF));
        JButton grateful = createMoodButton("Grateful", new Color(0xFFD97B));
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
        quote = switch (mood) {
            case "Grateful" -> qtGrateful[random.nextInt(qtGrateful.length)];
            case "Calm" -> qtCalm[random.nextInt(qtCalm.length)];
            case "Hopeful" -> qtHopeful[random.nextInt(qtHopeful.length)];
            case "Down" -> qtDown[random.nextInt(qtDown.length)];
            case "Overthinking" -> qtOverthinking[random.nextInt(qtOverthinking.length)];
            case "Overwhelmed" -> qtOverwhelmed[random.nextInt(qtOverwhelmed.length)];
            default -> quote;
        };

        JLabel qtTitle = new JLabel(mood, JLabel.CENTER);
        qtTitle.setFont(new Font("Segoe UI", Font.BOLD, 60));
        qtTitle.setForeground(clrPrimary);
        quoteCard.add(qtTitle, gbc);

        gbc.gridy = 1;
        // Quote label
        JTextArea quoteArea = new JTextArea(quote);
        quoteArea.setFont(new Font("Segoe UI", Font.PLAIN, 40));
//        System.out.println(quote.length());
        if (quote.length() > 250){
            quoteArea.setPreferredSize(new Dimension(1100, 400));
        } else if (quote.length() > 90){
            quoteArea.setPreferredSize(new Dimension(1000, 230));
        } else {
            quoteArea.setPreferredSize(new Dimension(1000, 180));
        }
        quoteArea.setBackground(bgMain);
        quoteArea.setForeground(clrPrimary);
        quoteArea.setLineWrap(true);
        quoteArea.setWrapStyleWord(true);
        quoteArea.setEditable(false);

        quoteCard.add(quoteArea, gbc);

        // Your button logic here (already existing)
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        backButton.setForeground(clrBlue);
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


    private void aboutLayout() {
        plAbout = new JPanel(new BorderLayout());
        plAbout.setBackground(bgMain);

        // Title
        JLabel title = new JLabel("About Reflect Note");
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(clrPrimary);

        // About info
        String aboutText = """
        TYPE: Digital Diary System
        
        DESCRIPTION:
        Reflect Note is a minimal, user-friendly digital diary application
        built with Java Swing. It offers a distraction-free interface,
        emotion-based quotes, and intuitive features for writing and managing
        daily reflections.
        
        FEATURES:
        • Multi-user login support
        • Organized viewing of past entries
        • Create, read, update, and delete (CRUD) notes
        • Theme switching with clean aesthetics
        • Inspirational quotes based on mood
        
        TECHNOLOGY:
        • Language: Java
        • GUI: javax.swing, java.awt
        • Action Handling: java.awt
        • Event Handling: java.awt.event
        • Utilities: java.util, java.time.LocalDate
        
        LIMITATIONS:
        • No SQL or database integration
        • Uses arrays and ArrayList for storage
        
        PROJECT INFO:
        • Course: Intermediate Programming (Final Project)
        • Year: 2024–2025
        • Institution: Holy Cross College
        • Professor: Sir. Jayson R. Francisco
        
        DEVELOPED BY:
        - Phomela Ferrer     | Flowchart Design
        - Anthony Dichoso   | Algorithm Step by Step
        - Joseph Canzana     | System Programming
        
        CONTACT:
        • 14462018@holycross.edu.ph
        • 77562024@holycross.edu.ph
        • 78722024@holycross.edu.ph
        """;

        JTextArea aboutArea = new JTextArea(aboutText);
        aboutArea.setEditable(false);
        aboutArea.setWrapStyleWord(true);
        aboutArea.setLineWrap(true);
        aboutArea.setBackground(bgMain);
        aboutArea.setForeground(clrSecondary);
        aboutArea.setFont(new Font("SansSerif", Font.PLAIN, 24));
        aboutArea.setMargin(new Insets(15, 50, 30, 50)); // Inner padding

        JScrollPane scrollText = new JScrollPane(aboutArea);
        scrollText.setBorder(null);
        scrollText.setBackground(bgMain);

        // Add components
        plAbout.add(title, BorderLayout.NORTH);
        plAbout.add(scrollText, BorderLayout.CENTER);

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
            int guiIndex = entryList.getSelectedIndex();
            if (guiIndex >= 0) {
                // since the list is ascending need to get the last array
                int dataIndex = lsJournalTitles.get(sessionID).size() - 1 - guiIndex;
                checkLastEntry(dataIndex);

                // Set text
                txtContentTitle.setText(lsJournalTitles.get(sessionID).get(dataIndex).trim());
                txtContent.setText(lsJournalContents.get(sessionID).get(dataIndex).trim());
            }

            int answer = JOptionPane.showConfirmDialog(null,"Are you sure?", "Logout", JOptionPane.OK_CANCEL_OPTION);
            if(answer == JOptionPane.YES_OPTION){
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

    // ==== HOME ====
    private void btnChangeThemeAction(){
        btnChangeTheme.addActionListener(e -> {
            // Wrap around
            if (currentTheme == THEMES.length - 1){
                currentTheme = 0;
            } else {
                // Next theme
                currentTheme++;
            }
            String themeName = THEMES[currentTheme];

            switch (themeName){
                case "DARK":
                    bgMain = new Color(0x1C1C1E);
                    bgSecondary = new Color(0x2C2C2E);
                    bgTertiary = new Color(0x3A3A3C);

                    clrPrimary = new Color(0xFFFFFF);
                    clrSecondary = new Color(0xD1D1D6);
                    clrTertiary = new Color(0x8E8E93);

                    clrBlue = new Color(0x0A84FF);
                    clrRed = new Color(0xFF453A);
                    clrYellow = new Color(0xFFD60A);
                    clrGreen = new Color(0x30D158);
                    clrPurple = new Color(0xBF5AF2);
                    clrLightGray = new Color(0x2C2C2E);

                    break;
                case "TWILIGHT":
                    bgMain = new Color(0xF5F3FA);
                    bgSecondary = new Color(0xE6E2F0);
                    bgTertiary = new Color(0xD7D1E8);

                    clrPrimary = new Color(0x2D1E4D);
                    clrSecondary = new Color(0x4A3B66);
                    clrTertiary = new Color(0x8E8EA0);

                    clrBlue = new Color(0x6D88FF);
                    clrRed = new Color(0xFF6B6B);
                    clrYellow = new Color(0xFFDB58);
                    clrGreen = new Color(0x60D394);
                    clrPurple = new Color(0x8A77D9);
                    clrLightGray = new Color(0xE6E2F0);
                    break;

                case "STARDUST":
                    bgMain = new Color(0x0B1D2B);
                    bgSecondary = new Color(0x122A40);
                    bgTertiary = new Color(0x1C3A57);

                    clrPrimary = new Color(0xE0F1FF);
                    clrSecondary = new Color(0xAFC9DE);
                    clrTertiary = new Color(0x6C8EA4);

                    clrBlue = new Color(0x5FA8FF);
                    clrRed = new Color(0xFF6B6B);
                    clrYellow = new Color(0xFFD56B);

                    clrGreen = new Color(0x5ED1B1);
                    clrPurple = new Color(0xA48FEF);
                    clrLightGray = new Color(0x2B3A4A);
                    break;

                case "DAWN":
                    bgMain = new Color(0xFFF8F0);
                    bgSecondary = new Color(0xF5EBDD);
                    bgTertiary = new Color(0xEADAC8);

                    clrPrimary = new Color(0x3E3B32);
                    clrSecondary = new Color(0x7B6F5A);
                    clrTertiary = new Color(0xB8AFA0);

                    clrBlue = new Color(0xA77B5A);
                    clrRed = new Color(0xC85A4A);
                    clrYellow = new Color(0xD4B15F);

                    clrGreen = new Color(0x88A66F);
                    clrPurple = new Color(0x9C8AA5);
                    clrLightGray = new Color(0xEFE8E1);

                    break;

                default:
                    bgMain = Color.white;
                    bgSecondary = new Color(0xFFF2F2F7);
                    bgTertiary = new Color(0xFFE5E5EA);

                    clrPrimary = new Color(0xFF1C1C1E);
                    clrSecondary = new Color(0x333333);
                    clrTertiary = new Color(0x8e8e93);

                    clrBlue = new Color(0x007aff);
                    clrRed = new Color(0xff3b30);
                    clrYellow = new Color(0xffcc00);
                    clrGreen = new Color(0x34c759);
                    clrPurple = new Color(0x5856d6);
                    clrLightGray = new Color(0xf2f2f7);

                    break;
            }

            dispose();
            java.awt.EventQueue.invokeLater(App::new);

        });
    }

    // ==== JOURNAL ENTRIES ====
    private void entryListJournalListener(){
        entryList.addListSelectionListener(e -> {
            txtContent.setEnabled(true);
            int guiIndex = entryList.getSelectedIndex();
            if (guiIndex >= 0) {
                // since the list is ascending need to get the last array
                int dataIndex = lsJournalTitles.get(sessionID).size() - 1 - guiIndex;
                checkLastEntry(dataIndex);

                // Set text
                txtContentTitle.setText(lsJournalTitles.get(sessionID).get(dataIndex).trim());
                txtContent.setText(lsJournalContents.get(sessionID).get(dataIndex).trim());
            }
        });
    }

    private void btnNewJournalAction(){
        btnNew.addActionListener(e->{

            // Input a title
            String lsTitle = JOptionPane.showInputDialog(null, "Enter a Title: ");
            // pressed Cancel
            if (lsTitle == null){
                return;
            }

            // Remove white space at the start and end
            lsTitle = lsTitle.trim();
            if (lsTitle.length() > 30){
                JOptionPane.showMessageDialog(null, "Your title is too long. Please keep it under 31 characters.", "Title Too Long", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (lsTitle.isBlank()){
                JOptionPane.showMessageDialog(null, "Title can't be empty.", "Title is empty", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create a start up text and list title
            String newEntry = currentDate + ": " + lsTitle;;

            // Update temporary data
            lsJournalContents.get(sessionID).add(String.valueOf(currentDate));
            lsJournalEntriesDate.get(sessionID).add(String.valueOf(currentDate));
            lsJournalTitles.get(sessionID).add(lsTitle);

            // Append on the default list from JList in journalLayout
            ((DefaultListModel<String>) entryList.getModel()).add(0,newEntry);

            // go to the added journal
            entryList.setSelectedIndex(0);
        });
    }

    private void btnUpdateJournalAction(){
        btnUpdate.addActionListener(e->{
            // Entry selected
            int guiIndex = entryList.getSelectedIndex();

            if (guiIndex >= 0){
                // ask user if he/she really want to save
                int agree = JOptionPane.showConfirmDialog(null, "Save changes to your journal entry?", "Save Changes", JOptionPane.OK_CANCEL_OPTION);
                if (agree != JOptionPane.YES_OPTION){
                    return;
                }

                int dataIndex = lsJournalContents.get(sessionID).size() - 1 - guiIndex;
                // Update the entry
                lsJournalContents.get(sessionID).set(dataIndex, txtContent.getText());
                JOptionPane.showMessageDialog(null, "Your journal entry was successfully updated.", "Entry Updated", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Please select an entry to update.", "No Entry Selected", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void editTitleAction(){
        btnEditTitle.addActionListener(e->{
            int guiIndex = entryList.getSelectedIndex();
//            System.out.println(guiIndex);
            if(guiIndex >= 0){
                int dataIndex = lsJournalContents.get(sessionID).size() - 1 - guiIndex;
                System.out.println(dataIndex);

                String newTitle = JOptionPane.showInputDialog(null, "What would you like to name this entry?");
                // If cancel
                if (newTitle == null){
                    return;
                }

                newTitle = newTitle.trim();
                if (newTitle.isBlank()){
                    JOptionPane.showMessageDialog(null, "Title can’t be empty. Please enter a valid title.", "Invalid Title", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Updating the array
                lsJournalTitles.get(sessionID).set(dataIndex, newTitle);

                // update gui title
                txtContentTitle.setText(newTitle);

                // Using DefaultListModel to reference the entryList and change it
                DefaultListModel<String> model = (DefaultListModel<String>) entryList.getModel();
                String newListTag = lsJournalEntriesDate.get(sessionID).get(dataIndex) + ": " + newTitle;
                model.setElementAt(newListTag, guiIndex);
                JOptionPane.showMessageDialog(null, "The title has been updated.", "Title Updated", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Please select an entry to rename.", "No Entry Selected", JOptionPane.WARNING_MESSAGE);
            }

        });
    }

    private void btnDeleteJournalAction(){
        btnDelete.addActionListener(e->{
            // Entry Selected
            int guiIndex = entryList.getSelectedIndex();

            if (guiIndex >= 0) {
                int dataIndex = lsJournalContents.get(sessionID).size() - 1 - guiIndex;

                // Ask for confirmation before deletion
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete this entry?", "Delete Entry", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {

                    // Using dynamic array wouldn't leave blank array slot
                    lsJournalContents.get(sessionID).remove(dataIndex);
                    lsJournalTitles.get(sessionID).remove(dataIndex);
                    lsJournalEntriesDate.get(sessionID).remove(dataIndex);
                    ((DefaultListModel<String>) entryList.getModel()).remove(guiIndex);
                    txtContentTitle.setText("Select an entry");
                    // reset
                    txtContent.setEnabled(false);
                    txtContent.setText("Please select a journal entry to view or edit.");
                    lastAccessedIndex = -1;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select an entry to delete.", "No Entry Selected", JOptionPane.ERROR_MESSAGE);
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
        setLabelMaxSize(label, "Segoe UI", Font.BOLD, 40);
        label.setForeground(clrPrimary);
        return label;
    }


    private JButton createNavButton(String text, Color fgColor) {
        // Creating nav button aesthetics
        JButton button = new JButton(text);
        button.setBackground(bgSecondary);
        button.setForeground(fgColor);
        setButtonMaxSize(button, "Segoe UI", Font.PLAIN, 17);
        button.setBorder(null);

        // In button hover
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.BOLD, button.getFont().getSize() + 1));
                if(text.equals("Logout")){
                    button.setForeground(clrRed);
                } else {
                    button.setForeground(clrBlue);
                }
            }
            public void mouseExited(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, button.getFont().getSize() - 1));
                button.setForeground(fgColor);
            }
        });
        return button;
    }

    // Inspire mood button aesthetics
    private JButton createMoodButton(String text, Color bgColor){
        JButton button = new JButton(text);
        setButtonMaxSize(button, "SansSerif", Font.BOLD, 38);
        button.setForeground(new Color(0xFF1C1C1E));
        button.setBackground(bgColor);
        return button;
    }

    // Journal options button
    private JButton createJornalOptionButton(String text){
        JButton button = new JButton(text);
        setButtonMaxSize(button, "Segoe UI", Font.PLAIN, 20);
        button.setForeground(clrPrimary);
        button.setBackground(bgSecondary);
        return button;

    }

    // Helper method to update description on hover
    private void addHoverDescription(JButton button, JLabel descriptionLabel, String text) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                descriptionLabel.setText(text);
                descriptionLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
                descriptionLabel.setForeground(clrBlue);
                button.setBackground(clrBlue);
            }
            public void mouseExited(MouseEvent e) {
                descriptionLabel.setText("Hover over a button to learn more.");
                descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 23));
                descriptionLabel.setForeground(clrTertiary);
                button.setBackground(bgSecondary);
            }
        });
    }

    private void checkLastEntry(int currentDataIndex) {

        // Since the default last access is in negative and in every delete it was reset in -1
        if (lastAccessedIndex < 0) {
            lastAccessedIndex = currentDataIndex;
            return;
        }

        String lastAccessedText;
        try {
            lastAccessedText = lsJournalContents.get(sessionID).get(lastAccessedIndex).trim();
        } catch (IndexOutOfBoundsException e) {
            lastAccessedIndex = currentDataIndex;
            System.out.println("Out of Index:" +  lastAccessedIndex);
            return;
        }

        // Remove white space for consistency
        String txtCurrentState = txtContent.getText().trim();

        // CHeck if the text in text area is the same in array
        if (!Objects.equals(lastAccessedText, txtCurrentState)) {
            int ans = JOptionPane.showConfirmDialog(null, "You have changes on your current content, do you want to update it?", "Unsaved Changes", JOptionPane.OK_CANCEL_OPTION);

            if (ans == JOptionPane.YES_OPTION) {
                lsJournalContents.get(sessionID).set(lastAccessedIndex, txtContent.getText());
                JOptionPane.showMessageDialog(null, "Your journal entry was successfully updated.", "Entry Updated", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        // Update after check
        lastAccessedIndex = currentDataIndex;
    }

    private void setLabelMaxSize(JLabel label, String font, int fontStyle, int size) {
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int maxSize = (int) (size * 0.40) + size;

                if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    // Window is maximized
                    label.setFont(new Font(font, fontStyle, maxSize));
                    footerPanel.setPreferredSize(new Dimension(0, 60));
                } else {
                    // Window is in normal state
                    label.setFont(new Font(font, fontStyle, size));
                    footerPanel.setPreferredSize(new Dimension(0, 50));
                }
            }
        });
    }

    private void setButtonMaxSize(JButton button, String font, int fontStyle, int size) {
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int maxSize = (int) (size * 0.40) + size;

                if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    // Window is maximized
                    button.setFont(new Font(font, fontStyle, maxSize));
                } else {
                    // Window is in normal state
                    button.setFont(new Font(font, fontStyle, size));
                }
            }
        });
    }

    // Go to login first
    public static void main(String[] args) {
        EventQueue.invokeLater(Login::new);
    }
}
