package cS356A2;
/**
 * This class count the total number of groups within a data structure.
 * @author Oscar Nevarez
 * @version 1.0
 */
public class GroupTotal implements Visitor {
	/*
	 * count of counted groups
	 */
	int count=0;
	@Override
	public void visit(Users node) {
		if(node instanceof UserGroup)
			count++;
		else 
			return;

	}
	/**
	 * This method returns how many user groups there are in a data structure.
	 * @return the amount of user groups.
	 */
	public int result(){
		return count;
	}

}
