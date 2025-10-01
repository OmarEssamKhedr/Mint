package tests;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AccountMenuPage;
import pages.Personal_InformationPage;
import testdata.TestData;
import utils.AllureUtils;
import utils.AssertUtils;

/**
 * Simple Personal Information Tests - Name Editing
 *
 * @author Ciye Test Team
 */
@Epic("Ciye Mobile App Testing")
@Feature("User Profile Management")
public class Personal_InformationTest extends BaseTest {

    // ==================== TEST 1: ACCESS PERSONAL INFO PAGE ====================

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify user can access personal information page")
    @Story("Profile Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User navigates to personal information page and verifies all fields are visible")
    @TmsLink("TC-PI-001")
    public void testAccessPersonalInformationPage() {
        AllureUtils.step("Starting test: Access Personal Information Page");

        // Login
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        // Open profile menu
        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();

        // Navigate to Personal Information
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Verify page loaded
        AssertUtils.assertTrue(personalInfoPage.isPersonalInformationPageDisplayed(),
                "Personal Information page should be displayed");

        AllureUtils.step("✅ Test passed: Personal Information page is accessible");
    }

    // ==================== TEST 2: VERIFY DATA DISPLAY ====================

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify personal information displays correct data")
    @Story("Profile Data Display")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates that name, birthday, and gender are displayed correctly")
    @TmsLink("TC-PI-002")
    public void testPersonalInformationDataDisplay() {
        AllureUtils.step("Starting test: Verify Data Display");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Get user data
        String fullName = personalInfoPage.getUserFullName();
        String birthday = personalInfoPage.getUserBirthday();
        String gender = personalInfoPage.getUserGender();

        // Log the data
        AllureUtils.addParameter("Full Name", fullName);
        AllureUtils.addParameter("Birthday", birthday);
        AllureUtils.addParameter("Gender", gender);

        // Verify data is not empty
        AssertUtils.assertTrue(!fullName.isEmpty(), "Name should not be empty");
        AssertUtils.assertTrue(!birthday.isEmpty(), "Birthday should not be empty");
        AssertUtils.assertTrue(!gender.isEmpty(), "Gender should not be empty");

        AllureUtils.step("✅ Test passed: All data displayed correctly");
    }


    // ==================== TEST 3: ACCESS NAME EDIT PAGE ====================

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify user can access name editing page")
    @Story("Name Editing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User clicks on name field to open the edit page")
    @TmsLink("TC-PI-003")
    public void testAccessNameEditPage() {
        AllureUtils.step("Starting test: Access Name Edit Page");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Click name field
        personalInfoPage.clickUserName();

        // Verify edit page opened
        AssertUtils.assertTrue(personalInfoPage.isFirstandLastnamepageDisplayed(),
                "Name edit page should be displayed");

        AllureUtils.step("✅ Test passed: Name edit page is accessible");
    }

    // ==================== TEST 4: EDIT FIRST NAME ====================

    @Test(priority = TestData.TestPriority.CRITICAL,
            description = "Verify user can edit first name successfully")
    @Story("Name Editing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User updates first name and verifies the change is saved")
    @TmsLink("TC-PI-004")
    public void testEditFirstName() {
        AllureUtils.step("Starting test: Edit First Name");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Store original name
        String originalName = personalInfoPage.getUserFullName();
        String originalFirst = personalInfoPage.getFirstName();

        AllureUtils.addParameter("Original Name", originalName);

        // Edit first name
        personalInfoPage.clickUserName();
        String newFirstName = "TestFirst";
        personalInfoPage.updateFirstName(newFirstName);
        personalInfoPage.clickConfirm();

        // Verify change
        String updatedName = personalInfoPage.getUserFullName();
        AllureUtils.addParameter("Updated Name", updatedName);
        AssertUtils.assertTrue(updatedName.contains(newFirstName),
                "Name should contain new first name: " + newFirstName);

        // Restore original
        personalInfoPage.clickUserName();
        personalInfoPage.updateFirstName(originalFirst);
        personalInfoPage.clickConfirm();

        AllureUtils.step("✅ Test passed: First name edited successfully");
    }

    // ==================== TEST 5: EDIT LAST NAME ====================

