package hypDev;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class quickFoods {
	//main method which calls all the relevant methods from the various classes 
	public static void main(String[]args) {
		String[][] ButlersMenu={{"Lrg Pizza","120"},{"Med Pizza","100"},{"Sml Pizza","80"},{"Soda","10"},{"ice cream","25"}};
		Menu[] mnu=new Menu[10]
;		Menu.getMenu(ButlersMenu,mnu);		
		qfRestaurant qfPizza=new qfRestaurant("Butlers","Cape Town","0662247771",mnu);
		int delivLoad=0;;
		String name="";
		String pos="";
		String[][] ordr=new String[100][2];
		
		Driver drvr=new Driver(name,pos,delivLoad);
		Driver[] driverArr=new Driver[100];
		int size=0;
		driverArr=drvr.getDrivers(driverArr,size);
		drvr=bestDriver(driverArr,qfPizza,drvr);
		Customer cust=new Customer(0,"","","","","",0,ordr ,"",0.0);
		cust.getCust(cust, qfPizza);
		printInv(cust,qfPizza,drvr);
	}
	
	
	
	
	//Determines the best driver for the customer based on their position and current load
	public static Driver bestDriver(Driver[] driverArr,qfRestaurant rest,Driver bestDriv) {
		int delivs=100;
		
		for(int i=0;i<driverArr.length;i++) {
			if(rest.pos==driverArr[i].pos) {
				if(driverArr[i].delivLoad<delivs) {
					bestDriv=new Driver(driverArr[i].name,driverArr[i].pos,driverArr[i].delivLoad);
					delivs=driverArr[i].delivLoad;
				}
			}
			else {
				System.out.println("Sorry! Our drivers are too far away from you to be able to "
						+ "deliver to your location");
			}
		}
		return bestDriv;
	}
	
	//Prints the invoice to a text file 
	public static void printInv(Customer cust,qfRestaurant rest,Driver bestDrvr) {
		try {
			FileWriter fw=new FileWriter("invoice.txt");
			fw.write("Order number: "+cust.ordrNum+"\nCustomer: "+cust.name+"\nEmail: "+cust.email+"\n Phone number: "
					+cust.contactNum+"\nLocation: "+cust.pos+"\n\nYou have ordered the following from "+rest.name+" in "
					+rest.pos+":\n\n"+cust.orderDisp(cust.ordr,rest.menu)+"\nTotal: "+cust.getTtl(cust.ordr,rest.menu)+"\n"+
					bestDrvr.name+" is nearest to the restaurant and so he will be delivering your order to you at:\n\n"
					+cust.address+"\n"+cust.pos+"\n\nIf you need to contact the restaurant, their number is "+rest.num+".");
			fw.close();
			System.out.println("Successfully invoiced!");
		}
		catch(IOException io) {
			System.out.println("File was not found");
		}
	}
	
	//Alphabetically orders customers names and order numbers then prints to a new text file
	public static void namesAndOrdrNum(Customer[] cust,String[][] alphaCust) {
		try {
			FileWriter fw=new FileWriter("alphaSortd.txt",true);
			for(int i=0; i<cust.length;i++) {
				alphaCust[i][0]=cust[i].name;
				alphaCust[i][1]=Integer.toString(cust[i].ordrNum);
			}
			
			Arrays.sort(alphaCust);
			for(int i=0; i<alphaCust.length;i++) {
				fw.write(alphaCust[i][0]+" "+alphaCust[i][1]);
			}
			fw.close();
			System.out.println("The customers were successfully sorted!");
		}
		catch(IOException io) {
			
		}
	}
	
	//groups customer names based on their positions
	public static void custGrouping(Customer[] cust) {
		try {
			FileWriter fw=new FileWriter("groupedCusts.txt",true);
			ArrayList<String> cpt,jhb,dbn,sBok,pcsm,wbnk,PE,bloem;
			cpt=new ArrayList();
			jhb=new ArrayList();
			dbn=new ArrayList();
			sBok=new ArrayList();
			pcsm=new ArrayList();
			wbnk=new ArrayList();
			PE=new ArrayList();
			bloem=new ArrayList();
			
			for(int i=0;i<cust.length;i++) {
					if(cust[i].pos.equalsIgnoreCase("Cape Town")) {
						cpt.add(cust[i].name);
					}
					
					else if(cust[i].pos.equalsIgnoreCase("Johannesburg")) {
						jhb.add(cust[i].name);
					}
					
					else if(cust[i].pos.equalsIgnoreCase("Durban")) {
						dbn.add(cust[i].name);
					}
					
					else if(cust[i].pos.equalsIgnoreCase("Springbok")) {
						sBok.add(cust[i].name);
					}
					
					else if(cust[i].pos.equalsIgnoreCase("Potchefstroom")) {
						pcsm.add(cust[i].name);
					}
					
					else if(cust[i].pos.equalsIgnoreCase("Witbank")) {
						wbnk.add(cust[i].name);				
					}
					
					else if(cust[i].pos.equalsIgnoreCase("Port Elizabeth")) {
						PE.add(cust[i].name);
					}
					
					else if(cust[i].pos.equalsIgnoreCase("Bloemfontein")) {
						bloem.add(cust[i].name);
					}
				}
			fw.write("Cape Town:\n"+cpt+"\nJohannesburg:\n"+jhb+"\nDurban:\n"+dbn+"\nSpringbok:\n"+sBok+"\nPotchefStroom:\n"+pcsm
					+"\nWitbank:\n"+wbnk+"\nPort Elizabeth:\n"+PE+"\nBloemfontein:\n"+bloem);
			fw.close();
			
			}
			catch(IOException io){
				System.out.println("File was not found");
			}
	}
	 
	//gets the line number of which row needs to be replaced
	public static int getLineNum(Driver drvr) {
		try {
			Scanner scFile=new Scanner(new File("drivers.txt")); 
			int i=0;
			
			while(scFile.hasNext()) {
				String line=scFile.nextLine();
				if(line.contains(drvr.name)) {
					return i;
				}
				i++;
			}
			return i;
		}
		catch(IOException io) {
			System.out.println("File was not found");
			return 0;
		}
	}
	
	//updates the given drivers load on the drivers.txt textfile
	public static void updtLoad(Driver drvr) {
		try {
			int lineNumber =getLineNum(drvr);
			String data=drvr.name+", "+drvr.pos+", "+drvr.delivLoad+1;
			Path path=Paths.get("drivers.txt");
			List<String> lines=Files.readAllLines(path,StandardCharsets.UTF_8);
			lines.set(lineNumber, data);
			Files.write(path, lines, StandardCharsets.UTF_8);
		}
		catch(IOException io) {
			System.out.println("File was not found");
		}
	}
}