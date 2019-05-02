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

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Login_nickname (9)'), 'katalon')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Pense Bet/input_Login_password (9)'), 'o+tS4OuGt32s9ezZj287yw==')

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/button_Login (9)'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/a_Waiting (2)'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/span_katalon'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/a_Create new bet (3)'))

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Bet subject_subject (3)'), 'sdfgdhgf')

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Amount_amount (3)'), '5')

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/button_Register Bet (3)'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/button_Ignore bet'))

WebUI.closeBrowser()

