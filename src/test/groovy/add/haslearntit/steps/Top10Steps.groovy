package add.haslearntit.steps

import add.haslearntit.domain.EntryDomain
import add.haslearntit.ui.DashboardUi
import add.haslearntit.ui.Top10Ui
import cucumber.runtime.PendingException
import cucumber.table.DataTable

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)


Top10Ui top10Ui
DashboardUi dashboardUi;
EntryDomain entryDomain;

Before() {
	dashboardUi = world.asType(DashboardUi);
	top10Ui = world.asType(Top10Ui)
	entryDomain = world.asType(EntryDomain)
}


Given(~'^entry list is empty$') { ->
}

When(~'^user enters top TEN list$') { ->
	dashboardUi.enter()	
}

Then(~'^empty list should appear$') { ->
	top10Ui.assertIsEmpty()
}

Given(~'^Entry list contains less than TEN unique skills during last week$') { DataTable data ->
	data.asMaps().each { raw ->
		entryDomain.createEntry(raw['what'], "easy", "1", new Date());
	}
	
}

Then(~'^all skills should appear ordered by occurrences$') { DataTable data ->
	List<String> trends = top10Ui.getTrends(); //java(2)
	
	data.asMaps().each { raw ->
		def what = raw['what']
		def count = raw['count']
		assertTrendsContain(what, count, trends)
	}
	
		
}

private assertTrendsContain(what, count,List trends) {
	def result = trends.findAll() {trend->
		trend == "${what}(${count})"
	}
	
	assert result.size() == 1
}

Given(~'^Entry list contains more than TEN unique skills during last week$') { DataTable data ->
	data.asMaps().each { raw ->
		
		(raw['count'] as int).times {
			entryDomain.createEntry(raw['what'], "easy", "1", new Date());
		}
		
	}
}

Then(~'^Top TEN should not contain$') { DataTable data->
	List<String> trends = top10Ui.getTrends();
	assert trends.size() == 10
	assert trends.contains(data.asMaps().first().excluded) == false
}

