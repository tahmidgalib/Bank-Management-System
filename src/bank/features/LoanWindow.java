package bank.features;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoanWindow extends JFrame {

	private String filePath;
    private JTextField loanAmountField;
    private JButton requestLoanButton;
    private JButton repayLoanButton;
    private double currentBalance;
    private double newBalance;
    private double currentLoan;

    public LoanWindow(String filePath) {
    	this.filePath = filePath;
        setTitle("Loan Window");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel loanAmountLabel = new JLabel("Enter amount:");
        loanAmountField = new JTextField(10);
        requestLoanButton = new JButton("Request Loan");
        repayLoanButton = new JButton("Repay Loan");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(loanAmountLabel);
        inputPanel.add(loanAmountField);
        panel.add(inputPanel);
        panel.add(requestLoanButton);
        panel.add(repayLoanButton);
        
        requestLoanButton.addActionListener(new ActionListener() {
        	
        	private boolean hasOutstandingLoan() {
        	    // Read user info file and check if the loan balance field is non-zero
        		
        		double loanBalance = 0;
        		
        	    try {
        	        
        	    	FileReader fr = new FileReader(filePath);
                	BufferedReader br = new BufferedReader(fr);
                    
                	String line;
                    int lineNum = 1;
                    
                    while ((line = br.readLine()) != null) {
                    	if (lineNum == 10) {
                    		if (!line.isEmpty())
                    		loanBalance = Double.parseDouble(line);
                    		else {
                    			loanBalance = 0;
                    		}
                    		
                    		
                    	}
                    	lineNum++;
                    }
                    
                    fr.close();
                    br.close();
        	       
        	    } catch (FileNotFoundException e) {
        	        e.printStackTrace();
        	        return false;
        	    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	    return (loanBalance != 0);
        	}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String loanAmountString = loanAmountField.getText();
				
				if (loanAmountString.isEmpty()) {
	                JOptionPane.showMessageDialog(LoanWindow.this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
				
                double loanAmount = Double.parseDouble(loanAmountString);
                
                if (loanAmount <= 0) {
                    JOptionPane.showMessageDialog(LoanWindow.this, "Please enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (hasOutstandingLoan()) {
                	JOptionPane.showMessageDialog(null, "You already have an outstanding loan. Please repay it first.");
                	return;
                }  else {
                	
                    int confirm = JOptionPane.showConfirmDialog(LoanWindow.this, "Are you sure you want to take a loan of  " + loanAmount + "?", "Confirm Loan", JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                    	
                    	File mainFile = new File(filePath);
                    	
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
                                	newBalance = currentBalance + loanAmount;
                                	
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
                            
                            FileWriter loanRecord = new FileWriter(mainFile, true);
                            
                            loanRecord.write(loanAmountString + "\n");
                            
                            loanRecord.close();
                            
                           	dispose();
                            
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        } catch (SecurityException se) {
                        	System.err.println("SecurityException: " + se.getMessage());
                        }
                    	
                    	JOptionPane.showMessageDialog(null, "Loan request approved. " + loanAmount + " has been added to your account.");
                    	
                    }
                	
                }
                
                
                
                
				
			}
			
			
			
		});
        
        repayLoanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String loanAmountString = loanAmountField.getText();
				
				if (loanAmountString.isEmpty()) {
	                JOptionPane.showMessageDialog(LoanWindow.this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
				
                double loanAmount = Double.parseDouble(loanAmountString);
                
                if (loanAmount <= 0) {
                    JOptionPane.showMessageDialog(LoanWindow.this, "Please enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                	
                	int confirm = JOptionPane.showConfirmDialog(LoanWindow.this, "Are you sure you want to repay " + loanAmount + " of your loan?", "Confirm Repay", JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                    	
                    	File mainFile = new File(filePath);
                    	
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
                                	
                                	if (currentBalance < loanAmount) {
                                		JOptionPane.showMessageDialog(null, "Repay failed. Insufficient funds.", "Repay failed", JOptionPane.ERROR_MESSAGE);
                                		return;
                                	}
                                	
                                	newBalance = currentBalance - loanAmount;
                                	
                                	fileOutput.append(Double.toString(newBalance));
                                	fileOutput.append("\n");
                                	
                                } else if (lineNum == 10) {
                                	
                                	currentLoan = Double.parseDouble(line);
                                	currentLoan -= loanAmount;
                                	fileOutput.append(Double.toString(currentLoan));
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
                    	
                    	JOptionPane.showMessageDialog(null, "Repay successful! " + loanAmount + " has been charged from your account.");

                    	
                    	
                    }
                }
				
			}
		});
        

        add(panel);
        setVisible(true);
    }


}
