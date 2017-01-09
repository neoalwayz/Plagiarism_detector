package filechooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class JTableModel extends AbstractTableModel{
	String []name;
	String [][]arr;
	display d;
	public JTableModel(String []name,String [][]arr,display d) {
		this.name=name;
		this.arr=arr;
		this.d=d;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return name.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return arr.length;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return name[column];
	}

	@Override
	public Object getValueAt(final int arg0, int arg1) {
		// TODO Auto-generated method stub
		if(arg1==3){
			final JButton button=new JButton("Information");
			button.setEnabled(true);
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(arr[arg0][2].compareTo("0.0")==0){
						JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(button), "No File Information.");
					}else{
						content c=new content("Content",arg0);
						c.setExtendedState(JFrame.MAXIMIZED_BOTH);
						c.setVisible(true);
					}
				}
			});
			return button;
		}
		return arr[arg0][arg1];
	}

}
