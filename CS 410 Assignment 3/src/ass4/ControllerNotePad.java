package ass4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;

public class ControllerNotePad extends JFrame implements ActionListener{
	ModelNotePad model=new ModelNotePad();
	
	
    public ControllerNotePad() {
    	 
        setTitle("A Simple Notepad Tool");
        model.getFileMenu().add(model.getOpen());
        model.getFileMenu().addSeparator();
        model.getFileMenu().add(model.getNewFile());
        model.getFileMenu().addSeparator();
        model.getFileMenu().add(model.getSaveFile());     
        model.getFileMenu().addSeparator();
        model.getFileMenu().add(model.getPrintFile());
        model.getFileMenu().addSeparator();
        
        model.getFileMenu().add(model.getRecent());
        
        model.getEditMenu().add(model.getReplace()); 
        model.getEditMenu().add(model.getUndo());
         model.getEditMenu().add(model.getCopy());
         model.getEditMenu().add(model.getPaste());
         
        model.getNewFile().addActionListener(this);
        
        model.getNewFile().setActionCommand("new");
        model.getSaveFile().addActionListener(this);
        
        model.getSaveFile().setActionCommand("save");
        model.getPrintFile().addActionListener(this);
        
        model.getPrintFile().setActionCommand("print");
        
        model.getCopy().addActionListener(this);
        model.getCopy().setActionCommand("copy");
        model.getPaste().addActionListener(this);
        model.getPaste().setActionCommand("paste");
        model.getUndo().addActionListener(this);
        model.getUndo().setActionCommand("undo");
        model.getOpen().addActionListener(this);
        model.getOpen().setActionCommand("open");
        
        model.getReplace().addActionListener(this);
        model.getReplace().setActionCommand("replace");
        
        model.getRecent().setMnemonic(KeyEvent.VK_S);

        
     //   recent.addMenuListener(new MenuListener());

        model.getRecent().addActionListener(this);
        model.getRecent().setActionCommand("recent");
        
        model.getMenuBar().add(model.getFileMenu());
        model.getMenuBar().add( model.getEditMenu());
        setJMenuBar(model.getMenuBar());
        add(new JScrollPane(model.getTextArea()));
        setPreferredSize(new Dimension(600,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	    	
        if(e.getActionCommand().equals("new")) {
        	model.getTextArea().setText("");
        }else if(e.getActionCommand().equals("save")) {
            File fileToWrite = null;
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION)
                fileToWrite = fc.getSelectedFile();
            try {
                PrintWriter out = new PrintWriter(new FileWriter(fileToWrite));
                out.println(model.getTextArea().getText());
                JOptionPane.showMessageDialog(null, "File is saved successfully...");
                out.close();
            } catch (IOException ex) {
            }
        }else if(e.getActionCommand().equals("print")) {
            try{
                PrinterJob pjob = PrinterJob.getPrinterJob();
                pjob.setJobName("Sample Command Pattern");
                pjob.setCopies(1);
                pjob.setPrintable(new Printable() {
                    public int print(Graphics pg, PageFormat pf, int pageNum) {
                        if (pageNum>0)
                            return Printable.NO_SUCH_PAGE;
                        pg.drawString(model.getTextArea().getText(), 500, 500);
                        paint(pg);
                        return Printable.PAGE_EXISTS;
                    }
                }
                
                		);

                if (pjob.printDialog() == false)
                    return;
                pjob.print();
            } catch (PrinterException pe) {
                JOptionPane.showMessageDialog(null,
                        "Printer error" + pe, "Printing error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getActionCommand().equals("copy")) {
        	model.getTextArea().copy();
        }else if(e.getActionCommand().equals("paste")) {
            StyledDocument doc = model.getTextArea().getStyledDocument();
            Position position = doc.getEndPosition();
            System.out.println("offset"+position.getOffset());
            model.getTextArea().paste();
            
        }
        
        else if(e.getActionCommand().equals("open")) {
        	File fileToOpen = null;
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION)
                fileToOpen = fc.getSelectedFile();
            try {
            	    
            	Desktop desk=Desktop.getDesktop();
            	desk.open(fileToOpen);
            	model.getQueue().add(fileToOpen.getName());
                JOptionPane.showMessageDialog(null, "File is opening successfully...");
            } catch (IOException ex) {
            }	 
            
        }else if(e.getActionCommand().equals("replace")) {
        	
        JLabel lab=new JLabel("Replace or Insert With");
        JFrame replace=new JFrame("Input");
        replace.setLocation(new Point(5,10));
        replace.setSize(new Dimension(500, 200));
        replace.setLayout(null);
        replace.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lab.setBounds(100, 50, 200, 20);
        model.getTextField().setBounds(100,70,350,25);
        JButton one=new JButton("Cancel");
        JButton two=new JButton("Ok");
        one.setBounds(150,120,80,20);
        two.setBounds(250,120,80,20);
        one.addActionListener(this);
        one.setActionCommand("cancel");
        two.addActionListener(this);
        two.setActionCommand("ok");
        replace.add(one);
        replace.add(lab);
        replace.add(two);
        replace.add(model.getTextField());
        replace.setVisible(true);
        
        }else if(e.getActionCommand().equals("ok")){
            
        	String input=model.getTextField().getText();
        	String select=model.getTextArea().getSelectedText();
        	if(select!=null) { 
        		String text=model.getTextArea().getText().replaceAll(select, input);
        		model.getTextArea().setText(text);
        	}else
        		model.getTextArea().setText(model.getTextArea().getText()+" "+input);
        }else if(e.getActionCommand().equals("cancel")) {
        	return;
        }
        
        	int num=0;
        	while(!model.getQueue().isEmpty() && num!=5) {
        		try {
        		JMenuItem item=new JMenuItem(model.getQueue().remove());
        		model.getRecent().add(item);
        		}catch(Exception ex) {
        			System.out.println(ex.getMessage());
        		}
        		num++;
        	}
        	
        }

}