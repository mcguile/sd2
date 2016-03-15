package lab2.network3;

public class EventPost extends Post {

	private String eventType;
	
	/**
     * Constructor for objects of class EventPost.
     * 
     * @param author    The username of the author of this post.
     * @param event     The event of this post.
     */
	public EventPost(String author, String event) {
		super(author);
		eventType = event;
	}
	
	/**
	 * Return the event type
	 * 
	 * @return The String of the event title
	 */
	public String getEvent() {
		return eventType;
	}

	/**
     * Display the details of this post.
     * 
     * (Currently: Print to the text terminal. This is simulating display 
     * in a web browser for now.)
     */
    public void display()
    {
    	super.display();
        System.out.println(eventType);
    }
}
