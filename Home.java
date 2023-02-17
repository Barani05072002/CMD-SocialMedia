import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
public class Home {
	public static void viewAllPost(LinkedHashSet<Message> post) {
		LinkedList<Message> data = new LinkedList<>();
		Message m;
		data.addAll(post);
		Iterator<Message> it = data.descendingIterator();
		while(it.hasNext()) {
			m = it.next();
			System.out.println(m.getUser()+">"+m.getContent()+"("+m.getDate()+")");
		}
		
		
	}
	public static void main(String arg[]) throws IOException {
		
		//Data
		Map<Long,User> userData = new HashMap<>();
		LinkedHashSet<Message> post  = new LinkedHashSet<>();
		
		Scanner s = new Scanner(System.in);
		User u;
		int l1,l2;
		do {
			System.out.print("1.Login  ||  2.Newuser  ||  3.Close \n>>>");
			l1 = s.nextInt();
			switch(l1) {
			case 1:
				System.out.print("Enter the mobileno:");
				Long mobile = s.nextLong();
				System.out.print("Enter the password:");
				String password = s.next();
				if(userData.containsKey(mobile)) {
					u = User.logIn(userData,mobile,password);
					if(u.getUsername().equals("Nothing"))
						System.out.println(">>>Incorrext mobile no or password");
					else {
						do {
							System.out.println("===========================================================================================");							
							viewAllPost(post);
							System.out.println("===========================================================================================");
							System.out.print("1.UserSettings  ||  2.Friends and Chats  ||    3.Post  ||  4.logout \n>>>");
							l2 = s.nextInt();
							int userSetting,friendChat;
							switch(l2) {
							case 1:
								do {
									System.out.print("1.Changeusername  ||  2.Changepassword  || 3.Changemobileno ||  4.ViewProfile  ||  5.Back \n>>>");
									userSetting = s.nextInt();
									switch(userSetting) {
									case 1:
										u.setUsername();
										break;
									case 2:
										u.setPassword();
										break;
									case 3:
										u.setUsermobile();
										break;
									case 4:
										System.out.println("Name>"+u.getUsername()+"\nMobileNo>"+u.getUsermobile());
									}
								}while(userSetting<5);
								break;
							case 2:
								do {
									System.out.print("1.Addfriend  ||  2.ViewFriendList  ||  3.Chatfriend  ||  4.Back \n>>>");
									friendChat = s.nextInt();
									switch(friendChat) {
									case 1:
										System.out.print("Enter the friend mobile no:");
										Long mobile1 = s.nextLong();
										u.addFriend(userData, mobile1);
										break;
									case 2:
										u.viewFriends();
										break;
									case 3:
										System.out.print("Enter the mobile no");
										u.sendMessage(userData, s.nextLong());
										break;
									}
								}while(friendChat<4);
								break;
							case 3:
								post.add(u.post());
								System.out.println(">>>Your post is added");
								
							}
						}while(l2<4);
					}
				}
				else {
					System.out.println(">>>User not found :/");
				}
				break;
			case 2:
				User newUser = new User();
				userData.put(newUser.getUsermobile(),newUser);
				System.out.println(">>>Successfully Create");
				break;
			}
		}while(l1<3);
	}

}
