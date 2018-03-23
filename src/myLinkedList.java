import java.io.PrintWriter;

public class myLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	
	public myLinkedList() {
		setHead(tail = null);
	}
	public boolean isEmpty() {
		return getHead() == null;
	}
	public void addToHead(T el) {
		setHead(new Node<T>(el,getHead()));
		if (tail == null)
			tail = getHead();
	}
	public void addToTail(T el) {
		if (!isEmpty()) {
			tail.setNext(new Node<T>(el));
			tail = tail.getNext();
		}
		else 
			setHead(tail = new Node<T>(el));
	}
	public T deleteFromHead() { // delete the head and return its info;
			T el = (T)getHead().getItem();
		if (getHead() == tail) // if only one node on the list;
			setHead(tail = null);
		else 
			setHead(getHead().getNext());
		return el;
	}
	public Node<T> getHead() {
		return head;
	}
	public void setHead(Node<T> head) {
		this.head = head;
	}
	public Node<T> getTail() {
		return tail;
	}
	public void setTail(Node<T> tail) {
		this.tail = tail;
	}
	public void printAllFile(PrintWriter writer) {
		for (Node<T> tmp = getHead(); tmp != null; tmp = tmp.getNext())
			writer.print(tmp.getItem() + " ");
	}
	public void printAllConsole() {
		for (Node<T> tmp = getHead(); tmp != null; tmp = tmp.getNext())
			System.out.print(tmp.getItem() + " ");
	}
	public boolean isInList(T el) {
		Node<T> tmp;
		for (tmp = getHead(); tmp != null && !tmp.getItem().equals(el); tmp = tmp.getNext());
		return tmp != null;
	}
	public T getheadItem() {
		return (T)getHead().getItem();
	}
	public void clear() {
		setHead(tail = null);
	}
}
