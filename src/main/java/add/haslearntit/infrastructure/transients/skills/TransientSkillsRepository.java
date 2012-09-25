package add.haslearntit.infrastructure.transients.skills;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;

public class TransientSkillsRepository implements SkillsRepository{

	private static SkillsRepository instance = new TransientSkillsRepository();
	
	private List<Skill> storage;
	
	public TransientSkillsRepository() {
		storage = new ArrayList<Skill>();
	}

	public void store(Skill skill) {
		storage.add(skill);
	}

	public List<Skill> loadAll() {
		return Collections.unmodifiableList(storage);
	}

	public static SkillsRepository get() {
		return instance;
	}
	
	public static void clear(){
		instance = new TransientSkillsRepository();
	}

}
