
import io.github.pixee.security.BoundedLineReader;
import java.io.*;



public class FileIO2 {
	
	private static TextFilter textFilter = new TextFilter();
	private static final int sizeOfArray = 5;
	
	
	public static void write(String Filename, HashTable1 hashTable){
		
		BufferedWriter bw = null;

		try{
			
			/*New File object*/
			File file = new File(Filename);

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			
			for(int i = 0; i< sizeOfArray; i++){
				LinkedList l = hashTable.table[i];
				
				/*if the linkedlist at the hashtable is null we will skip over it*/
				if(l != null){
					l.currentNode = l.Start;
					while(l.currentNode != null){
						String writeToFile = l.currentNode.getName() + " " + l.currentNode.getScore() + " " + l.currentNode.getLines()[0] + " " + l.currentNode.getLines()[1];
						bw.write(writeToFile + "\n");
						bw.flush();
						l.currentNode = l.currentNode.getNext();
					}
				}
			}
			
			
			
			//System.out.println("File written sucessfuly");
			
			bw.close();
			
		

		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		finally
		{
			try{
				if(bw!=null)
					bw.close();
			}catch(Exception ex){
				System.out.println("Error in closing the BufferedWriter text");
			}
		}

	}

	public static void read(String fileName, HashTable1 hashtable){
		
		BufferedReader br = null;
		

		try{
			File file = new File(fileName);
			
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String line;
			
			int currentLineCount = 0;
			while((line= BoundedLineReader.readLine(br, 5_000_000)) != null){
				currentLineCount += 1;
				//divides the line by spaces and puts it into a list
				String[] currentLine = line.split(" ");
				
				for(int i = 0; i < currentLine.length; i++){
					currentLine[i] = textFilter.filterText(currentLine[i]);
					//makes the string lower case
					currentLine[i].toLowerCase();
					//index is where this value will reside in the hashtable
					int index = hashValue(currentLine[i]);
					hashtable.add(currentLine[i], index, currentLineCount);
				}
			}

		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		finally
		{
			try{
				if(br!=null)
					br.close();
			}catch(Exception ex){
				System.out.println("Error in closing the BufferedReader text");
			}
		}


	}
	
	/*returns either frequency, the first line, or the last line*/
	public static int analyze(String fileName, String word, String function){
		BufferedReader br = null;
		
		File file = new File(fileName);
		
		String name = "";

		try{
			
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			
			while((line = BoundedLineReader.readLine(br, 5_000_000)) != null){
				/*The line is split into the name and frequency*/
				String[] currentLine = line.split(" ");
				name = currentLine[0];
				if(word.equals(name)){
					if(function.equals("frequency")){
						int count = Integer.parseInt(currentLine[1]);
						return count;
					}
					else if(function.equals("firstLine")){
						int firstLine = Integer.parseInt(currentLine[2]);
						return firstLine;
					}
					else if(function.equals("lastLine")){
						int lastLine = Integer.parseInt(currentLine[3]);
						
						/*This happens when the word appears only once*/
						if(lastLine == 0){
							lastLine = Integer.parseInt(currentLine[2]);
						}
						return lastLine;
					}
				}
			}
			return 0;

		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		finally
		{
			try{
				if(br!=null)
					br.close();
			}catch(Exception ex){
				System.out.println("Error in closing the BufferedReader text");
			}
		}
		
		return 0;
	}
	

	public static int hashValue(String word){
		int sum = 0;
		for(int i = 0; i < word.length(); i ++){
			sum += Character.getNumericValue(word.charAt(i));
		}
		/*This algorithm was provided by Mr. Hussain*/
		/*determines at what index of the hashTable the word should go to*/
		int index = sum % sizeOfArray;
		return index;
	}

	
	public static boolean checkForFile(String fileName){
		
		/*checks whether or not a file exists */
		File file = new File(fileName);
		if(!file.exists()){
			return false;
		}
		return true;
	}
	
}
