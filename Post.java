import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
public class Post{
	private String userid;
	private int postid;
	private String content;
	private Date postDate;
	private int likes;
	private int views;
	private String discription;
	private static int postCount=0;
	private HashMap<String,String> comments = new HashMap<>();
	
	
	public Post(String name, String message) {
		this.userid = name;
		this.content = message;
		this.postDate = new Date();
		this.likes = 0;
		this.views = 0;
		this.discription = new String();
		postCount++;
		this.postid = postCount;
	}
	public int getPostId()
	{return postid;}
	public void setUser(String user)
	{
		this.userid = user;
	}
	public String getUser()
	{
		return userid;
	}
	public String getContent() {
		return content;
	}
	public Date getDate() {
		return postDate;
	}
	public int getPostLikes() {
		return likes;
	}
	public void addLike() {
		likes++;
	}
	public int getViews() {
		return views;
	}
	public void addViews() {
		this.views++;
	}
	public void comment(String userName) throws IOException{
		DataInputStream ds = new DataInputStream(System.in);
		System.out.print("Enter your comment:");
		String data = ds.readLine();
		this.comments.put(userName, data);
	}
	public void addDiscription() throws IOException
	{
		DataInputStream ds = new DataInputStream(System.in);
		System.out.print("Enter the Discription:");
		this.discription = ds.readLine();
	}
	public void viewAllComment()
	{
		Iterator<String> it = this.comments.keySet().iterator();
		while(it.hasNext()) {
			System.out.println(it.next() +">" + this.comments.get(it));
		}
	}
	public boolean search(String subString) {
		return this.content.contains(subString);
	}
}
