package bank.template;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreateAccount{
	
	JFrame frame;
	
	CreateAccount() {
		
		
		frame = new JFrame("Create an Account"); // set the title of the JFrame
        JPanel mainPanel = new JPanel(new BorderLayout()); // create a new JPanel with BorderLayout
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // add padding to the panel
        mainPanel.setBackground(Color.CYAN); // set the background color of the panel
        frame.add(mainPanel);
        
        // create a JLabel for the title and set its font and size
        JLabel titleLabel = new JLabel("Create an Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        String[] accountTypes = {"Student", "Salary"};
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(accountTypes);
        JComboBox comboBox = new JComboBox(comboModel);
       // JComboBox<String> accountTypeComboBox = new JComboBox<>(accountTypes);
        
        JTextField fullNameField = new JTextField();
        JTextField dobField= new JTextField();
        JTextField  addressField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField  initialDepositField= new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        formPanel.add(new JLabel("Account Type:"));
        formPanel.add(comboBox);
        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(fullNameField);
        formPanel.add(new JLabel("Date of Birth:"));
        formPanel.add(dobField);
        formPanel.add(new JLabel("Gender:"));
        formPanel.add(male);
        formPanel.add(new JLabel(""));
            
            formPanel.add(female);
            formPanel.add(new JLabel("Address:"));
            formPanel.add(addressField);
            formPanel.add(new JLabel("Phone:"));
            formPanel.add(phoneField);
            formPanel.add(new JLabel("Initial Deposit:"));
            formPanel.add(initialDepositField);
            formPanel.add(new JLabel("Email:"));
            formPanel.add(emailField);
            formPanel.add(new JLabel("Password:"));
            formPanel.add(passwordField);
            
            
            JPanel additionalFieldPanel = new JPanel(new GridLayout(0, 2));
            JTextField additionalField = new JTextField();
            comboBox.addActionListener(e -> {
            	String selectedAccountType = (String) comboBox.getSelectedItem();
            	additionalFieldPanel.removeAll();
            	if (selectedAccountType.equals("Salary")) {
            		additionalFieldPanel.add(new JLabel("Salary:"));
            		additionalFieldPanel.add(additionalField);
            	} else if (selectedAccountType.equals("Student")) {
            		additionalFieldPanel.add(new JLabel("Institution:"));
            		additionalFieldPanel.add(additionalField);
            	}
            	additionalFieldPanel.revalidate();
            	additionalFieldPanel.repaint();
            });
            
            JPanel buttonPanel = new JPanel();
            JButton registerButton = new JButton("Register");
            JButton goBackButton = new JButton("Go Back");
            buttonPanel.add(registerButton);
            buttonPanel.add(goBackButton);
            
            // JPanel mainPanel = new JPanel(new BorderLayout());
            // mainPanel.add(titlePanel, BorderLayout.NORTH);
            mainPanel.add(formPanel, BorderLayout.CENTER);
            mainPanel.add(additionalFieldPanel, BorderLayout.SOUTH);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            frame.setSize(600, 1400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            registerButton.addActionListener(e -> {
            	// get input values from text fields
            	String fullName = fullNameField.getText();
            	String dob = dobField.getText();
            	String gender = "";
            	if (male.isSelected()) {
            		gender = "Male";
            	}
            	else if (female.isSelected()) {
            		gender = "Female";
            	}
            	String address = addressField.getText();
                String phone = phoneField.getText();
	            String initialDeposit = initialDepositField.getText();
	            String email = emailField.getText();
	            String additionalFieldText = additionalField.getText();
	            String password = String.valueOf(passwordField.getPassword());
	            

            // check if any field is empty

		            if (fullName.trim().isEmpty() || dob.trim().isEmpty() || gender.trim().isEmpty() ||
		                    address.trim().isEmpty() || phone.trim().isEmpty() || initialDeposit.trim().isEmpty() ||
		                    email.trim().isEmpty() || password.trim().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
		                
		            }
		            
		            String accountType = (String) comboBox.getSelectedItem();
		            String folderPath = "D:\\Users\\Sakib\\eclipse-workspace\\Banking System\\" + accountType;
		            String fileName = fullName + ".txt";
		            String filePath = folderPath + "\\" + fileName;

		            // create the file and write the input values to it
		            try {
		                // create the folder if it doesn't exist
		                File folder = new File(folderPath);
		                if (!folder.exists()) {
		                    folder.mkdir();
		                }

		                // create the file and write the input values to it
		               
		                
		                int accountNumber = 0;
		                String accountStr = "";
		                try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Users\\Sakib\\eclipse-workspace\\Banking System\\AccountNo.txt"))) {
		                    accountStr = reader.readLine();
		                	accountNumber = Integer.parseInt(accountStr);
		                } catch (IOException ex) {
			                ex.printStackTrace();
		                }
		                accountNumber++;
		                accountStr = String.valueOf(accountNumber);
		                FileWriter writer = new FileWriter(filePath);
		                writer.write(fullName + "\n");
		                writer.write(dob + "\n");
		                writer.write(gender + "\n");
		                writer.write(address + "\n");
		                writer.write(phone + "\n");
		                writer.write(initialDeposit + "\n");
		                writer.write(email + "\n");
		                //writer.write(additionalFieldText + "\n");
		                writer.write(accountNumber + "\n");
		                writer.write(password);
		                writer.close();
		                
		                JOptionPane.showMessageDialog(null, "Registration successful. Your Account number is:" + accountStr, "Success", JOptionPane.INFORMATION_MESSAGE);
		                
		                try (FileWriter writer2 = new FileWriter("D:\\Users\\Sakib\\eclipse-workspace\\Banking System\\AccountNo.txt")) {
		                    
		                	writer2.write(accountStr);
		                    writer2.close();
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		                
		            } catch (IOException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error writing to file.", "Error", JOptionPane.ERROR_MESSAGE);
		            }

		            BankHomePage bankHomePage = new BankHomePage();
		            frame.dispose();
		            bankHomePage.frame.setVisible(true);
	            

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
        
        
        
