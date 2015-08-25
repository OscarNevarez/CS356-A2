import java.util.Arrays;

/**
 * This class calculates the percentage of positive messages in the tree model.
 * @author Oscar Nevarez
 * @version 1.0
 */
public class PositivePercentage implements Visitor {
	/*
	 * The current count of positive messages
	 */
	int positiveMessages=0;
	/*
	 * The current count of all messages in the tree model.
	 */
	int messageCount=0;
	
	/*
	 * String array of positive words.
	 */
	String[] positiveWords;
	
	/**
	 * This method splits the input string into an array of positive keywords and assigns it 
	 * to this objects string array field. The string must separate keywords by spaces!
	 * @param positiveWords a string containing positive words
	 */
	public PositivePercentage(String positiveWords){
		this.positiveWords=positiveWords.split(" ");// split remove empty spaces.
	}
	
	/**
	 * This method assigns the string array input parameter to this objects string array field.
	 * @param positiveWords a string array of positive words.
	 */
	public PositivePercentage(String[] positiveWords){
		this.positiveWords=positiveWords;
	}
	@Override
	public void visit(Users node) {
		if(node instanceof IndividualUser){
			Object[] array=((IndividualUser) node).getMessages();
			String[] messages=Arrays.copyOf(array,array.length, String[].class);
			messageCount+=messages.length;
			for(String currentKeyWord: positiveWords)
				for(String currentMessage:messages)
					if(currentMessage.contains(currentKeyWord))
						positiveMessages++;
		}
	}
	/**
	 * Calculates the percentage of positive messages.
	 * @return returns the percentage of positive messages
	 */
	public double result(){
		return positiveMessages*100.0/messageCount;
	}

}
