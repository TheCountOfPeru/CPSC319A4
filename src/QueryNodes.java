/**
 * This class serves to hold the start and end nodes of a query
 * @author Jonathan
 *
 */
public class QueryNodes {
	private Integer start, end;
	
	public QueryNodes(Integer s, Integer e) {
		setStart(s);
		setEnd(e);
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
	public String toString() {
		return start + " " + end;
	}
}
