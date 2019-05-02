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

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Login_nickname (5)'), 'katalon')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Pense Bet/input_Login_password (5)'), 'o+tS4OuGt32s9ezZj287yw==')

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/button_Login (5)'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/a_katalon (1)'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/a_Create new bet (2)'))

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Bet subject_subject (2)'), 'test1')

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/div_Amount_input-group col-12 (1)'))

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Amount_amount (2)'), '5')

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/button_Register Bet (2)'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/button_Agree'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/a_Active (1)'))

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/h2_test1'))

