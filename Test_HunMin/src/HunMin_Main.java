import java.util.*;

public class HunMin_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in); 
		
		String tempStr;
	    String lastStr = "";    
	    
	    System.out.println("문자를 입력하시오:");
	    tempStr = scan.nextLine();
		
		HunMinChange test = new HunMinChange(tempStr,lastStr);
	}

}
