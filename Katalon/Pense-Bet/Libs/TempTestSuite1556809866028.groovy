import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/Pense-Bet')

suiteProperties.put('name', 'Pense-Bet')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())



RunConfiguration.setExecutionSettingFile("D:\\dev\\JEE\\Pense-Bet\\Katalon\\Pense-Bet\\Reports\\Pense-Bet\\20190502_171106\\execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/Pense-Bet', suiteProperties, [new TestCaseBinding('Test Cases/UserCreation', 'Test Cases/UserCreation',  null), new TestCaseBinding('Test Cases/UserCreationFailPassword', 'Test Cases/UserCreationFailPassword',  null), new TestCaseBinding('Test Cases/UserCreationFailEmail', 'Test Cases/UserCreationFailEmail',  null), new TestCaseBinding('Test Cases/UserCanConnect', 'Test Cases/UserCanConnect',  null), new TestCaseBinding('Test Cases/UserCannotConnect', 'Test Cases/UserCannotConnect',  null), new TestCaseBinding('Test Cases/UserCanCreateBet', 'Test Cases/UserCanCreateBet',  null), new TestCaseBinding('Test Cases/UserCreateBetFailSubject', 'Test Cases/UserCreateBetFailSubject',  null), new TestCaseBinding('Test Cases/UserCanIgnoreBet', 'Test Cases/UserCanIgnoreBet',  null), new TestCaseBinding('Test Cases/UserCanParticipateBet', 'Test Cases/UserCanParticipateBet',  null), new TestCaseBinding('Test Cases/UserCreateBetFailDate', 'Test Cases/UserCreateBetFailDate',  null), new TestCaseBinding('Test Cases/UserCreation', 'Test Cases/UserCreation',  null)])
