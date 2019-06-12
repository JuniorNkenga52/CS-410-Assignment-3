package ass4;

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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;

public class ModelNotePad  {
	private Queue<String> queue=new LinkedList<>();
	private  JMenuBar menuBar = new JMenuBar();
	private  JMenu fileMenu = new JMenu("File");
	private  JMenu editMenu = new JMenu("Edit");
	private  JTextPane textArea = new JTextPane();
	private  JMenuItem open = new JMenuItem("Open");
	private  JMenu recent = new JMenu("Recent");
	private  JMenuItem newFile = new JMenuItem("New File");
	private  JMenuItem saveFile = new JMenuItem("Save File");
	private  JMenuItem printFile = new JMenuItem("Print File");
	private  JMenuItem undo = new JMenuItem("Undo");
	private  JMenuItem replace = new JMenuItem("Replace");
	private  JMenuItem copy = new JMenuItem("Copy");
	private  JMenuItem paste = new JMenuItem("Paste");
	private  JTextField textField=new JTextField();
	
	
	public Queue<String> getQueue() {
		return queue;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	public JMenu getFileMenu() {
		return fileMenu;
	}
	public JMenu getEditMenu() {
		return editMenu;
	}
	public JTextPane getTextArea() {
		return textArea;
	}
	public JMenuItem getOpen() {
		return open;
	}
	public JMenu getRecent() {
		return recent;
	}
	public JMenuItem getNewFile() {
		return newFile;
	}
	public JMenuItem getSaveFile() {
		return saveFile;
	}
	public JMenuItem getPrintFile() {
		return printFile;
	}
	public JMenuItem getUndo() {
		return undo;
	}
	public JMenuItem getReplace() {
		return replace;
	}
	public JMenuItem getCopy() {
		return copy;
	}
	public JMenuItem getPaste() {
		return paste;
	}
	
	
}

