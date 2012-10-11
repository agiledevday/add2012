package add.haslearntit.domain.skills;

import java.util.List;

public interface SkillsRepository {

	void store(Skill skill);

	List<Skill> loadAll();

	//TODO: MZA: Write to be able to push changes, requires automatic tests
	List<Skill> loadByNamePrefix(String namePrefix);
}