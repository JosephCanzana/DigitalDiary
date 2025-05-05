import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    Color bgMain = new Color(0xFFFFFFFF);
    Color bgSecondary = new Color(0xFFF2F2F7);
    Color bgTertiary = new Color(0xFFE5E5EA);

    // Text Colors
    Color textPrimary = new Color(0xFF1C1C1E);
    Color textSecondary = new Color(0xFF8E8E93);


    // MAIN SETUP
    Login() {
        // Basic setup
        setSize(850, 600);
        setMinimumSize(new Dimension(850, 600));
        getContentPane().setBackground(bgMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login");

        // Set layout
        setLayout(new BorderLayout(5,5));

        // Creating the panel
        loginNavBar = new JPanel();
        loginMainForm = new JPanel(new CardLayout());
        loginFooterForm = new JPanel();

        // Set Color to the panel
        loginNavBar.setBackground(bgSecondary);
        loginMainForm.setBackground(bgMain);
        loginFooterForm.setBackground(bgTertiary);

        // Set Size
        loginNavBar.setPreferredSize(new Dimension(100, 65));
        loginMainForm.setPreferredSize(new Dimension(300, 450));
        loginFooterForm.setPreferredSize(new Dimension(50, 50));

        // Adding and setting panel border location
        add(loginNavBar, BorderLayout.NORTH);
        add(loginMainForm, BorderLayout.CENTER);
        add(loginFooterForm, BorderLayout.SOUTH);

        // Different main page layout
        loginLayout();
        registerLayout();

        // Navigation bar call
        setNavigationBar();

        // Center the frame
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }


    // NAVIGATION BAR
    private void setNavigationBar(){
        // Set navigation bar layout to FlowLayout (left aligned)
        loginNavBar.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 10));

        // Create the title
        JLabel title = new JLabel("ReflectNote ||  ");
        title.setForeground(textPrimary);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));

        // Create the buttons
        btnLoginNav = createNavButton("Login");
        btnRegisterNav = createNavButton("Register");

        // Calling button action functionality
        btnLoginNavAction();
        btnRegisterNavAction();
        btnLoginAction();

        // Add title and buttons directly to the navigation bar
        loginNavBar.add(title);
        loginNavBar.add(btnLoginNav);
        loginNavBar.add(btnRegisterNav);

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

            String username = txtUsername.getText();
            char[] passwordChar = pwdLogin.getPassword();

            if (passwordChar.length < 8){
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters!", "Oops", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String password = new String(passwordChar);

            String tmpUsername = "admin";
            String tmpPassword = "01234567";
            if(!tmpUsername.equals(username) || !tmpPassword.equals(password)){
                JOptionPane.showMessageDialog(null, "Password or username invalid", "Oops", JOptionPane.WARNING_MESSAGE);
                return;
            }

            java.awt.EventQueue.invokeLater(App::new);
            dispose();
        });
    }


    // CONTENT PANEL SETUP
    private void loginLayout() {
        // Create main login panel with GridLayout
        plLogin = new JPanel(new GridBagLayout());
        plLogin.setBackground(bgMain);

        // GBC is basically like a cursor to change the position,size,anchor, and padding
        GridBagConstraints gbc = new GridBagConstraints();

        // Page title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5,0,5,0); //top,left, bottom, right
        JLabel lbLoginPage = setPageTitle("Login Page");
        plLogin.add(lbLoginPage, gbc);

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        lbUsername = createContentLabel("Username");
        plLogin.add(lbUsername, gbc);

        // Username textbox
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        txtUsername = createContentTextField();
        plLogin.add(txtUsername,gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        lbPassword = createContentLabel("Password");
        plLogin.add(lbPassword, gbc);

        // Password input field
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        pwdLogin = createContentPasswordField();
        plLogin.add(pwdLogin, gbc);

        //Login Button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        btnLogin = createConfirmButton("Login");
        plLogin.add(btnLogin,gbc);


        // Add login panel to CardLayout container
        loginMainForm.add(plLogin, "LOGIN");
    }


    // Register layout
    private void registerLayout(){
        // Create a JPanel for the register screen layout
        plRegister = new JPanel();
        plRegister.setLayout(new GridBagLayout());
        plRegister.setBackground(bgMain);

        GridBagConstraints gbc = new GridBagConstraints();

        // Setting and adding buttons, text field, and password field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(3,0,3,0); //top,left, bottom, right
        JLabel lblRegisterPage = setPageTitle("Register Page");
        plRegister.add(lblRegisterPage, gbc);

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        lbUsernameRegister = createContentLabel("Username: ");
        plRegister.add(lbUsernameRegister, gbc);

        // Username text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        txtUsernameRegister = createContentTextField();
        plRegister.add(txtUsernameRegister, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        lbPasswordRegister = createContentLabel("Password: ");
        plRegister.add(lbPasswordRegister, gbc);

        // Register password field
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        pwdRegister = createContentPasswordField();
        plRegister.add(pwdRegister, gbc);

        // Confirm label
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        lbConfirm = createContentLabel("Confirm Password: ");
        plRegister.add(lbConfirm, gbc);

        // Confirm password field
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        pwdConfirm = createContentPasswordField();
        plRegister.add(pwdConfirm, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        btnRegister = createConfirmButton("Register");
        plRegister.add(btnRegister, gbc);

        loginMainForm.add(plRegister, "REGISTER");
    }


    // HELPERS
    private JButton createNavButton(String text) {

        // Creating nav button aesthetics
        JButton button = new JButton(text);
        button.setBackground(bgSecondary);
        button.setForeground(Color.DARK_GRAY);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBorder(null);

        // In button hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 19));
                button.setFont(new Font("Segoe UI", Font.BOLD, 20));
                button.setForeground(Color.BLUE);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 19));
                button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                button.setForeground(Color.DARK_GRAY);
            }
        });
        return button;
    }

    private JLabel setPageTitle(String title){
        // Create the label for the home screen
        JLabel label = new JLabel(title);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        label.setForeground(textPrimary);
        return label;
    }

    private JLabel createContentLabel(String text){
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        return label;
    }

    private JButton createConfirmButton(String text){
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        button.setSize(new Dimension(100,50));
        button.setBackground(null);
        button.setOpaque(false);
        return button;
    }

    private JTextField createContentTextField(){
        JTextField text = new JTextField();
        text.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        text.setPreferredSize(new Dimension(500,50));
        return text;
    }

    private JPasswordField createContentPasswordField(){
        JPasswordField password = new JPasswordField();
        password.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        password.setPreferredSize(new Dimension(500,50));
        return password;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(Login::new);
    }
}
