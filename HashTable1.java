

public class HashTable1{

	private static final int sizeOfArray = 5;
	
	LinkedList[] table;

	public HashTable1(){
		/*Creates a list of LinkedLists*/
		table = new LinkedList[sizeOfArray];
		
		/*each linked list is assigned a null value*/
		int count = 0;
		while(count < sizeOfArray){
			table[count] = new LinkedList();
			count += 1;
		}
	}
	
	public void add(String word, int index, int lineAddadive){
		//if the list at this index of the array is empty
		
		if(table[index] == null || table[index].find(word) == null){
			//since there was not a list at this index before or since this word does
			//not have a node, the word is appearing for the first time here so its 
			//count is 1 and the only line in the list is whatever is passed into it
			int[] usethis = new int[2];
			/*set first line*/
			usethis[0] = lineAddadive;
			
			table[index].add(word, 1, usethis);
		}
		//the list at this index of the table has words in it but not
		//this word
		else if(table[index].find(word).getScore() > 0){
			Node currentNode = table[index].find(word);
			currentNode.setScore(currentNode.getScore() + 1);
			int[] setthis = currentNode.getLines();
			setthis[1] = lineAddadive;
		}
	}	
	
	/*returns index of a given word*/
	public int search(String word){
		int sum = 0;
		for(int i = 0; i < word.length(); i ++){
			sum += Character.getNumericValue(word.charAt(i));
		}
		int index = sum % sizeOfArray;
		return index;
	}
	

}
