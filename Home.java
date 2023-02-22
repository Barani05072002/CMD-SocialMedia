import java.io.IOException;
import java.util.*;
public class Home {
	public static void viewAllPost(HashMap<String,HashMap<Integer,Post>> postData) {
		LinkedList<Post> disp = new LinkedList<>();
		for(Map.Entry<String,HashMap<Integer,Post>> entry:postData.entrySet()) {
			disp.addAll(entry.getValue().values());
			}
		Iterator<Post> it = disp.descendingIterator();
		while(it.hasNext())
		{
			Post p = (Post) it.next();
			System.out.println("User>"+p.getUser()+"\nPostName>"+p.getPostId()+"\n"+p.getContent()+"\nViews:"+p.getViews()+"\tLikes:"+p.getPostLikes());
		}
	}
	public static User logIn(HashMap<String, User> userData, String userId, String password) {
		Scanner s =new Scanner(System.in);
		User user = userData.get(userId);
		if(user.getPassword().equals(password))
			System.out.println(">>>Login Successfully");
		else
			user = null;
		return user;
	}
	public static String userSearch(HashMap<String,User> userData,String name) {
		for(Map.Entry<String,User> entry:userData.entrySet()) {
			if(name.equals(entry.getValue().getUsername())) {
				return entry.getKey();
			}
		}
		return null;
	}
	public static void addFriend(HashMap<String,User> userData,User u,String name) {
		String friendId = userSearch(userData,name);
		if(friendId!=null) {
			u.setFriend(friendId);
			userData.get(friendId).setFriend(u.userID());
			System.out.println("Successfully added in friendList");
		}
	}
	public static LinkedList<Post> postSearch(HashMap<String,HashMap<Integer,Post>> postData,String searchString) {
		LinkedList<Post> temp = new LinkedList<>();
		
		for(Map.Entry<String,HashMap<Integer,Post>> entry:postData.entrySet()) {
			for(Map.Entry<Integer,Post> entry1:entry.getValue().entrySet()) {
				Post p = entry1.getValue();
				if(p.search(searchString)) {
				temp.add(p);
				}
			}
			}
		return temp;
		}
	public static void main(String arg[]) throws IOException {
		//Data
		HashMap<String,User> userData = new HashMap<>();
		HashMap<String,HashMap<Integer,Post>> postData = new HashMap<String,HashMap<Integer,Post>>();
		
		Scanner s = new Scanner(System.in);
		User u;
		int l1,l2;
		do {
			System.out.print("1.Login  ||  2.Newuser  ||  3.Close \n>>>");
			l1 = s.nextInt();
			switch(l1) {
			case 1:
				System.out.print("Enter the userId:");
				String userID = s.next();
				System.out.print("Enter the password:");
				String password = s.next();
				if(userData.containsKey(userID)) {
					u = logIn(userData,userID,password);
					if(u == null)
						System.out.println(">>>Incorrext mobile no or password");
					else {
						do {
							System.out.println("===========================================================================================");							
							viewAllPost(postData);
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
										System.out.print("Enter the friend name:");
										String name = s.next();
										addFriend(userData,u,name);
										break;
									case 2:
										u.viewFriends();
										break;
									case 3:
										System.out.print("Enter the userid");
										u.sendMessage(userData, s.next());
										break;
									}
								}while(friendChat<4);
								break;
							case 3:
								do {
									System.out.println("===========================================================================================");							
									viewAllPost(postData);
									System.out.println("===========================================================================================");
									System.out.print("1.addPost  ||  2.viewPost  ||  3.searchPost  ||  4.Back \n>>>");
									postControl = s.nextInt();
									switch(postControl) {
									case 1:
										if(postData.containsKey(u.userID())) {
											Post p = u.post();
											postData.get(u.userID()).put(p.getPostId(),p);
										}
										else
										{
											postData.put(u.userID(),new HashMap<Integer,Post>());
											Post p = u.post();
											postData.get(u.userID()).put(p.getPostId(),p);
										}
										System.out.println(">>>Your post is added");
										break;
									case 2:
										System.out.print("Enter the userid-postid:");
										String str = s.next();
										StringTokenizer st = new StringTokenizer(str,"-");
										Post postInner = postData.get(st.nextToken()).get(Integer.parseInt(st.nextToken()));
										postInner.addViews();
											do {
												System.out.println("===========================================================================================");	
												System.out.println(postInner.getUser()+"\n"+postInner.getPostId()+"\nViews:"+postInner.getViews()+"\tLikes:"+postInner.getPostLikes());
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
												System.out.println("Enter the mentioned person userid:");
												String mentionPerson = s.next();
												u.Mention(userData, mentionPerson,postInner.getPostId());
											}
										}while(postElementControl<4);
									case 3:
										System.out.print("Enter the word");
										LinkedList<Post> searchResult= postSearch(postData,s.next());
										Iterator<Post> it = searchResult.iterator();
										while(it.hasNext()) {
											Post post = it.next();
											System.out.println("===========================================================================================");	
											System.out.println(post.getUser()+"\n"+post.getPostId()+"\nViews:"+post.getViews()+"\tLikes:"+post.getPostLikes());
											System.out.println("===========================================================================================");
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
				userData.put(newUser.userID(),newUser);
				System.out.println(">>>Successfully Create");
				break;
			}
		}while(l1<3);
	}

}
