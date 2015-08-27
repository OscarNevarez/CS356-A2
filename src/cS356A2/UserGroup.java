package cS356A2;

/**
 * This class creates and instance of a user group and uses composition to store other Users type objects.
 * @author Oscar Nevarez
 * @version 1.0
 */
@SuppressWarnings("serial")
public class UserGroup extends Users{
	
	/*
	 *id of this userGroup 
	 */
	private String groupID;
	
	/**
	 *This method sets the id for this user group 
	 * @param id the id that this user group will have
	 */ 
	public UserGroup(String id){
		setID(id);
	}
	
	/**
	 * This method calls the insert method of the parent class to insert nodes. Note using @see {@link javax.swing.tree.DefaultTreeModel#insertNodeInto(javax.swing.tree.MutableTreeNode, javax.swing.tree.MutableTreeNode, int)} method that is provided by the 
	 * DefaultTreeModel is the preferred way to add nodes due to the fact that it fires the proper event for JTree.
	 * @see javax.swing.tree.DefaultTreeModel
	 * @see javax.swing.tree.DefaultMutableTreeNode
	 * @param user the user that will be added to this user group children
	 * @param childIndex the index where the user parameter will be added in this user groups children Vector
	 */
	public void insert(Users user,int childIndex){
		super.insert(user, childIndex);
	}
	
	/**
	 * This method calls the remove method of the parent class to remove nodes from this node.
	 * Note not used in this assignment.
	 * @param user the child that will be removed from this node.
	 */
	public void removeUser(Users user){
		super.remove(user);
	}
	
	@Override
	public void setID(String id) {
		this.groupID=id;
	}
	@Override
	public String getID() {
		return this.groupID;
	}
	@Override
	public String toString(){
		return this.getID();
	}
}