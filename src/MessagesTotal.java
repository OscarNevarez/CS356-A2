
public class MessagesTotal implements Visitor {
	int count=0;
	@Override
	public void visit(Users node) {
		if(node instanceof IndividualUser){
			count+=((IndividualUser) node).getMessages().size();
		}
	}
	public int result(){
		return count;
	}

}
