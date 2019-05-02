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

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Login_nickname (4)'), 'katalon')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Pense Bet/input_Login_password (4)'), 'o+tS4OuGt32s9ezZj287yw==')

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/button_Login (4)'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/html_Pense Betkatalonfont-familymonospacefont-size13pxbackground-colorrgba(000.7)positionfixedtop0left0right0displayblockz-index999999999line-height normal katalon divpadding0margin0colorfff katalon kbddisplayinline-bloc'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/a_Active'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/a_Waiting'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/a_katalon'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/a_Create new bet (1)'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/div_Create a new bet        Bet subject        Ending date        Amount    Register Bet'))

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Amount_amount (1)'), '10')

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/button_Register Bet (1)'))

