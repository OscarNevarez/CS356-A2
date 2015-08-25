/**
 * This class searches a data structure for an element that has the same id as the one 
 * given in the constructor.
 * @author Oscar Nevarez
 * @version 1.0
 */
public class LookForMatchingNode implements Visitor {
	
	/*
	 *the id of the node we are looking for 
	 */
	String userId;
	
	/*
	 * a flag used to check if a node was found in a data structure
	 */
	boolean isInTree=false;
	
	/*
	 * The user object that matches the userId string defined above.
	 */
	Users user;
	
	public LookForMatchingNode(String id){this.userId=id;}
	@Override
	public void visit(Users node) {
		if(node.getID().equals(userId)){
				isInTree=true;
				user=node;
		}
				
	}
	/**
	 * this method returns a boolean value based on is a element with a matching id is found
	 * @return true if an element with an id that matches the userId field of this class.
	 */
	public boolean isInTree(){
		return isInTree;
		
	}
	/**
	 * This method returns the element that was found
	 * @return the user node that was found in the tree see {@link LookForMatchingNode#visit(Users node)} method above
	 */
	public Users getFoundUser(){
		return this.user;
	}
}
