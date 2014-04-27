package ig;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.SwingConstants;

import algorithme.GlobalAlgo;

public class UI extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField graphFilePath;
	
	private JButton btn_file;
	private JButton btn_quit;
	private JButton btn_proceed;
	private JTextArea textAreaResults;
	private JRadioButton rdbtnAlgo1;
	private JRadioButton rdbtnAlgo2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		Box horizontalBoxFile = Box.createHorizontalBox();
		verticalBox.add(horizontalBoxFile);
		
		JLabel lblFile = new JLabel("Choose graph file");
		horizontalBoxFile.add(lblFile);
		
		Component horizontalStrut_File = Box.createHorizontalStrut(20);
		horizontalBoxFile.add(horizontalStrut_File);
		
		graphFilePath = new JTextField();
		horizontalBoxFile.add(graphFilePath);
		graphFilePath.setColumns(10);
		
		Component horizontalStrut_File2 = Box.createHorizontalStrut(20);
		horizontalBoxFile.add(horizontalStrut_File2);
		
		btn_file = new JButton("File");
		btn_file.setHorizontalAlignment(SwingConstants.LEFT);
		horizontalBoxFile.add(btn_file);
		btn_file.addActionListener(this);
		
		Box horizontalBox_Algo = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_Algo);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Algorithm");
		horizontalBox_Algo.add(lblNewLabel_1);
		
		Component horizontalStrutAlgo = Box.createHorizontalStrut(20);
		horizontalBox_Algo.add(horizontalStrutAlgo);
		
		rdbtnAlgo1 = new JRadioButton("Algorithm 1");
		rdbtnAlgo1.setSelected(true);
		horizontalBox_Algo.add(rdbtnAlgo1);
		
		Component horizontalStrut_Algo2 = Box.createHorizontalStrut(20);
		horizontalBox_Algo.add(horizontalStrut_Algo2);
		
		rdbtnAlgo2 = new JRadioButton("Algorithm 2");
		horizontalBox_Algo.add(rdbtnAlgo2);
		
		ButtonGroup bgroupAlgo = new ButtonGroup();
		bgroupAlgo.add(rdbtnAlgo1);
		bgroupAlgo.add(rdbtnAlgo2);
		
		rdbtnAlgo1.addActionListener(this);
		rdbtnAlgo2.addActionListener(this);
		
		JPanel panel_Results = new JPanel();
		contentPane.add(panel_Results, BorderLayout.CENTER);
		panel_Results.setLayout(new BorderLayout(0, 0));
		
		JLabel lblResults = new JLabel("Results :");
		panel_Results.add(lblResults, BorderLayout.NORTH);
		
		textAreaResults = new JTextArea();
		textAreaResults.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(textAreaResults);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_Results.add(scroll, BorderLayout.CENTER);
				
		JPanel panel_Action = new JPanel();
		contentPane.add(panel_Action, BorderLayout.SOUTH);
		panel_Action.setLayout(new BoxLayout(panel_Action, BoxLayout.Y_AXIS));
		
		Box horizontalBox_Actions = Box.createHorizontalBox();
		horizontalBox_Actions.setAlignmentX(Component.RIGHT_ALIGNMENT);
		horizontalBox_Actions.setAlignmentY(Component.CENTER_ALIGNMENT);
		panel_Action.add(horizontalBox_Actions);
		
		btn_proceed = new JButton("Proceed");
		horizontalBox_Actions.add(btn_proceed);
		btn_proceed.addActionListener(this);
		
		btn_quit = new JButton("Quit");
		horizontalBox_Actions.add(btn_quit);
		btn_quit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_file) {
			JFileChooser chooser;
			chooser = new JFileChooser(); 
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Select Corpus");
			chooser.setAcceptAllFileFilterUsed(false); 
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
				graphFilePath.setText(chooser.getSelectedFile().toString());
			}

		}
		if (e.getSource() == btn_proceed) {
			textAreaResults.setText("");
			if(graphFilePath.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Please load a file to proceed algorithm");
			}else if (!rdbtnAlgo1.isSelected() && !rdbtnAlgo2.isSelected()){
				JOptionPane.showMessageDialog(this, "Please choose an algorithm to proceed algorithm");
			}else{
				GlobalAlgo.result="";
				GlobalAlgo.algo = (rdbtnAlgo1.isSelected()) ? 1 : 2;
				GlobalAlgo.launchAlgo(graphFilePath.getText());
				textAreaResults.setText(GlobalAlgo.result);
				validate();
			}
		}
		if (e.getSource() == btn_quit) {
	        int result = JOptionPane.showConfirmDialog(
	        		this,
	                "Are you sure you want to exit the application?",
	                "Exit Application",
	                JOptionPane.YES_NO_OPTION);
	        if (result == JOptionPane.YES_OPTION)
	        	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

}
