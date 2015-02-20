package src;

public class Constraint {
	private String name;
	private ConstraintType constraintType;

	public Constraint(String name, ConstraintType cT) {
		this.name = name;
		this.constraintType = cT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConstraintType getConstraintType() {
		return constraintType;
	}

	public void setConstraintType(ConstraintType constraintType) {
		this.constraintType = constraintType;
	}
	
}
