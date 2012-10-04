package add.haslearntit.steps

import org.apache.xalan.xsltc.compiler.When;

import geb.Browser
import add.haslearntit.domain.skills.SkillsRepository;
import add.haslearntit.hooks.Context;
import add.haslearntit.infrastructure.transients.skills.TransientSkillsRepository
import add.haslearntit.pages.*
import cucumber.table.DataTable

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    SkillsRepository skillRepository;
	
	Before {
        
        skillRepository = Context.get().getBean(SkillsRepository);
	}
	
    When(~'^skill should not be recorded$'){ ->
        
        assert skillRepository.loadAll().size() == 0
        
    }
    