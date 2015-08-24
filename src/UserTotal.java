
public class UserTotal implements Visitor {
	int count=0;
	@Override
	public void visit(Users node) {
		if(node instanceof IndividualUser)
			count++;
		else return;
	}
	public int result(){
		return count;
	}
}
