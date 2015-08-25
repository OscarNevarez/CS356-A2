import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.tree.TreeNode;


public class UserGroup extends Users{
	
	/*
	 *parent group of this usergroup, if null the this node is the root. 
	 */
	private Users parent;
	
	/*
	 *id of this userGroup 
	 */
	private String groupID;
	
	/*
	 * used to store IndividualUsers and other UserGroups
	 */
	private Vector children; //can also have another userGroup inside.
	
	
	/**
	 *This method sets the id for this user group 
	 * @param id the id that this user group will have
	 */ 
	public UserGroup(String id){
		setID(id);
	}
	
	/**
	 * This method calls the insert method of the parent class to insert nodes. Note using @see inserNodeInto method that is provided by the 
	 * DefaultTreeModel is the preferred way to add nodes due to the fact that it fires the proper event for JTree.
	 * @see javax.swing.tree.DefaultTreeModel
	 * @see javax.swing.tree.DefaultMutableTreeNode
	 * @param user the user that will be added to this user group children
	 * @param childIndex the index where the user parameter will be added in this user groups children Vector
	 */
	public void addUser(Users user,int childIndex){
		super.insert(user, childIndex);
	}
	
	/**
	 * This method calls the remove method of the parent class to remove nodes from this node.
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