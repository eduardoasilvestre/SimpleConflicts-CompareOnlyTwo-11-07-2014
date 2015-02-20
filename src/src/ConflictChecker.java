package src;


public class ConflictChecker {
	public boolean conflictChecker2Norms(Norm norm1, Norm norm2) {
		
		// returns true if there is a conflict: prohibition x obligation OR prohibition x permission
		String conflictConcept = deonticConceptChecker(norm1, norm2);
		if ("false".equals(conflictConcept)) {
			return false;
		}
		
		//returns true if the context of the norms are the same
		String conflictContext = contextChecker(norm1, norm2);
		if ("false".equals(conflictContext)) {
			return false;
		}
		
		//returns true if the state of the norms are both NONE
		String normState = stateChecker(norm1, norm2);
		if ("false".equals(normState)) {
			return false;
		}
		
		//returns true if the  if the entities are the same OR one is ALL
		String conflictEntity = entityChecker(norm1, norm2);
		if ("false".equals(conflictEntity)) {
			return false;
		}
				
		//returns true if there is not conflict between activation and deactivation constraint
		String conflictConstraint = constraintChecker(norm1, norm2);
		if ("false".equals(conflictConstraint)) {
			return false;
		}
					
		//returns true if the action are the same
		String conflictAction = isTheSameAction(norm1, norm2);
		if ("false".equals(conflictAction)) {
			return false;
		}

		//at this moment all conditions are valide and the norms are in conflict
		return true;
	}


	/*public String conflictChecker2Norms(Norm norm1, Norm norm2) {
		String conflictConcept, conflictEntity, conflictAction, conflictContext, conflictConstraint;
		Norm[] normsVector = new Norm[2];

		//checking the norm state
		String normState = checkTheNormState(norm1, norm2);
		if ("false".equals(normState)) {
			return "false";
		}
		
		conflictConcept = deonticConceptChecker(norm1, norm2);
		
		// Prohibition x Obligation
		if (conflictConcept == "true") {
			
			conflictContext = contextChecker(norm1, norm2);
			
			//Norms are in the same context or null
			if (conflictContext != "false") {
				normsVector = rewriteNormBasedOnContext(norm1, norm2, conflictContext);
				norm1 = normsVector[0];
				norm2 = normsVector[1];

				//check the entity - in this code the entity must be the same
				conflictEntity = entityChecker(norm1, norm2);
				
				//continue if the entities are the same
				if (conflictEntity != "false") {
					normsVector = rewriteNormBasedOnEntity(norm1, norm2, conflictEntity);
					norm1 = normsVector[0];
					norm2 = normsVector[1];

					//checks the constraint
					conflictConstraint = constraintChecker(norm1, norm2);
					
					//checks the action: MÉTODO COMENTADO ABAIXO
					//conflictAction = actionChecker(norm1, norm2); 

					// The constraints intersect
					if (conflictConstraint == "true") {
						
						// The actions are the same
						conflictAction = isTheSameAction(norm1, norm2);
												
						if (conflictAction == "true") {
							return "true";
						}

					}
					System.out.println("norm1: " + norm1.getAction().getName() + ", norm 2: " + norm2.getAction().getName());

				}
			}
		}

		return "false";
	}*/
	
	public String stateChecker(Norm norm1, Norm norm2) {
		State s1 = norm1.getState();
		State s2 = norm2.getState();
		
		if (s1 == null) {
			s1 = new State("state1", StateType.NONE);
			norm1.setState(s1);
		}
		
		if (s2 == null) {
			s2 = new State("state2", StateType.NONE);
			norm2.setState(s2);
		}
		if (s1.getStateType().equals(StateType.NONE) && s2.getStateType().equals(StateType.NONE)) {
			return "true";
		}
		
		return "false";
	}
	

	public Norm[] rewriteNormBasedOnContext(Norm norm1, Norm norm2, String conflictContext) {
		Norm[] normsVector = new Norm[2];
		
		//if norm1 has context null, put the context of norm2 in nomrm1
		if (conflictContext == "norm1ContextNull") {
			norm1.setContext(norm2.getContext());
		}
		
		//if norm2 has context null, put the context of norm1 in nomrm2
		if (conflictContext == "norm2ContextNull") {
			norm2.setContext(norm1.getContext());
		}

		normsVector[0] = norm1;
		normsVector[1] = norm2;
		
		return normsVector;
	}

