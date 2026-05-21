package bank.features;

import javax.swing.*;

import bank.template.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class CloseAccountWindow extends JFrame {
    private String filePath;
    private JButton goBackButton;
    private JButton closeButton;

    public CloseAccountWindow(String filePath) {
        this.filePath = filePath;
        setTitle("Close Account Window");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Are you sure you want to close your account?");
        panel.add(label, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());

        closeButton = new JButton("Close Account");
        buttonPanel.add(closeButton);
        
        
        goBackButton = new JButton("Go Back");
        buttonPanel.add(goBackButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(CloseAccountWindow.this,
                        "Are you sure you want to close your account?", "Confirm Close Account", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        if (file.delete()) {
                            JOptionPane.showMessageDialog(CloseAccountWindow.this,
                                    "Account has been closed successfully. Remaining funds can be withdrawn from your local branch.",
                                    "Account Closed", JOptionPane.INFORMATION_MESSAGE);
                            
                            dispose();
                            
                            BankHomePage bankHomePage = new BankHomePage();
                            bankHomePage.frame.setVisible(true);
                            
                        } else {
                            JOptionPane.showMessageDialog(CloseAccountWindow.this, "An error occurred while closing the account.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(CloseAccountWindow.this, "File not found.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        goBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				UserDashboard userDashboard = new UserDashboard(filePath);
				dispose();
				userDashboard.frame.setVisible(true);
				
			}
		});
    }
}
