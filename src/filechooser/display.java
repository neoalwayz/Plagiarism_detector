package filechooser;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class display extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Container ct;
	JLabel l1;
	JButton b1,b2;
	
	String columnNames[]={
		"First", "Second", "Percentage", "Content"	
	};
	
	display(String arr[][], String title){
		super(title);
		
		final display d=this;
		
		ct=getContentPane();
		
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc=new GridBagConstraints();
		
		l1=new JLabel("ANALYSIS");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 60));
		l1.setHorizontalAlignment(0);
		l1.setForeground(Color.RED);
		
		b1=new JButton("NEW");
		b1.addActionListener(this);
		
		b2=new JButton("CLOSE");
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				d.dispose();
			}
		});
		
		JTable table=new JTable(new JTableModel(columnNames,arr,this));
		JScrollPane sp=new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setRowHeight(50);
		table.setRowMargin(15);
		
		table.addMouseListener(new TableMouseListener(table));
		
		TableCellRenderer tcr=new JTableButtonRenderer();
		table.getColumn("Content").setCellRenderer(tcr);
		
		FlowLayout fl=new FlowLayout(0, 20, 0);
		
		JPanel p3=new JPanel();
		p3.add(b1,FlowLayout.LEFT);
		p3.add(b2,FlowLayout.CENTER);
		p3.setLayout(fl);
		
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridy=0;
		ct.add(l1,gbc);
		
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridy=1;
		gbc.weightx=2000;
		gbc.weighty=2000;
		ct.add(sp,gbc);
		
		gbc.fill=GridBagConstraints.CENTER;
		gbc.gridy=2;
		ct.add(p3,gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			this.dispose();
			frame f = new frame("TESTER");
			f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			f.setVisible(true);
		}
	}
}
