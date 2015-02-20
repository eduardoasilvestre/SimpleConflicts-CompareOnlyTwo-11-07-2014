package src;


public class Teste {


	public static void main(String[] args) {

		//printNormsExample();
		
		isTherAConflictBetween2Norms();
		
				
		
	}

	/**
	 * Este método pega 2 normas simples e responde se existe conflito entre elas
	 * @author eduardo.silvestre
	 */

	
	private static void isTherAConflictBetween2Norms() {
		//actions
				Action action1 = new AtomicAction("giveTalks");
				Action action2 = new AtomicAction("teach");
				
				//constraint
				Constraint aConstraint1 = null; //activation constraint
				Constraint dConstraint1 = null; //deactivation constraint
				Constraint aConstraint2 = null; //activation constraint
				Constraint dConstraint2 = null; //deactivation constraint
				
				//context
				Context context1 = new Context("university", ContextType.ORGANIZATION);
				Context context2 = new Context("depCS", ContextType.ORGANIZATION);
				
				Entity entity1 = new Entity ("professor", EntityType.ROLE);
				Entity entity2 = new Entity ("researcher", EntityType.ROLE);
				
				State state1 = null;
				State state2 = null;
				
				Norm norm1 = new Norm(DeonticConcept.OBLIGATION, context1, entity1, action1, aConstraint1, dConstraint1, state1);
				System.out.println(norm1.toString());
								
				Norm norm2 = new Norm(DeonticConcept.PROHIBITION, context2, entity2, action2, aConstraint2, dConstraint2, state2);
				System.out.println(norm2.toString());
				
				/*
				List<Norm> normSet = new ArrayList<>();
				normSet.add(norm1);
				normSet.add(norm2);
				
				Norm[] normSet = new Norm[2];
				normSet[0] = norm1;
				normSet[1] = norm2;
				 */
				
				ConflictChecker conflictChecker = new ConflictChecker();
				boolean conflictCheckerReturn = conflictChecker.conflictChecker2Norms(norm1, norm2);
			    
			    if (conflictCheckerReturn)
			    	System.out.println("the norms are in conflict!!");
			    else
			    	System.out.println("the norms are NOT in conflict!!");
	}

	/**
	 * Este método pega duas normas simples e as imprime
	 * @author eduardo.silvestre
	 */

	private static void printNormsExample() {
		//actions
		Action action1 = new AtomicAction("giveTalks");
		Action action2 = new AtomicAction("teach");
		
		//constraint
		Constraint aConstraint1 = null; //activation constraint
		Constraint dConstraint1 = null; //desactivation constraint
		Constraint aConstraint2 = null; //activation constraint
		Constraint dConstraint2 = null; //desactivation constraint
		
		//context
		Context context1 = new Context("university", ContextType.ORGANIZATION);
		Context context2 = new Context("depCS", ContextType.ORGANIZATION);
		
		Entity entity1 = new Entity ("professor", EntityType.ROLE);
		Entity entity2 = new Entity ("researcher", EntityType.ROLE);
		
		State state1 = new State("ativa", StateType.NONE);
		State state2 = new State("ativa", StateType.NONE);
		
		Norm norm1 = new Norm(DeonticConcept.OBLIGATION, context1, entity1, action1, aConstraint1, dConstraint1, state1);
		
		System.out.println(norm1.toString());
						
		Norm norm2 = new Norm(DeonticConcept.PROHIBITION, context2, entity2, action2, aConstraint2, dConstraint2, state2);
		System.out.println(norm2.toString());
	}
	
	
	

}
