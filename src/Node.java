public class Node {
	private int item;
	private Node next;
	public Node(int i){
		setItem(i); 
		setNext(null);
	}
	public Node(int itemA, Node nextA){
		setItem(itemA) ;
		setNext(nextA);
	}
	public int getItem() {
		return item;
	}
	public void setItem(int itemA) {
		item = itemA;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node nextA) {
		next = nextA;
	}

}
