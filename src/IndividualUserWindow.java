import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

import java.awt.Color;


@SuppressWarnings("serial")
public class IndividualUserWindow extends JFrame {

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
	private JList<String> followingsList;
	private JList<String> newsFeedList;
	/*
	 * buttons 
	 */
	private JButton btnFollow;
	private JButton btnTweet;
	private JButton btnNewButton;
	/*
	 * list models one for followings, the other is a field in the IndividualUserClass.
	 */
	private DefaultListModel<String> modelFollowings;
	private DefaultListModel<String> modelNewsFeed;
	private JPanel border1;
	private JPanel border2;
	private JPanel border3;
	private JPanel border4;

	public IndividualUserWindow(IndividualUser individualUser,DefaultTreeModel tree) {
		this.user=individualUser;
		this.treeModel=(DefaultTreeModel) tree;
		this.modelNewsFeed= user.getListModel();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(user.getID()+"'s "+"User View");
		setBounds(100, 100, 477, 435);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		modelFollowings = new DefaultListModel<String>();

		border1 = new JPanel();
		border1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Following:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		border1.setBounds(0, 56, 469, 119);
		contentPane.add(border1);
		border1.setLayout(null);
		followingsList=new JList<String>(modelFollowings);
		JScrollPane scrollPane = new JScrollPane(followingsList);
		scrollPane.setBounds(10, 16, 449, 92);
		border1.add(scrollPane);


		border2 = new JPanel();
		border2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "News Feed", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		border2.setBounds(0, 235, 465, 170);
		contentPane.add(border2);
		border2.setLayout(null);
		newsFeedList=new JList<String>(user.getListModel());
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
	private void infoBox(String infoMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
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
	private boolean alreadyFollowingUser(Users node){
		return user.getFollowings().contains(node);
	}
	private boolean followingOwn(Users node){
		return node.getID().equals(user.getID());
	}
	private class TheHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
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
					modelFollowings.addElement(userId);
					infoBox("User was found!","Found User");
					return;
				}
			}
			if(e.getSource()==btnTweet){
				String msg=textTweetMessage.getText().trim();
				user.tweet(msg);
				modelNewsFeed.addElement("- "+"Me: "+msg);
			}
		}

	}
}
