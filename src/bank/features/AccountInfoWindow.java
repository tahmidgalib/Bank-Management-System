package bank.features;

import bank.template.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;


public class AccountInfoWindow extends JFrame {
	private JTextArea accountInfoText;
	private String fileName;

    public AccountInfoWindow(String fileName) {
    	this.fileName = fileName;
    	
        setTitle("Account Information");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        accountInfoText = new JTextArea();
        accountInfoText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(accountInfoText);

        try {
            // Read the account info from the file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int lineCounter = 0;
            String line = reader.readLine();
            accountInfoText.append("\n\n\n");
            while (line != null) {
            	lineCounter++;
            	if (lineCounter == 1)
            		accountInfoText.append("\t\tName: " + line + "\n");
            	if (lineCounter == 2)
            		accountInfoText.append("\n\t\tDate of Birth: " + line + "\n");
            	if (lineCounter == 3)
            		accountInfoText.append("\n\t\tGender: " + line + "\n");
            	if (lineCounter == 4)
            		accountInfoText.append("\n\t\tAdress: " + line + "\n");
            	if (lineCounter == 5)
            		accountInfoText.append("\n\t\tPhone: " + line + "\n");
            	if (lineCounter == 6)
            		accountInfoText.append("\n\t\tBalance: " + line + "\n");
            	if (lineCounter == 7)
            		accountInfoText.append("\n\t\tEmail: : " + line + "\n");
            	if (lineCounter == 8)
            		accountInfoText.append("\n\t\tAccount Number: " + line + "\n");
            	if (lineCounter == 9)
            		accountInfoText.append("\n\t\tPasword: " + line + "\n");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading account info file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        

        add(scrollPane, BorderLayout.CENTER);
    }
}

