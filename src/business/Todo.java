package business;

public class Todo {
	
	private String content;
	private boolean done = false;
	
	public Todo(String content) {
		this.content = content;
		this.done = false;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String str) {
		this.content = str;
	}
	
	public boolean getDone() {
		return done;
	}
	
	public void setDone() {
		done = !done;
	}
}
