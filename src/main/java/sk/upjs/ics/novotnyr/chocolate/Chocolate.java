package sk.upjs.ics.novotnyr.chocolate;

public class Chocolate {
	private String id;
	
	private String title;
	
	private int percentage;
	
	public Chocolate() {
		// empty constructor
	}
	
	public Chocolate(String id, String title, int percentage) {
		this.id = id;
		this.title = title;
		this.percentage = percentage;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public int getPercentage() {
		return percentage;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
