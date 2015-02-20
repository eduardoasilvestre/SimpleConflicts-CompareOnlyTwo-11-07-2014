package src;

public class Norm {

	private DeonticConcept deonticConcept; /* deontic concept from the set {obligation, prohibition or permission}*/

	private Context context; /* context where the norm is defined*/
	
	private Entity entity; /* entity whose behaviour is being regulated*/
	
	private Action action; /* action being regulated*/
	
	private Constraint activationConstraint; /* condition that activates the norm*/
	
	private Constraint deactivationConstraint; /* condition that deactivates the norm */
	
	private State state; /* indicates the state of the norm from the se  {fulfilled, violated, none}. None indicates that the norm has not been fulfilled or violated yet*/ 
	
	public Norm(DeonticConcept deonticConcept, Context context, Entity entity,
			Action action, Constraint activationConstraint,
			Constraint deactivationConstraint, State state) {
		
		this.deonticConcept = deonticConcept;
		this.context = context;
		this.entity = entity;
		this.action = action;
		this.activationConstraint = activationConstraint;
		this.deactivationConstraint = deactivationConstraint;
		this.state = state;
	}

	public DeonticConcept getDeonticConcept() {
		return deonticConcept;
	}

	public void setDeonticConcept(DeonticConcept deonticConcept) {
		this.deonticConcept = deonticConcept;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Constraint getActivationConstraint() {
		return activationConstraint;
	}

	public void setActivationConstraint(Constraint activationConstraint) {
		this.activationConstraint = activationConstraint;
	}

	public Constraint getDeactivationConstraint() {
		return deactivationConstraint;
	}

	public void setDeactivationConstraint(Constraint deactivationConstraint) {
		this.deactivationConstraint = deactivationConstraint;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		String dc =  deonticConcept == null ? "DeonticConcept: NULL | " : "DeonticConcept: " + deonticConcept + " | ";
		builder.append(dc);
	
		String c = context == null ? "Context: NULL | " : "Context: " + context.getName() + " | ";
		builder.append(c);
		
		String e = entity == null ? "Entity: NULL | " : "Entity: " + entity.getName() + " | ";
		builder.append(e);
		
		String a = action == null ? "Action: NULL | " : "Action: " + action.getName() + " | ";
		builder.append(a);
		
		String ac = activationConstraint == null ? "ActivationConstraint: NULL | " : "ActivationConstraint: " + activationConstraint.getName() + " | ";
		builder.append(ac);
		
		String de = deactivationConstraint == null ? "DeactivationConstraint: NULL | " : "DeactivationConstraint: " + deactivationConstraint.getName() + " | ";
		builder.append(de);
		
		String s = state == null ? "State: NULL | " : "State: " + state.getName() + "\n";
		builder.append(s);
				
		return builder.toString();
	}
}
