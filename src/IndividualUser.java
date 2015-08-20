import java.util.List;
import java.util.Iterator;


public class IndividualUser implements Users,Observer,Subject {
	private String id;
	private List<Observer> followers;
	private List<Subject> followings;
	private List<String> newsfeed;
	private String message;
	private boolean changeState=false;
	
	public void setID(String id){
		this.id=id;
	}
	public String getID(String message){
		return this.id;
	}
	public void setMessage(String message){
		this.message=message;
	}
	public String getMessage(){
		return this.message;
	}
	public void follow(IndividualUser user){
		followings.add(user);
	}
	public void tweet(String message){
		this.message=message;
		newsfeed.add(message);
		this.changeState=true;
		notifyObservers();
	}
	/**
	 * subject methods
	 */
	@Override
	public void register(Observer o) {
		followers.add(o);
	}
	@Override
	public void unregister(Observer o) {
		followers.remove(o);
	}
	@Override
	public void notifyObservers() {
		
		if(changeState){
			changeState=false;
			Iterator<Observer> observerloop=followers.iterator();
			while(observerloop.hasNext()){
				observerloop.next().update(this);
			}
		}
		else 
			return;
		//if  changed iterates through observer arraylist off followers and calls update.
		
	}
	@Override
	public Object getUpdate(Observer o) {
		return this.getMessage();
	}
	
	
	/**
	 * Observer Methods
	 */
	
	@Override
	public void update(Subject s) {
		s.getUpdate(this);
		
	}
	@Override
	public void setSubject(Subject s) {
		followings.add(s);
		
	}

}
