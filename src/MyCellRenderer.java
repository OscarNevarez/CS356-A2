import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * This class creates a custom Tree cell renderer
 * @author Oscar Nevarez
 * @version 1.0
 */
public class MyCellRenderer extends DefaultTreeCellRenderer {
	/*
	 * icon for leaf nodes
	 */
	Icon leafIcon;
	
	/*
	 * icons for non  leaf nodes
	 */
	Icon nonLeafIcon;
	/**
	 * This constructor assigns the passed in icons to the class attribute icons.
	 * @param leafIcon the icon that will be set for leaf nodes
	 * @param nonLeafIcon the icon that will be set for non leaf nodes
	 */
    public MyCellRenderer(Icon leafIcon, Icon nonLeafIcon) {
     this.leafIcon = leafIcon;
     this.nonLeafIcon=nonLeafIcon;
    }
    /**
     * @see DefaultTreeCellRenderer
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value,
        boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

      super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row,
          hasFocus);
      
      if (value instanceof IndividualUser) {
        setIcon(leafIcon);
      } else {
    	setIcon(nonLeafIcon);
      }
      return this;
    }
}
