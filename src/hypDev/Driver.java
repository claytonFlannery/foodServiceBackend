package hypDev;

import java.util.*;
import java.io.*;
public class Driver {
	int delivLoad;
	String name,pos;
	
	//Driver object constructor
	public Driver(String name,String pos,int delivLoad) {
		this.name=name;
		this.pos=pos;
		this.delivLoad=delivLoad;
	}
	
	//fills a driver object array with the information from the driver text file
	public Driver[] getDrivers(Driver[] driverArr,int size) {
		try {
		Scanner scFile=new Scanner(new File("driverInfo.txt"));
		String Line;
		
			while(scFile.hasNext()) {
				Line=scFile.nextLine();
				Scanner scLine=new Scanner(Line).useDelimiter(", ");
				driverArr[size].name=scLine.next();
				driverArr[size].pos=scLine.next();
				driverArr[size].delivLoad=Integer.parseInt(scLine.next());
						
				size++;
			}
		}
		
		//catch statement with explanation of error
		catch(FileNotFoundException fnf){
			System.out.println("File was not found");
		}
		
		return driverArr;
	}
}