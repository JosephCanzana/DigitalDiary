import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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

    // Panel inside main content panel
    JPanel plHome;
    JPanel plJournal;
    JPanel plInspire;
    JPanel plAbout;
    JPanel plLogout;

    // Base Backgrounds
    Color bgMain = new Color(0xFFFFFFFF);
    Color bgSecondary = new Color(0xFFF2F2F7);
    Color bgTertiary = new Color(0xFFE5E5EA);

    // Text Colors
    Color textPrimary = new Color(0xFF1C1C1E);
    Color textSecondary = new Color(0xFF8E8E93);


    // MAIN SETUP
    App(){
        // Basic setup the JFrame
        setSize(850,600);
        setMinimumSize(new Dimension(850,600));
        getContentPane().setBackground(bgMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ReflectNote: Right your thoughts");

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
        logoutLayout();

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


    // BUTTON ACTIONS
    private void btnHomeAction(){
        btnHome.addActionListener(e ->{
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "HOME");
            System.out.println("Home");
        });
    }

    private void btnJournalAction(){
        btnJournal.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "JOURNAL");
            System.out.println("Journal");
        });
    }

    private void btnInspireAction(){
        btnInspire.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "INSPIRE");
            System.out.println("Inspire");
        });
    }

    private void btnAboutAction(){
        btnAbout.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "ABOUT");
            System.out.println("About");
        });
    }

    private void btnLogoutAction(){
        btnLogout.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, "LOGOUT");
            System.out.println("Logout");
        });
    }


    // LAYOUT PANEL DESIGN
    private void homeLayout(){
        // Create a JPanel for the home screen layout
        plHome = new JPanel();
        plHome.setLayout(new BorderLayout());
        plHome.setBackground(bgMain);

        JLabel lbHome = setPageTitle("Home Page");
        plHome.add(lbHome, BorderLayout.NORTH);

        mainContentPanel.add(plHome, "HOME");
    }

    private void journalLayout(){
        // Create a JPanel for the journal screen layout
        plJournal = new JPanel();
        plJournal.setLayout(new BorderLayout());
        plJournal.setBackground(bgMain);

        JLabel lbJournal = setPageTitle("Journal Page");
        plJournal.add(lbJournal, BorderLayout.NORTH);

        mainContentPanel.add(plJournal, "JOURNAL");
    }

    private void inspireLayout(){
        // Create a JPanel for the inspire screen layout
        plInspire = new JPanel();
        plInspire.setLayout(new BorderLayout());
        plInspire.setBackground(bgMain);

        JLabel lbInspire = setPageTitle("Inspire Page");
        plInspire.add(lbInspire, BorderLayout.NORTH);

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

    private void logoutLayout(){
        // Create a JPanel for the logout screen layout
        plLogout = new JPanel();
        plLogout.setLayout(new BorderLayout());
        plLogout.setBackground(bgMain);

        JLabel lbLogout = setPageTitle("Logout Page");
        plLogout.add(lbLogout, BorderLayout.NORTH);
        mainContentPanel.add(plLogout, "LOGOUT");
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
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 19));
                button.setFont(new Font("Segoe UI", Font.BOLD, 20));
                if(text == "Logout"){
                    button.setForeground(Color.RED);
                } else {
                    button.setForeground(Color.BLUE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 19));
                button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                button.setForeground(fgColor);
            }
        });
        return button;
    }

}


