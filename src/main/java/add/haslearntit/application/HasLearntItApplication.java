package add.haslearntit.application;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.proxy.IProxyTargetLocator;
import org.apache.wicket.proxy.LazyInitProxyFactory;

import add.haslearntit.application.skills.UserSkillsPage;
import add.haslearntit.domain.skills.SkillsRepository;
import add.haslearntit.infrastructure.transients.skills.TransientSkillsRepository;

public class HasLearntItApplication extends WebApplication {

	private static class IProxyTargetLocatorImplementation implements IProxyTargetLocator {
		
		@Override
		public Object locateProxyTarget() {
						
			return TransientSkillsRepository.get();
		}
	}

	@Override
	public Class<UserSkillsPage> getHomePage() {
		return UserSkillsPage.class;
	}

	@Override
	public void init() {
		super.init();

		mountPage("/user", getHomePage());
	}

	public SkillsRepository getSkillsRepository() {

		SkillsRepository repository = (SkillsRepository) LazyInitProxyFactory.createProxy(SkillsRepository.class, new IProxyTargetLocatorImplementation());

		return repository;
	}
}
