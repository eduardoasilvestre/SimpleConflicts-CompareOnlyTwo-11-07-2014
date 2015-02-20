package src;

public class State {

	private String name;
	private StateType stateType;

	public State(String name, StateType stateType) {
		this.name = name;
		this.stateType = stateType;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public StateType getStateType() {
		return stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}
}
