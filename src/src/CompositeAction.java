package src;

public class CompositeAction extends Action {

	private Action[] actions;

	public CompositeAction(String name, Action[] actions) {
		this.name = name;
		this.actions = actions;
	}

	public Action[] getActions() {
		return actions;
	}

	public void setActions(Action[] actions) {
		this.actions = actions;
	}
}
