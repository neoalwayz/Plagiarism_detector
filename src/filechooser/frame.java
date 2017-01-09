package filechooser;
	
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;

public class frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSplitPane sp;
	Container ct;
	JPanel p1, p2;
	JLabel l1;
	JButton b1;
	JProgressBar jp;
	javax.swing.Timer tm1,tm2;
	int l=0;
	HashMap<String, String> hm;
	
	frame(String title) {
		
		super(title);
		
		hm=new HashMap<String, String>();
		ct = getContentPane();
		
		p2 = new panel2(hm);
		p1 = new panel1(p2,hm);
		
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc=new GridBagConstraints();	
		
		l1 = new JLabel("PLAGIARISM DETECTION");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 60));
		l1.setForeground(Color.RED);
		l1.setHorizontalAlignment(0);
		
		sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p1, p2);
		sp.setOneTouchExpandable(true);
		sp.setDividerLocation(620);
		
		tm1=new javax.swing.Timer(100,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(l<=95){
					jp.setString("Comparing...");
					jp.setValue(jp.getValue()+1);
				}else{
					tm1.stop();
				}
				l++;
			}
		});
		
		
		final frame f = this;
		
		
		b1=new JButton("PROCEED");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(hm.size()>=2){
					
					tm1.start();
					
					MyThread t1 = new MyThread(hm, f);
					t1.start();
				
				}else{
					
					JOptionPane.showMessageDialog(f, "Select atleast two files to proceed further");
				}
			}
		});
		
		jp=new JProgressBar(0,1,100);
		jp.setStringPainted(true);
		jp.setBackground(Color.WHITE);
		jp.setForeground(Color.BLUE);
		
		FlowLayout fl=new FlowLayout(0, 20, 0);
		
		JPanel p3=new JPanel();
		p3.add(b1,FlowLayout.LEFT);
		p3.add(jp,FlowLayout.CENTER);
		p3.setLayout(fl);
		
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridy=0;
		ct.add(l1,gbc);
		
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridy=1;
		gbc.insets=new Insets(20, 20, 20, 20);
		gbc.weightx=2000;
		ct.add(sp,gbc);
		
		gbc.fill=GridBagConstraints.CENTER;
		gbc.gridy=2;
		gbc.gridx=0;
		ct.add(p3,gbc);
	}
}			
