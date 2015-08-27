package cS356A2;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;

/**
 * This class creates a JFrame or admin panel, this class does not handle data, that task is delegated to the 
 * TreeDataHandler class.
 * @author Oscar Nevarez
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AdminWindow extends JFrame implements AdminPanel{
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
	private TreeDataHandler treeDataHandler;
	private PopUpDialogBox popUp=new PopUpDialogBox();
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
		 *create data handler provides access to tree data
		 */
		treeDataHandler=new TreeDataHandler(new HashMap<String,Users>());

		/*
		 * create JTree
		 */
		panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tree View", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel1.setBounds(0, 11, 251, 670);
		contentPane.add(panel1);
		panel1.setLayout(null);
		tree=new JTree(treeDataHandler.getModel());
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

		/*
		 * buttons
		 */
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
		
		/*
		 * text boxes
		 */
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
	 * This method returns an instance of this class.
	 * It enacts the singleton pattern.
	 * @return an instance of this class
	 */
	public static AdminWindow getInstance(){
		if (instance==null)
			instance=new AdminWindow();
		return instance;
	}
	@Override
	public void openUserView(Users user) {
		Users node=getSelectedNode(this.tree);
		if(!(node instanceof IndividualUser)){
			popUp.infoBox("Operation not supported! No user view for groups!","ERROR!");
			return;
		}
		else {
			new IndividualUserWindow((IndividualUser)node,treeDataHandler);
		}
	}
	@Override
	public Users getSelectedNode(JTree tree){
		TreePath parentPath = tree.getSelectionPath(); 
		Users selectedNode=null;
		if (parentPath == null) {
			selectedNode = (Users) treeDataHandler.getModel().getRoot();
		} else {
			selectedNode = (Users)(parentPath.getLastPathComponent());
		}
		return selectedNode;	
	}
	@Override
	public void setIcons() {
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
	private class Handler implements ActionListener {
		//action from respective buttons
		@Override
		public void actionPerformed(ActionEvent e) {

			//grab the currently selected node in the j tree.
			Users selectedNode=getSelectedNode(tree);


			if(e.getSource()==btnAddUser){
				String userId=txtrUserId.getText().trim();
				Users newUser=new IndividualUser(userId);
				selectedNode=getSelectedNode(tree);
				if(userId.isEmpty())
					return;
				if(treeDataHandler.addNode(selectedNode,newUser)){
					tree.scrollPathToVisible(new TreePath(newUser.getPath()));
				}
				else {
					return;
				}
				
			}
			if(e.getSource()==btnAddGroup){
				String groupId=txtrUserGroupId.getText().trim();
				Users newUserGroup=new UserGroup(groupId);
				if(groupId.isEmpty())
					return;
				if(treeDataHandler.addNode(selectedNode,newUserGroup)){
					tree.scrollPathToVisible(new TreePath(newUserGroup.getPath()));
				}
				else {
					return;
				}
			}
			if(e.getSource()==btnOpenUserView){
				openUserView(selectedNode);
			}
			else{

				if(e.getSource()==btnShowUserTotal){
					UserTotal userTotal=new UserTotal();
					treeDataHandler.accept(userTotal);
					popUp.infoBox("There are "+userTotal.result()+" users.","Total Users");
				}
				if(e.getSource()==btnShowGroupTotal){
					GroupTotal groupTotal=new GroupTotal();
					treeDataHandler.accept(groupTotal);
					popUp.infoBox("There are "+groupTotal.result()+" groups.","Total Groups");

				}
				if(e.getSource()==btnShowMessagesTotal){
					MessagesTotal messagesTotal= new MessagesTotal();
					treeDataHandler.accept(messagesTotal);
					popUp.infoBox("There are "+messagesTotal.result()+" Messages.","Total Messages");
				}
				if(e.getSource()==btnShowPositivePercentage){
					PositivePercentage positivePercentage=new PositivePercentage("good happy awesome great excellent agree honest admirable amazing astonishing beatiful caring"
							+ "brilliant charismatic dandy faithful giving");
					treeDataHandler.accept(positivePercentage);
					popUp.infoBox(String.format("%.4g",positivePercentage.result())+"% of messages are positive","Positive Percentage of messages");
				}
			}
		}


	}
}
