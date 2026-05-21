package bank.features;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PayBillWindow extends JFrame {
    
	private String filePath;
    private JLabel institutionLabel, amountLabel;
    private JTextField institutionTextField, amountTextField;
    private JButton payBillButton;
    private double currentBalance;
    private double newBalance;
    
    public PayBillWindow(String filePath) {
    	this.filePath = filePath;
        setTitle("Pay Bill / Send Donation");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // create components
        institutionLabel = new JLabel("Institution:");
        amountLabel = new JLabel("Amount:");
        institutionTextField = new JTextField();
        amountTextField = new JTextField();
        payBillButton = new JButton("Pay Bill / Send Donation");
        
        // create panels
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(institutionLabel);
        inputPanel.add(institutionTextField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountTextField);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(payBillButton);
        
        // add panels to frame
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // display window
        setVisible(true);
        
        payBillButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String amountStr = amountTextField.getText();
				String instStr = institutionTextField.getText();
				if (instStr.isEmpty()) {
                    JOptionPane.showMessageDialog(PayBillWindow.this, "Please enter an institution.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (amountStr.isEmpty()) {
                    JOptionPane.showMessageDialog(PayBillWindow.this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(PayBillWindow.this, "Please enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try 
                {
                	FileReader fr = new FileReader(filePath);
                	BufferedReader br = new BufferedReader(fr);
                    
                	String line;
                    int lineNum = 1;
                    
                    StringBuilder fileOutput = new StringBuilder();
                    
                    while ((line = br.readLine()) != null) {
                    	
                        if (lineNum == 6) {
                        	currentBalance = Double.parseDouble(line);
                        	if (currentBalance < amount) {
                        		JOptionPane.showMessageDialog(null, "Send Money failed. Insufficient funds.", "Sending failed", JOptionPane.ERROR_MESSAGE);
                        		dispose();
                        		return;
                        	}
                        	newBalance = currentBalance - amount;
                        	
                        	fileOutput.append(Double.toString(newBalance));
                        	fileOutput.append("\n");
                        	
                        } else {
                        	fileOutput.append(line);
                        	fileOutput.append("\n");
                        }
                        
                        lineNum++;    
                    }
                    
                    PrintWriter fileToWrite = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
                    fileToWrite.print(fileOutput.toString());
                    fileToWrite.close();
                    
                    fr.close();
                    br.close();
                    
                   	dispose();
                    
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (SecurityException se) {
                	System.err.println("SecurityException: " + se.getMessage());
                }
                
                JOptionPane.showMessageDialog(null, "Successfully sent the Bill/Donation!", "Sending successful", JOptionPane.INFORMATION_MESSAGE);

				
			}
		});
    }
    
}

