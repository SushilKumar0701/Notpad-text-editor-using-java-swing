import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;

class Editor extends JFrame implements ActionListener{
	
	final JTextArea t;
	JFrame f;
	JMenu m1,m2,m3;
	JMenuBar mb;
	JMenuItem mi1,mi2,mi3,mi4,mi5,mi6,mi7,mi8,mi9;
	public Editor() {
		f=new JFrame("Notpad Editor");
		
		/*try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
 
            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e) {
        }*/
		
		
		t=new JTextArea(15,65);
		f.add(new JScrollPane(t));
		final JCheckBox cb=new JCheckBox("Word Wrap");
		cb.setSelected(t.getWrapStyleWord());
		cb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				t.setWrapStyleWord(cb.isSelected());
			}
		});
		
		//f.setLocationRelativeTo(null);
		f.setLocation(300, 100);
		
		
		
		
		mb=new JMenuBar();
		
		m1=new JMenu("File");
		mi1=new JMenuItem("New");
		mi2=new JMenuItem("Open");
		mi3=new JMenuItem("Save");
		mi4=new JMenuItem("Save As");
		mi5=new JMenuItem("Print");
		mi6=new JMenuItem("Close");
		
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);
		
		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi4);
		m1.add(mi5);
		m1.add(mi6);
		
		m2=new JMenu("Edit");
		mi7=new JMenuItem("Cut");
		mi8=new JMenuItem("Copy");
		mi9=new JMenuItem("Paste");
		
		mi7.addActionListener(this);
		mi8.addActionListener(this);
		mi9.addActionListener(this);
		
		m2.add(mi7);
		m2.add(mi8);
		m2.add(mi9);
		
		mb.add(m1);
		mb.add(m2);
		
		f.setJMenuBar(mb);
		f.add(t);
		f.add(cb,BorderLayout.SOUTH);
		f.setSize(500, 500);
		f.show();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String str=e.getActionCommand();
		if(str.equals("New")) {
			t.setText("");
		}
		else if(str.equals("Cut")) {
			t.cut();
		}
		else if(str.equals("Copy")) {
			t.copy();
		}
		else if(str.equals("Paste")) {
			t.paste();
		}
		else if(str.equals("Print")) {
			try {
				t.print();
			} catch (PrinterException e1) {
				JOptionPane.showMessageDialog(f, e1.getMessage());
			}
		}
		else if(str.equals("Save")) {
			JFileChooser jfc=new JFileChooser("d:");
			int r=jfc.showSaveDialog(null);
			
			if(r==JFileChooser.APPROVE_OPTION) {
				File file=new File(jfc.getSelectedFile().getAbsolutePath());
				try {
					FileWriter fr=new FileWriter(file,false);
					BufferedWriter bw=new BufferedWriter(fr);
					
					bw.write(t.getText());
					bw.flush();
					bw.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(f, e2.getMessage());
				}
			}
			else {
				JOptionPane.showMessageDialog(f, "User Cancelled");
			}
		}
		else if(str.equals("Open")) {
				JFileChooser jfc=new JFileChooser("d:");
				int r=jfc.showOpenDialog(null);
				
				if(r==JFileChooser.APPROVE_OPTION) {
					File file=new File(jfc.getSelectedFile().getAbsolutePath());
					try {
						String str1="",sf="";
						FileReader fr=new FileReader(file);
						BufferedReader br=new BufferedReader(fr);
						sf=br.readLine();
						while((str1 =br.readLine()) != null) {
							sf = sf+"\n"+str1;
						}
						t.setText(sf);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(f, e2.getMessage());
					}
				}
				else {
					JOptionPane.showMessageDialog(f, "User Cancelled operation");
				}
			}
		else if(str.equals("Save As")) {
			JFileChooser jfc=new JFileChooser("d:");
			int r=jfc.showSaveDialog(null);
			
			if(r==JFileChooser.APPROVE_OPTION) {
				File file=new File(jfc.getSelectedFile().getAbsolutePath());
				try {
					FileWriter fr=new FileWriter(file,false);
					BufferedWriter bw=new BufferedWriter(fr);
					
					bw.write(t.getText());
					bw.flush();
					bw.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(f, e2.getMessage());
				}
			}
			else {
				JOptionPane.showMessageDialog(f, "User Cancelled");
			}
		}
		else if(str.equals("Close")) {
			f.setVisible(false);
		}
		
	}
	
}
public class Notpad {

	public static void main(String[] args) {
		Editor editor=new Editor();

	}

}
