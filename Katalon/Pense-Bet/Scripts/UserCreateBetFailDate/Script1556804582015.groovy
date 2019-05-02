import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/login')

WebUI.setText(findTestObject('Page_Pense Bet/input_Login_nickname'), 'katalon')

WebUI.setEncryptedText(findTestObject('Page_Pense Bet/input_Login_password'), 'o+tS4OuGt32s9ezZj287yw==')

WebUI.sendKeys(findTestObject('Page_Pense Bet/input_Login_password'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('Page_Pense Bet/a_katalon'))

WebUI.click(findTestObject('Page_Pense Bet/a_Create new bet'))

WebUI.setText(findTestObject('Page_Pense Bet/input_Bet subject_subject'), 'je paris qu’on aura 6 au projet de QDL')

WebUI.click(findTestObject('Page_Pense Bet/form_Bet subject        Ending date        Amount    Register Bet'))

WebUI.setText(findTestObject('Page_Pense Bet/input_Amount_amount'), '10')

WebUI.click(findTestObject('Page_Pense Bet/button_Register Bet'))

