import java.util.Enumeration;
import javax.swing.tree.DefaultTreeModel;

/**
 * This class provides data access to a DefaultTreeModel data model
 * @author Oscar Nevarez
 * @version 1.0
 */
public class VisitableTree  implements Visitable{
	/*
	 * the tree structure that this class will provide access to.
	 */
    private DefaultTreeModel tree;
    
    public VisitableTree(DefaultTreeModel tree){
    	this.tree=tree;
    }
	@Override
	public void accept(Visitor visitor) {
		Users rootNode=(Users) tree.getRoot();
		Enumeration<Users> en = rootNode.preorderEnumeration();
		while (en.hasMoreElements())
		{
			Users node = en.nextElement();
			visitor.visit(node);
		}
		
	}


}
