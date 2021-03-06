package cS356A2;

import javax.swing.DefaultListModel;
/**
 * This class is used to create Individual User objects for use in a default tree model.
 * Note this inheritance is sound, although Individual Users cannot have children, DefaultMutableTree can also not have children.
 * Whether they are allowed to have children is determined by the boolean variable allowsChildren
 * @see  javax.swing.tree.DefaultMutableTreeNode
 * @author Oscar Nevarez
 * @version 1.0
 */
@SuppressWarnings("serial")
public class IndividualUser extends Users implements Observer,Subject {
	
	/*
	 * String that represents the users id.
	 */
	private String id;
	
	/*
	 * A list of followers, used for observer pattern
	 */
	private DefaultListModel<Observer> followers=new DefaultListModel<Observer>();
	
	/*
	 * A list of subjects, used for the observer pattern
	 */
	private DefaultListModel<Subject> followings=new DefaultListModel<Subject>();
		
	/*
	 * list model used to update jlist in gui, takes advantage of observer pattern to update
	 * news feed. 
	 */
	private DefaultListModel<String> newsFeedListModel=new DefaultListModel<String>();
	
	/*
	 * A message that will be added to news feed.
	 */
	private String message;
	
	/*
	 * When this objects news feed is added to its state changes
	 */
	private boolean changeState=false;
	
	/**
	 * This constructor creates and instance of this object with the id parameter, and instantiates this 
	 * objects list model.
	 * @param id the id that will be assigned to this user object
	 * @see javax.swing.DefaultListModel
	 */
	public IndividualUser(String id){
		setID(id);
		this.allowsChildren=false;
		this.newsFeedListModel=new DefaultListModel<String>();
	}
	@Override
	public void setID(String id){
		this.id=id;
	}
	@Override
	public String getID(){
		return this.id;
	}
	
	/**
	 * This method returns the listModel of type string in this objects listModel field.
	 * @return A listModel object.
	 */
	public DefaultListModel<String> getNewsFeedListModel(){
		return this.newsFeedListModel;
	}
	/**
	 * This method returns the listModel of type subject in this objects listModel field.
	 * @return A listModel  object.
	 */
	public DefaultListModel<Subject> getFollowingsListModel(){
		return this.followings;
	}
	@Override
	public String toString(){
		return this.getID();
	}
	
	/**
	 * Adds the supplied user object to this objects subject list. 
	 * The supplied user object registers this object as an observer.
	 * @param user the subject that will be added to this objects subject list.
	 */
	public void follow(IndividualUser user){
		setSubject(user);
		user.register(this);
	}
	
	/**
	 * Sets the message of this object to the supplied message string.
	 * Adds the string to the news feed list and sets the chageState flag to true.
	 * Notifies registered observers.
	 * @param message a message that will be added to this objects news feed.
	 */
	public void tweet(String message){
		this.message=message;
		newsFeedListModel.addElement("- Me: "+message);
		this.changeState=true;
		notifyObservers();
	}
	
	/**
	 * Returns an object array of this objects news feed
	 * @return this objects news feed
	 */
	public Object[] getMessages(){
			return  this.newsFeedListModel.toArray();
	}
	
	/**
	 * Returns an object array of this object followers
	 * @return this objects followers
	 */
	public Object[] getFollowers(){
		return this.followers.toArray();
	}
	
	/**
	 * Returns an object array of this objects followings, other users that this user is following.
	 * Must be down casted!
	 * @return this objects followings
	 */
	public Object[] getFollowings(){
		return this.followings.toArray();
	}
	
	/**
	 * subject methods
	 */
	@Override
	public void register(Observer o) {
		followers.addElement(o);
	}
	@Override
	public void unregister(Observer o) {
		followers.removeElement(o);
	}
	@Override
	public void notifyObservers() {
		
		if(changeState){
			changeState=false;
			for(Object user:followers.toArray())
				((Observer) user).update(this);
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
		
		//add the new message returned by subject to this news feed.
		this.newsFeedListModel.addElement("- "+s.toString()+": "+update);
		
	}
	@Override
	public void setSubject(Subject s) {
		followings.addElement(s);
		
	}
}