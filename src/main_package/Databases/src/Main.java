public class Main {

	static private int acno,balance;
	static private String name,type;
	
	static protected String getname()
	{	 return name;	}
	static protected int getbal()
	{	 return balance;	}
	static protected int getacno()
	{	 return acno;	}
	static protected String gettype()
	{	 return type;	}
	
	protected void set(int acno,String name,String type,int balance)
	{
		Main.acno=acno;
		Main.name=name;
		Main.type=type;
		Main.balance=balance;
	}
	protected boolean isempty(){
		return name.isEmpty();
	}
};
