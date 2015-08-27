package cS356A2;
/**
 * This interface defines the default behaviors of an IndividualPanel type.
 * @author Oscar Nevarez
 * @version 1.0
 */
public interface IndividualPanel {
	
	/**
	 * This method checks to see if the parameter node is already being followed by this user
	 * @param user node the user this user is trying to follow
	 * @return true if this user is already following the parameter node.
	 */
	public boolean alreadyFollowingUser(Users user);
	
	/**
	 * This method check to see if the the parameter node is this user and returns true if this 
	 * node and the parameter node are equal.
	 * @param user the user node that this user is trying to follow
	 * @return true if this user is the same as the parameter node
	 */
	public boolean followingOwn(Users user);
	
	/**
	 * This method adds a IndividualUsers object to the current viewed users followings list
	 * @param userToFollow the user who will be followed by the currently viewed user.
	 */
	public void follow(IndividualUser userToFollow);
	
	/**
	 * This method adds a  message to the currently viewed users news feed list.
	 * @param msg the message that will be added
	 */
	public void tweet(String msg);
	
}
