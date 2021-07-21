package hypDev;

public class qfRestaurant {
	String name,pos,num;
	Menu[] menu;
	
	
	//Creates the restaurant object
	public qfRestaurant(String name,String pos, String num,Menu[] menu) {
			this.name=name; 
			this.pos=pos;
			this.num=num;
			this.menu=menu;
			
		}
	
	//Displays menu items to the customer
	public void displayMenu(Menu[] menu,int size) {
		for(int i=0;i<size;i++) {
			System.out.println(menu[i].itm+" \t"+menu[i].price);
		}
	}
	
}
