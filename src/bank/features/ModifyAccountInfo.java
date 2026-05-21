package bank.features;
import javax.swing.*;

import bank.template.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

public class ModifyAccountInfo{
	
	public JFrame frame;
	private String filePath;
	
	public ModifyAccountInfo(String filePath) {
		
		this.filePath = filePath;
		frame = new JFrame("Modify Account Info"); // set the title of the JFrame
        JPanel mainPanel = new JPanel(new BorderLayout()); // create a new JPanel with BorderLayout
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // add padding to the panel
        mainPanel.setBackground(Color.WHITE); // set the background color of the panel
        frame.add(mainPanel);
        
        // create a JLabel for the title and set its font and size
        JLabel titleLabel = new JLabel("Modify Account Info", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        
        JTextField fullNameField = new JTextField();
        JTextField dobField= new JTextField();
        JTextField  addressField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField  initialDepositField= new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(fullNameField);
        formPanel.add(new JLabel("Date of Birth:"));
        formPanel.add(dobField);

            formPanel.add(new JLabel("Address:"));
            formPanel.add(addressField);
            formPanel.add(new JLabel("Phone:"));
            formPanel.add(phoneField);
//            formPanel.add(new JLabel("Initial Deposit:"));
//            formPanel.add(initialDepositField);
            formPanel.add(new JLabel("Email:"));
            formPanel.add(emailField);
            formPanel.add(new JLabel("Password:"));
            formPanel.add(passwordField);
            
            
            JPanel additionalFieldPanel = new JPanel(new GridLayout(0, 2));
            JTextField additionalField = new JTextField();

            
            JPanel buttonPanel = new JPanel();
            JButton modifyButton = new JButton("Modify");
            JButton goBackButton = new JButton("Go Back");
            buttonPanel.add(modifyButton);
            buttonPanel.add(goBackButton);
            
            // JPanel mainPanel = new JPanel(new BorderLayout());
            // mainPanel.add(titlePanel, BorderLayout.NORTH);
            mainPanel.add(formPanel, BorderLayout.CENTER);
            mainPanel.add(additionalFieldPanel, BorderLayout.SOUTH);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            frame.setSize(600, 1400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            modifyButton.addActionListener(e -> {
            	// get input values from text fields
            	String fullName = fullNameField.getText();
            	String dob = dobField.getText();
            	
            	
            	String address = addressField.getText();
                String phone = phoneField.getText();
	            String initialDeposit = initialDepositField.getText();
	            String email = emailField.getText();
	            String additionalFieldText = additionalField.getText();
	            String password = String.valueOf(passwordField.getPassword());
	            

            // check if any field is empty

		            if (fullName.trim().isEmpty() || dob.trim().isEmpty() ||
		                    address.trim().isEmpty() || phone.trim().isEmpty() ||
		                    email.trim().isEmpty() || password.trim().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
		                
		            }
		            
		            String fileName = fullName + ".txt";
		            String filePath2 = filePath + "\\" + fileName;

		            // create the folder if it doesn't exist
					File folder = new File(filePath2);
					if (!folder.exists()) {
					    folder.mkdir();
					}

					// create the file and write the input values to it
            
					
					File mainFile = new File(filePath);
					
					try 
					{
						                	
						FileReader fr = new FileReader(filePath);
						BufferedReader br = new BufferedReader(fr);
					    
						String line;
					    int lineNum = 1;
					    
					    StringBuilder fileOutput = new StringBuilder();
					    
					    while ((line = br.readLine()) != null) {
					    	
					    	if (lineNum == 1) {
					    		fileOutput.append(fullName);
					        	fileOutput.append("\n");
					    	} else if (lineNum == 2) {
					        	
					    		fileOutput.append(dob);
					        	fileOutput.append("\n");
					        	
					        } else if (lineNum == 4) {
					        	fileOutput.append(address);
					        	fileOutput.append("\n");
					        } else if (lineNum == 5) {
					        	fileOutput.append(phone);
					        	fileOutput.append("\n");
					        } else if (lineNum == 7) {
					        	fileOutput.append(email);
					        	fileOutput.append("\n");
					        } else if (lineNum == 9) {
					        	fileOutput.append(password);
					        	fileOutput.append("\n");
					        } else {
					        	fileOutput.append(line);
					        	fileOutput.append("\n");
					        }
					        
					        lineNum++;    
					    }
					    
					    PrintWriter fileToWrite = new PrintWriter(new BufferedWriter(new FileWriter(mainFile)));
					    fileToWrite.print(fileOutput.toString());
					    fileToWrite.close();
					    
					    String path = mainFile.getParent();
					    path += "\\" + fullName + ".txt";
					    
					    File rename = new File(path);
					    
					    fr.close();
					    br.close();
					    
					    boolean flag = mainFile.renameTo(rename);
					    
					    if (flag == true) {
					    	System.out.println("File Successfully Rename");
					    }
					    else {
					    	System.out.println("Operation Failed");
					    }
					    
					   	//dispose();
					    
					} catch (IOException ex) {
					    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					    return;
					} catch (SecurityException se) {
						System.err.println("SecurityException: " + se.getMessage());
					}
					

					JOptionPane.showMessageDialog(null, "Account info modified successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					


		            frame.dispose();
	            

        });
            
            goBackButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					 
			            frame.dispose();
			           
				}
			});
	}
	

}
        
        
       