package cS356A2;
import javax.swing.JTree;

/**
 * This interface defines the default behaviors of type AdminPanel
 * @author Oscar Nevarez
 * @version 1.0
 *
 */
public interface AdminPanel {

	/**
	 *Sets the icons for node types, if node is an instance of IndividualUser it is a leaf
	 *@see MyCellRenderer
	 */
	public void setIcons();

	/**
	 * This method opens a user view for a given user object
	 * @param user the user whose user view will be opened.
	 */
	public void openUserView(Users user);

	/**
	 * This method return the currently selected node in the JTree
	 * @param tree the JTree we will get the selected node of
	 * @return the selected node.
	 */
	public Users getSelectedNode(JTree tree);
}
