class Data {
	private static String uname,pword;
	private static int LoggedIn=0;
	final public static int eq(String incomingUname,String incomingPword)
	{
	try{
			if(uname.equals(incomingUname)&&pword.equals(incomingPword))
			{
				System.out.println("Access granted !");
				LoggedIn=1;
				return 1;
			}
			else
			{
				System.out.println("Invalid\n\nEnter Again >>");
				return 0;
			}
		}
		catch(NullPointerException e)
		{
			System.out.println("You have not Registered Yet ! Please Register First.");
			return -1;
		}
	}
	final public static void getpword(long hashCode)
	{
		if(hashCode==-1200549338)
		System.out.println("Access Granted...\nPassword is "+pword);
		else
		System.out.println("Incorrect Answer.\nAccess Denied.");
	}
	final public static void set(String tempu,String tempp)
	{
		uname=tempu;
		pword=tempp;
	}
	final protected static boolean GetLoginStatus()
	{
		return LoggedIn==1 ? true:false;
	}
	final protected static void LogOut()
	{
		LoggedIn=0;
		System.out.println("Logged Out of Account '"+uname+"'");
	}
} //end of data class
