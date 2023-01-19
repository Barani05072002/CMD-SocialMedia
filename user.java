import java.util.*;
public class user
{
	private String name;
	private String password;
	private String num;
	private ArrayList<message> receive_msg = new ArrayList<>();
	private ArrayList<message> send_msg = new ArrayList<>();
	private static ArrayList<user> u = new ArrayList<>();
	private boolean isonline=false;
	private ArrayList<Integer> friends = new ArrayList<>();
	
	public user()
	{
	Scanner s = new Scanner(System.in);
	System.out.print("\nEnter your name:"); 
	name = s.next();				
	System.out.print("\nEnter the password:");
	password = s.next();	
	System.out.print("\nEnter your mobile no:");
	num = s.next();
	
	u.add(this);
	}
	
	public void set_online()	//set online 
	{ isonline=true; }

	public void set_offline()
	{isonline=false; }

	public boolean isonline()
	{return isonline;}	

	public void set_name(String name)	
	{this.name = name;}
	
	public void set_password(String pass)	
	{password = pass;}	

	public String get_num()	
	{return num;}
	
	public String get_name()
	{return name;}
	
	private String get_password()
	{return password;}	

	public void send(String num,String mess)	
	{
	message m = new message(this.name,mess,num); //change in fut
	send_msg.add(m);
	Iterator<Integer> i = friends.iterator();
	if(friends.isEmpty()==false){
	while(i.hasNext())
	{
		int temp = i.next();
		if(num.equals(u.get(temp).get_num()))
		{u.get(temp).receive(m);break;}
	}}
	else
	{
	System.out.println("Add Friend first");
	}
	}

	public void receive(message m)
	{
	receive_msg.add(m);
	}
	
	public void read()
	{
	Iterator<message> i = receive_msg.iterator();
	while(i.hasNext())
	{
		message m = i.next();
		System.out.println(m.get_sender() +": "+ m.get_message());
		m.set_opened();
	} 
	}

	private void addfriend(String num)
	{
	Iterator<user> i = u.iterator();
	int flag = -1;
	boolean isadded = false;
		while(i.hasNext())
		{
		flag++;
			if(num.equals(i.next().get_num()))
			{
				isadded = true;

				if(friends.contains(flag))
				{System.out.println("Already have been friend");}
				
				else
				{friends.add(flag);
				System.out.println("Success fully added");
				break;}
			}
		}
		if(isadded == false)
		{
		System.out.println("person not found");
		}

	}
	
	public static void login()
	{
	int id=-1;
	String num,password;
	Scanner s = new Scanner(System.in);
	
	System.out.print("\nEnter your login num:");
	num = s.next();
	
	System.out.print("Enter your password:");
	password = s.next();

	Iterator<user> i = u.iterator();
	while(i.hasNext())
	{
	id++;
	if(num.equals(i.next().get_num()))
	break;
	}
	

	//after id setting
	user t = u.get(id);

	if(num.equals(t.get_num()) && password.equals(t.get_password()))
	{
	t.set_online();
	System.out.println("log in....");
	}
	else
	{
	System.out.println("Password or user_name is wrong");
	}
	
	while(t.isonline())
	{
	System.out.println("1.Send messages\n2.Read messages\n3.Add Friend\n4.log_off");
	int op1 = s.nextInt();   //phase 2
	switch(op1)
	{
	case 1:
		{
		System.out.print("\nEnter your frnd num:");
		String num1 = s.next();
		System.out.print("\nEnter your message:");
		String data1 = s.next();
		t.send(num1,data1);
		break;
		}
	case 2:
		{t.read();
		break;}
	case 3:
		{
		System.out.print("Enter your frnd num:");
		t.addfriend(s.next());
		break;
		}
	case 4:
		{
		t.set_offline();
		}
	}
	}	
	}
}

