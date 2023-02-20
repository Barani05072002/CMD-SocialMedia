import java.util.*;
import java.io.*;
public class User {
		private String userName;
		private Long userMobile;
		private String passWord;
		private Map<String,LinkedList<Message>> friendList;
		
		public static User logIn(Map<Long,User> data,Long mobile,String password) {
			Scanner s =new Scanner(System.in);
			User user = data.get(mobile);
			if(user.getPassword().equals(password))
				System.out.println(">>>Login Successfully");
			else
				user = null;
			return user;
		}
		public User(String username,Long usermobile,String password) {
			this.userName = username;
			this.userMobile= usermobile;
			this.passWord = password;
			this.friendList = new HashMap<String,LinkedList<Message>>();
		}
		public User(){
			Scanner s = new Scanner(System.in);
			System.out.print("Enter the Name:");
			this.userName= s.next();
			System.out.print("Enter the mobile:");
			this.userMobile = s.nextLong();
			System.out.print("Enter the password:");
			this.passWord= s.next();
			this.friendList = new HashMap<String,LinkedList<Message>>();
		}
		public String getUsername() {
			return userName;
		}
		public void setUsername() {
			Scanner s = new Scanner(System.in);
			System.out.print("Enter the name:");
			this.userName = s.next();
			System.out.println(">>>Successfully changed");
		}
		public Long getUsermobile() {
			return userMobile;
		}
		public void setUsermobile() {
			Scanner s = new Scanner(System.in);
			System.out.print("Enter the mobileno:");
			this.userMobile = s.nextLong();
			System.out.println(">>>Successfully changed");
		}
		public String getPassword() {
			return passWord;
		}
		public void setPassword() {
			Scanner s = new Scanner(System.in);
			System.out.print("Enter the old password:");
			String password = s.next();
			if(this.getPassword().equals(password)) {
				System.out.print("Enter the new password:");
				this.passWord = s.next();
				System.out.println(">>>Successfully changed");
			}
			else {
				System.out.println(">>>Enter the correct password");
			}
		}
		public void addFriend(Map<Long,User> userData,Long mobile) {

			if(userData.containsKey(mobile)) {
			this.friendList.put(userData.get(mobile).getUsername(),new LinkedList<Message>());
			
			userData.get(mobile).setFriend(this.userName);
			System.out.println(">>>SuccessFully Added in your FriendList");
			}
			else {
				System.out.println("User not found");
			}
		}
		public void setFriend(String name) {
			this.friendList.put(name,new LinkedList<Message>());
		}
		public void viewFriends() {
			Iterator<String> it = this.friendList.keySet().iterator();
			System.out.println("Friends:");
			while(it.hasNext()) {
				System.out.println(">>>"+it.next());
			}
		}
		public void sendMessage(Map<Long,User> data,Long mobile) throws IOException {
			String name = data.get(mobile).getUsername();
			DataInputStream ds = new DataInputStream(System.in);
			if(this.friendList.containsKey(name)) {
				System.out.println("If you want exit the chat type (?exit)");
				this.pastMessages(name);
				String temp = "NULL";
				
				while(!temp.equals("?exit")) {
				System.out.print(this.userName+">");
				temp = ds.readLine();
				Message m = new Message(this.userName,temp);
				this.friendList.get(name).add(m);
				data.get(mobile).setMessage(this.userName,m);
				}
				}
			else {
				System.out.println(">>>User not found");
			}
		}
		public void setMessage(String name,Message m) {
			this.friendList.get(name).add(m);
		}
		public void deleteMessage(String name) {
			this.friendList.get(name).removeLast();
		}
		public void deleteChat(String name) {
			this.friendList.get(name);
		}
		public void pastMessages(String name) {
			Iterator<Message> it = this.friendList.get(name).iterator();
			Message m;
			while(it.hasNext()) {
				m = it.next();
				System.out.println(m.getUser()+">"+m.getContent()+"("+m.getDate()+")");
			}
		}
		public Post post(String name) throws IOException{
			DataInputStream ds = new DataInputStream(System.in);
			System.out.print("Enter the post message:");
			String message = ds.readLine();
			Post p = new Post(this.userName,name,message);
			
			return p;
		}
		public void Mention(Map<Long,User> data,Long mobile,String postName)
		{
			String name = data.get(mobile).getUsername();
			String ment = this.userName + " is mentioned you in "+ postName;
			Message m = new Message(this.userName,ment);
			if(this.friendList.containsKey(mobile)) {
				this.friendList.get(name).add(m);
				data.get(mobile).setMessage(this.userName, m);
			}
		}
}
