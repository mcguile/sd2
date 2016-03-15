package lab2.network3;

import java.util.ArrayList;

public class CommentedPost extends Post {
	
	private int likes;
    private ArrayList<String> comments;

	public CommentedPost(String author) {
		super(author);
		likes = 0;
        comments = new ArrayList<String>();
	}

	/**
     * Record one more 'Like' indication from a user.
     */
    public void like()
    {
        likes++;
    }

    /**
     * Record that a user has withdrawn his/her 'Like' vote.
     */
    public void unlike()
    {
        if (likes > 0) {
            likes--;
        }
    }
    
    /**
     * Add a comment to this post.
     * 
     * @param text  The new comment to add.
     */
    public void addComment(String text)
    {
        comments.add(text);
    }
    
    /**
     * Display the details of this post.
     * 
     * (Currently: Print to the text terminal. This is simulating display 
     * in a web browser for now.)
     */
    public void display()
    {
        super.display(likes, comments);
    }
}
