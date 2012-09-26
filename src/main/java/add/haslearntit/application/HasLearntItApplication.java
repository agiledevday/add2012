package add.haslearntit.application;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.proxy.IProxyTargetLocator;
import org.apache.wicket.proxy.LazyInitProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import add.haslearntit.application.skills.UserSkillsPage;
import add.haslearntit.domain.skills.SkillsRepository;

public class HasLearntItApplication extends WebApplication implements ApplicationContextAware {

	private ApplicationContext applicationContext;

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

		return (SkillsRepository) LazyInitProxyFactory.createProxy(SkillsRepository.class, bean("skillsRepository"));
	}

	private IProxyTargetLocator bean(final String beanName) {
		
		return new BeanLocator(beanName);
	}

	protected ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	private final static class BeanLocator implements IProxyTargetLocator {
		
		private final String beanName;
		
		private BeanLocator(String beanName) {
			this.beanName = beanName;
		}
		
		@Override
		public Object locateProxyTarget() {
			HasLearntItApplication application = (HasLearntItApplication) Application.get();
			return application.getApplicationContext().getBean(beanName);
		}
	}
}
