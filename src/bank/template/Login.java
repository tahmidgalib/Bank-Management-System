package bank.template;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import bank.features.AccountInfoWindow;

public class Login {
	
	JFrame frame;
	static String name;
	private String fileName;
	static File file;
	
	
	
	Login() {
		frame = new JFrame("Login"); // set the title of the JFrame
        JPanel mainPanel = new JPanel(new BorderLayout()); // create a new JPanel with BorderLayout
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // add padding to the panel
        mainPanel.setBackground(Color.CYAN); // set the background color of the panel
        frame.add(mainPanel);
        
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        
        String[] accountTypes = {"Student", "Salary"};
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(accountTypes);
        JComboBox comboBox = new JComboBox(comboModel);
        
        JTextField fullNameField = new JTextField();
        JTextField accountField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        
        formPanel.add(new JLabel("Account Type:"));
        formPanel.add(comboBox);
        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(fullNameField);
        formPanel.add(new JLabel("Account Number:"));
        formPanel.add(accountField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton goBackButton = new JButton("Go Back");
        buttonPanel.add(loginButton);
        buttonPanel.add(goBackButton);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String type = (String) comboBox.getSelectedItem();
				String storedAccountNumber;
				String storedPassword;
				
				
				
				if (type.equalsIgnoreCase("Salary")) {

					name = fullNameField.getText();
					

					fileName = "D:\\Users\\Sakib\\eclipse-workspace\\Banking System\\Salary\\" + name + ".txt";
					file = new File(fileName);
					
					if (!file.exists()) {
						JOptionPane.showMessageDialog(null,"Account does not exist. Please create an account first.", "Login Failed", JOptionPane.ERROR_MESSAGE);
						return;
					}
					boolean loggedIn = false;
					boolean accountNumberMatch = false;
					boolean passwordMatch = false;
					while (!loggedIn) {

						String accountNumber = accountField.getText();
						String password = String.valueOf(passwordField.getPassword());
						
						try {
							
							BufferedReader reader = new BufferedReader(new FileReader(file));
							String line = null;
							int lineNumber = 0;
							
							while ((line = reader.readLine()) != null) {
								
								
								lineNumber++;
								if (lineNumber == 8) {
									storedAccountNumber = line;
									if (accountNumber.equals(storedAccountNumber)) {
										accountNumberMatch = true;
									}
								}
								if (lineNumber == 9) {
									storedPassword = line;
									if (password.equals(storedPassword)) {
										passwordMatch = true;
									}
								}
							}
							
							reader.close();
							
							if (accountNumberMatch && passwordMatch) {
								JOptionPane.showMessageDialog(null, "Login successful. Welcome " + name, "Success", JOptionPane.INFORMATION_MESSAGE);
								loggedIn = true;

								UserDashboard userDashboard = new UserDashboard(fileName);
								frame.dispose();
								userDashboard.frame.setVisible(true);
								
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect account number or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
								accountNumberMatch = false;
								passwordMatch = false;
								return;
							}
						
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null,"Account does not exist. Please create an account first.", "Login Failed", JOptionPane.ERROR_MESSAGE);
							return;
						} catch (IOException e2) {
					        e2.printStackTrace();
					    }
					}
				}
				else if (type.equalsIgnoreCase("Student")) {
					
					name = fullNameField.getText();
					

					fileName = "D:\\Users\\Sakib\\eclipse-workspace\\Banking System\\Student\\" + name + ".txt";
					file = new File(fileName);
					
					if (!file.exists()) {
						JOptionPane.showMessageDialog(null,"Account does not exist. Please create an account first.", "Login Failed", JOptionPane.ERROR_MESSAGE);
						return;
					}
					boolean loggedIn = false;
				boolean accountNumberMatch = false;
				boolean passwordMatch = false;

						String accountNumber = accountField.getText();
						String password = String.valueOf(passwordField.getPassword());
						
						try {
							
							BufferedReader reader = new BufferedReader(new FileReader(file));
							String line = null;
							int lineNumber = 0;
							
							while ((line = reader.readLine()) != null) {
								
								
								lineNumber++;
								if (lineNumber == 8) {
									storedAccountNumber = line;
									if (accountNumber.equals(storedAccountNumber)) {
										accountNumberMatch = true;
									}
								}
								if (lineNumber == 9) {
									storedPassword = line;
									if (password.equals(storedPassword)) {
										passwordMatch = true;
									}
								}
							}
							
							reader.close();
							
							if (accountNumberMatch && passwordMatch) {
								JOptionPane.showMessageDialog(null, "Login successful. Welcome " + name, "Success", JOptionPane.INFORMATION_MESSAGE);
								loggedIn = true;

								UserDashboard userDashboard = new UserDashboard(fileName);
								frame.dispose();
								userDashboard.frame.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Incorrect account number or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
								accountNumberMatch = false;
								passwordMatch = false;
								return;
							}
						
						} catch (FileNotFoundException e3) {
							JOptionPane.showMessageDialog(null,"Account does not exist. Please create an account first.", "Login Failed", JOptionPane.ERROR_MESSAGE);
							return;
						} catch (IOException e2) {
					        e2.printStackTrace();
					    }
					
					}
					
				}
			
			
		});
        
        goBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				BankHomePage bankHomePage = new BankHomePage();
	            frame.dispose();
	            bankHomePage.frame.setVisible(true);
				
			}
		});
        
	}
}
