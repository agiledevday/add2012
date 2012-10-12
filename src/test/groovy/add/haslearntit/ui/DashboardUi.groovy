package add.haslearntit.ui

import geb.Browser;
import add.haslearntit.pages.DashboardPage


class DashboardUi {

    String newSkillName;
    String newSkillDifficulty;
    String newSkillTime;
    
    public recordNewSkill(){
        
        newSkillName = "how to bind cucumber features to groovy steps";
        newSkillDifficulty = "easy";
        newSkillTime = "2 hours";
        
        recordSkill(newSkillName, newSkillDifficulty, newSkillTime);
    }
    
    DashboardPage page() {
    
        assertIsOnPage();
        return browser.getPage();
    }
    
    private recordSkill(String skillName, String skillDifficulty, String skillTime) {
        
        enter();
        page().recordNewSkill(skillName, skillDifficulty, skillTime)
    }
        
    public assertIsOnPage(){
        browser.at DashboardPage; 
    }

    public enter(){
        browser.to DashboardPage;
        assertIsOnPage();
    }
    
    public assertNewSkillIsDisplayed(){
        
        assert page().recentlyLearntSkill().contains(newSkillName);
        assert page().recentlyLearntSkill().contains(newSkillDifficulty);
        assert page().recentlyLearntSkill().contains(newSkillTime);
    }
    

    def displayedEntries={
        
        return page().learntSkills();
    }    

    def displayedEntriesPoints={
        
        return page().learntSkillsPoints();
    }

    public submitEntry(){
        
        page().submitNewSkillForm();
    }
    
    public fillNewSkillForm(String name, String difficulty, String time){
        
        page().fillNewSkillForm(name, difficulty, time);
    }
    
    public assertEncouragementMessageIsPresent(){

        assert page().encouragementIsPresent();
    }
    
    public assertEncouragementMessageIsNotPresent(){
        
        assert !page().encouragementIsPresent();
    }
    
    public assertNewEntryFormContainError(String error){
        
        assert page().messages.entries.contains(error);
    }
    
    public startTypingEntryName(String partialName){

        enter();
        page().typeSkillPart(partialName);
    }
 
    public List<String> displayedSkillSuggestions(){
        
        return page().displayedSkillSuggestions().sort();
    }
}
