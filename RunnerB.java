import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import acm.graphics.GCanvas;
import acm.program.Program;
import acmx.export.javax.swing.*;

public class RunnerB implements ActionListener{

	/*Instance Variables*/
	HashTable1 hashTable;
	static FileIO2 fileReaderWriter;
	static String fileNameSource = "hamlet.txt";
	static String fileNameOutput = "hamletoutput4.txt";
	
	static int TEXT_FIELD_SIZE = 25;
	
	private static JLabel word;
	private static JTextField wordTextField;
	
	private static ButtonStuff button;
	
	
	public static void main(String array[]) {		
		
		HashTable1 hashTable = new HashTable1();
		/*new instance of file reader writer*/
		fileReaderWriter = new FileIO2();
		/*reads and writes using the new hashTable*/
		fileReaderWriter.read(fileNameSource, hashTable);
		fileReaderWriter.write(fileNameOutput, hashTable);
	
		/*button is button interface*/
		button = new ButtonStuff(fileReaderWriter, fileNameOutput, true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}
