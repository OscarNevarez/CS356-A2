
public class LookForMatchingNode implements Visitor {
	String userId;
	boolean isInTree=false;
	Users user;
	public LookForMatchingNode(String id){this.userId=id;}
	@Override
	public void visit(Users node) {
		if(node.getID().equals(userId)){
				isInTree=true;
				user=node;
		}
				
	}
	public boolean isInTree(){
		return isInTree;
		
	}
	public Users getFoundUser(){
		return this.user;
	}
}
