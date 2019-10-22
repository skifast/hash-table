
public class Node{
	
	String name;
	int score;
	Node next;
	int[] lines;
	
	public Node(String name, int score, int[] lines){
		this.name = name;
		this.score = score;
		this.next = null;
		this.lines = lines;
	}
	
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public int[] getLines(){
		return lines;
	}
	
	public void setLines(int[] line){
		this.lines = line;
	}
	
}