package filechooser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class panel2 extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultListModel<String> model;
	JList<String> list;
	JButton b1, b2, b3;
	String item;
	int ind;
	HashMap<String, String> hm;

	panel2(HashMap<String, String> hm) {
		this.hm=hm;
		model = new DefaultListModel<String>();
		
		list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(24);
		list.setFont(new Font("Arial",Font.BOLD,16));
		
		b1 = new JButton("Remove");
		b2 = new JButton("UNDO");
		b3 = new JButton("REDO");
		
		Box bo = Box.createHorizontalBox();
		bo.add(b2);
		bo.add(b1);
		bo.add(b3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		b2.setEnabled(false);
		b3.setEnabled(false);
		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		setLayout(layout);
		
		JScrollPane scrollPane = new JScrollPane(list);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(scrollPane).addComponent(bo));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(scrollPane).addComponent(bo));
	}

	public void addItem(String text) {
		model.addElement(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == b1 && list.getSelectedIndex() >= 0) {
			
			int index = list.getSelectedIndex();
			ind = index;
			item = list.getSelectedValue().toString();
			model.remove(index);
			hm.remove(item);
			b2.setEnabled(true);
			
		} else if (e.getSource() == b2 && b2.isEnabled()) {
			
			model.addElement(item);
			b2.setEnabled(false);
			b3.setEnabled(true);
		
		} else if (e.getSource() == b3 && b3.isEnabled()) {
			
			model.removeElement(item);
			b3.setEnabled(false);
			b2.setEnabled(true);
		}
	}
}
