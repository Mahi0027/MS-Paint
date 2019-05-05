import java.util.Scanner; 
public class Main_Screen {
	public static void main(String[] args) {
		String choice=null;
		boolean running=true;
		Scanner scan=new Scanner(System.in);
		while(running==true)
		{
		System.out.print("MENU\n\nPress\n1. To Login\n2. Register\n"+
						 "3. Forgot Password ?\n4. LOGOUT\n\n5. Exit\nChoice : ");	
	 	try{
		choice=scan.nextLine();
		int ch=Integer.parseInt(choice);
		switch(ch)
	 	{ 
	 	case 1  :  if(Login.GetLoginStatus())
	 					System.out.println("Already Logged in");
	 			   else
	 					Login.login(); 
	 			   break;
	 	case 2  :  Register.reg(); break;
	 	case 3  :  Login.OTP.genOtp(); break;
	 	case 4  :  Login.LogOut(); break;
	 	case 5  :  System.out.println("Exiting....."); System.exit(0); break;
	 	default :  throw new Exception();
	 	}
	 	}
		catch(Exception e)
	 	{
	 		System.out.println("Invalid Choice.");
	 	}
	 	Scanner scr=new Scanner(System.in);
		System.out.print("Back to Home Screen ? (y/n) ");
		String temp=scr.nextLine();
		if(temp.equals("y"))
	 		running=true;
	 	else
	 		{
	 		running=false;
			scr.close();}
		}
		scan.close();
	} //end of main()
} // end of main_screen