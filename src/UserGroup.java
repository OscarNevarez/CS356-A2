import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.tree.TreeNode;


public class UserGroup extends Users{
	private Users parent;
	private String groupID;
	private Vector children; //can also have another userGroup inside.
	
	public UserGroup(String id){
		setID(id);
	}
	public void addUser(Users user,int childIndex){
		super.insert(user, childIndex);
	}
	public void removeUser(Users user){
		super.remove(user);
	}
	public void cycleChildren(){
		System.out.println(""+this.getChildCount());
	}
	public void getLastChildName(){
		System.out.println(""+this.getLastChild());
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