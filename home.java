import java.util.*;
class home
{
/*public static void login()
{
	String num,password;
	int id;
	Scanner s = new Scanner(System.in);
	
	System.out.print("\nEnter your login num:");
	num = s.next();
	
	System.out.print("Enter your password:");
	password = s.next();
	
	//if(user.check(num,password))
	//System.out.println("log in......");

	//else
	//System.out.println("User_name or Password wrong");
	
	user.login(num,password);
	
}*/
public static void main(String arg[])
{
	
	Scanner s = new Scanner(System.in);
	int op1;  //phase 1;
	do
	{
	System.out.println("1.login\n2.New User\n3.Exit");
	op1 = s.nextInt();
		if(op1==1)
		{
		try
		{user.login();}
		catch(Exception e)
		{System.out.println("User not found,Create a new user");}
		}
		if(op1==2)
		{
		user u1 = new user();
		System.out.println("Successfully Created,Welcome\n\n");
		}
	}while(op1<3);
}
}