package tapNpay;

import java.awt.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;

public class Home extends javax.swing.JFrame {
    private String userEmail;
    private int customerId;
    private JLabel lblName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JLabel lblCardId;
    private JButton btnGenerateCard;

    public Home(String email) {
        this.userEmail = email;
        initComponents();
        loadUserData();
    }

    private void initComponents() {
        setTitle("TapNpay - Home");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(850, 550);
        setResizable(false);
        setLayout(new BorderLayout());

        // Top Panel with navigation buttons
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(153, 153, 255));
        topPanel.setPreferredSize(new Dimension(850, 80));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JButton btnHome = new JButton("Home");
        btnHome.setPreferredSize(new Dimension(100, 80));
        btnHome.setBackground(new Color(153, 153, 255));
        btnHome.addActionListener(evt -> tabbedPane.setSelectedIndex(0));

        JButton btnAccount = new JButton("Account");
        btnAccount.setPreferredSize(new Dimension(100, 80));
        btnAccount.setBackground(new Color(153, 153, 255));
        btnAccount.addActionListener(evt -> tabbedPane.setSelectedIndex(1));

        JButton btnTransactions = new JButton("Transactions");
        btnTransactions.setPreferredSize(new Dimension(120, 80));
        btnTransactions.setBackground(new Color(153, 153, 255));
        btnTransactions.addActionListener(evt -> tabbedPane.setSelectedIndex(2));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(430, 80));
        
        JButton btnLogout = new JButton("Log out");
        btnLogout.setPreferredSize(new Dimension(110, 60));
        btnLogout.setBackground(new Color(153, 153, 255));
        btnLogout.addActionListener(evt -> {
            Login login = new Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            dispose();
        });
        
        JLabel lblBalance = new JLabel("Current balance = __");
        lblBalance.setForeground(Color.WHITE);
        lblBalance.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        rightPanel.add(lblBalance);
        rightPanel.add(Box.createHorizontalStrut(20));
        rightPanel.add(btnLogout);

        topPanel.add(btnHome);
        topPanel.add(btnAccount);
        topPanel.add(btnTransactions);
        topPanel.add(rightPanel);

        // Tabbed Pane for content
        tabbedPane = new JTabbedPane();

        // Home Tab
        JPanel homePanel = new JPanel(null);
        homePanel.setBackground(new Color(240, 248, 255));

        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(new Color(153, 255, 153));
        cardPanel.setBounds(40, 50, 350, 250);
        cardPanel.setLayout(new GridLayout(5, 1, 5, 10));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblCardTitle = new JLabel("TapNpay Card", JLabel.CENTER);
        lblCardTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        cardPanel.add(lblCardTitle);
        cardPanel.add(new JLabel("User: _______"));
        cardPanel.add(new JLabel("Card ID: _____"));
        cardPanel.add(new JLabel("Exp: DD/YY"));
        cardPanel.add(new JLabel("Address: _____"));

        JButton btnSendMoney = new JButton("Send to card money");
        btnSendMoney.setBounds(450, 50, 250, 60);
        btnSendMoney.setBackground(new Color(100, 149, 237));
        btnSendMoney.setForeground(Color.WHITE);
        btnSendMoney.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnWithdraw = new JButton("Withdraw from card");
        btnWithdraw.setBounds(450, 130, 250, 60);
        btnWithdraw.setBackground(new Color(100, 149, 237));
        btnWithdraw.setForeground(Color.WHITE);
        btnWithdraw.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnSeeTransactions = new JButton("See transactions");
        btnSeeTransactions.setBounds(450, 210, 250, 60);
        btnSeeTransactions.setBackground(new Color(100, 149, 237));
        btnSeeTransactions.setForeground(Color.WHITE);
        btnSeeTransactions.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSeeTransactions.addActionListener(evt -> tabbedPane.setSelectedIndex(2));

        homePanel.add(cardPanel);
        homePanel.add(btnSendMoney);
        homePanel.add(btnWithdraw);
        homePanel.add(btnSeeTransactions);

        // Account Tab
        JPanel accountPanel = new JPanel();
        accountPanel.setBackground(new Color(240, 248, 255));
        accountPanel.setLayout(new BorderLayout());
        accountPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // Account info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6, 2, 15, 15));
        infoPanel.setBackground(new Color(240, 248, 255));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Name
        JLabel lblNameTitle = new JLabel("Full Name:");
        lblNameTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNameTitle.setForeground(new Color(51, 153, 255));
        
        lblName = new JLabel("____________________");
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblName.setForeground(new Color(0, 0, 0));

        // Email
        JLabel lblEmailTitle = new JLabel("Email:");
        lblEmailTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblEmailTitle.setForeground(new Color(51, 153, 255));
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtEmail.setEditable(false);
        txtEmail.setBackground(new Color(240, 240, 240));

        // Phone
        JLabel lblPhoneTitle = new JLabel("Phone Number:");
        lblPhoneTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPhoneTitle.setForeground(new Color(51, 153, 255));
        
        txtPhone = new JTextField();
        txtPhone.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPhone.setToolTipText("Enter your phone number");

        // Card ID
        JLabel lblCardIdTitle = new JLabel("Card ID:");
        lblCardIdTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCardIdTitle.setForeground(new Color(51, 153, 255));
        
        lblCardId = new JLabel("Not Generated");
        lblCardId.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCardId.setForeground(new Color(0, 0, 0));
        
        // Update Account button
        JButton btnUpdateAccount = new JButton("Update Account");
        btnUpdateAccount.setBackground(new Color(0, 102, 102));
        btnUpdateAccount.setForeground(Color.WHITE);
        btnUpdateAccount.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnUpdateAccount.addActionListener(evt -> {
            String phoneNumber = txtPhone.getText().trim();
            if (phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a phone number!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!phoneNumber.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit phone number!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                updatePhoneNumber(phoneNumber);
            }
        });
        
        // Generate Card ID button
        btnGenerateCard = new JButton("Generate Card");
        btnGenerateCard.setBackground(new Color(0, 102, 102));
        btnGenerateCard.setForeground(Color.WHITE);
        btnGenerateCard.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGenerateCard.addActionListener(evt -> {
            generateCardId();
        });

        infoPanel.add(lblNameTitle);
        infoPanel.add(lblName);
        infoPanel.add(lblEmailTitle);
        infoPanel.add(txtEmail);
        infoPanel.add(lblPhoneTitle);
        infoPanel.add(txtPhone);
        infoPanel.add(lblCardIdTitle);
        infoPanel.add(lblCardId);
        infoPanel.add(new JLabel());
        infoPanel.add(btnUpdateAccount);
        infoPanel.add(new JLabel());
        infoPanel.add(btnGenerateCard);
        
        accountPanel.add(infoPanel, BorderLayout.CENTER);

        // Transactions Tab
        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setBackground(new Color(240, 248, 255));
        transactionsPanel.setLayout(new BorderLayout());
        
        JLabel lblTransactions = new JLabel("Transaction History", JLabel.CENTER);
        lblTransactions.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTransactions.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        transactionsPanel.add(lblTransactions, BorderLayout.NORTH);
        
        JTextArea txtTransactions = new JTextArea("No transactions yet.");
        txtTransactions.setEditable(false);
        txtTransactions.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTransactions.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(txtTransactions);
        transactionsPanel.add(scrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("Home", homePanel);
        tabbedPane.addTab("Account", accountPanel);
        tabbedPane.addTab("Transactions", transactionsPanel);

        add(topPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
    }

    private void loadUserData() {
        String url = "jdbc:mysql://localhost:3306/tapnpay5";
        String dbUser = "root";
        String dbPassword = "";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            
            // Get customer info
            String query = "SELECT id, FULL_NAME FROM CUSTOMER_INFO WHERE Email = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userEmail);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                customerId = rs.getInt("id");
                lblName.setText(rs.getString("FULL_NAME"));
                txtEmail.setText(userEmail);
            }
            rs.close();
            pst.close();
            
            // Get account info if exists
            String accountQuery = "SELECT phone_number, card_id FROM Account WHERE customer_id = ?";
            PreparedStatement pst2 = conn.prepareStatement(accountQuery);
            pst2.setInt(1, customerId);
            ResultSet rs2 = pst2.executeQuery();
            
            boolean hasPhone = false;
            boolean hasCardId = false;
            
            if (rs2.next()) {
                String phone = rs2.getString("phone_number");
                String cardId = rs2.getString("card_id");
                
                if (phone != null && !phone.isEmpty()) {
                    txtPhone.setText(phone);
                    hasPhone = true;
                }
                if (cardId != null && !cardId.isEmpty()) {
                    lblCardId.setText(cardId);
                    btnGenerateCard.setEnabled(false);
                    hasCardId = true;
                }
            }
            
            rs2.close();
            pst2.close();
            conn.close();
            
            // Check if account details are incomplete
            if (!hasPhone || !hasCardId) {
                SwingUtilities.invokeLater(() -> {
                    int result = JOptionPane.showConfirmDialog(this, 
                        "Please complete your account details!\nWould you like to fill them now?", 
                        "Account Details Required", 
                        JOptionPane.OK_CANCEL_OPTION, 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        tabbedPane.setSelectedIndex(1); // Switch to Account tab
                    }
                });
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updatePhoneNumber(String phoneNumber) {
        String url = "jdbc:mysql://localhost:3306/tapnpay5";
        String dbUser = "root";
        String dbPassword = "";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            
            // Check if account exists
            String checkQuery = "SELECT id FROM Account WHERE customer_id = ?";
            PreparedStatement pst = conn.prepareStatement(checkQuery);
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Update existing account
                String updateQuery = "UPDATE Account SET phone_number = ? WHERE customer_id = ?";
                PreparedStatement pst2 = conn.prepareStatement(updateQuery);
                pst2.setString(1, phoneNumber);
                pst2.setInt(2, customerId);
                pst2.executeUpdate();
                pst2.close();
            } else {
                // Create new account
                String insertQuery = "INSERT INTO Account (customer_id, phone_number) VALUES (?, ?)";
                PreparedStatement pst2 = conn.prepareStatement(insertQuery);
                pst2.setInt(1, customerId);
                pst2.setString(2, phoneNumber);
                pst2.executeUpdate();
                pst2.close();
            }
            
            rs.close();
            pst.close();
            conn.close();
            
            JOptionPane.showMessageDialog(this, "Phone number updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating phone number: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void generateCardId() {
        String url = "jdbc:mysql://localhost:3306/tapnpay5";
        String dbUser = "root";
        String dbPassword = "";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            
            // Generate random 16-digit card ID
            Random random = new Random();
            StringBuilder cardId = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                cardId.append(random.nextInt(10));
                if ((i + 1) % 4 == 0 && i < 15) {
                    cardId.append("-");
                }
            }
            String generatedCardId = cardId.toString();
            
            // Check if account exists
            String checkQuery = "SELECT id FROM Account WHERE customer_id = ?";
            PreparedStatement pst = conn.prepareStatement(checkQuery);
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Update existing account
                String updateQuery = "UPDATE Account SET card_id = ? WHERE customer_id = ?";
                PreparedStatement pst2 = conn.prepareStatement(updateQuery);
                pst2.setString(1, generatedCardId);
                pst2.setInt(2, customerId);
                pst2.executeUpdate();
                pst2.close();
            } else {
                // Create new account
                String insertQuery = "INSERT INTO Account (customer_id, card_id) VALUES (?, ?)";
                PreparedStatement pst2 = conn.prepareStatement(insertQuery);
                pst2.setInt(1, customerId);
                pst2.setString(2, generatedCardId);
                pst2.executeUpdate();
                pst2.close();
            }
            
            rs.close();
            pst.close();
            conn.close();
            
            lblCardId.setText(generatedCardId);
            btnGenerateCard.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Card ID generated successfully!\nCard ID: " + generatedCardId, "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating card ID: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new Home("test@example.com").setVisible(true));
    }

    private JTabbedPane tabbedPane;
}

