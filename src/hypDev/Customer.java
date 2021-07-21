package hypDev;

import javax.swing.*;
import java.util.*;

public class Customer {
	int ordrNum;
	String[][] ordr=new String[100][2];
	String contactNum,name, pos, address, email, spclPrep;
	double ttl;
	int ordrAmnt;
	//Customer object constructor
	public Customer(int ordrNum, String name, String contactNum,String address,String pos
			,String email,int ordrAmnt,String[][] ordr,
			String spclPrep,double ttl) 
	{
		this.name=name;
		this.pos=pos;
		this.ordrNum=ordrNum;
		this.contactNum=contactNum;
		this.address=address;
		this.email=email;
		this.ordrAmnt=ordrAmnt;
		this.ordr=ordr;
		this.spclPrep=spclPrep;
		this.ttl=ttl;
	}
	
	//method gets relevant info to create customer object
	public Customer getCust(Customer newCust,qfRestaurant Rest) {
		newCust.name=JOptionPane.showInputDialog("What is your name and surname?");
		newCust.pos=JOptionPane.showInputDialog("What suburb are you situated in?");
		newCust.contactNum=JOptionPane.showInputDialog("What is your cellphone number?");
		newCust.address=JOptionPane.showInputDialog("What is your address?");
		newCust.email=JOptionPane.showInputDialog("What is your email?");
		newCust.spclPrep=JOptionPane.showInputDialog("Do you have any special preparation requests?");
		newCust.ordr=getOrder(ordr);
		newCust.ordrNum=(int)(Math.random()*10000)+1000;
		
		
		return newCust;
	}
	
	//method to get the customers order
	public static String[][] getOrder(String[][] ordr){
		int i=1;
		ordr[0][1]=JOptionPane.showInputDialog("Please enter a word or enter the word "
                + "order to stop"); 
		ordr[0][0]=JOptionPane.showInputDialog("How many of this item would you like to order?");
		
		while(!ordr[i-1].equals("order")){
			ordr[i][1]=JOptionPane.showInputDialog("Please enter an order or enter the word "
                    + "order to stop"); 
			ordr[i][0]=JOptionPane.showInputDialog("How many of this item would you like to order?");
			i++;
        }
		ordr[i-1][1]="Are there any special instructions?";
		
		return ordr;
	}
	
	//gets the price of the item the customer is ordering
	public static double getPrc(String meal,int i,Menu[] mnu) {
		if(meal.equalsIgnoreCase(mnu[i].itm)) {
			return mnu[i].price;
		}
		else {
			return getPrc(meal,i,mnu);
		}
		
	}
	
	//method for displaying the customer objects order
	public static String orderDisp(String[][] custOrdr,Menu[] gvnMnu) {
		String ordr="";
		for(int i=0;i<custOrdr.length-1;i++) {
			ordr=ordr+custOrdr[i][0]+" x "+custOrdr[i][1]+"(R"+getPrc(custOrdr[i][1],i,gvnMnu)+"\n";
		}
		ordr=ordr+""+custOrdr[custOrdr.length-1][1];
		return ordr;
	}
	
	//calculates and returns the total of the customers given order
	public static double getTtl(String[][] ordr,Menu[] menu) {
		int ttl=0; 
		
		for(int i=0;i<=(ordr.length-1);i++) {
			if(ordr[i][2].equals(menu[i].itm)) {
				ttl=ttl+Integer.parseInt(ordr[i][1])*menu[i].price;
			}
		}
		return ttl;
	}
	
}
