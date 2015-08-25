/**
 * This interface defines the basic behavior of an Observer type.
 * @author Oscar Nevarez
 * @version 1.0
 */
public interface Observer {
	/**
	 * This method retrieves the update from the subject, this method does not dictate how a 
	 * subject pushes updates.
	 * @param s the subject that has the new updated data
	 */
	public void update(Subject s);
	/**
	 * This method sets this observers subject to s
	 * @param s the subject that will be set for the observer
	 */
	public void setSubject(Subject s);
}