    @Test(priority = TestData.TestPriority.CRITICAL,
            description = "Verify user can edit last name successfully")
    @Story("Name Editing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User updates last name and verifies the change is saved")
    @TmsLink("TC-PI-005")
    public void testEditLastName() {
        AllureUtils.step("Starting test: Edit Last Name");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Store original name
        String originalFirst = personalInfoPage.getFirstName();
        String originalLast = personalInfoPage.getLastName();

        // Edit last name
        personalInfoPage.clickUserName();
        String newLastName = "TestLast";
        personalInfoPage.updateLastName(newLastName);
        personalInfoPage.clickConfirm();

        // Verify change
        String updatedName = personalInfoPage.getUserFullName();
        AllureUtils.addParameter("Updated Name", updatedName);
        AssertUtils.assertTrue(updatedName.contains(newLastName),
                "Name should contain new last name: " + newLastName);

        // Restore original
        personalInfoPage.clickUserName();
        personalInfoPage.updateLastName(originalLast);
        personalInfoPage.clickConfirm();

        AllureUtils.step("✅ Test passed: Last name edited successfully");
    }

    // ==================== TEST 6: EDIT BOTH NAMES ====================

    @Test(priority = TestData.TestPriority.CRITICAL,
            description = "Verify user can edit both names at once")
    @Story("Name Editing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User updates both first and last name together")
    @TmsLink("TC-PI-006")
    public void testEditBothNames() {
        AllureUtils.step("Starting test: Edit Both Names");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Store original name
        String originalFirst = personalInfoPage.getFirstName();
        String originalLast = personalInfoPage.getLastName();

        // Edit both names
        personalInfoPage.clickUserName();
        String newFirst = "NewFirst";
        String newLast = "NewLast";
        personalInfoPage.updateFullName(newFirst, newLast);
        personalInfoPage.clickConfirm();

        // Verify changes
        String updatedName = personalInfoPage.getUserFullName();
        AllureUtils.addParameter("Updated Name", updatedName);
        AssertUtils.assertTrue(updatedName.contains(newFirst) && updatedName.contains(newLast),
                "Name should contain both new names");

        // Restore original
        personalInfoPage.clickUserName();
        personalInfoPage.updateFullName(originalFirst, originalLast);
        personalInfoPage.clickConfirm();

        AllureUtils.step("✅ Test passed: Both names edited successfully");
    }

    // ==================== TEST 7: CANCEL NAME EDIT ====================

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify user can cancel name editing")
    @Story("Name Editing")
    @Severity(SeverityLevel.NORMAL)
    @Description("User starts editing but cancels without saving")
    @TmsLink("TC-PI-007")
    public void testCancelNameEdit() {
        AllureUtils.step("Starting test: Cancel Name Edit");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Store original name
        String originalName = personalInfoPage.getUserFullName();

        // Start editing but cancel
        personalInfoPage.clickUserName();
        personalInfoPage.updateFullName("TempFirst", "TempLast");
        personalInfoPage.navigateBackFromNamePage();

        // Verify name unchanged
        String currentName = personalInfoPage.getUserFullName();
        AssertUtils.assertEquals(currentName, originalName,
                "Name should remain unchanged after cancel");

        AllureUtils.step("✅ Test passed: Cancel works correctly");
    }

    // ==================== TEST 8: CLEAR FIRST NAME ====================

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify clearing first name field behavior")
    @Story("Name Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Tests what happens when first name is cleared")
    @TmsLink("TC-PI-008")
    public void testClearFirstName() {
        AllureUtils.step("Starting test: Clear First Name");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Open edit page and clear first name
        personalInfoPage.clickUserName();
        personalInfoPage.clearFirstName();

        // Cancel without saving
        personalInfoPage.navigateBackFromNamePage();

        // Verify we're back
        AssertUtils.assertTrue(personalInfoPage.isPersonalInformationPageDisplayed(),
                "Should return to personal info page");

        AllureUtils.step("✅ Test passed: Clear first name handled");
    }

    // ==================== TEST 9: MULTIPLE EDITS ====================

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify multiple consecutive name edits")
    @Story("Name Editing")
    @Severity(SeverityLevel.NORMAL)
    @Description("User performs multiple name updates in sequence")
    @TmsLink("TC-PI-009")
    public void testMultipleNameEdits() {
        AllureUtils.step("Starting test: Multiple Name Edits");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Store original
        String originalFirst = personalInfoPage.getFirstName();
        String originalLast = personalInfoPage.getLastName();

        // First edit
        personalInfoPage.clickUserName();
        personalInfoPage.updateFullName("Edit1", "Test1");
        personalInfoPage.clickConfirm();

        // Second edit
        personalInfoPage.clickUserName();
        personalInfoPage.updateFullName("Edit2", "Test2");
        personalInfoPage.clickConfirm();

        // Restore
        personalInfoPage.clickUserName();
        personalInfoPage.updateFullName(originalFirst, originalLast);
        personalInfoPage.clickConfirm();

        // Verify restored
        String finalName = personalInfoPage.getUserFullName();
        AssertUtils.assertTrue(finalName.contains(originalFirst),
                "Should have original name after multiple edits");

        AllureUtils.step("✅ Test passed: Multiple edits work correctly");
    }

