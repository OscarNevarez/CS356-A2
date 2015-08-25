/**
 * This interface outlines the required behaviors of a Subject type
 * @author Oscar Nevarez
 * @version 1.0
 */
public interface Subject {
	
	/**
	 * This method registers an Observer type to a subject type
	 * @param o the observer that will be registered to this subject
	 */
	public void register(Observer o);
	
	/**
	 * This method unregisters a previously registered observer
	 * @param o the observer that will be unregistered to this subject
	 */
	public void unregister(Observer o);
	
	/**
	 *This method notifies this subject observers of changes 
	 */
	public void notifyObservers();
	
	/**
	 * This method pushes the update to observers, called from the observer method update()
	 * @param o the observer that is calling this method
	 * @return the new updated state 
	 */
	public String getUpdate(Observer o);
}
