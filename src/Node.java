public class Node<T> {
	private T item;
	private Node<T> next;
	public Node(T i){
		setItem(i); 
		setNext(null);
	}
	public Node(T itemA, Node<T> nextA){
		setItem(itemA) ;
		setNext(nextA);
	}
	public T getItem() {
		return item;
	}
	public void setItem(T itemA) {
		item = itemA;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> nextA) {
		next = nextA;
	}

}
