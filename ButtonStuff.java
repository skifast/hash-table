
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import acmx.export.javax.swing.JButton;
import acmx.export.javax.swing.JFrame;
import acmx.export.javax.swing.JTextField;

public class ButtonStuff implements ActionListener{
	JTextField tf1,tf2,tf3;  
    JButton b1, b2, b3, b4;
    String fileName;
    FileIO2 fileReaderWriter;
    boolean hashBool;
    
    
    public ButtonStuff(FileIO2 FileReaderWriter, String FileName, boolean trueFalse){
    	/*declare instance variables*/
    	fileName = FileName;
    	fileReaderWriter = FileReaderWriter;
    	hashBool = trueFalse;
    	
    	/*JFrame is the baseline canvas*/
    	JFrame f = new JFrame();
    	f.setSize(500, 500);
    		
		/*textFields. The third cannot be edited; it is purly to display messages*/
		tf1=new JTextField();  
	    tf1.setBounds(50,100,150,20);  	
	    tf2=new JTextField();  
	    tf2.setBounds(50,150,150,20);  
	    tf3=new JTextField();  
	    tf3.setBounds(20,400,470,50);  
	    tf3.setEditable(false);
	    
	    /*display message if there is not a selected file*/
	    if(FileName == null){
	    	tf3.setText("There is no file selected, select File");
	    }
	     
	    /*JLabels*/
	     JLabel l1 = new JLabel("FileName");
	     l1.setBounds(50,60,150,50);
	     JLabel l2 = new JLabel("Word");
	     l2.setBounds(50,110,150,50);
	     JLabel lmain = new JLabel("Text Anaysis");
	     lmain.setBounds(200, 10, 100, 50);
	  
	     /*JButtons */
	     b1=new JButton("Frequency");  
	     b1.setBounds(150,200,100,100);  
	     b1.setVisible(true);
	     b2 = new JButton("Change File");
	     b2.setBounds(50,200,100,100);  
	     b2.setVisible(true); 
	     b3 = new JButton("First Line");
	     b3.setBounds(250, 200, 100, 100);
	     b3.setVisible(true);
	     b4 = new JButton("Last Line");
	     b4.setBounds(350, 200, 100, 100);
	     b4.setVisible(true);
	         
	     b1.addActionListener(this);  
	     b2.addActionListener(this);
	  	 b3.addActionListener(this);
	     b4.addActionListener(this);
	       
	     f.add(tf1); f.add(tf2);f.add(tf3);f.add(b1); f.add(b2); f.add(b3); f.add(b4); f.add(l1); f.add(l2); f.add(lmain);
	     
	         
	     f.setLayout(null);  
	     f.setVisible(true); 
    	
    }         
    public void actionPerformed(ActionEvent e) {  
    	String word = tf2.getText();
    	String file = tf1.getText();
    	/*If the user is looking for the frequency of a word*/
    	if(e.getSource()==b1){
    		//----------------------------------------------can I not just put this in this method here
    		
    		/*If the file does not exist, create a new instance of the hashTable*/
    		if(!fileReaderWriter.checkForFile(fileName) || !hashBool){
    			/*Create a new hashTable based off of this file and read/write the file to the same file*/
    			HashTable1 hashTable = new HashTable1();
    			fileReaderWriter.read(fileName, hashTable);
    			fileReaderWriter.write(fileName, hashTable);
    			//fileReaderWriter.write(fileName, hashTable);
    			/*The hashTable only searches for words when they are lower case*/
    			word = word.toLowerCase();
    			tf3.setText("This word appears " + getFrequency(word) + " times.");
    			hashBool = true;
    		}
    		
    		/*If there is no file selected*/
    		else if(fileName.equals(null)){
    			tf3.setText("Please enter a file name.");
    		}
    		
    		/*If there is a file selected and the hashTable is currently looking at values from this file*/
    		else{
    			/*Make the word lower case*/
    			word = word.toLowerCase();
    			/*return the frequency of the word*/
    			tf3.setText("This word appears " + getFrequency(word) + " times.");
    		}
    	}
    	
    	/*The hashTable is not created until the user searches for the frequency of a word*/
    	/*This is so the user does not enter a file, then changes their mind, resulting in 
    	 * an unnecessary creation of a hashTable and the run through of a series of conditionals*/
    	if(e.getSource() == b2){
    		/*Look at text entered into the select file box*/
    		String lookThroughThis = tf1.getText();
			int lengthOfText = lookThroughThis.length();
			
			/*The extension needed is .txt*/
			/*Therefore the minimum length of any file name is 4 characters*/
			if(lengthOfText > 3){
				/*If the name of the file ends in .txt*/
				if(lookThroughThis.substring(lengthOfText - 4, lengthOfText).equals(".txt")){
					/*Set the file to the provided file name*/
					changeFile(file);
					tf3.setText("The file has been changed to " + lookThroughThis);
		
				}
				
				/*else there is no valid ending to the file name*/
				else{
					tf3.setText("This is not a valid file name. Use .txt");
				}
			}
			/*else there is no valid ending to the file name*/
			else{
				tf3.setText("This is not a valid file name. Use .txt");
			}
    	}
    	
    	if(e.getSource() == b3){
    		if(!tf2.equals(null)){
    			int firstLine = getFirstLine(tf2.getText());
    			tf3.setText(tf2.getText() + " appears first on line " + firstLine);
    		}
    	}
    	
    	if(e.getSource() == b4){
    		if(!tf2.equals(null)){
    			int lastLine = getLastLine(tf2.getText());
    			tf3.setText(tf2.getText() + " appears last on line " + lastLine);
    		}
    	}
    }  
    
    /*Accesses the file reader writer to get the frequency*/
    public int getFrequency(String word){
    	return fileReaderWriter.analyze(fileName, word, "frequency");
    }
    
    public int getFirstLine(String word){
    	return fileReaderWriter.analyze(fileName, word, "firstLine");
    }
    
    public int getLastLine(String word){
    	return fileReaderWriter.analyze(fileName, word, "lastLine");
    }
    
    /*Changes the current file name to be the given file name*/
    /*Before this is used there is a check for whether or not the file exists*/
    /*There is also a check for whether or not the file has the correctly formatted name*/
    public void changeFile(String fileNameInput){
    	if(!fileNameInput.equals(fileName)){
    		fileName = fileNameInput;
    		hashBool = false;
    	}
    	
    }
	
}