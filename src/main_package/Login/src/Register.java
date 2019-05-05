import java.util.Scanner; 
class Register extends Data{
	public static void reg()
	{
		String tempp=null,tempu=null;
		Scanner sc=new Scanner(System.in);
		System.out.print("Welcome !\nPlease Register yourself below >>\n");
		System.out.print("Select a Username : ");
		try{
		tempu=sc.nextLine();
		if(tempu.contains(" "))
			{sc.close();
			throw new Exception("Invalid Username");}
		System.out.print("Select a Password : ");
		tempp=sc.nextLine();
		System.out.println("Username = "+tempu);
		System.out.print("Password = "+tempp+"\nPress (y/n) to confirm ? : ");
		String foo=sc.nextLine();
		if(foo.equals("y"))
		{
		  Data.set(tempu,tempp);
		}
		} // end of try
		catch(Exception e)
		{
			System.out.print("Error\n"+e.toString().split(":")[1]);
		}
		
	} //end of register()
} //end of Register class