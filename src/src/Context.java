package src;

public class Context {

	private String name;
	private ContextType contextType;

	public Context(String name, ContextType contextType) {
		this.name = name;
		this.contextType = contextType;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ContextType getContextType() {
		return contextType;
	}
	
	public void setContextType(ContextType contextType) {
		this.contextType = contextType;
	}
}
