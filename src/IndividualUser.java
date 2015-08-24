import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class IndividualUser extends Users implements Observer,Subject {
	private String id;
	private ArrayList<Observer> followers=new ArrayList<Observer>();;
	private ArrayList<Subject> followings=new ArrayList<Subject>();
	private ArrayList<String> newsfeed=new ArrayList<String>();
	private DefaultListModel<String> newsFeedListModel;
	private String message;
	private boolean changeState=false;
	
	public IndividualUser(String id,DefaultListModel<String>list){
		setID(id);
		this.allowsChildren=false;
		this.newsFeedListModel=list;
	}
	@Override
	public void setID(String id){
		this.id=id;
	}
	@Override
	public String getID(){
		return this.id;
	}
	public DefaultListModel<String> getListModel(){
		return this.newsFeedListModel;
	}
	@Override
	public String toString(){
		return this.getID();
	}
	public void follow(IndividualUser user){
		setSubject(user);
		user.register(this);
	}
	public void tweet(String message){
		this.message=message;
		newsfeed.add(message);
		this.changeState=true;
		notifyObservers();
	}
	public ArrayList<String> getMessages(){//used to see if observer pattern works.
			return  this.newsfeed;
	}
	public ArrayList<Observer> getFollowers(){
		return this.followers;
	}
	public ArrayList<Subject> getFollowings(){
		return this.followings;
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
	}
	@Override
	public String getUpdate(Observer o) {
		return this.message;
	}
	
	
	/**
	 * Observer Methods
	 */
	
	@Override
	public void update(Subject s) {
		String update=s.getUpdate(this);
		this.newsfeed.add(update);//add the new message returned by subject to this news feed.
		this.newsFeedListModel.addElement("- "+s.toString()+": "+update);
		
	}
	@Override
	public void setSubject(Subject s) {
		followings.add(s);
		
	}
}