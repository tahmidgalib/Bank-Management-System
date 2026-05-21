package bank.features;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class WithdrawWindow extends JFrame {
	
	private String filePath;
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton withdrawButton;
    private double currentBalance;
    private double newBalance;
    
    public WithdrawWindow(String filePath) throws IOException {
    	this.filePath = filePath;
        setTitle("Withdraw Window");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        amountLabel = new JLabel("Enter amount to withdraw:");
        amountField = new JTextField(10);
        withdrawButton = new JButton("Withdraw");
        
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        panel.add(inputPanel);
        panel.add(withdrawButton);

        add(panel);
        setVisible(true);
         
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = amountField.getText();
                if (amountStr.isEmpty()) {
                    JOptionPane.showMessageDialog(WithdrawWindow.this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(WithdrawWindow.this, "Please enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
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
                        		JOptionPane.showMessageDialog(null, "Withdrawal failed. Insufficient funds.", "Withdrawal failed", JOptionPane.ERROR_MESSAGE);
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
                
                JOptionPane.showMessageDialog(null, "Withdrawal successful. New balance: " + newBalance, "Withdrawal successful", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
    }
}
