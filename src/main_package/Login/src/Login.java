import java.util.Scanner; 
public class Login extends Data{
	static String tempuname,temppword;
	static Scanner sc=new Scanner(System.in);
	public static void login() {
		int condition=0;
		do
		{
		System.out.print("Enter User Name : ");
		tempuname = sc.nextLine().split(" ")[0];
		System.out.print("Enter Password : ");
		temppword = sc.nextLine().split(" ")[0];
		condition=check();
		}while(!(condition!=0));
		
		if(condition==1)
			System.out.println("Logged IN.");
		else if(condition==-1)
			Register.reg();
	}

	public static int check() {
	    return Data.eq(tempuname,temppword);
	}
	static class OTP
	{
		static String ques="Naman ki bandi kaun ?";
		static void genOtp()
		{
		if(check()!=-1)
		{
		System.out.print("FORGOT PASSWORD ?\nAnswer the Security Question >> "+ques+"\n\nANSWER : ");
		String ans=sc.nextLine().split(" ")[0];
		Data.getpword((int)ans.hashCode());
		}
		}
	}
}//end of Login class
