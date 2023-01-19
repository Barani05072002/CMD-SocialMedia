public class message
{
	private String data;
	private String sender;
	private String receiver;
	private String reply;
	private boolean isopened=false;

	public message(String sender,String data,String receiver)
	{
	this.sender = sender;
	this.data = data;
	this.receiver = receiver;
	}

	public String get_message(){return data;}

	public String get_sender(){return sender;}

	public String get_receiver(){return receiver;}

	public void set_opened()
	{
	isopened = true;
	}

}