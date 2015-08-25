import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 * This class creates a JFrame or admin panel
 * @author Oscar Nevarez
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AdminWindow extends JFrame{
	private static AdminWindow instance;
	private JPanel contentPane;
	private JButton btnAddUser;
	private JButton btnAddGroup;
	private JButton btnOpenUserView;
	private JButton btnShowUserTotal;
	private JButton btnShowMessagesTotal;
	private JButton btnShowGroupTotal;
	private JButton btnShowPositivePercentage;
	private JTextArea txtrUserId;
	private JTextArea txtrUserGroupId;
	private JTree tree;
	private DefaultTreeModel treeModel;
	Users temp=new UserGroup("Root");
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;

	/*
	 * Create the frame.
	 */
	private AdminWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Admin Panel");
		setBounds(100, 100, 764, 707);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * create JTree
		 */
		Users rootNode= new UserGroup("Root");
		treeModel=new DefaultTreeModel(rootNode);
		
		panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tree View", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel1.setBounds(0, 11, 251, 670);
		contentPane.add(panel1);
		panel1.setLayout(null);
		tree=new JTree(treeModel);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setBounds(0, 0, 235, 681);
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBounds(10, 21, 231, 638);
		panel1.add(scrollPane);
		
		
		/*
		 * set Icons and create cell renderer.
		 */
		Icon leafIcon = UIManager.getIcon("FileView.fileIcon");
		Icon nonLeafIcon=UIManager.getIcon("FileView.directoryIcon");
		tree.setCellRenderer(new MyCellRenderer(leafIcon,nonLeafIcon));
		
		
		//handler for events, privately enclosed class
		Handler handler=new Handler();

		btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(handler);
		btnAddUser.setBounds(543, 135, 170, 71);
		contentPane.add(btnAddUser);

		btnAddGroup=new JButton("Add Group");
		btnAddGroup.addActionListener(handler);
		btnAddGroup.setBounds(543, 34, 170, 71);
		contentPane.add(btnAddGroup);

		btnOpenUserView=new JButton("Open User View");
		btnOpenUserView.addActionListener(handler);
		btnOpenUserView.setBounds(297, 245, 384, 71);
		contentPane.add(btnOpenUserView);

		btnShowUserTotal=new JButton("Show User Total");
		btnShowUserTotal.addActionListener(handler);
		btnShowUserTotal.setBounds(297, 460, 170, 71);
		contentPane.add(btnShowUserTotal);

		btnShowMessagesTotal=new JButton("Show Messages Total");
		btnShowMessagesTotal.addActionListener(handler);
		btnShowMessagesTotal.setBounds(297, 542, 170, 71);
		contentPane.add(btnShowMessagesTotal);

		btnShowGroupTotal=new JButton("Show Group Total");
		btnShowGroupTotal.addActionListener(handler);
		btnShowGroupTotal.setBounds(543, 542, 170, 71);
		contentPane.add(btnShowGroupTotal);

		btnShowPositivePercentage=new JButton("Show Positive Percentage");
		btnShowPositivePercentage.addActionListener(handler);
		btnShowPositivePercentage.setBounds(543, 460, 170, 71);
		contentPane.add(btnShowPositivePercentage);
		
		panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel2.setBounds(261, 130, 223, 73);
		contentPane.add(panel2);
		panel2.setLayout(null);

		txtrUserId = new JTextArea();
		txtrUserId.setBounds(6, 16, 211, 50);
		panel2.add(txtrUserId);
		
		panel3 = new JPanel();
		panel3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Group ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel3.setBounds(261, 30, 223, 73);
		contentPane.add(panel3);
		panel3.setLayout(null);

		txtrUserGroupId = new JTextArea();
		txtrUserGroupId.setBounds(6, 16, 211, 50);
		panel3.add(txtrUserGroupId);
		
		this.setVisible(true);
	}
	
	/**
	 *Sets the icons for node types, if node is an instance of IndividualUser it is a leaf
	 *@see MyCellRenderer
	 */
	private void setIcons() {
		tree.setCellRenderer(new DefaultTreeCellRenderer() {
         private Icon groupIcon = UIManager.getIcon("FileView.directoryIcon");
         private Icon userIcon = UIManager.getIcon("FileView.fileIcon");
         
         @Override
         public Component getTreeCellRendererComponent(JTree tree,
                 Object value, boolean selected, boolean expanded,
                 boolean isLeaf, int row, boolean focused) {
             Component c = super.getTreeCellRendererComponent(tree, value,
                     selected, expanded, isLeaf, row, focused);
             if (isLeaf)
                 setIcon(userIcon);
             else
                 setIcon(groupIcon);
             return c;
         }
		});
	}
	public static AdminWindow getInstance(){
		if (instance==null)
			instance=new AdminWindow();
		return instance;
	}

   /**
    * this method adds a new node into the tree, invokes the tree model's inserNodeInto internally
    * to trigger the appropriate event for the JTree
    * @param child the node that will be added to the tree model
    */
	public void addNode(Users child) {
		if(treeContains(child)){
			infoBox("Node already exists in tree!","error");
			return;
		}
		
		TreePath parentPath = tree.getSelectionPath(); 
		Users parentNode=null;
		if (parentPath == null) {
			parentNode = (Users) treeModel.getRoot();
		} else {
			parentNode = (Users)(parentPath.getLastPathComponent());
		}
		if(!parentNode.getAllowsChildren()){
			infoBox("Operation not supported!Leaf nodes cannot have children.","ERROR!");
			return;
		}
		treeModel.insertNodeInto(child,parentNode,parentNode.getChildCount());
		tree.scrollPathToVisible(new TreePath(child.getPath()));
	}
	
	/**
	 * This method checks to see if a the parameter node is already in the tree
	 * @param child the node that is being searched for in the tree
	 * @return true if the parameter node is already in the tree.
	 */
	private boolean treeContains(Users child) {
		LookForMatchingNode iterateTree=new LookForMatchingNode(child.getID());
		VisitableTree tree=new VisitableTree(treeModel);
		tree.accept(iterateTree);
		return iterateTree.isInTree();
	}
	
	/**
	 *This method opens a user view 
	 */
	private void openUserView(){

		TreePath parentPath = tree.getSelectionPath(); 
		Users testNode=(Users)(parentPath.getLastPathComponent());
		if(testNode instanceof IndividualUser){
			JFrame userView=new IndividualUserWindow((IndividualUser) testNode,treeModel);
			userView.setVisible(true);
		}
		else 
			infoBox("Action not supported, no User View for Groups","ERROR!");
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
	private class Handler implements ActionListener {
		//action from respective buttons
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAddUser){
				String userId=txtrUserId.getText().trim();
				if(userId.isEmpty())
					return;
				addNode(new IndividualUser(userId,new DefaultListModel<String>()));
			}
			if(e.getSource()==btnAddGroup){
				String groupId=txtrUserGroupId.getText().trim();
				if(groupId.isEmpty())
					return;
				addNode(new UserGroup(groupId));
			}
			if(e.getSource()==btnOpenUserView){
				openUserView();
			}
			else{
				VisitableTree tree=new VisitableTree(treeModel);
				
				if(e.getSource()==btnShowUserTotal){
					UserTotal userTotal=new UserTotal();
					tree.accept(userTotal);
					infoBox("There are "+userTotal.result()+" users.","Total Users");
				}
				if(e.getSource()==btnShowGroupTotal){
					GroupTotal groupTotal=new GroupTotal();
					tree.accept(groupTotal);
					infoBox("There are "+groupTotal.result()+" groups.","Total Groups");

				}
				if(e.getSource()==btnShowMessagesTotal){
					MessagesTotal messagesTotal= new MessagesTotal();
					tree.accept(messagesTotal);
					infoBox("There are "+messagesTotal.result()+" Messages.","Total Messages");
				}
				if(e.getSource()==btnShowPositivePercentage){
					PositivePercentage positivePercentage=new PositivePercentage("good happy awesome great excellent agree honest admirable amazing astonishing beatiful caring"
							+ "brilliant charismatic dandy faithful giving");
					tree.accept(positivePercentage);
					infoBox(+positivePercentage.result()+"% of messages are positive","Positive Percentage of messages");
				}
			}
		}


	}

}
