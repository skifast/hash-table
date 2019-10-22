

public class LinkedList{
	/*Instance Variables*/
	Node currentNode;
	Node Start;
	
	
	/**An empty linked list**/
	public LinkedList(){
		
		Start = null;
		currentNode = Start;
	}
	
	public LinkedList(int score, String name, int[] lines){
		
		Start = new Node(name, score, lines);
		currentNode = Start;
		
		/* three pieces of data the linked list holds*/
		Start.score = score;
		Start.name = name;
		Start.next = null;
		Start.lines = lines;
		
	}
	
	/*prints out everything in the list*/
	public void display(){
		currentNode = Start;
		
		while(currentNode != null){
			System.out.println(currentNode.getName() + " " + currentNode.getScore() + " " + currentNode.getLines());
			currentNode = currentNode.getNext();
		}
		
	
	}
	
	
	public Node find(String name){
		
		currentNode = Start;
		
		if(currentNode == null){
			return null;
		}
		
		
		while(currentNode != null){
			if(name.equals(currentNode.getName())){
				return currentNode;
			}
			else{
				currentNode = currentNode.getNext();
			}
		}
		
		/*Returns null if the name does not match any nodes*/
		return null;
		
	}
	
	public void remove(Node n){
		
		currentNode = Start;
		
		if(currentNode.equals(n)){
			Start = currentNode.getNext();
		}
		else{
			/*At this point the current node is the start node.
			 * If the start node is the node you are trying to 
			 * delete, the start node must be reset.*/
			while(currentNode.getNext() != null){
				if(currentNode.getNext().getName().equals(n.getName())){
					if(currentNode.getNext().getNext() != null){
						currentNode.setNext(currentNode.getNext().getNext());
					}
					else{
						currentNode.setNext(null);
					}
				}
				else{
					currentNode = currentNode.getNext();
				}
			}
		}
		
	
	}
	
	public void add(Node node){
		currentNode = Start;
		
		if(currentNode == null){
			Start = node;
		}
		/*goes to the end of the list to add the node*/
		else{
			while(currentNode.getNext() != null){
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(node);
		}
	}
	
	/*same idea as the previous method but with parameters*/
	public void add(String name, int value, int[] lines){
		Node node = new Node(name, value, lines);
		
		if(Start == null){
			Start = node;
		}
		else{
			currentNode = Start;
			
			while(currentNode.getNext() != null){
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(node);
			
		}
	}
	
	/* finds the node given the name and adds up all of the values then deletes all but the final value*/
	public void editScore(String name, int newValue){
		
		int[] lines;
		
		currentNode = Start;
		
		
		boolean found = false;
		
		int totalScore = newValue;
		
		while(currentNode != null){
			if(currentNode.getName().equals(name)){
				totalScore += currentNode.getScore();
				found = true;
				lines = currentNode.getLines();
				remove(currentNode);
				
			}
			else{
				currentNode = currentNode.getNext();
			}
		}
		lines = null;
		
		if(found){
			Node totalNode = new Node(name, totalScore, lines);
			add(totalNode);
		}
		/*if it is not found a new node is created to replace the deleted one*/
		else{
			/*Re-adds the old score into the list*/
			Node totalNode = new Node(name, newValue, lines);
			add(totalNode);
		}
		
	}
	
	public void editLines(String name, int addThis){
		currentNode = Start;
		
		
		while(currentNode != null){
			if(currentNode.getName().equals(name)){
				/*Sets the index of length plus one to the provided int*/
				currentNode.getLines()[currentNode.getLines().length + 1] = addThis;
			}
			else{
				currentNode = currentNode.getNext();
			}
		}
	}
	
	public void toString2(){
		
		currentNode = Start;
		
		if(currentNode == null){
			System.out.println("The list is empty");
		}
		else{
			while(currentNode != null){
				System.out.print(currentNode.getName() + " " + currentNode.getScore() + "\n");
				currentNode = currentNode.getNext();
			}
		}
	}
}
