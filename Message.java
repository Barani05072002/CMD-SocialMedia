import java.util.Date;

public class Message {
	private String content;
	private Date date;
	private String user;
	
	public Message(String name,String message){
		this.user = name;
		this.content = message;
		this.date = new Date();
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getUser()
	{
		return user;
	}
	public String getContent() {
		return content;
	}
	public Date getDate() {
		return date;
	}
}
