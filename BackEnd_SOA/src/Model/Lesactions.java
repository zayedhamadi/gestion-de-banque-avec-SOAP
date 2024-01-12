package Model;


public class Lesactions {
private int id;
private String action;

public Lesactions() {
	super();
	// TODO Auto-generated constructor stub
}
public Lesactions(int id, String action) {
	super();
	this.id = id;
	this.action = action;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAction() {
	return action;
}
public void setAction(String action) {
	this.action = action;
}

}