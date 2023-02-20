import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
public class Post extends Message{
	private int likes;
	private int views;
	private String postName;
	private String discription;
	private HashMap<String,String> comments = new HashMap<>();
	
	public Post(String name,String postName, String message) {
		super(name, message);
		this.postName = postName;
		this.likes = 0;
		this.views = 0;
		this.discription = new String();
	}
	public String getPostName() {
		return postName;
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
}
