/**
 * This class calculates the total amount of individual users in a data structure. 
 * @author Oscar Nevarez
 * @version 1.0
 */
public class UserTotal implements Visitor {
	/*
	 * the count of Users counted
	 */
	int count=0;
	@Override
	public void visit(Users node) {
		if(node instanceof IndividualUser)
			count++;
		else return;
	}
	/**
	 * This method returns the result of the calculation performed by {@link UserTotal#visit(Users node)}
	 * @return the total count of Individual Users in a data structure.
	 */
	public int result(){
		return count;
	}
}
