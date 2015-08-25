import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


/**
 * This abstract class determines default behaviors for classes the extend this one.
 * @author Oscar Nevarez
 * @version 1.0
 */
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

	public abstract String toString();
}
