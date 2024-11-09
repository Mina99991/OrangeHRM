package stepdefinitions;

import Common.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.AdminPage;

public class addAndDeleteUserStepDef extends Hooks {
    private DashboardPage home;
    private AdminPage dashboard;

    @Before
    public void startDriver() {

            startDriverSession();
            home = new DashboardPage(driver);
            dashboard = new AdminPage(driver);


    }

    @After
    public void tearDown() {
        tearDownDriver();
    }


    @Given("the user navigates to orange hrm website")
    public void theUserNavigatesToOrangeHRMWebsite() {
        home.checkLoginScreen();
    }
    @When("the user logs in with valid credentials {string} and {string}")
    public void theUserLogsInWithValidCredentials(String username, String password) {
        home.checkLoginSuccessfully(username, password);
    }


    @Then("the dashboard should be displayed successfully")
    public void theDashboardShouldBeDisplayedSuccessfully() {
        home.checkDashboardDisplayed();
    }

    @And("the user navigates to the admin page")
    public void theUserNavigatesToTheAdminPage() {
        dashboard.checkRecordsScreen();
    }

    @When("the user creates a record with valid data and existing employee Name {string}")
    public void theUserCreatesARecordWithValidDataAndExistingEmployeeName(String employeeName) {
        dashboard.checkRecordCreation(employeeName);

    }

    @Then("the record should be created successfully")
    public void theRecordShouldBeCreatedSuccessfully() {
        dashboard.validateRecordCreatedSuccessfully();

    }

    @And("the user attempts to delete the record")
    public void theUserAttemptsToDeleteTheRecode() {
        dashboard.validateDeleteRecordsFunctionality();
    }

    @Then("the record should be deleted successfully")
    public void theRecordShouldBeDeletedSuccessfully() {
        dashboard.validateDeletingSuccessfully();

    }

}
