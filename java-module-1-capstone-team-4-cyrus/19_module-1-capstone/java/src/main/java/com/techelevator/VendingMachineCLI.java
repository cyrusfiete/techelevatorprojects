package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

/**************************************************************************************************************************
*  This is your Vending Machine class
*  
*  You code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;
public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
														MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT };
	private static final String FEED_MONEY = "Feed Money";
	private static final String SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = { FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION };
	
	Map<String, Slots> inventory = new TreeMap<String, Slots>();
	
	double balance = 0.00;
	private Menu menu;
	Scanner input = new Scanner(System.in);
	boolean purchaseMenu = true;
	boolean feeding = true;
	//boolean buying = false;
	int chipsBought = 0;
	int candyBought = 0;
	int drinkBought = 0;
	int gumBought = 0;
	int quarters = 0;
	int dimes = 0;
	int nickels = 0;
	
	int potato = 0;
	int stacker = 0;
	int grain = 0;
	int cloud = 0;
	int moon = 0;
	int cow = 0;
	int wonka = 0;
	int crunchie = 0;
	int cola = 0;
	int salt = 0;
	int mountain = 0;
	int heavy = 0;
	int uchews = 0;
	int little = 0;
	int chiclets = 0;
	int triple = 0;
	
	double totalSales = 0.0;
	
//	public void writeFile() throws IOException {
//		FileWriter fileWriter = new FileWriter("Log.txt");
//		PrintWriter writer = new PrintWriter(fileWriter);
//	}
	
//
//	public VendingMachineCLI(Menu menu) {  // Constructor - user will pass a menu for this class to use
//		this.menu = menu;									// Make the Menu the user object passed, our Menu

	public void readFile() throws FileNotFoundException {
		File inputFile = new File("vendingmachine.csv");
		Scanner fileScanner = new Scanner(inputFile);
	}
	
//	public void writeFile() throws FileNotFoundException {
//		
//	}
	
	
	public VendingMachineCLI(Menu menu) throws FileNotFoundException {  // Constructor - user will pas a menu for this class to use
		this.menu = menu; // Make the Menu the user object passed, our Menu
		loadInventory();

	}
	
	public void run() throws IOException {
		
		PrintWriter writer = new PrintWriter("Log.txt");
		while(true) {   // loop until user indicates they want to exit
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					System.out.println(inventory);
				
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseMenu = true;
				while(purchaseMenu) {
				System.out.println("\nCurrent Money Provided: $" + balance);
				String secondChoice = (String)menu.getChoiceFromOptions(SUB_MENU_OPTIONS);
				
				if(secondChoice.equals(FEED_MONEY)) {
					feeding = true;
					while(feeding == true) {
					System.out.println("Please insert a $1, $2, $5 or $10 bill");
					String moneyFeed = input.nextLine();
					if(moneyFeed.matches("1|2|5|10")) {
					writer.println(getTime() + " " + "FEED MONEY" + " $" + balance + " $" + (balance + Integer.parseInt(moneyFeed)));
					balance += Integer.parseInt(moneyFeed);

					System.out.println("\nCurrent Money Provided: $" + balance);
				
					System.out.println("Would you like to insert more money? (Y/N)");
					String answer = input.nextLine();
					if(answer.equalsIgnoreCase("Y")) {
						feeding = true;
					}
					if(answer.equalsIgnoreCase("N")) {
						feeding = false;
					}
					}
				}
				}
				else if(secondChoice.equals(SELECT_PRODUCT)) {
					System.out.println("What would you like?");
					String order = input.nextLine();
				if(inventory.containsKey(order)) {
					if(!inventory.get(order).stackEmpty()) {
						if(inventory.get(order).getPrice()<=balance) {
							if(inventory.get(order).getType().equals("Chip")) {
								chipsBought++;
							}
							if(inventory.get(order).getType().equals("Candy")) {
								candyBought++;
							}
							if(inventory.get(order).getType().equals("Drink")) {
								drinkBought++;
							}
							if(inventory.get(order).getType().equals("Gum")) {
								gumBought++;
							}
							System.out.println("\nEnjoy your " + inventory.get(order).getName() + "!");
							writer.println(getTime() + " " + inventory.get(order).seeName() + " " + order + " $" + balance + " $" + (balance - inventory.get(order).getPrice()));
								
							//checking name for sales report
							
								if(inventory.get(order).seeName().equals("Potato Crisps")) {
									potato++;
								}
								if(inventory.get(order).seeName().equals("Stackers")) {
									stacker++;
								}
								if(inventory.get(order).seeName().equals("Grain Waves")) {
									grain++;
								}
								if(inventory.get(order).seeName().equals("Cloud Popcorn")) {
									cloud++;
								}
								if(inventory.get(order).seeName().equals("Moonpie")) {
									moon++;
								}
								if(inventory.get(order).seeName().equals("Cowtales")) {
									cow++;
								}
								if(inventory.get(order).seeName().equals("Wonka Bar")) {
									wonka++;
								}
								if(inventory.get(order).seeName().equals("Crunchie")) {
									crunchie++;
								}
								if(inventory.get(order).seeName().equals("Cola")) {
									cola++;
								}
								if(inventory.get(order).seeName().equals("Dr. Salt")) {
									salt++;
								}
								if(inventory.get(order).seeName().equals("Mountain Melter")) {
									mountain++;
								}
								if(inventory.get(order).seeName().equals("Heavy")) {
									heavy++;
								}
								if(inventory.get(order).seeName().equals("U-Chews")) {
									uchews++;
								}
								if(inventory.get(order).seeName().equals("Little League Chew")) {
									little++;
								}
								if(inventory.get(order).seeName().equals("Chiclets")) {
									chiclets++;
								}
								if(inventory.get(order).seeName().equals("Triplemint")) {
									triple++;
								}
								
							purchaseMenu = true;
							balance -= inventory.get(order).getPrice();
							totalSales += inventory.get(order).getPrice();
							
						}
						else if(inventory.get(order).getPrice()>balance) {
							System.out.println("You have not fed enough money.");
							//purchaseMenu = true;
						}
						}
					if(inventory.get(order).stackEmpty()) {
						System.out.println("Product is sold out.");
					}
					}
					if(!inventory.containsKey(order)) {
						System.out.println("That is not a valid order.");
						//purchaseMenu = true;
					}
				}
				else if(secondChoice.equals(FINISH_TRANSACTION)) {
					writer.println(getTime() + " " + "GIVE CHANGE" + " $" + balance + " $0.00");
					balance = (balance*100);
					System.out.println("Here is your change!");
					if(balance > 0) {
					while (balance >= 25) {
							balance -= 25;
							quarters ++;
					}
					while(balance >= 10) {
							balance -= 10;
							dimes ++;
					}
					while(balance >=5) {
							balance -= 5;
							nickels++;
					}
					}
					System.out.println(quarters + ": quarters\n" + dimes + ": dimes\n" + nickels + ": nickels");
					quarters = 0;
					dimes = 0;
					nickels = 0;
					while(chipsBought>0) {
						System.out.println("Crunch Crunch, Yum!");
						chipsBought--;
					}
					while(candyBought>0) {
						System.out.println("Munch Munch, Yum!");
						candyBought--;
					}
					while(drinkBought>0) {
						System.out.println("Glug Glug, Yum!");
						drinkBought--;
					}
					while(gumBought>0) {
						System.out.println("Chew Chew, Yum!");
						gumBought--;
					}
					purchaseMenu = false;
				}
				}
			}
				else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
					PrintWriter salesWriter = new PrintWriter("SalesReport.txt");
					salesWriter.println("Potato Crisps|" + potato + "\nStackers|" + stacker + "\nGrain Waves|" + grain + "\nCloud Popcorn|" + cloud + "\nMoonpie|" + moon + "\nCow Tales|" + cow + "\nWonka Bar|" + wonka + "\nCrunchie|" + crunchie + "\nCola|" + cola + "\nDr. Salt|" + salt + "\nMountain Melter|" + mountain + "\nHeavy|" + heavy + "\nU-Chews|" + uchews + "\nLittle League Chew|" + little + "\nChiclets|" + chiclets + "\nTriplemint|" + triple + "\n\n**TOTAL SALES** $" + totalSales);
					salesWriter.close();
					writer.close();
					return;  // exit the process (ie. shut down the vending machine)
			}
		}
	}
	
	public void loadInventory() throws FileNotFoundException {
		String[] oneLine = new String[100];
		String slotName = "";
		String name = "";
		double cost = 0.0;
		String type = "";
		File inputFile = new File("vendingmachine.csv");
		Scanner fileScanner = new Scanner(inputFile); 
		while(fileScanner.hasNextLine()) { 
		String line = fileScanner.nextLine();
		oneLine = line.split("\\|");
		slotName = oneLine[0];
		name = oneLine[1];
		cost = Double.parseDouble(oneLine[2]);
		type = oneLine[3];
		Stack<Items> slotOfProducts = new Stack<Items>();
		Items anItem = new Items(name, type);

		for (int i = 0; i<5; i++) {
			slotOfProducts.push(anItem);
		}
		Slots aSlot = new Slots(cost, slotOfProducts);
		inventory.put(slotName, aSlot);
		}
	}
	
	private static String getTime() {
		Calendar aCalendar = Calendar.getInstance();
		Date theTime = aCalendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd yyyy HH:mm:ss");
		String formattedTime = dateFormat.format(theTime);
		return formattedTime;
	}
	
	
/*************************************************************************************************************************
*  This is the application program to instantiate a Vending Machine as start it running
*  
*  DO NOT PUT ANY NEW CODE HERE!
 * @throws IOException 
***************************************************************************************************************************/
	
	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);          // Instantiate a menu for Vending Machine to use
		VendingMachineCLI cli = new VendingMachineCLI(menu);  // Instantiate a Vending Machine passing it the Menu object to use
		cli.run();                                            // Tell the Vending Machine to start running
	}
}
