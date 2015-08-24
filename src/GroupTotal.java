
public class GroupTotal implements Visitor {
	int count=0;
	@Override
	public void visit(Users node) {
		if(node instanceof UserGroup)
			count++;
		else 
			return;

	}
	public int result(){
		return count;
	}

}