    // ==================== TEST 10: EMPTY FIRST NAME VALIDATION ====================

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify validation error when first name is empty")
    @Story("Name Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User clears first name and attempts to save, validation error should appear")
    @TmsLink("TC-PI-010")
    public void testEmptyFirstNameValidation() {
        AllureUtils.step("Starting test: Empty First Name Validation");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Open edit page and clear first name
        personalInfoPage.clickUserName();
        personalInfoPage.clearFirstName();

        // Keep last name filled, only first name is empty
        personalInfoPage.clickConfirm();

        // Verify validation error appears
        AssertUtils.assertTrue(personalInfoPage.isEmptyFieldsErrorDisplayed(),
                "Validation error should appear when first name is empty");

        String errorMessage = personalInfoPage.getValidationErrorMessage();
        AllureUtils.addParameter("Error Message", errorMessage);
        AssertUtils.assertTrue(errorMessage.contains("cannot be empty"),
                "Error message should indicate fields cannot be empty");

        AllureUtils.step("Test passed: Empty first name validation works correctly");
    }

// ==================== TEST 11: EMPTY LAST NAME VALIDATION ====================

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify validation error when last name is empty")
    @Story("Name Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User clears last name and attempts to save, validation error should appear")
    @TmsLink("TC-PI-011")
    public void testEmptyLastNameValidation() {
        AllureUtils.step("Starting test: Empty Last Name Validation");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Open edit page and clear last name
        personalInfoPage.clickUserName();
        personalInfoPage.clearLastName();

        // Keep first name filled, only last name is empty
        personalInfoPage.clickConfirm();

        // Verify validation error appears
        AssertUtils.assertTrue(personalInfoPage.isEmptyFieldsErrorDisplayed(),
                "Validation error should appear when last name is empty");

        String errorMessage = personalInfoPage.getValidationErrorMessage();
        AllureUtils.addParameter("Error Message", errorMessage);
        AssertUtils.assertTrue(errorMessage.contains("cannot be empty"),
                "Error message should indicate fields cannot be empty");

        AllureUtils.step("Test passed: Empty last name validation works correctly");
    }

// ==================== TEST 12: BOTH FIELDS EMPTY VALIDATION ====================

    @Test(priority = TestData.TestPriority.CRITICAL,
            description = "Verify validation error when both name fields are empty")
    @Story("Name Validation")
    @Severity(SeverityLevel.BLOCKER)
    @Description("User clears both first and last name and attempts to save, validation error should appear")
    @TmsLink("TC-PI-012")
    public void testBothFieldsEmptyValidation() {
        AllureUtils.step("Starting test: Both Fields Empty Validation");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Open edit page and clear both fields
        personalInfoPage.clickUserName();
        personalInfoPage.clearBothNames();
        personalInfoPage.clickConfirm();

        // Verify validation error appears
        AssertUtils.assertTrue(personalInfoPage.isEmptyFieldsErrorDisplayed(),
                "Validation error should appear when both fields are empty");

        String errorMessage = personalInfoPage.getValidationErrorMessage();
        AllureUtils.addParameter("Error Message", errorMessage);
        AssertUtils.assertTrue(errorMessage.contains("first and last name"),
                "Error message should mention both first and last name");
        AssertUtils.assertTrue(errorMessage.contains("cannot be empty"),
                "Error message should indicate fields cannot be empty");

        AllureUtils.step("Test passed: Both fields empty validation works correctly");
    }
// ==================== TEST 13: ACCESS YEAR PICKER ====================

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify user can access year picker")
    @Story("Birthday Editing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User clicks on birthday field to open the year picker wheel")
    @TmsLink("TC-PI-013")
    public void testAccessYearPicker() {
        AllureUtils.step("Starting test: Access Year Picker");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Click birthday field
        personalInfoPage.clickBirthdayField();

        // Verify year picker opened
        AssertUtils.assertTrue(personalInfoPage.isYearPickerDisplayed(),
                "Year picker should be displayed");

        AllureUtils.step("Test passed: Year picker is accessible");
    }

// ==================== TEST 14: SELECT COMPLETE BIRTHDAY ====================

