/* 	Name: Joseph Eddy
	Course: CNT 4717 – Spring 2024
	Assignment Title: Project 1 – An Event-Driven Enterprise Simulation
	Date: Sunday January 28, 2024
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;

public class guiWindow extends JFrame implements ActionListener{
	
	private static Scanner scan;
	
	//Declaring labels for text to be used on frame
	JLabel itemPrompt,qtyPrompt,detPrompt,subPrompt,cartPrompt;
	
	//Declaring buttons as global variables
	JButton findBtn,addBtn,viewBtn,checkBtn,emptyBtn,exitBtn;
	
	//Declaring text boxes for input
	JTextField idBox,qtyBox,details,subtotal,firstCart,secondCart,thirdCart,fourthCart,fifthCart;
	
	//Declaring strings for ID and Quantity to be read in
	String ID;
	String quantity;
	
	//Declaring string for item row
	String[] itemRow;
	
	//Logic integer variables
	//int for current item number
	int itemNum = 1;
	//int for item number just entered
	int itemCounter = 0;
	
	//Number formatter for $ amount with .00 at end
	NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
	
	//Item total for each individual item
	float itemTotal = 0.00f;
	String itemTotalString;
	//Item total for cart
	float grandTotal = 0.00f;
	String grandTotalString;
	//Holds dollar amount for TAX
	float taxAmount = 0.00f;
	String taxAmountString;
	//Holds amount for grand total + TAX
	float finalTotal = 0.00f;
	String finalTotalString;
	
	//Dicount variables
	float discount = 1.00f;
	String discountString;
	
	//For display of discount percent
	int discountPercent = 0;
	
	//Float values for math
	float quantityFloat = 1;
	float quantityFloatCSV = 1;
	float priceFloat = 0;
	
	//Array for Cart
	String[][] cart = new String[5][6];
	//SKU, Name, Price, Quantity, Total
	
	guiWindow(){
		
	//Code for labels
		//Create labels
		itemPrompt = new JLabel();
		qtyPrompt = new JLabel();
		detPrompt = new JLabel();
		subPrompt = new JLabel();
		cartPrompt = new JLabel();
		
		//Customize labels
		itemPrompt.setText("Enter item ID for Item #"+itemNum+":");
		itemPrompt.setBounds(100,10,300,35);
		
		qtyPrompt.setText("Enter quantity for Item #"+itemNum+":");
		qtyPrompt.setBounds(100,50,300,35);
		
		detPrompt.setText("Details for Item #"+itemNum+":");
		detPrompt.setForeground(Color.red);
		detPrompt.setBounds(100,90,300,35);
		
		subPrompt.setText("Order subtotal for "+itemCounter+" item(s):");
		subPrompt.setForeground(Color.blue);
		subPrompt.setBounds(100,130,300,35);
		
		cartPrompt.setText("Your shopping cart is empty");
		cartPrompt.setForeground(Color.red);
		cartPrompt.setBounds(400,170,300,35);
	
	//Code for Buttons
		//Create and customize buttons
		findBtn = new JButton();
		findBtn.setBounds(195,380,300,60);
		findBtn.addActionListener(this);
		findBtn.setText("Find Item #"+itemNum);
		findBtn.setFocusable(false);
		
		addBtn = new JButton();
		addBtn.setBounds(505,380,300,60);
		addBtn.addActionListener(this);
		addBtn.setText("Add Item #"+itemNum+" to Cart");
		addBtn.setFocusable(false);
		addBtn.setEnabled(false);
		
		viewBtn = new JButton();
		viewBtn.setBounds(195,460,300,60);
		viewBtn.addActionListener(this);
		viewBtn.setText("View Cart");
		viewBtn.setFocusable(false);
		viewBtn.setEnabled(false);
		
		checkBtn = new JButton();
		checkBtn.setBounds(505,460,300,60);
		checkBtn.addActionListener(this);
		checkBtn.setText("Check Out");
		checkBtn.setFocusable(false);
		checkBtn.setEnabled(false);
		
		emptyBtn = new JButton();
		emptyBtn.setBounds(195,540,300,60);
		emptyBtn.addActionListener(this);
		emptyBtn.setText("Empty Cart - Start new Order");
		emptyBtn.setFocusable(false);
		
		exitBtn = new JButton();
		exitBtn.setBounds(505,540,300,60);
		exitBtn.addActionListener(this);
		exitBtn.setText("Exit");
		exitBtn.setFocusable(false);
	
	//Code for text fields
		//Create and customize text fields for user entry
		//idBox,qtyBox
		idBox = new JTextField();
		idBox.setBounds(305,10,500,35);
		
		qtyBox = new JTextField();
		qtyBox.setBounds(305,50,500,35);
		
		details = new JTextField();
		details.setBounds(305,90,500,35);
		details.setEditable(false);
		
		subtotal = new JTextField();
		subtotal.setBounds(305,130,500,35);
		subtotal.setEditable(false);
		
		//Create and customize text fields for Cart
		firstCart = new JTextField();
		firstCart.setBounds(100,200,800,30);
		firstCart.setEditable(false);
		
		secondCart = new JTextField();
		secondCart.setBounds(100,235,800,30);
		secondCart.setEditable(false);
		
		thirdCart = new JTextField();
		thirdCart.setBounds(100,270,800,30);
		thirdCart.setEditable(false);
		
		fourthCart = new JTextField();
		fourthCart.setBounds(100,305,800,30);
		fourthCart.setEditable(false);
		
		fifthCart = new JTextField();
		fifthCart.setBounds(100,340,800,30);
		fifthCart.setEditable(false);
		
	//Code for JFrame
		this.setTitle("Nile Dot Com");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,700);
		this.setResizable(false);
		//Add labels
		this.add(itemPrompt);
		this.add(qtyPrompt);
		this.add(detPrompt);
		this.add(subPrompt);
		this.add(cartPrompt);
		//Add buttons
		this.add(findBtn);
		this.add(addBtn);
		this.add(viewBtn);
		this.add(checkBtn);
		this.add(emptyBtn);
		this.add(exitBtn);
		//Add text fields
		this.add(idBox);
		this.add(qtyBox);
		this.add(details);
		this.add(subtotal);
		
		this.add(firstCart);
		this.add(secondCart);
		this.add(thirdCart);
		this.add(fourthCart);
		this.add(fifthCart);
		
		this.setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	//Declare variables: float for math and string to fit into array
		
		//String for reading in of CSV
		itemRow = new String[5];
		
		
	//Find Item Button
		if(e.getSource() == findBtn) {
			//Gets ID/quantity from text field
			ID = idBox.getText();
			quantity = qtyBox.getText();

			//Sends ID into readRow, which searches inventory.csv for ID, and returns it to itemRow
			itemRow = readCSV(ID, "inventory.csv");
			
			//If item not found
			if(itemRow==null) {
				if(ID.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter an item ID","Nile Dot Com - ERROR",JOptionPane.ERROR_MESSAGE);
					idBox.setText("");
					qtyBox.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "Item ID "+ID+" not on file", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE);
					idBox.setText("");
					qtyBox.setText("");
				}
			}
			
			//If item is found
			else {
				//Turns item quantity in CSV file to a float to compare to quantity requested by user
				quantityFloatCSV = Float.parseFloat(itemRow[3]);
				//Converts quantity from a string to a float for math purposes
				quantityFloat = Float.parseFloat(quantity);
				//Converts price from a string to a float for math purposes
				priceFloat = Float.parseFloat(itemRow[4]);
				//Uses method to calculate discount based on quantity ordered
				discount = findDiscount(quantityFloat);
				//Converts discount to a "percent-off" number for display purposes
				discountPercent = Math.round(discount*100); 
				//Price for multiple of the same item
				itemTotal = quantityFloat * priceFloat * (1-discount);
				//Formats as money
				itemTotalString = moneyFormat.format(itemTotal);
				itemRow[4] = moneyFormat.format(priceFloat);

				
				//If user enters a quantity of 0
				if(itemRow[3].equals("0")) {
					JOptionPane.showMessageDialog(null, "Sorry, that item is out of stock, please try another item","Nile Dot Com - ERROR",JOptionPane.ERROR_MESSAGE);
					findBtn.setEnabled(true);
					idBox.setText("");
					qtyBox.setText("");
					details.setText("");
				}
				else if(quantityFloat == 0) {
					JOptionPane.showMessageDialog(null, "Please select a quantity greater than 0","Nile Dot Com - ERROR",JOptionPane.ERROR_MESSAGE);
					qtyBox.setText("");
				}
				//Makes sure user only can add number of items in stock into cart
				else if(quantityFloat>quantityFloatCSV) {
					JOptionPane.showMessageDialog(null, "Insufficient stock, only "+itemRow[3]+" on hand. Please reduce quantity","Nile Dot Com - ERROR",JOptionPane.ERROR_MESSAGE);
					qtyBox.setText("");
				}
				//If everything goes correctly
				else {
					addBtn.setEnabled(true);
					findBtn.setEnabled(false);
			
					//Sets the text for the details box
					details.setText(itemRow[0]+" "+itemRow[1]+" "+itemRow[4]+" "+quantity+" "+discountPercent+"% "+itemTotalString);
				}
			}
		}
		
	//Add button
		if(e.getSource() == addBtn) {
			//Sends ID into readRow, which searches inventory.csv for ID, and returns it to itemRow
			itemRow = readCSV(ID, "inventory.csv");
			
			//Adds item's total to grand subtotal
			grandTotal = grandTotal + itemTotal;
			grandTotalString = moneyFormat.format(grandTotal);

			//Convert from float to string
			discountString = Float.toString(discount);
				
			itemRow[4] = moneyFormat.format(priceFloat);

			//Save current item to array "cart"
			cart[itemCounter][0] = itemRow[0];
			cart[itemCounter][1] = itemRow[1];
			cart[itemCounter][2] = itemRow[4];
			cart[itemCounter][3] = quantity;
			cart[itemCounter][4] = discountString;
			cart[itemCounter][5] = itemTotalString;
				
			//Display in cart below
			if(itemNum == 1) {
				firstCart.setText("Item "+itemNum+
						" - SKU: "+cart[itemCounter][0]+
						", Desc: "+cart[itemCounter][1]+
						", Price Ea. "+cart[itemCounter][2]+
						", Qty: "+cart[itemCounter][3]+
						", Total: "+cart[itemCounter][5]);
			}
			if(itemNum == 2) {
				secondCart.setText("Item "+itemNum+
						" - SKU: "+cart[itemCounter][0]+
						", Desc: "+cart[itemCounter][1]+
						", Price Ea. "+cart[itemCounter][2]+
						", Qty: "+cart[itemCounter][3]+
						", Total: "+cart[itemCounter][5]);
			}
			if(itemNum == 3) {
				thirdCart.setText("Item "+itemNum+
						" - SKU: "+cart[itemCounter][0]+
						", Desc: "+cart[itemCounter][1]+
						", Price Ea. "+cart[itemCounter][2]+
						", Qty: "+cart[itemCounter][3]+
						", Total: "+cart[itemCounter][5]);
			}
			if(itemNum == 4) {
				fourthCart.setText("Item "+itemNum+
						" - SKU: "+cart[itemCounter][0]+
						", Desc: "+cart[itemCounter][1]+
						", Price Ea. "+cart[itemCounter][2]+
						", Qty: "+cart[itemCounter][3]+
						", Total: "+cart[itemCounter][5]);
			}
			if(itemNum == 5) {
				fifthCart.setText("Item "+itemNum+
						" - SKU: "+cart[itemCounter][0]+
						", Desc: "+cart[itemCounter][1]+
						", Price Ea. "+cart[itemCounter][2]+
						", Qty: "+cart[itemCounter][3]+
						", Total: "+cart[itemCounter][5]);
			}
				
			//Increment counters
			itemNum++;
			itemCounter++;
								
			//Adjust GUI
			findBtn.setText("Find Item #"+itemNum);
			addBtn.setText("Add Item #"+itemNum+" to Cart");
			itemPrompt.setText("Enter item ID for Item #"+itemNum+":");
			qtyPrompt.setText("Enter quantity for Item #"+itemNum+":");
			detPrompt.setText("Details for item #"+itemNum);
			subPrompt.setText("Order subtotal for "+itemCounter+" item(s)");
			cartPrompt.setText("Your shopping cart with "+itemCounter+" item(s)");
			idBox.setText("");
			qtyBox.setText("");
			subtotal.setText(""+grandTotalString);
			addBtn.setEnabled(false);
			findBtn.setEnabled(true);
			viewBtn.setEnabled(true);
			checkBtn.setEnabled(true);
			
			//Limits number of items in cart to 5
			if(itemNum>5) {
				findBtn.setEnabled(false);
				idBox.setEditable(false);
				qtyBox.setEditable(false);
			}
			//FOR ERROR HANDLING
			/*for (int i = 0; i < cart.length; i++) {
		           for (int j = 0; j < cart[i].length; j++) {
		               System.out.print(cart[i][j] + " ");
		           }
		           System.out.println();
		    }*/
		}
		
	//View Cart Button
		if(e.getSource() == viewBtn){
			//Creates a "message" string to use in JOptionPane
			StringBuilder message = new StringBuilder();
			
			//Pulls values from array to form message
			for (int i = 0; i < itemCounter; i++) {
				//gets the discount percentage for each value
		        discountPercent = Math.round(Float.parseFloat(cart[i][4])*100);
		        message.append((i+1)+". "+cart[i][0]+" "+cart[i][1]+" "+cart[i][2]+" "+cart[i][3]+" "+discountPercent+"% "+cart[i][5]+"\n").append("\t");
			}
			
			//Option pane displaying contents of cart
			JOptionPane.showMessageDialog(null, message.toString()
	    		   ,"Nile Dot Com - Current Shopping Cart Status"
	    		   ,JOptionPane.PLAIN_MESSAGE);
		}
	//Check Out Button
		if(e.getSource() == checkBtn) {
			//Calculate tax amount and final total
			taxAmount = (float) (grandTotal * .06);
			taxAmountString = moneyFormat.format(taxAmount);
			finalTotal = taxAmount+grandTotal;
			finalTotalString = moneyFormat.format(finalTotal);

			
			//Gets the date and time
			ZonedDateTime currentDateTime = ZonedDateTime.now();
			//Defines a custom date/time format with timezones and AM/PM
			String datePattern = "MMMM d, yyyy, hh:mm:ssa z";
			//Customizes date and time format using datePattern
			DateTimeFormatter format = DateTimeFormatter.ofPattern(datePattern);
			//Formats the current date and time using formatter
			String currentCheckoutDate = currentDateTime.format(format);
			
			//Creates a "message" string to use in JOptionPane
			StringBuilder message = new StringBuilder();
			message.append("Date: "+currentCheckoutDate+"\n\nNumber of line items: "+itemCounter
					+"\n\nItem# / ID / Title / Price / Qty / Disc % / Subtotal:\n\n").append("\t");
			
			//Pulls values from array to form message
			for (int i = 0; i < itemCounter; i++) {
				//gets the discount percentage for each value
		        discountPercent = Math.round(Float.parseFloat(cart[i][4])*100);
		        message.append((i+1)+". "+cart[i][0]+" "+cart[i][1]+" "+cart[i][2]+" "+cart[i][3]+" "+discountPercent+"% "+cart[i][5]+"\n").append("\t");
			}
			
			message.append("\n\nOrder subtotal: "+grandTotalString+"\nTax rate: 6%\nTax amount: "+taxAmountString
					+"\n\n\nORDER TOTAL: "+finalTotalString+"\n\nThanks for shopping at Nile Dot Com!").append("\t");
			
			//Option pane displaying contents of cart
			JOptionPane.showMessageDialog(null, message.toString()
	    		   ,"Nile Dot Com - Current Shopping Cart Status"
	    		   ,JOptionPane.PLAIN_MESSAGE);
			
			//Disables buttons/textfields after checkout
			findBtn.setEnabled(false);
			addBtn.setEnabled(false);
			checkBtn.setEnabled(false);
			idBox.setEditable(false);
			qtyBox.setEditable(false);
			
		//Creates Code for CSV file
						
			//Defines a custom date/time format for code
			String codePattern = "ddMMYYYYhhmmss";
			//Customizes date and time format using codePattern
			DateTimeFormatter code = DateTimeFormatter.ofPattern(codePattern);
			//Formats the current date and time using formatter
			String currentCode = currentDateTime.format(code);
			
			//Append to CSV file
			String filePath = "transactions.csv";
			
			//Pulls values from array to form CSV data
			for (int i = 0; i < itemCounter; i++) {
				
				//Info to be appended
				String[] infoAppend = {currentCode,cart[i][0],cart[i][1],cart[i][2],cart[i][3],cart[i][4],cart[i][5],currentCheckoutDate};
			
				try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
					//Appends each value to the CSV file
					for(String value : infoAppend) {
						writer.append(value);
						writer.append(",");
					}
				
					//Adds a new line after appending values
					writer.append("\n");
					
					//FOR ERROR HANDLING
					//System.out.println("success");
					writer.close();

				}
			
				catch(IOException f) {
					f.printStackTrace();
				}
			}
		}
		
	//Empty Cart Buton
		if(e.getSource() == emptyBtn) {
			//Set variables back to 0
			itemNum = 1;
			itemCounter = 0;
			grandTotal = 0;
			taxAmount = 0;
			finalTotal = 0;
			
			//Set buttons to be enabled/disabled
			findBtn.setEnabled(true);
			addBtn.setEnabled(false);
			viewBtn.setEnabled(false);
			checkBtn.setEnabled(false);
			
			//Rest text fields
			idBox.setText("");
			idBox.setEditable(true);
			qtyBox.setText("");
			qtyBox.setEditable(true);
			details.setText("");
			subtotal.setText("");
			firstCart.setText("");
			secondCart.setText("");
			thirdCart.setText("");
			fourthCart.setText("");
			fifthCart.setText("");
			
			//Reset labels
			findBtn.setText("Find Item #"+itemNum);
			addBtn.setText("Add Item #"+itemNum+" to Cart");
			itemPrompt.setText("Enter item ID for Item #"+itemNum+":");
			qtyPrompt.setText("Enter quantity for Item #"+itemNum+":");
			detPrompt.setText("Details for item #"+itemNum);
			subPrompt.setText("Order subtotal for "+itemCounter+" item(s)");
			cartPrompt.setText("Your shopping cart is empty");

			
			//Clear array of strings
			for(int i = 0; i < cart.length; i++) {
				Arrays.fill(cart[i],null);
			}
			//FOR ERROR HANDLING
			/*for (int i = 0; i < cart.length; i++) {
	            for (int j = 0; j < cart[i].length; j++) {
	                System.out.print(cart[i][j] + " ");
	            }
	            System.out.println();  // Move to the next line after each row
	        }*/
			
		}
		
	//Exit Button
		if(e.getSource() == exitBtn) {
			System.exit(0);
		}
	}
	
	//Method that calculates the discount for number of items purchased
	public static float findDiscount(float qty) {
		if(qty>=5 && qty<=9) {
			return 0.10f;
		}
		else if(qty>=10 && qty<=14) {
			return 0.15f;
		}
		else if(qty>=15) {
			return 0.20f;
		}
		else {
			return 0.00f;
		}
	}
	
	//Method that finds a specific inventory item and returns its information
	public static String[] readCSV(String searchFor, String fileName) {
		//Declare variables
		String[] csvString = new String[5];
		//Determines if item is found or not
		boolean found = false;
		//Variables that hold values found in CSV
		String idCSV = "";
		String descCSV = "";
		String stockCSV = "";
		String ivtyCSV = "";
		String priceCSV = "";
		
		try {
			scan = new Scanner(new File(fileName));
			scan.useDelimiter("[,\n]");
			
			//Search thru file for ID
			while(scan.hasNext() && found == false) {
				idCSV = scan.next();
				descCSV = scan.next();
				stockCSV = scan.next();
				ivtyCSV = scan.next();
				priceCSV = scan.next();
				
				//Ends the loop if found
				if(searchFor.equals(idCSV) == true) {
					found = true;
				}				
			}
			
			//Set the itemInfo into a String[] and return it:
			if(found == true) {
				csvString[0] = idCSV;
				csvString[1] = descCSV;
				csvString[2] = stockCSV;
				csvString[3] = ivtyCSV;
				csvString[4] = priceCSV;
				return csvString;
			}
		}
			catch(Exception e) {
				System.out.println(e);
				return(null);
			}
			//Return null if ID not found
			return(null);
	}
}
