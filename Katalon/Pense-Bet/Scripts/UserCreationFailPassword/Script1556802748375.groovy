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

WebUI.navigateToUrl('http://localhost:8080/register')

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Register_email (1)'), 'katalon2@test.ch')

WebUI.setText(findTestObject('Object Repository/Page_Pense Bet/input_Register_nickname (1)'), 'katalon2')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Pense Bet/input_Register_password (1)'), 'o+tS4OuGt32s9ezZj287yw==')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Pense Bet/input_Register_passwordConfirmation (1)'), 'P9ET2sDE0SE=')

WebUI.click(findTestObject('Object Repository/Page_Pense Bet/button_Register (1)'))

WebUI.verifyElementPresent(findTestObject('Page_Pense Bet/label_The password and its confirmation doesnt match retry'), 
    0)

