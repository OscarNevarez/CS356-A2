import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

import java.awt.Color;

/**
 * This class creates a JFrame or individual user view
 * @author Oscar Nevarez
 * @version 1.0
 */
@SuppressWarnings("serial")
public class IndividualUserWindow extends JFrame {
	/*
	 * user object and treeModel object passed in via constructor.
	 */
	private IndividualUser user;
	private DefaultTreeModel treeModel;
	/*
	 * text Boxes
	 */
	private JTextArea textUserId;
	private JTextArea textTweetMessage;
	/*
	 * JLists
	 */
	private JList<Subject> followingsList;
	private JList<String> newsFeedList;
	/*
	 * buttons 
	 */
	private JButton btnFollow;
	private JButton btnTweet;
	private JButton btnNewButton;
	/*
	 * list models one for followings, the other is a field in the IndividualUser Class.
	 */
	private DefaultListModel<Subject> modelFollowings;
	private DefaultListModel<String> modelNewsFeed;
	private JPanel border1;
	private JPanel border2;
	private JPanel border3;
	private JPanel border4;

	public IndividualUserWindow(IndividualUser individualUser,DefaultTreeModel tree) {
		this.user=individualUser;
		this.treeModel=(DefaultTreeModel) tree;
		this.modelNewsFeed= user.getNewsFeedListModel();
		this.modelFollowings=user.getFollowingsListModel();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(user.getID()+"'s "+"User View");
		setBounds(100, 100, 477, 435);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//border and JList
		border1 = new JPanel();
		border1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Following:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		border1.setBounds(0, 56, 469, 119);
		contentPane.add(border1);
		border1.setLayout(null);
		followingsList=new JList<Subject>(modelFollowings);
		JScrollPane scrollPane = new JScrollPane(followingsList);
		scrollPane.setBounds(10, 16, 449, 92);
		border1.add(scrollPane);

		//border and JList
		border2 = new JPanel();
		border2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "News Feed", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		border2.setBounds(0, 235, 465, 170);
		contentPane.add(border2);
		border2.setLayout(null);
		newsFeedList=new JList<String>(user.getNewsFeedListModel());
		JScrollPane scrollPane_1 = new JScrollPane(newsFeedList);
		scrollPane_1.setBounds(10, 21, 445, 139);
		border2.add(scrollPane_1);


		/*
		 * border for text box
		 */
		border3 = new JPanel();
		border3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Message", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		border3.setBounds(10, 179, 290, 59);
		contentPane.add(border3);
		border3.setLayout(null);

		/*
		 * text box
		 */
		textTweetMessage = new JTextArea();
		textTweetMessage.setBounds(6, 16, 274, 32);
		border3.add(textTweetMessage);

		/*
		 * border for text box
		 */
		border4 = new JPanel();
		border4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		border4.setBounds(0, 0, 290, 59);
		contentPane.add(border4);
		border4.setLayout(null);

		/*
		 * text box
		 */
		textUserId = new JTextArea();
		textUserId.setBounds(6, 16, 274, 36);
		border4.add(textUserId);
		
		//event handler
		TheHandler handler=new TheHandler();

		/*
		 * buttons 
		 */
		btnTweet = new JButton("Post Tweet");
		btnTweet.setBounds(322, 188, 125, 36);
		btnTweet.addActionListener(handler);
		getContentPane().add(btnTweet);


		btnFollow = new JButton("Follow User");
		btnFollow.setBounds(322, 10, 125, 35);
		btnFollow.addActionListener(handler);
		getContentPane().add(btnFollow);

		setVisible(true);
	}
	
	/**
	 * This method creates a pop up box with a message
	 * @param infoMessage the message that appears in the pop up box
	 * @param titleBar the title bar for the window
	 */
	private void infoBox(String infoMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * This method takes in a Users node and checks for possible issues following said node
	 * @param node the user this object is trying to follow
	 * @return true if there is an error with the following of the parameter node.
	 */
	private boolean errorFollowingUser(Users node){
		if(alreadyFollowingUser(node)){
			infoBox("You are already following this user!", "ERROR!");
			return true;
		}
		if(followingOwn(node)){
			infoBox("You cannot follow yourself!", "ERROR!");
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks to see if the parameter node is already being followed by this user
	 * @param node the User this node is trying to follow
	 * @return true if this user is already following the parameter node.
	 */
	private boolean alreadyFollowingUser(Users node){
		Object[] array= user.getFollowings();
		for(Object user:array){
			if(user.equals(node))
				return true;
		}
		 return false;
	}
	
	/**
	 * This method check to see if the the parameter node is this user and returns true of if this 
	 * node and the parameter node are equal.
	 * @param node the User node that this user is trying to follow
	 * @return true if this user is the same as the parameter node
	 */
	private boolean followingOwn(Users node){
		return node.getID().equals(user.getID());
	}
	
	/*
	 * This class serves as an event handler for buttons.
	 */
	private class TheHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//source of action is follow button
			if(e.getSource()==btnFollow){
				String userId=textUserId.getText().trim();
				Users node=null;
				LookForMatchingNode iterateTree=new LookForMatchingNode(userId);
				VisitableTree tree=new VisitableTree(treeModel);
				tree.accept(iterateTree);
				if(iterateTree.isInTree())
					node=iterateTree.getFoundUser();
				else infoBox("User not found!","ERROR!");
				if(errorFollowingUser(node))
					return;
				if(node instanceof IndividualUser){
					user.follow((IndividualUser) node);
					infoBox("User was found!","Found User");
					return;
				}
			}
			//source of action is tweet button
			if(e.getSource()==btnTweet){
				String msg=textTweetMessage.getText().trim();
				user.tweet(msg);
			}
		}

	}
}