	public Norm[] rewriteNormBasedOnEntity(Norm norm1, Norm norm2, String conflictEntity) {
		Norm[] normsVector = new Norm[2];

		// if norm1 is all set the entity of norm2 in nomrm1
		if ("norm1All".equals(conflictEntity)) { 
			norm1.setEntity(norm2.getEntity());
		}
		//if norm 2 is all set the entity of norm1 in norm2
		if ("norm2All".equals(conflictEntity)) {
			norm2.setEntity(norm1.getEntity());
		}
		System.out.println("norm1: " + norm1.getEntity().getName() + ", norm 2: " + norm2.getEntity().getName());

		normsVector[0] = norm1;
		normsVector[1] = norm2;
		return normsVector;
	}

	public String deonticConceptChecker(Norm norm1, Norm norm2) {
		if ((norm1.getDeonticConcept() == DeonticConcept.PROHIBITION && norm2
				.getDeonticConcept() == DeonticConcept.OBLIGATION)
				|| (norm1.getDeonticConcept() == DeonticConcept.PROHIBITION && norm2
						.getDeonticConcept() == DeonticConcept.PERMISSION))
			return "true";
		if ((norm2.getDeonticConcept() == DeonticConcept.PROHIBITION && norm1
				.getDeonticConcept() == DeonticConcept.OBLIGATION)
				|| (norm2.getDeonticConcept() == DeonticConcept.PROHIBITION && norm1
						.getDeonticConcept() == DeonticConcept.PERMISSION))
			return "true";

		return "false";
	}

	public String contextChecker(Norm norm1, Norm norm2) {
		Context c1 = norm1.getContext();
		Context c2 = norm2.getContext();
		
		if (c1 == null || c1.getName() == null || c1.getContextType() == null) {
			c1 = new Context("context1", ContextType.ORGANIZATION);
			norm1.setContext(c1);
		}
		
		if (c2 == null || c2.getName() == null || c2.getContextType() == null) {
			c2 = new Context("context2", ContextType.ORGANIZATION);
			norm2.setContext(c2);
		}
		
		
		if (norm1.getContext().getName() == norm2.getContext().getName())
			return "true";

		return "false";
	}

	public String entityChecker(Norm norm1, Norm norm2) {
		
		Entity e1 = norm1.getEntity();
		Entity e2 = norm2.getEntity();
		
		boolean flagE1 = false;
		boolean flagE2 = false;
		
		if (e1 == null || e1.getName() == null || e1.getEntityType() == null) {
			e1 = new Entity("e1", EntityType.ALL);
			norm1.setEntity(e1);
			flagE1 = true;
		}
		if (e2 == null || e2.getName() == null || e2.getEntityType() == null) {
			e2 = new Entity("e2", EntityType.ALL);
			norm2.setEntity(e2);
			flagE2 = true;
		}
		

		
		//the if condition was valid OR the all entities are all
		if ((flagE1 && flagE2) || (e1.getEntityType() == EntityType.ALL && e2.getEntityType() == EntityType.ALL)) {
			return "true";
		}
		//if the execution arrives her mean that one entity at least is not all
		
		
		
		if (e1.getEntityType().equals(EntityType.ALL)) {
			e1.setEntityType(e2.getEntityType());
			norm1.setEntity(e1);
		}
		if (e2.getEntityType().equals(EntityType.ALL)) {
			e2.setEntityType(e1.getEntityType());
			norm2.setEntity(e2);
		}
		
		// if the entities are equal
		if (norm1.getEntity().equals(norm2.getEntity())) {
			return "true";
		}

		return "false";
	}
/*
	public String actionChecker(Norm norm1, Norm norm2) {
		ActionsRelationship[] actionsRelationship;
		Integer i = 0;

		actionsRelationship = ontology.getActionsRelationship();

		if (actionsRelationship != null) {
			for (i = 0; i < actionsRelationship.length; i++) {
				if (norm1.getAction() == actionsRelationship[i].getSource()
						&& norm2.getAction() == actionsRelationship[i]
								.getTarget()) {
					if (actionsRelationship[i].getRelationship() == Relationship.REFINEMENT)
						return "norm1SuperAction";
					if (actionsRelationship[i].getRelationship() == Relationship.AGGREGATION)
						return "norm1WholeAction";
				}
				if (norm2.getAction() == actionsRelationship[i].getSource()
						&& norm1.getAction() == actionsRelationship[i]
								.getTarget()) {
					if (actionsRelationship[i].getRelationship() == Relationship.REFINEMENT)
						return "norm2SuperAction";
					if (actionsRelationship[i].getRelationship() == Relationship.AGGREGATION)
						return "norm2WholeAction";
				}
			}

		}
		return "false";

	}*/
	
