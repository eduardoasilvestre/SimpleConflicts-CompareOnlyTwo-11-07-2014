package src;

public class Entity {

	private String name;
	private EntityType entityType;

	public Entity(String name, EntityType entityType) {
		this.name = name;
		this.entityType = entityType;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}


}
