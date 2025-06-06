import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Login extends JFrame {
    // JFrame main panels
    JPanel loginNavBar;
    JPanel loginMainForm;
    JPanel loginFooterForm;

    // Navbar buttons
    JButton btnLoginNav;
    JButton btnRegisterNav;

    // Login and register button
    JButton btnLogin;
    JButton btnRegister;

    // Main Panel subpanel
    JPanel plLogin;
    JPanel plRegister;

    // For Login page
    JLabel lbPassword;
    JLabel lbUsername;
    JTextField txtUsername;
    JPasswordField pwdLogin;

    // For Register page
    JLabel lbPasswordRegister;
    JLabel lbUsernameRegister;
    JLabel lbConfirm;
    JTextField txtUsernameRegister;
    JPasswordField pwdRegister;
    JPasswordField pwdConfirm;

    // Background colors
    // Background Colors - Beige Theme
    Color bgMain = new Color(0xFFF8F0);
    Color bgSecondary = new Color(0xF5EBDD);
    Color bgTertiary = new Color(0xEADAC8);

    // Text Colors
    Color clrPrimary = new Color(0x3E3B32);
    Color clrSecondary = new Color(0x7B6F5A);
    Color clrTertiary = new Color(0xB8AFA0);
    // Accent Color
    Color clrBeige = new Color(0xA77B5A);

    public static boolean initialized = false;

    // Temporary storing system (since database is not allowed)
    public static ArrayList<String> USERS = new ArrayList<>();
    public static ArrayList<String> PASSWORD = new ArrayList<>();
    public static int sessionID;
    public static String currentUser;

    public static ArrayList<ArrayList<String>> lsJournalContents = new ArrayList<>();
    public static ArrayList<ArrayList<String>> lsJournalEntriesDate = new ArrayList<>();
    public static ArrayList<ArrayList<String>> lsJournalTitles = new ArrayList<>();


    // MAIN SETUP
    Login() {
        // Basic setup
        setSize(1150,710);
        setMinimumSize(new Dimension(1150,710));
//        setResizable(false);
        getContentPane().setBackground(bgMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ReflectNote: Your mind, beautifully organized.");

        // Icon Logo
        ImageIcon icon = new ImageIcon("src/Icon/logo.png");
        setIconImage(icon.getImage());

        // Set layout
        setLayout(new BorderLayout(5,5));

        // Creating the panel
        loginNavBar = new JPanel();
        loginMainForm = new JPanel(new CardLayout());
        loginFooterForm = new JPanel(new BorderLayout());

        // Set Color to the panel
        loginNavBar.setBackground(bgSecondary);
        loginMainForm.setBackground(bgMain);
        loginFooterForm.setBackground(bgTertiary);

        // Set Size
        loginFooterForm.setPreferredSize(new Dimension(0, 50));

        // Adding and setting panel border location
        add(loginNavBar, BorderLayout.NORTH);
        add(loginMainForm, BorderLayout.CENTER);
        add(loginFooterForm, BorderLayout.SOUTH);

        // admin accounts
        if(!initialized) {
            int currentId = setAdminAccount("Admin", "12345678");
            addAdminJournalContent(currentId, "2025-04-28", "Our first proposal is Rejected!", "4-28-2025 \n\n The project that was first declined is Readability and cipher which is cool project however we though t tahat the project would be just a CLI and maybe being rejected wasn't that bad after all.");
            addAdminJournalContent(currentId, "2025-05-17", "About to be done", "2025-05-17 \n\n As of writing this, developers are currently writing the admin account to mimick what it would look like to have an entry on different days.");
            addAdminJournalContent(currentId, "2025-05-21", "Added a \"Responsive design\"", "2025-05-21 \n\n This is during exam day exactly 11:27 as of updating the admin account and added a responsive like feature to make the app clean like it would get the max size of window and if the current app window is equal to that then change the font size by 40%(as of writing maybe it would change)");
            addAdminJournalContent(currentId, "2025-05-24", "System is don", "2025-05-24 \n\n The system is Complete \n Start Date: April 29 2025\n Completion Date: May 24 2025 \n Average Development Time per Day: 5 hours  Estimated overall: ~120 hours\n \"I will give thanks to you, Lord, with all my heart; I will tell of all your wonderful deeds.\" \n– Psalm 9:1\n This verse serves as a powerful reminder to remain thankful. As we close this chapter, we reflect on the dedication and grace that made this possible. This system is not just a technical achievement, but a reflection of purpose, perseverance, and growth.");


            currentId = setAdminAccount("Canzana", "LifeIsFun");
            addAdminJournalContent(currentId, "2024-04-17", "EcoSense: Capstone Project Defended!", "2024-04-17 \n\n The project that was not in option on three accepted title, I'm not even agreeing to do AI base trash bin. Since creating AI base trash bin would be close to impossible as a shs. But my groupmates is much more ambitious, and thank to that we have a project that gave us a project to be proud of.");

            addAdminJournalContent(currentId, "2025-05-26", "Why I Picked CompSci", "2025-05-26 \n\n I pick Computer science not because there's no option, I'm here because I genuinely enjoy not just programming but also math, calculus and theory. It's not just about maintaining but about how much we can push and create an efficient solution not just in programming but our general problem solving thinking. \n Many people ask why not IT or CompE? IT grads become sys admins, tech support, network engineers; CS grads become AI engineers, Data scientist, or researchers;And CompE grad become Embedded Systems Engineer, Chip Designer, Firmware Engineer. While they all can be software engineer, I kinda like the skill set and job of cs although I also like the compE hardware but I would probably study it in my free time just cuase it 's cool.");

            currentId = setAdminAccount("Ferrer", "12345678");
            addAdminJournalContent(currentId, "2025-05-26", "PADAYON", "Alam kong malayo pa, but i’m willing to wait, be patient okay? because everything has the right moment. kaya wag tayong susuko ha?");

            currentId = setAdminAccount("Dichoso", "88888888");
            addAdminJournalContent(currentId, "2025-05-26", "SANA", "Sana pumasa this Defense");
            initialized = true;
        }

        // Different main page layout (card layout)
        loginLayout();
        registerLayout();

        // Navigation bar call
        setNavigationBar();

        // Footer
        JLabel lbFooter = new JLabel("Ferrer - Canzana - Dichoso  |  ReflectNote © 2025", JLabel.CENTER);
        lbFooter.setForeground(clrTertiary);
        setLabelFontSize(lbFooter, "SansSerif", Font.PLAIN, 12);
        loginFooterForm.add(lbFooter, BorderLayout.CENTER);

        // Center the frame
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }


    // NAVIGATION BAR
    private void setNavigationBar(){
        // Set navigation bar layout to FlowLayout (left aligned)
        loginNavBar.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 13));

        // Create the title
        JLabel title = new JLabel("ReflectNote ||  ");
        title.setForeground(clrPrimary);
        setLabelFontSize(title, "Segoe UI", Font.BOLD, 30);

        // Create the buttons
        btnLoginNav = createNavButton("Login");
        btnRegisterNav = createNavButton("Register");

        // Calling button action functionality
        btnLoginNavAction();
        btnRegisterNavAction();

        // Add title and buttons directly to the navigation bar
        loginNavBar.add(title);
        loginNavBar.add(btnLoginNav);
        loginNavBar.add(btnRegisterNav);

    }

    // CONTENT PANEL SETUP
    private void loginLayout() {

        plLogin = new JPanel(new GridLayout(0,2,3,3));
        plLogin.setBackground(bgMain);

        //Left side
        ImageIcon poster = new ImageIcon("src/Icon/poster.png");
        Image scaledImage = poster.getImage().getScaledInstance(650, 900, Image.SCALE_SMOOTH);
        ImageIcon scaledPoster = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledPoster);
        plLogin.add(imageLabel);

        //RIght side
        // Create main login panel with GridLayout
        JPanel plRight = new JPanel(new GridBagLayout());
        plRight.setBackground(bgMain);

        // GBC is basically like a cursor to change the position,size,anchor, and padding
        GridBagConstraints gbc = new GridBagConstraints();

        // Page title
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10,0,10,0); //top,left, bottom, right
        JLabel lbLoginPage = setPageTitle("Login Page");
        plRight.add(lbLoginPage, gbc);

        // Username label
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        lbUsername = createContentLabel("Username:");
        plRight.add(lbUsername, gbc);

        // Username textbox
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        txtUsername = createContentTextField();
        plRight.add(txtUsername,gbc);

        // Password Label
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        lbPassword = createContentLabel("Password:");
        plRight.add(lbPassword, gbc);

        // Password input field
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        pwdLogin = createContentPasswordField();
        plRight.add(pwdLogin, gbc);

        //Login Button
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.NORTH;
        btnLogin = createConfirmButton("Login");
        plRight.add(btnLogin,gbc);

        plLogin.add(plRight);

        // action for submitting
        btnLoginAction();

        // Add login panel to CardLayout container
        loginMainForm.add(plLogin, "LOGIN");
    }


    // Register layout
    private void registerLayout(){
        plRegister = new JPanel(new GridLayout(0,2,3,3));
        plRegister.setBackground(bgMain);

        //Left side
        ImageIcon poster = new ImageIcon("src/Icon/poster.png");

        Image scaledImage = poster.getImage().getScaledInstance(650, 900, Image.SCALE_SMOOTH);
        ImageIcon scaledPoster = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledPoster);
        plRegister.add(imageLabel);

        // Right side
        // Create a JPanel for the register screen layout
        JPanel plRight = new JPanel();
        plRight.setLayout(new GridBagLayout());
        plRight.setBackground(bgMain);

        GridBagConstraints gbc = new GridBagConstraints();

        // Setting and adding buttons, text field, and password field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(3,0,3,0); // top,left, bottom, right
        JLabel lblRegisterPage = setPageTitle("Register Page");
        plRight.add(lblRegisterPage, gbc);

        // Username label
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        lbUsernameRegister = createContentLabel("Username: ");
        plRight.add(lbUsernameRegister, gbc);

        // Username text field
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        txtUsernameRegister = createContentTextField();
        plRight.add(txtUsernameRegister, gbc);

        // Password Label
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        lbPasswordRegister = createContentLabel("Password: ");
        plRight.add(lbPasswordRegister, gbc);

        // Register password field
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        pwdRegister = createContentPasswordField();
        plRight.add(pwdRegister, gbc);

        // Confirm label
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        lbConfirm = createContentLabel("Confirm Password: ");
        plRight.add(lbConfirm, gbc);

        // Confirm password field
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        pwdConfirm = createContentPasswordField();
        plRight.add(pwdConfirm, gbc);

        // Register button
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.NORTH;
        btnRegister = createConfirmButton("Register");
        plRight.add(btnRegister, gbc);
        plRegister.add(plRight);

        // Action Button for submitting
        btnRegisterAction();

        loginMainForm.add(plRegister, "REGISTER");
    }



    // BUTTON ACTIONS
    private void btnLoginNavAction() {
        btnLoginNav.addActionListener(e -> {
            CardLayout cl = (CardLayout) loginMainForm.getLayout();
            cl.show(loginMainForm, "LOGIN");
            // System.out.println("login");
        });
    }

    private void btnRegisterNavAction() {
        btnRegisterNav.addActionListener(e -> {
            CardLayout cl = (CardLayout) loginMainForm.getLayout();
            cl.show(loginMainForm, "REGISTER");
            // System.out.println("register");
        });
    }

    private void btnLoginAction(){
        btnLogin.addActionListener(e -> {

            String username = txtUsername.getText().trim(); // We used trim to remove white space at the end
            String password = new String(pwdLogin.getPassword());

            // Check if all form is filled up
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both your username and password.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Finding if user exist
            int index = 0; // for counting what index is target username for password
            boolean foundUser = false;
            for (String user : USERS){
                if(username.equals(user)){
                    foundUser = true;
                    break;
                }
                index++;
            }
            // Checker
            if (!foundUser) {
                JOptionPane.showMessageDialog(null, "We couldn't find an account with that username.", "User Not Found", JOptionPane.WARNING_MESSAGE);
                return;
            } else if(!password.equals(PASSWORD.get(index))){
                JOptionPane.showMessageDialog(null, "The password you entered is incorrect.", "Incorrect Password", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // assigning what user index is selected
            sessionID = index;
            currentUser = USERS.get(index);

            EventQueue.invokeLater(()->{
                // For developing puposes
//                System.out.println("===== From Login ====");
//                System.out.println("User: "  + currentUser);
//                System.out.println("All list: \n" + lsJournalContents);
//                System.out.println("User content List: " + lsJournalContents.get(sessionID));
//                System.out.println("===== From Login ====");
                new App();
            });

            dispose();
        });
    }

    private void btnRegisterAction(){
        btnRegister.addActionListener(e -> {
            // Getting data from the text box after pressed
            String username = txtUsernameRegister.getText().trim(); // used trim to remove whitespace
            String password = new String(pwdRegister.getPassword());
            String confirmPassword = new String(pwdConfirm.getPassword());

            // Check if all form is filled up
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please complete all fields to register.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Requirement checking
            for (String user : USERS){
                if (username.equals(user)){
                    JOptionPane.showMessageDialog(null, "That username is already taken. Please choose another.", "Username Unavailable", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (password.length() < 8){
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Password Too Short", JOptionPane.WARNING_MESSAGE);
                return;
            }else if (!password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(null, "Your passwords don’t match. Please try again.", "Password Mismatch", JOptionPane.WARNING_MESSAGE);
                return;
            }

            USERS.add(username);
            PASSWORD.add(password);

            // Intializing the new contents
            lsJournalContents.add(new ArrayList<>());
            lsJournalTitles.add(new ArrayList<>());
            lsJournalEntriesDate.add(new ArrayList<>());

            JOptionPane.showMessageDialog(null, "Your Account is successfully registered", "Successful", JOptionPane.INFORMATION_MESSAGE);

            // Direct the user to the login form
            CardLayout cl = (CardLayout) loginMainForm.getLayout();
            cl.show(loginMainForm, "LOGIN");

            // for developing purposes
//            System.out.println(USERS);
//            System.out.println(PASSWORD);


        });
    }

    // HELPERS
    private JButton createNavButton(String text) {

        // Creating nav button aesthetics
        JButton button = new JButton(text);
        button.setBackground(bgSecondary);
        button.setForeground(clrSecondary);
        setButtonFontSize(button, "Segoe UI", Font.PLAIN, 17);
        button.setBorder(null);

        // In button hover
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.BOLD, button.getFont().getSize() + 1));
                button.setForeground(clrBeige);

            }
            public void mouseExited(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, button.getFont().getSize() - 1));
                button.setForeground(clrSecondary);
            }
        });
        return button;
    }

    private JLabel setPageTitle(String title){
        // Create the label for the home screen
        JLabel label = new JLabel(title);
        setLabelFontSize(label, "Segoe UI", Font.BOLD, 50);
        label.setForeground(clrPrimary);
        return label;
    }

    private JLabel createContentLabel(String text){
        JLabel label = new JLabel(text);
        setLabelFontSize(label, "Segoe UI", Font.PLAIN, 30);
        label.setForeground(clrPrimary);
        return label;
    }

    private JButton createConfirmButton(String text){
        JButton button = new JButton(text);
        setButtonFontSize(button, "Segoe UI", Font.PLAIN, 25);
        button.setSize(new Dimension(100,50));
        button.setForeground(clrPrimary);
        button.setBackground(null);
        button.setOpaque(false);
        return button;
    }

    private JTextField createContentTextField(){
        JTextField txtField = new JTextField();
        int fontSize = 30;
        int ySize = 450;
        int xSize = 60;

        // For full size window
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int maxFontSize = (int) (fontSize * 0.40) + fontSize;
                int maxFieldSizeX = (int) (xSize * 0.40) + xSize;
                int maxFieldSizeY = (int) (ySize * 0.40) + ySize;

                if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    // Window is maximized
                    txtField.setFont(new Font("Segoe UI", Font.PLAIN, maxFontSize));
                    txtField.setPreferredSize(new Dimension(maxFieldSizeY, maxFieldSizeX));

                } else {
                    // Window is in normal state
                    txtField.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
                    txtField.setPreferredSize(new Dimension(ySize, xSize));
                }
            }
        });

        txtField.setBorder(BorderFactory.createLineBorder(clrTertiary,1));
        txtField.setBackground(bgMain);
        return txtField;
    }

    private JPasswordField createContentPasswordField(){
        JPasswordField pwdField = new JPasswordField();

        int fontSize = 30;
        int ySize = 450;
        int xSize = 60;

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int maxFontSize = (int) (fontSize * 0.40) + fontSize;
                int maxFieldSizeX = (int) (xSize * 0.40) + xSize;
                int maxFieldSizeY = (int) (ySize * 0.40) + ySize;

                if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    // Window is maximized
                    pwdField.setFont(new Font("Segoe UI", Font.PLAIN, maxFontSize));
                    pwdField.setPreferredSize(new Dimension(maxFieldSizeY, maxFieldSizeX));
                } else {
                    // Window is in normal state
                    pwdField.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
                    pwdField.setPreferredSize(new Dimension(ySize, xSize));
                }
            }
        });
        pwdField.setBorder(BorderFactory.createLineBorder(clrTertiary,1));
        pwdField.setBackground(bgMain);
        return pwdField;
    }

    private int setAdminAccount(String username, String password){
        USERS.add(username);
        PASSWORD.add(password);
        lsJournalContents.add(new ArrayList<>());
        lsJournalTitles.add(new ArrayList<>());
        lsJournalEntriesDate.add(new ArrayList<>());
        return USERS.size() - 1;
    }

    private void addAdminJournalContent(int userID, String date, String title, String contents){
        lsJournalEntriesDate.get(userID).add(date);
        lsJournalTitles.get(userID).add(title);
        lsJournalContents.get(userID).add(contents);
    }

    private void setLabelFontSize(JLabel label, String font, int fontStyle, int size) {
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int maxSize = (int) (size * 0.40) + size;

                if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    // Window is maximized
                    label.setFont(new Font(font, fontStyle, maxSize));
                    loginFooterForm.setPreferredSize(new Dimension(0, 60));
                } else {
                    // Window is in normal state
                    label.setFont(new Font(font, fontStyle, size));
                    loginFooterForm.setPreferredSize(new Dimension(0, 50));
                }
            }
        });
    }

    private void setButtonFontSize(JButton button, String font, int fontStyle, int size) {
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
}
