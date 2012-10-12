package add.haslearntit

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

Given(~'^there is scenario with tag @currentlyInDevelopment$') {->}
When(~'^I run test CurrentlyInDevelopmentAcceptanceIT$') {->}
Then(~'^there should be run only scenarios in development$') {->}
