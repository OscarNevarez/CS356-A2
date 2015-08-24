import java.util.Enumeration;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;



public class VisitableTree  implements Visitable{
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
