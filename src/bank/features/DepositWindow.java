package bank.features;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DepositWindow extends JFrame {
	
	private String filePath;
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton depositButton;
    private double currentBalance;
    private double newBalance;
    
    public DepositWindow(String filePath) throws IOException {
    	this.filePath = filePath;
        setTitle("Deposit Window");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        amountLabel = new JLabel("Enter amount to deposit:");
        amountField = new JTextField(10);
        depositButton = new JButton("Deposit");
        
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        panel.add(inputPanel);
        panel.add(depositButton);

        add(panel);
        setVisible(true);
         
    depositButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String amountStr = amountField.getText();
            if (amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(DepositWindow.this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(DepositWindow.this, "Please enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(DepositWindow.this, "Are you sure you want to deposit " + amount + "?", "Confirm Deposit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                //double newBalance = currentBalance + amount;
                
                File mainFile = new File(filePath);
                String tempFilePath = filePath + "temp.txt";
                
                File tempFile = new File(tempFilePath);
                
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
                        	newBalance = currentBalance + amount;
                        	
                        	fileOutput.append(Double.toString(newBalance));
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
                    
                    fr.close();
                    br.close();
                    
                   	dispose();
                    
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (SecurityException se) {
                	System.err.println("SecurityException: " + se.getMessage());
                }
                
                JOptionPane.showMessageDialog(null, "Deposit successful. New balance: " + newBalance, "Deposit successful", JOptionPane.INFORMATION_MESSAGE);
                
            }
        }
    
    });
    
}

}
