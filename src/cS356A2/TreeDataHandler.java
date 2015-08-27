package cS356A2;
import java.util.Map;
import javax.swing.tree.DefaultTreeModel;

/**
 * This class provide access to a tree data structure, for quick checking and validation 
 * purposes a hash map is used to store references of nodes in the tree, thus checking and finding users is constant time.
 * The sole reason a DefaultTreeModel is used is for JTree.
 * @author Oscar Nevarez
 * @version 1.0
 */
public class TreeDataHandler implements Visitable,TreeDataStructure  {
	private Map<String,Users> map;
	private DefaultTreeModel tree;
	private PopUpDialogBox popUp=new PopUpDialogBox();
	
	/**
	 * This constructor creates an instance of this class with the specified map in the parameter
	 * @param map the map that will be used to store node refrences.
	 */
	public  TreeDataHandler(Map<String,Users> map){
		Users root=new UserGroup("Root");
		this.map=map;
		this.tree=new DefaultTreeModel(root);
		map.put(root.getID(), root);
	}
	@Override
	public boolean addNode(Users parentNode,Users child) {
		if(treeContains(child.getID())){
			popUp.infoBox("Node already exists in tree!","ERROR!");
			return false;
		}
		if(!parentNode.getAllowsChildren()){
			popUp.infoBox("Operation not supported! Leaf nodes cannot have children.","ERROR!");
			return false;
		}
		map.put(child.getID(), child);
		tree.insertNodeInto(child,parentNode,parentNode.getChildCount());
		return true;
	}
    @Override
	public boolean treeContains(String userId) {
		return map.containsKey(userId);
	}
	@Override
	public Users getUser(String userId){
		if (treeContains(userId)){
			return map.get(userId);
		}
		else{
			return null;
		}
	}
	@Override
	public DefaultTreeModel getModel() {
		return this.tree;
	}
	@Override
	public void accept(Visitor visitor) {
		for (Map.Entry<String, Users> entry : map.entrySet()){
			  visitor.visit(entry.getValue());
		}


	}

}
