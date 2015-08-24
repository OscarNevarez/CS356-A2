import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


/**
 * @author Oscar Nevarez
 * @version 1.0
 *
 */
public abstract class Users extends DefaultMutableTreeNode {
	public abstract void setID(String id);
	public abstract String getID();
	public abstract String toString();
}
