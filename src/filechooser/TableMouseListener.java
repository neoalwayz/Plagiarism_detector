package filechooser;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;

public class TableMouseListener extends MouseAdapter{
	JTable table;
	public TableMouseListener(JTable table) {
		this.table=table;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point point=e.getPoint();
		int column=table.columnAtPoint(point);
		int row=table.rowAtPoint(point);
		if(column==3){
			Object value=table.getValueAt(row, column);
			if(value instanceof JButton){
				((JButton)value).doClick();
			}
		}
	}
}
