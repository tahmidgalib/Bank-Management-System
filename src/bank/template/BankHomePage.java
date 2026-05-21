package bank.template;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankHomePage {
	
	public JFrame frame;
	
    public BankHomePage() {
        
    	frame = new JFrame("Bank Name"); // set the title of the JFrame
    	frame.setSize(800, 600);
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10)); // create a new JPanel with GridLayout
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // add padding to the panel
        panel.setBackground(Color.CYAN); // set the background color of the panel

        // create a JLabel for the title and set its font and size
        JLabel titleLabel = new JLabel("MANGP Bank", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        panel.add(titleLabel);

        // create a JLabel for the welcome message and set its font and size
        JLabel welcomeLabel = new JLabel("Honesty & Integraty & happiness", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(welcomeLabel);

        // create JButtons for the create account, login, and exit buttons
        JButton createAccountButton = new JButton("Create Account");
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");
        panel.add(createAccountButton);
        panel.add(loginButton);
        panel.add(exitButton);

        // set the preferred size for the buttons
        createAccountButton.setPreferredSize(new Dimension(200, 50));
        loginButton.setPreferredSize(new Dimension(200, 50));
        exitButton.setPreferredSize(new Dimension(200, 50));

        // add the panel to the JFrame and set the size and visibility
        frame.add(panel);
        //frame.pack(); // adjust the size of the frame to fit its components
        frame.setLocationRelativeTo(null); // center the JFrame on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        createAccountButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreateAccount createAccount = new CreateAccount();
				frame.dispose();
				createAccount.frame.setVisible(true);
				
			}
		});
        
        loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login login = new Login();
				frame.dispose();
				login.frame.setVisible(true);
				
			}
		});
    }
}
