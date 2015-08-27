package cS356A2;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * This abstract class determines default behaviors for classes the extend this one.
 * @author Oscar Nevarez
 * @version 1.0
 */
@SuppressWarnings("serial")
public abstract class Users extends DefaultMutableTreeNode {
	
	/**
	 * This method set the Id of the class instance
	 * @param id the id that will be set for a class instance
	 */
	public abstract void setID(String id);
	
	/**
	 *This method returns the id of this class instance 
	 * @return the id of this class instance represented as a string
	 */
	public abstract String getID();
	
	/**
	 * This method returns the string representation of an object
	 * @return the string representation of an object.
	 */
	public abstract String toString();
}
