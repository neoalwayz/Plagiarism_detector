package filechooser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class panel1 extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFileChooser chooser;
	JPanel p2;
	HashMap<String, String> hm;

	panel1(JPanel p2,HashMap<String, String> hm) {
		setLayout(new BorderLayout(40, 40));
		
		this.p2 = p2;
		this.hm=hm;
		
		chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		add(chooser);
		
		FileNameExtensionFilter filter=new FileNameExtensionFilter("DOCUMENT FILES","doc","pdf","ppt","odt", "txt", "docx", "pptx");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == JFileChooser.APPROVE_SELECTION) {
			
			File[] selected = chooser.getSelectedFiles();
			for (int i = 0; i < selected.length; i++) {
				if(hm.containsValue(selected[i].getAbsolutePath())){
				//	JOptionPane.showMessageDialog(null, "Already Added the file "+selected[i].getName()+". Please select another file.");
					continue;
				}
				hm.put(selected[i].getName(),selected[i].getAbsolutePath());
				((panel2) p2).addItem(selected[i].getName());
			}
		}
		if (arg0.getActionCommand() == JFileChooser.CANCEL_SELECTION) {

		}
	}
}