    @Test(priority = TestData.TestPriority.CRITICAL,
            description = "Verify user can select complete birthday (year, month, day)")
    @Story("Birthday Editing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User selects year, month, and day through the three-step picker process")
    @TmsLink("TC-PI-014")
    public void testSelectCompleteBirthday() {
        AllureUtils.step("Starting test: Select Complete Birthday");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Store original birthday
        String originalBirthday = personalInfoPage.getUserBirthday();
        AllureUtils.addParameter("Original Birthday", originalBirthday);

        // Open birthday picker
        personalInfoPage.clickBirthdayField();

        // Complete birthday selection
        String year = "1995";
        String month = "6";
        String day = "15";

        personalInfoPage.selectCompleteBirthday(year, month, day);

        // Verify back on personal info page
        AssertUtils.assertTrue(personalInfoPage.isPersonalInformationPageDisplayed(),
                "Should return to personal information page");

        // Verify birthday changed
        String updatedBirthday = personalInfoPage.getUserBirthday();
        AllureUtils.addParameter("Updated Birthday", updatedBirthday);
        AssertUtils.assertNotEquals(updatedBirthday, originalBirthday,
                "Birthday should be updated");

        AllureUtils.step("Test passed: Complete birthday selected successfully");
    }

// ==================== TEST 15: CANCEL YEAR SELECTION ====================

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify user can cancel year selection")
    @Story("Birthday Editing")
    @Severity(SeverityLevel.NORMAL)
    @Description("User opens year picker but navigates back without selecting")
    @TmsLink("TC-PI-015")
    public void testCancelYearSelection() {
        AllureUtils.step("Starting test: Cancel Year Selection");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Store original birthday
        String originalBirthday = personalInfoPage.getUserBirthday();
        AllureUtils.addParameter("Original Birthday", originalBirthday);

        // Open picker and cancel
        personalInfoPage.clickBirthdayField();
        personalInfoPage.selectBirthYear("2000");
        personalInfoPage.navigateBackFromYearPicker();

        // Verify birthday unchanged
        String currentBirthday = personalInfoPage.getUserBirthday();
        AssertUtils.assertEquals(currentBirthday, originalBirthday,
                "Birthday should remain unchanged after canceling");

        AllureUtils.step("Test passed: Cancel year selection works correctly");
    }

// ==================== TEST 16: CANCEL MONTH SELECTION ====================

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify user can cancel month selection")
    @Story("Birthday Editing")
    @Severity(SeverityLevel.NORMAL)
    @Description("User selects year, opens month picker but cancels")
    @TmsLink("TC-PI-016")
    public void testCancelMonthSelection() {
        AllureUtils.step("Starting test: Cancel Month Selection");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Store original birthday
        String originalBirthday = personalInfoPage.getUserBirthday();

        // Open picker, select year, then cancel month
        personalInfoPage.clickBirthdayField();
        personalInfoPage.selectBirthYear("1998");
        personalInfoPage.confirmYearSelection();

        // Now on month picker
        AssertUtils.assertTrue(personalInfoPage.isMonthPickerDisplayed(),
                "Month picker should be displayed");

        personalInfoPage.selectBirthMonth("3");
        personalInfoPage.navigateBackFromMonthPicker();

        // Verify birthday unchanged
        String currentBirthday = personalInfoPage.getUserBirthday();
        AssertUtils.assertEquals(currentBirthday, originalBirthday,
                "Birthday should remain unchanged");

        AllureUtils.step("Test passed: Cancel month selection works correctly");
    }
    // ==================== TEST 17: CANCEL DAY SELECTION ====================

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify user can cancel day selection")
    @Story("Birthday Editing")
    @Severity(SeverityLevel.NORMAL)
    @Description("User selects year and month, opens day picker but cancels before confirming")
    @TmsLink("TC-PI-017")
    public void testCancelDaySelection() {
        AllureUtils.step("Starting test: Cancel Day Selection");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        // Store original birthday
        String originalBirthday = personalInfoPage.getUserBirthday();
        AllureUtils.addParameter("Original Birthday", originalBirthday);

        // Open picker and go through year and month
        personalInfoPage.clickBirthdayField();
        personalInfoPage.selectBirthYear("2000");
        personalInfoPage.confirmYearSelection();

        personalInfoPage.selectBirthMonth("5");
        personalInfoPage.confirmMonthSelection();

        // Now on day picker - select but cancel
        AssertUtils.assertTrue(personalInfoPage.isDayPickerDisplayed(),
                "Day picker should be displayed");

        personalInfoPage.selectBirthDay("20");
        personalInfoPage.navigateBackFromDayPicker();

        // Verify birthday unchanged
        String currentBirthday = personalInfoPage.getUserBirthday();
        AssertUtils.assertEquals(currentBirthday, originalBirthday,
                "Birthday should remain unchanged after canceling day selection");

        AllureUtils.step("Test passed: Cancel day selection works correctly");
    }

// ==================== TEST 18: VALIDATE MINIMUM AGE (2007) ====================

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify user cannot select birth year 2008 or later (must be 18+)")
    @Story("Birthday Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test validates age restriction - users born in 2008 or later should not be able to register")
    @TmsLink("TC-PI-018")
    public void testMinimumAgeValidation() {
        AllureUtils.step("Starting test: Minimum Age Validation (Cannot be younger than 2007)");

        // Login and navigate
        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        String originalBirthday = personalInfoPage.getUserBirthday();
        AllureUtils.addParameter("Original Birthday", originalBirthday);

        // Open picker
        personalInfoPage.clickBirthdayField();

        // Check minimum available year
        String minimumYear = personalInfoPage.checkMinimumAvailableYear();
        AllureUtils.addParameter("Minimum Year Available", minimumYear);

        // Verify it's 2007 (not 2008)
        AssertUtils.assertEquals(minimumYear, "2007",
                "Minimum available year should be 2007 (18+ age requirement)");

        // Cancel without making changes
        personalInfoPage.navigateBackFromYearPicker();

        AllureUtils.step("Test passed: Minimum age validation enforced - users must be born in 2007 or earlier");
    }
    // ==================== TEST 19: ACCESS GENDER PICKER ====================

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify user can access gender picker")
    @Story("Gender Editing")
    @Severity(SeverityLevel.NORMAL)
    @Description("User clicks on gender field to open the gender selection page")
    @TmsLink("TC-PI-019")
    public void testAccessGenderPicker() {
        AllureUtils.step("Starting test: Access Gender Picker");

        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        personalInfoPage.clickGenderField();

        AssertUtils.assertTrue(personalInfoPage.isGenderPickerDisplayed(),
                "Gender picker should be displayed");

        AllureUtils.step("Test passed: Gender picker is accessible");
    }

// ==================== TEST 20: SELECT GENDER ====================

