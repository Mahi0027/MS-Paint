import java.util.Scanner;

class Test {

	public static void main(String[] args) {
	byte b1,b2,sum;
	b1=120;
	b2=8;
	sum=(byte)(b1+b2);
	System.out.println(sum);
	h1();
	}
	
	static void h1()
	{
	Throwable t = new Throwable("h");
	System.out.println();
	t.printStackTrace();
	Scanner sc=new Scanner(System.in);
	
	///		nextLine after next creates error as next doesn't absorbs newline character.
	String a=sc.next();
	sc.nextLine();
	String b=sc.nextLine();
	
	System.out.println(">"+a+"<\n>"+b+"<");
	sc.close();
	
	} 

}
