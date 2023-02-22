import java.util.*;
import java.io.*;
public class User {
		private String userId;
		private String userName;
		private Long userMobile;
		private String passWord;
		private Map<String,LinkedList<Message>> friendList;
		
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
			System.out.print("Enter the userid:(ex>lux0507)");
			this.userId = s.next();
			System.out.print("Enter the password:");
			this.passWord= s.next();
			this.friendList = new HashMap<String,LinkedList<Message>>();
		}
		public String userID() {
			return this.userId;
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
		public void sendMessage(HashMap<String,User> data,String name) throws IOException {
			DataInputStream ds = new DataInputStream(System.in);
			if(this.friendList.containsKey(name)) {
				System.out.println("If you want exit the chat type (?exit)");
				this.pastMessages(data,name);
				String temp = "NULL";
				
				while(!temp.equals("?exit")) {
				System.out.print(this.userName+">");
				temp = ds.readLine();
				Message m = new Message(this.userId,temp,name);
				this.friendList.get(name).add(m);
				data.get(name).setMessage(this.userId, m);
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
		public void pastMessages(HashMap<String,User> userData,String userid) {
			Iterator<Message> it = this.friendList.get(userid).iterator();
			Message m;
			while(it.hasNext()) {
				m = it.next();
				System.out.println(userData.get(m.getSender()).getUsername()+">"+m.getContent()+"("+m.getDate()+")");
			}
		}
		public Post post() throws IOException{
			DataInputStream ds = new DataInputStream(System.in);
			System.out.print("Enter the post message:");
			String message = ds.readLine();
			Post p = new Post(this.userId,message);
			
			return p;
		}
		public void Mention(Map<String,User> data,String name,int i)
		{
			String ment = this.userName + " is mentioned you in "+ i;
			Message m = new Message(this.userName,ment,name);
			if(this.friendList.containsKey(name)) {
				this.friendList.get(name).add(m);
				data.get(name).setMessage(this.userName, m);
			}
		}
}
