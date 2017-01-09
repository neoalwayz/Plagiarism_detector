package filechooser;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JFrame;

import com.plag.GetFiles;

public class MyThread extends Thread {
	HashMap<String, String> hm;
	frame fl;
	
	public MyThread(HashMap<String, String> hm, frame f){
		this.hm = hm;
		this.fl = f;
	}
	
	public void run(){
		String s[][]={};
		File f[]=new File[hm.size()];
		int j=0;
		Set<Entry<String, String>> set=hm.entrySet();
		Iterator<Entry<String, String>> i=set.iterator();
		while(i.hasNext()){
			Map.Entry<String, String> me=(Map.Entry<String, String>)i.next();
			f[j]=new File(me.getValue());
			j++;
		}
		GetFiles gf=new GetFiles(f);
		try {
			s=gf.getText();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		display d1=new display(s, "Analysis");
		d1.setExtendedState(JFrame.MAXIMIZED_BOTH);
		d1.setVisible(true);
		fl.dispose();
	}
	
}
