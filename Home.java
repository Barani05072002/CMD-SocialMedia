import java.io.IOException;
import java.util.*;
public class Home {
	public static void viewAllPost(HashMap<String,Post> post) {
		LinkedList<Post> disp = new LinkedList<>();
		disp.addAll(post.values());
		Iterator<Post> it = disp.descendingIterator();
		while(it.hasNext())
		{
			Post p = (Post) it.next();
			System.out.println("User>"+p.getUser()+"\nPostName>"+p.getPostName()+"\n"+p.getContent()+"\nViews:"+p.getViews()+"\tLikes:"+p.getPostLikes());
		}
		}
	public static void main(String arg[]) throws IOException {
		
		//Data
		HashMap<Long,User> userData = new HashMap<>();
		HashMap<String,Post> post = new HashMap<>();
		
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
					if(u == null)
						System.out.println(">>>Incorrext mobile no or password");
					else {
						do {
							System.out.println("===========================================================================================");							
							viewAllPost(post);
							System.out.println("===========================================================================================");
							System.out.print("1.UserSettings  ||  2.Friends and Chats  ||    3.Post  ||  4.logout \n>>>");
							l2 = s.nextInt();
							int userSetting,friendChat,postControl,postElementControl;
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
								do {
									System.out.println("===========================================================================================");							
									viewAllPost(post);
									System.out.println("===========================================================================================");
									System.out.print("1.addPost  ||  2.viewPost  ||  3.searchPost  ||  4.Back \n>>>");
									postControl = s.nextInt();
									switch(postControl) {
									case 1:
										System.out.print("Enter the post Name:");
										String name = s.next();
										post.put(name, u.post(name));
										System.out.println(">>>Your post is added");
										break;
									case 2:
										System.out.print("Enter the postName:");
										Post postInner =post.get(s.next());
										postInner.addViews();
											do {
												System.out.println("===========================================================================================");	
												System.out.println(postInner.getUser()+"\n"+postInner.getPostName()+"\nViews:"+postInner.getViews()+"\tLikes:"+postInner.getPostLikes());
												System.out.println("===========================================================================================");

											System.out.print("1.Like  ||  2.Comment  ||  3.Mention   ||  4.Back \n>>>");
											postElementControl =s.nextInt();
											switch(postElementControl) {
											case 1:
												postInner.addLike();
												break;
											case 2:
												postInner.viewAllComment();
												postInner.comment(u.getUsername());
												break;
											case 3:
												System.out.println("Enter the mentioned person mobile no:");
												mobile = s.nextLong();
												u.Mention(userData,mobile,postInner.getPostName());
												break;
											}
										}while(postElementControl<4);
									case 3:
										System.out.print("Enter the postName:");
										String searchPost = s.next();
										if(post.containsKey(searchPost)) {
											System.out.println("===========================================================================================");	
											System.out.println(post.get(searchPost).getUser()+"\n"+post.get(searchPost).getPostName()+"\nViews:"+post.get(searchPost).getViews()+"\tLikes:"+post.get(searchPost).getPostLikes());
											System.out.println("===========================================================================================");
										}
										else {
											System.out.println(">>>Post not fount");
										}
									}
								}while(postControl<4);
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
