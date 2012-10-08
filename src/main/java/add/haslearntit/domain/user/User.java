package add.haslearntit.domain.user;

import java.io.Serializable;

public class User implements Serializable{

	public static final User ANONYMOUS = new User("Anonymous", "");
	
	private final String name;
    private final String password;

	public User(String name, String password) {
		this.name = name;
        this.password = password;
	}

	public String getName() {
		return name;
	}

    public String getPassword() {
        return password;
    }
	
	
}