	public String isTheSameAction(Norm norm1, Norm norm2) {
		Action action1 = norm1.getAction();
		Action action2 = norm1.getAction();
		if (action1.equals(action2)) {
			return "true";
		}
		return "false";
		
	}

	public String constraintActivationChecker(Norm norm1, Norm norm2) {

		//if the activation or deactivation constraint is not configured
		if (norm1.getActivationConstraint() == null || norm2.getActivationConstraint() == null) {
			return "true";
		}

		//if they are equals
		if (norm1.getActivationConstraint() == norm2.getActivationConstraint()) {
			return "true";
		}

		//if norm1 or norm2 has constraint type equal to ACTIONTYPE
		if ((norm1.getActivationConstraint().getConstraintType() == ConstraintType.ACTIONTYPE) || (norm2.getActivationConstraint().getConstraintType() == ConstraintType.ACTIONTYPE)) { 
			return "doubt";
		}

		// If the activation conditions are dates
		if ((norm1.getActivationConstraint().getConstraintType() == ConstraintType.DATETYPE)
				&& (norm1.getActivationConstraint().getConstraintType() == ConstraintType.DATETYPE)) {
			
			 //the numbers could be long or use a library such as JODA TIME ?
			Integer norm1Date = Integer.parseInt(norm1.getActivationConstraint().getName());
			Integer norm2Date =  Integer.parseInt(norm2.getActivationConstraint().getName());
			
			if (norm1Date <= norm2Date) {
				return "norm1ActivationConstraint<=norm2ActivationConstraint";
			} else {
				return "norm1ActivationConstraint>norm2ActivationConstraint";
			}
		}
		
		//what does it mean ?
		return "false";
	}

	/*
	 * public String constraintDeactivationChecker (Norm norm1, Norm norm2) {
	 * Integer norm1Date, norm2Date;
	 * 
	 * if (norm1.getDeactivationConstraint()==null ||
	 * norm2.getDeactivationConstraint()==null) return "true";
	 * 
	 * if (norm1.getDeactivationConstraint()==norm2.getDeactivationConstraint())
	 * return "true";
	 * 
	 * if
	 * ((norm1.getDeactivationConstraint().getConstraintType()==ConstraintType
	 * .ACTIONTYPE) ||
	 * (norm2.getDeactivationConstraint().getConstraintType()==ConstraintType
	 * .ACTIONTYPE)) return "doubt";
	 * 
	 * //If the deactivation conditions are dates if
	 * ((norm1.getDeactivationConstraint
	 * ().getConstraintType()==ConstraintType.DATETYPE) &&
	 * (norm1.getDeactivationConstraint
	 * ().getConstraintType()==ConstraintType.DATETYPE)) { norm1Date =
	 * Integer.parseInt(norm1.getDeactivationConstraint().getConstraint());
	 * norm2Date =
	 * Integer.parseInt(norm2.getDeactivationConstraint().getConstraint()); if
	 * (norm1Date <= norm2Date) return
	 * "norm1DeactivationConstraint<=norm2DeactivationConstraint"; else return
	 * "norm1DeactivationConstraint>norm2DeactivationConstraint";
	 * 
	 * } return "false"; }
	 */

	public String constraintChecker(Norm norm1, Norm norm2) {
		
		//this method returns the kind of constraint
		String resultActivation = constraintActivationChecker(norm1, norm2);
		
		// String resultDeactivation= constraintDeactivationChecker(norm1,norm2);
		Integer norm1Date, norm2Date;

		
		if (resultActivation == "norm1ActivationConstraint<=norm2ActivationConstraint") {
			//if deactivationConstraint is null then return true (there is a conflict)
			if (norm1.getDeactivationConstraint() != null) {
				norm1Date = Integer.parseInt(norm1.getDeactivationConstraint().getName());
				norm2Date = Integer.parseInt(norm2.getActivationConstraint().getName());
				
				//it means that the end of norm1 is before the begin of norm2
				if (norm1Date <= norm2Date) {
					return "false";
				} else {
					return "true";
				}
			} else {
				return "true";
			}
		} else if (resultActivation == "norm1ActivationConstraint>norm2ActivationConstraint") {
			if (norm2.getDeactivationConstraint() != null) {
				norm1Date = Integer.parseInt(norm1.getActivationConstraint().getName());
				norm2Date = Integer.parseInt(norm2.getDeactivationConstraint().getName());
				if (norm1Date >= norm2Date) {
					return "false";
				} else {
					return "true";
				}
			} else {
				return "true";
			}
		}
		return resultActivation;
	}

}
