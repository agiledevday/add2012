package add.haslearntit.application.skills;

import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;

public class AllLearntSkillsListModel extends LoadableDetachableModel<List<Skill>> implements SkillsListModel {

	private final SkillsRepository repository;

	public AllLearntSkillsListModel(SkillsRepository repository) {
		this.repository = repository;
	}

	@Override
	protected List<Skill> load() {
		return repository.loadAll();
	}

}
