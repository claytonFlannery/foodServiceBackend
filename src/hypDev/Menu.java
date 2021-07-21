package hypDev;

import javax.swing.JOptionPane;

public class Menu {
	String itm;
	int price;
	
	//menu constructor
	public Menu(String itm, int price) {
		this.itm=itm;
		this.price=price;
	}
	//gets the given menu to be used in the restaurant object
	public static Menu[] getMenu(String[][] gvnMenu,Menu[] menu) {
		for(int i=0;i<gvnMenu.length;i++) {
			menu[i]=new Menu(gvnMenu[i][0],Integer.parseInt(gvnMenu[i][1]));
		}
		return menu;
	}
}