package cS356A2;
/**
 * This class calculates the amount of messages in the data structure this class is visiting.
 * @author Oscar Nevarez
 * @version 1.0
 */
public class MessagesTotal implements Visitor {
	/*
	 * current count of counted messages
	 */
	int count=0;
	@Override
	public void visit(Users node) {
		if(node instanceof IndividualUser){
			count+=((IndividualUser) node).getMessages().length;
		}
	}
	/**
	 * This method returns the result of the calculation performed in the {@link MessagesTotal#visit(Users node)} method
	 * @return the total number of messages in the data structure
	 */
	public int result(){
		return count;
	}

}
