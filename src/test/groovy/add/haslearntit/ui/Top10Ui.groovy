package add.haslearntit.ui

class Top10Ui {

	public assertIsEmpty() {
		assert browser.page.trends.find("span").size()==0
	}
	
	public getTrends() {
		browser.page.trends.find("span")*.text()
	}
}
