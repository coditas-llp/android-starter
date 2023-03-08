package com.coditas.example.test.steps.login

import androidx.test.core.app.ActivityScenario
import com.coditas.example.test.utils.UiAutomator
import com.coditas.example.ui.MainActivity
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class LoginSteps {

    @Given("^User is on login screen$")
    fun launchApplication(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @When("^I should verify \"([^\"]*)\" text is displayed on the screen$")
    fun verifyTextVisible(string: String){
        UiAutomator.checkTextViewVisibility(inputString = string)
    }

    @Then("^I enter \"([^\"]*)\" in \"([^\"]*)\" field$")
    fun enterTextInEditText(value: String,fieldName:String){
        UiAutomator.enterTextInEditTextField(value, fieldName)
    }

    @Then("^I tap on the \"([^\"]*)\" button$")
    fun onClickButton(string: String){
        UiAutomator.onClickButtonView(string)
    }

}