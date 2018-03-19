import java.io.PrintWriter;

public class myLinkedList {
	private Node head;
	private Node tail;
	
	public myLinkedList() {
		setHead(tail = null);
	}
	public boolean isEmpty() {
		return getHead() == null;
	}
	public void addToHead(int el) {
		setHead(new Node(el,getHead()));
		if (tail == null)
			tail = getHead();
	}
	public void addToTail(int el) {
		if (!isEmpty()) {
			tail.setNext(new Node(el));
			tail = tail.getNext();
		}
		else 
			setHead(tail = new Node(el));
	}
	public int deleteFromHead() { // delete the head and return its info;
			int el = getHead().getItem();
		if (getHead() == tail) // if only one node on the list;
			setHead(tail = null);
		else 
			setHead(getHead().getNext());
		return el;
	}
	public Node getHead() {
		return head;
	}
	public void setHead(Node head) {
		this.head = head;
	}
	public Node getTail() {
		return tail;
	}
	public void setTail(Node tail) {
		this.tail = tail;
	}
	public void printAllFile(PrintWriter writer) {
		for (Node tmp = getHead(); tmp != null; tmp = tmp.getNext())
			writer.print(tmp.getItem() + " ");
	}
	public void printAllConsole() {
		for (Node tmp = getHead(); tmp != null; tmp = tmp.getNext())
			System.out.print(tmp.getItem() + " ");
	}
	public boolean isInList(int el) {
		Node tmp;
		for (tmp = getHead(); tmp != null && tmp.getItem()!=el; tmp = tmp.getNext());
		return tmp != null;
	}
	public int getheadItem() {
		return getHead().getItem();
	}
	
	
}
