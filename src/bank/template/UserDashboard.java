package bank.template;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import bank.features.*;



public class UserDashboard {
	
	public JFrame frame;
	private String fileName;
	
	public UserDashboard(String fileName) {
		// TODO Auto-generated constructor stub
		this.fileName = fileName;
		
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JPanel titlePanel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel welcomeLabel = new JLabel("Welcome To MANGP Bank");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(welcomeLabel);
        
        frame.add(titlePanel, BorderLayout.NORTH);
        
        // create buttons for each feature
        JButton depositButton = new JButton("Deposit");
        depositButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton requestLoanButton = new JButton("Request Loan");
        requestLoanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton payBillButton = new JButton("Pay Bill / Send Donation");
        payBillButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton balanceEnquiryButton = new JButton("Balance Enquiry");
        balanceEnquiryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton accountInfoButton = new JButton("Account Info");
        accountInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton modifyInfoButton = new JButton("Modify Info");
        modifyInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton closeAccountButton = new JButton("Close Account");
        closeAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        panel.add(depositButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(withdrawButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(requestLoanButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(payBillButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(balanceEnquiryButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(accountInfoButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(modifyInfoButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(closeAccountButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(logOutButton);
        
        

        //Set up the content pane.
        frame.add(panel, BorderLayout.CENTER);

        //Display the window.
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        depositButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				DepositWindow depositWindow = null;
				try {
					depositWindow = new DepositWindow(fileName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				depositWindow.setVisible(true);
				
			}
		});
        
        withdrawButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				WithdrawWindow withdrawWindow = null;
				try {
					withdrawWindow = new WithdrawWindow(fileName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				withdrawWindow.setVisible(true);
				
				
			}
		});
        
        requestLoanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				LoanWindow loanWindow = null;
				try {
					loanWindow = new LoanWindow(fileName);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				loanWindow.setVisible(true);
				
			}
		});
        
        payBillButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				PayBillWindow payBillWindow = new PayBillWindow(fileName);
				payBillWindow.setVisible(true);
				
			}
		});
        
        balanceEnquiryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try 
		        {
		        	FileReader fr = new FileReader(fileName);
		        	BufferedReader br = new BufferedReader(fr);
		            
		        	String line;
		            int lineNum = 1;
		            double currentBalance;
		            
		            while ((line = br.readLine()) != null) {
		            	
		                if (lineNum == 6) {
		                	currentBalance = Double.parseDouble(line);
		                    JOptionPane.showMessageDialog(null, "Your current balance is: " + currentBalance, "Your Balance", JOptionPane.INFORMATION_MESSAGE);
		                }
		                lineNum++;
		            }
		            
		            fr.close();
		            br.close();
				
				
		        } catch (IOException ex) {
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
				
			
				
			}
		});
        
        accountInfoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				AccountInfoWindow accountInfoWindow = new AccountInfoWindow(fileName);
				accountInfoWindow.setVisible(true);

				
			}
		});
        
        modifyInfoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ModifyAccountInfo modifyAccountInfo = new ModifyAccountInfo(fileName);
				modifyAccountInfo.frame.setVisible(true);
				
			}
		});
        
        closeAccountButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				CloseAccountWindow closeAccountWindow = new CloseAccountWindow(fileName);
				frame.dispose();
				closeAccountWindow.setVisible(true);
				
			}
		});
        
        logOutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
			    if (confirmed == JOptionPane.YES_OPTION) {

			    	BankHomePage bankHomePage = new BankHomePage();
		            frame.dispose();
		            bankHomePage.frame.setVisible(true);
			    } else {
			        // do nothing
			    }
				
				
				
			}
		});
	}

}
