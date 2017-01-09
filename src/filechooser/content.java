package filechooser;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class content extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel l1;
	JTextArea t1;
	Container ct;
	String text;
	JPanel p1;
	JButton b1;
	
	content(String title,int row){
		super(title);
		ct=getContentPane();
		
		l1=new JLabel("CONTENT");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 60));
		l1.setHorizontalAlignment(0);
		l1.setForeground(Color.RED);
		final content c=this;
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("dumpfile_"+row));
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();
	
			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    text = sb.toString();
			} finally {
			    br.close();
			}
		}catch(Exception e){
			
		}
		
		t1=new JTextArea();
		t1.setText(text);
		t1.setMinimumSize(new Dimension(400,400));
		t1.setFont(new Font("Arial", Font.BOLD,14));
		
		JScrollPane sp=new JScrollPane(t1);
		sp.setMinimumSize(new Dimension(400,400));
		
		b1=new JButton("Close");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.dispose();
			}
		});
		
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc=new GridBagConstraints();
		
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
		ct.add(b1,gbc);	
	}
}
