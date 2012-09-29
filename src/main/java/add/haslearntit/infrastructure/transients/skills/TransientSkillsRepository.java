package add.haslearntit.infrastructure.transients.skills;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import add.haslearntit.domain.skills.Comment;
import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;

public class TransientSkillsRepository implements SkillsRepository{

	private static SkillsRepository instance = new TransientSkillsRepository();
	
	private List<Skill> storage;
	private Multimap<Skill, Comment> comments;
	
	public TransientSkillsRepository() {
		storage = new ArrayList<Skill>();
		comments = HashMultimap.create();
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

	@Override
	public void addComment(Skill skill, Comment comment) {
		comments.put(skill, comment);
	}

	@Override
	public List<Comment> fetchAllComments(Skill skill) {
		return Lists.newArrayList(comments.get(skill));
	}

}
