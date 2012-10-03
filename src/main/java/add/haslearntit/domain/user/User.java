package add.haslearntit.domain.user;

import java.io.Serializable;

public class User implements Serializable{

	public static final User ANONYMOUS = new User("Anonymous");
	
	private final String name;

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
}