    @Test(priority = TestData.TestPriority.HIGH,
            description = "Verify user can select gender")
    @Story("Gender Editing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User selects a gender option and confirms selection")
    @TmsLink("TC-PI-020")
    public void testSelectGender() {
        AllureUtils.step("Starting test: Select Gender");

        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        String originalGender = personalInfoPage.getUserGender();
        AllureUtils.addParameter("Original Gender", originalGender);

        personalInfoPage.clickGenderField();
        personalInfoPage.selectGender("Female");

        String updatedGender = personalInfoPage.getUserGender();
        AllureUtils.addParameter("Updated Gender", updatedGender);

        AssertUtils.assertTrue(updatedGender.contains("Female"),
                "Gender should be updated to Female");

        // Restore original gender
        personalInfoPage.clickGenderField();
        personalInfoPage.selectGender(originalGender);

        AllureUtils.step("Test passed: Gender selection works correctly");
    }

// ==================== TEST 21: CANCEL GENDER SELECTION ====================

    @Test(priority = TestData.TestPriority.MEDIUM,
            description = "Verify user can cancel gender selection")
    @Story("Gender Editing")
    @Severity(SeverityLevel.NORMAL)
    @Description("User opens gender picker but cancels without selecting")
    @TmsLink("TC-PI-021")
    public void testCancelGenderSelection() {
        AllureUtils.step("Starting test: Cancel Gender Selection");

        LoginPage loginPage = welcomePage.navigateToLogin();
        loginPage.loginWithValidCredentials();

        AccountMenuPage accountMenuPage = new AccountMenuPage();
        accountMenuPage.clickValidUserProfile();
        Personal_InformationPage personalInfoPage = accountMenuPage.clickPersonalInformation();

        String originalGender = personalInfoPage.getUserGender();
        AllureUtils.addParameter("Original Gender", originalGender);

        personalInfoPage.clickGenderField();
        personalInfoPage.selectGenderMale(); // Select but don't confirm
        personalInfoPage.navigateBackFromGenderPicker();

        String currentGender = personalInfoPage.getUserGender();
        AssertUtils.assertEquals(currentGender, originalGender,
                "Gender should remain unchanged after cancel");

        AllureUtils.step("Test passed: Cancel gender selection works correctly");
    }
}