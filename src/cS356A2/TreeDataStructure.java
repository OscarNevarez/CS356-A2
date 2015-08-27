package cS356A2;
import javax.swing.tree.DefaultTreeModel;

/**
 * This interface is used to define the default behaviors that are required
 * for a tree data structure to implement for use with this application.
 * @author Oscar Nevarez
 * @version 1.0
 *
 */
public interface TreeDataStructure {
	
	/**
	 * This method adds a new node into the tree, invokes the tree model's inserNodeInto internally
	 * to trigger the appropriate event for the JTree. Requires JTree to make new node visible.
	 * @param parentNode parent of the node that will be added
	 * @param child the node that will be added to the tree model
	 * @return true if the node was added.
	 */
	public boolean addNode(Users parentNode,Users child);
	
	/**
	 * This method returns true if the tree contains the user specified by the input String
	 * @param userId the id of the user we are trying to find.
	 * @return true if user is in the tree false other wise.
	 */
	public boolean treeContains(String userId);
	
	/**
	 * This method returns a user contained within the tree.
	 * @param userId the id of the user we want to get
	 * @return the user if it is found or null if not
	 */
	public Users getUser(String userId);
	
	/**
	 * This method return the tree data structure.
	 * @return the tree data structure.
	 */
	public DefaultTreeModel getModel();

}
