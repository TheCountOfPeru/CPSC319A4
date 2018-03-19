public class Node<T> {
	private T item;
	private Node next;
	public Node(T i){
		setItem(i); 
		setNext(null);
	}
	public Node(T itemA, Node nextA){
		setItem(itemA) ;
		setNext(nextA);
	}
	public T getItem() {
		return item;
	}
	public void setItem(T itemA) {
		item = itemA;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node nextA) {
		next = nextA;
	}

}
