package pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.AllureUtils;
import utils.LogUtils;
import utils.WaitUtils;
import java.util.Map;
import static driver.DriverManager.getInstance;

/**
 * Personal Information Page Object
 * Contains elements and methods for the Personal Information/Profile screen
 *
 * @author Ciye Test Team
 */
public class Personal_InformationPage extends BasePage {

    //Title
    private final By personalInfoHeader = AppiumBy.androidUIAutomator("new UiSelector().text(\"PERSONAL INFORMATION\")");

    // Profile Info Fields
    private final By userProfileName = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").descriptionContains(\"Name,\")");
    private final By userBirthdayField = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").descriptionContains(\"Birthday,\")");
    private final By userGenderField = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").descriptionContains(\"Gender,\")");

    // Profile Photo Elements
    private final By profilePictureWithPhoto = AppiumBy.accessibilityId("Edit photo");
    private final By profilePictureWithoutPhoto = AppiumBy.androidUIAutomator("new UiSelector().descriptionMatches(\".+,\\s*Edit photo\")");
    private final By profilePictureEditButton = AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"Edit photo\")");

    // Action Buttons
    private final By logoutButton = AppiumBy.xpath("//android.widget.TextView[@text='Log out']/parent::android.view.ViewGroup");
    private final By deleteAccountButton = AppiumBy.xpath("//android.widget.TextView[@text='Delete account']/parent::android.view.ViewGroup");
    private final By backButton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").clickable(true).bounds(0,165,144,319)");

    //First & Last name page
    private final By Names_firstNameField = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.EditText\").instance(0)"
    );
    private final By Names_lastNameField = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.EditText\").instance(1)"
    );
    private final By Names_confirmButton = AppiumBy.xpath(
            "//android.widget.TextView[@text='Confirm']/parent::android.view.ViewGroup"
    );
    private final By Names_backButton = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.view.ViewGroup\").clickable(true).index(0)"
    );
    private final By Names_pageTitle = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Enter your first and last name.\")"
    );
    private final By Names_emptyFieldsError = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Please enter your first and last name. These fields cannot be empty.\")"
    );

    // ==================== BIRTHDAY PICKER LOCATORS ====================

    // Year Picker Elements
    private final By birthdayYearPageTitle = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"TELL US YOUR YEAR OF BIRTH\")"
    );

    private final By birthdayYearConfirmButton = AppiumBy.xpath(
            "//android.widget.TextView[@text='Confirm']/parent::android.view.ViewGroup"
    );

    // Month Picker Elements
    private final By birthdayMonthPageTitle = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"TELL US YOUR MONTH OF BIRTH\")"
    );

    private final By birthdayMonthConfirmButton = AppiumBy.xpath(
            "//android.widget.TextView[@text='Confirm']/parent::android.view.ViewGroup"
    );

    // Day Picker Elements
    private final By birthdayDayPageTitle = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"TELL US YOUR DAY OF BIRTH\")"
    );

    private final By birthdayDayConfirmButton = AppiumBy.xpath(
            "//android.widget.TextView[@text='Confirm']/parent::android.view.ViewGroup"
    );

    // ==================== Gender PICKER LOCATORS ====================

    private final By genderPageTitle = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"TELL US YOUR GENDER\")"
    );

    private final By genderFemaleOption = AppiumBy.accessibilityId("Female");

    private final By genderMaleOption = AppiumBy.accessibilityId("Male");

    private final By genderPreferNotToSayOption = AppiumBy.accessibilityId("Prefer Not to Say");

    private final By genderConfirmButton = AppiumBy.xpath(
            "//android.widget.TextView[@text='Confirm']/parent::android.view.ViewGroup"
    );

    /**
     * Check if personal information page is displayed
     */
    @Step("Verify personal information page is displayed")
    public boolean isPersonalInformationPageDisplayed() {
        AllureUtils.step("Checking if personal information page is displayed");
        boolean isDisplayed = isElementDisplayed(personalInfoHeader) && isElementDisplayed(profilePictureEditButton);
        LogUtils.info("Personal information page displayed: " + isDisplayed);
        return isDisplayed;
    }

    /**
     * Click profile picture to edit
     */
    @Step("Click profile picture to edit")
    public Personal_InformationPage clickEditProfilePicture() {
        AllureUtils.step("Tapping profile picture to edit");
        clickElement(profilePictureEditButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Click user name field
     */
    @Step("Click user name field")
    public Personal_InformationPage clickUserName() {
        AllureUtils.step("Tapping user name field");
        clickElement(userProfileName);
        waitForPageLoad();
        return this;
    }

    /**
     * Click birthday field
     */
    @Step("Click birthday field")
    public Personal_InformationPage clickBirthdayField() {
        AllureUtils.step("Tapping birthday field");
        clickElement(userBirthdayField);
        waitForPageLoad();
        return this;
    }

    /**
     * Click gender field
     */
    @Step("Click gender field")
    public Personal_InformationPage clickGenderField() {
        AllureUtils.step("Tapping gender field");
        clickElement(userGenderField);
        waitForPageLoad();
        return this;
    }

    /**
     * Click logout button
     */
    @Step("Click logout button")
    public WelcomePage clickLogout() {
        AllureUtils.step("Tapping logout button");
        clickElement(logoutButton);
        waitForPageLoad();
        return new WelcomePage();
    }

    /**
     * Click delete account button
     */
    @Step("Click delete account button")
    public Personal_InformationPage clickDeleteAccount() {
        AllureUtils.step("Tapping delete account button");
        clickElement(deleteAccountButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Click back button
     */
    @Step("Navigate back to previous page")
    public Personal_InformationPage clickBack() {
        AllureUtils.step("Tapping back button");
        clickElement(backButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Check if user has profile photo
     */
    @Step("Check if user has profile photo")
    public boolean hasProfilePhoto() {
        AllureUtils.step("Checking if user has a profile photo");
        try {
            return WaitUtils.isElementPresent(profilePictureWithPhoto);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if showing default initials (no photo)
     */
    @Step("Check if showing default user initials")
    public boolean hasDefaultInitials() {
        AllureUtils.step("Checking if showing default user initials");
        try {
            return WaitUtils.isElementPresent(profilePictureWithoutPhoto);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get user name text
     */
    @Step("Get user name from profile")
    public String getUserFullName() {
        AllureUtils.step("Getting user name from profile field");
        try {
            String contentDesc = getInstance().getDriver()
                    .findElement(userProfileName)
                    .getAttribute("content-desc");

            LogUtils.info("Raw content-desc for name: " + contentDesc);

            if (contentDesc != null && contentDesc.contains(",")) {
                String[] parts = contentDesc.split(",", 2);
                String fullName = parts[1].trim();
                LogUtils.info("Retrieved user name: " + fullName);
                return fullName;
            }

            return "";
        } catch (Exception e) {
            LogUtils.error("Failed to get user name: " + e.getMessage());
            return "";
        }
    }

    /**
     * Get user birthday text
     */
    @Step("Get user birthday from profile")
    public String getUserBirthday() {
        AllureUtils.step("Getting user birthday from profile field");
        try {
            String contentDesc = getInstance().getDriver()
                    .findElement(userBirthdayField)
                    .getAttribute("content-desc");

            LogUtils.info("Raw content-desc for birthday: " + contentDesc);

            if (contentDesc != null && contentDesc.contains(",")) {
                String[] parts = contentDesc.split(",", 2);
                String birthday = parts[1].trim();
                LogUtils.info("Retrieved birthday: " + birthday);
                return birthday;
            }

            return "";
        } catch (Exception e) {
            LogUtils.error("Failed to get birthday: " + e.getMessage());
            return "";
        }
    }


    /**
     * Get user gender text
     */
    @Step("Get user gender from profile")
    public String getUserGender() {
        AllureUtils.step("Getting user gender from profile field");
        try {
            String contentDesc = getInstance().getDriver()
                    .findElement(userGenderField)
                    .getAttribute("content-desc");

            LogUtils.info("Raw content-desc for gender: " + contentDesc);

            if (contentDesc != null && contentDesc.contains(",")) {
                String[] parts = contentDesc.split(",", 2);
                String gender = parts[1].trim();
                LogUtils.info("Retrieved gender: " + gender);
                return gender;
            }

            return "";
        } catch (Exception e) {
            LogUtils.error("Failed to get gender: " + e.getMessage());
            return "";
        }
    }


    /**
     * Verify all personal information fields are present
     */
    @Step("Verify all personal information fields are present")
    public boolean areAllFieldsPresent() {
        AllureUtils.step("Checking if all personal information fields are present");
        boolean namePresent = isElementPresent(userProfileName);
        boolean birthdayPresent = isElementPresent(userBirthdayField);
        boolean genderPresent = isElementPresent(userGenderField);

        LogUtils.info("Name field present: " + namePresent);
        LogUtils.info("Birthday field present: " + birthdayPresent);
        LogUtils.info("Gender field present: " + genderPresent);

        return namePresent && birthdayPresent && genderPresent;
    }

    /**
     * Perform logout action with confirmation
     */
    @Step("Perform logout with confirmation")
    public WelcomePage performLogout() {
        AllureUtils.step("Performing logout action");
        return clickLogout();
    }

    /**
     * Navigate back from personal information page
     */
    @Step("Navigate back from personal information page")
    public Personal_InformationPage goBack() {
        AllureUtils.step("Navigating back from personal information page");
        return clickBack();
    }
    /**
     * ///////////////////////////////////////////////////////////////////////////////////////
     * First and Last name page
     */

    //Open Page
    // Try this simpler approach - just verify the page title
    @Step("Verify First and Last name page is displayed")
    public boolean isFirstandLastnamepageDisplayed() {
        AllureUtils.step("Verify First and Last name page is displayed");
        waitForPageLoad(); // Give page time to fully load
        boolean isDisplayed = isElementDisplayed(Names_pageTitle);
        LogUtils.info("First and Last name page displayed: " + isDisplayed);
        return isDisplayed;
    }
    // Back Button
    @Step("Navigate back from First and Last name page")
    public Personal_InformationPage navigateBackFromNamePage() {
        AllureUtils.step("Navigating back from First and Last name page");
        clickElement(Names_backButton);
        waitForPageLoad();
        return new Personal_InformationPage();
    }
    /**
     * Navigate back from birthday picker without confirming
     */
    @Step("Navigate back from birthday picker")
    public Personal_InformationPage navigateBackFromBirthdayPicker() {
        AllureUtils.step("Navigating back from birthday picker without confirming");
        navigateBack(); // This works because we're inside BasePage subclass
        waitForPageLoad();
        return this;
    }

    @Step("Get first name from profile")
    public String getFirstName() {
        String fullName = getUserFullName();
        if (!fullName.isEmpty() && fullName.contains(" ")) {
            return fullName.split(" ")[0];
        }
        return fullName;
    }

    @Step("Get last name from profile")
    public String getLastName() {
        String fullName = getUserFullName();
        if (!fullName.isEmpty() && fullName.contains(" ")) {
            String[] parts = fullName.split(" ");
            return parts[parts.length - 1];
        }
        return "";
    }

    // First Name Methods
    @Step("Clear first name field")
    public Personal_InformationPage clearFirstName() {
        AllureUtils.step("Clearing first name field");
        waitForPageLoad(); // Wait for keyboard and fields to be ready

        WebElement firstNameElement = WaitUtils.waitForElementPresent(Names_firstNameField);
        firstNameElement.click(); // Focus the field
        firstNameElement.clear(); // Clear existing text

        LogUtils.info("First name field cleared");
        return this;
    }

    @Step("Enter first name: {firstName}")
    public Personal_InformationPage enterFirstName(String firstName) {
        AllureUtils.step("Entering first name: " + firstName);

        WebElement firstNameElement = WaitUtils.waitForElementPresent(Names_firstNameField);
        firstNameElement.sendKeys(firstName);

        LogUtils.info("Entered first name: " + firstName);
        return this;
    }

    @Step("Update first name to: {newFirstName}")
    public Personal_InformationPage updateFirstName(String newFirstName) {
        AllureUtils.step("Updating first name to: " + newFirstName);
        clearFirstName();
        enterFirstName(newFirstName);
        return this;
    }

    // Last Name Methods
    @Step("Clear last name field")
    public Personal_InformationPage clearLastName() {
        AllureUtils.step("Clearing last name field");
        waitForPageLoad();

        WebElement element = findElement(Names_lastNameField);
        element.clear();

        return this;
    }

    @Step("Enter last name: {lastName}")
    public Personal_InformationPage enterLastName(String lastName) {
        AllureUtils.step("Entering last name: " + lastName);

        WebElement element = findElement(Names_lastNameField);
        element.sendKeys(lastName);

        return this;
    }

    @Step("Update last name to: {newLastName}")
    public Personal_InformationPage updateLastName(String newLastName) {
        AllureUtils.step("Updating last name to: " + newLastName);
        clearLastName();
        enterLastName(newLastName);
        return this;
    }

    // Combined Methods
    @Step("Update both first and last name")
    public Personal_InformationPage updateFullName(String firstName, String lastName) {
        AllureUtils.step("Updating full name - First: " + firstName + ", Last: " + lastName);
        updateFirstName(firstName);
        updateLastName(lastName);
        return this;
    }

    @Step("Clear both name fields")
    public Personal_InformationPage clearBothNames() {
        AllureUtils.step("Clearing both first and last name fields");
        clearFirstName();
        clearLastName();
        return this;
    }

    /**
     * Check if empty fields validation error is displayed
     */
    @Step("Verify empty fields error is displayed")
    public boolean isEmptyFieldsErrorDisplayed() {
        AllureUtils.step("Checking if empty fields validation error is displayed");
        try {
            waitForPageLoad(); // Give dialog time to appear
            return WaitUtils.isElementPresent(Names_emptyFieldsError);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get validation error message text
     */
    @Step("Get validation error message")
    public String getValidationErrorMessage() {
        AllureUtils.step("Getting validation error message text");
        try {
            WebElement errorElement = WaitUtils.waitForElementPresent(Names_emptyFieldsError);
            return errorElement.getText();
        } catch (Exception e) {
            LogUtils.error("Failed to get validation error: " + e.getMessage());
            return "";
        }
    }


    // Confirm Button
    @Step("Click confirm button")
    public Personal_InformationPage clickConfirm() {
        AllureUtils.step("Clicking confirm button to save name changes");
        clickElement(Names_confirmButton);
        waitForPageLoad();
        return new Personal_InformationPage();
    }

// ==================== BIRTHDAY PICKER METHODS ====================

    /**
     * Check if year picker is displayed
     */
    @Step("Verify year picker is displayed")
    public boolean isYearPickerDisplayed() {
        AllureUtils.step("Checking if year picker is displayed");
        waitForPageLoad();
        boolean isDisplayed = isElementDisplayed(birthdayYearPageTitle);
        LogUtils.info("Year picker displayed: " + isDisplayed);
        return isDisplayed;
    }

    /**
     * Check if month picker is displayed
     */
    @Step("Verify month picker is displayed")
    public boolean isMonthPickerDisplayed() {
        AllureUtils.step("Checking if month picker is displayed");
        waitForPageLoad();
        boolean isDisplayed = isElementDisplayed(birthdayMonthPageTitle);
        LogUtils.info("Month picker displayed: " + isDisplayed);
        return isDisplayed;
    }

    /**
     * Check if day picker is displayed
     */
    @Step("Verify day picker is displayed")
    public boolean isDayPickerDisplayed() {
        AllureUtils.step("Checking if day picker is displayed");
        waitForPageLoad();
        boolean isDisplayed = isElementDisplayed(birthdayDayPageTitle);
        LogUtils.info("Day picker displayed: " + isDisplayed);
        return isDisplayed;
    }

    /**
     * Select birth year - fast scroll to top, then controlled scroll to target
     */
    @Step("Select birth year: {year}")
    public Personal_InformationPage selectBirthYear(String year) {
        AllureUtils.step("Selecting birth year: " + year);

        try {
            waitForPageLoad();

            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();
            int centerX = width / 2;

            // Step 1: Fast scroll to top (2007)
            LogUtils.info("Scrolling to top of year picker (2007)");
            int fastStartY = (int) (height * 0.525);
            int fastEndY = (int) (height * 0.465);

            for (int i = 0; i < 10; i++) {
                driver.executeScript("mobile: dragGesture", Map.of(
                        "startX", centerX,
                        "startY", fastEndY,
                        "endX", centerX,
                        "endY", fastStartY,
                        "speed", 10000
                ));
                Thread.sleep(1);
            }
            // Step 2: Controlled scroll to target
            LogUtils.info("Scrolling down to find year: " + year);
            int slowStartY = (int) (height * 0.52);
            int slowEndY = (int) (height * 0.48);
            int maxSwipes = 60;

            for (int i = 0; i < maxSwipes; i++) {
                try {
                    WebElement yearElement = driver.findElement(
                            AppiumBy.androidUIAutomator("new UiSelector().text(\"" + year + "\")")
                    );
                    yearElement.click();
                    LogUtils.info("Selected year: " + year);
                    return this;
                } catch (Exception e) {
                    // Not found
                }

                driver.executeScript("mobile: dragGesture", Map.of(
                        "startX", centerX,
                        "startY", slowStartY,
                        "endX", centerX,
                        "endY", slowEndY,
                        "speed", 5000
                ));
            }

            throw new RuntimeException("Year not found: " + year);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted", e);
        } catch (Exception e) {
            LogUtils.error("Failed to select year: " + e.getMessage());
            throw new RuntimeException("Failed to select year: " + year, e);
        }
    }

    /**
     * Select birth month - fast scroll to top, then controlled scroll to target
     */
    @Step("Select birth month: {month}")
    public Personal_InformationPage selectBirthMonth(String month) {
        AllureUtils.step("Selecting birth month: " + month);

        try {
            waitForPageLoad();

            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();
            int centerX = width / 2;

            // Step 1: Fast scroll to top (month 1)
            LogUtils.info("Scrolling to top of month picker (1)");
            int fastStartY = (int) (height * 0.525);
            int fastEndY = (int) (height * 0.465);

            for (int i = 0; i < 15; i++) {
                driver.executeScript("mobile: dragGesture", Map.of(
                        "startX", centerX,
                        "startY", fastEndY,
                        "endX", centerX,
                        "endY", fastStartY,
                        "speed", 10000
                ));
                Thread.sleep(1);
            }


            // Step 2: Controlled scroll to target
            LogUtils.info("Scrolling down to find month: " + month);
            int slowStartY = (int) (height * 0.52);
            int slowEndY = (int) (height * 0.48);
            int maxSwipes = 20;

            for (int i = 0; i < maxSwipes; i++) {
                try {
                    WebElement monthElement = driver.findElement(
                            AppiumBy.androidUIAutomator("new UiSelector().text(\"" + month + "\")")
                    );
                    monthElement.click();
                    LogUtils.info("Selected month: " + month);
                    return this;
                } catch (Exception e) {
                    // Not found
                }

                driver.executeScript("mobile: dragGesture", Map.of(
                        "startX", centerX,
                        "startY", slowStartY,
                        "endX", centerX,
                        "endY", slowEndY,
                        "speed", 5000
                ));
            }

            throw new RuntimeException("Month not found: " + month);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted", e);
        } catch (Exception e) {
            LogUtils.error("Failed to select month: " + e.getMessage());
            throw new RuntimeException("Failed to select month: " + month, e);
        }
    }

    /**
     * Select birth day - fast scroll to top, then controlled scroll to target
     */
    @Step("Select birth day: {day}")
    public Personal_InformationPage selectBirthDay(String day) {
        AllureUtils.step("Selecting birth day: " + day);

        try {
            waitForPageLoad();

            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();
            int centerX = width / 2;

            // Step 1: Fast scroll to top (day 1)
            LogUtils.info("Scrolling to top of day picker (1)");
            int fastStartY = (int) (height * 0.525);
            int fastEndY = (int) (height * 0.465);

            for (int i = 0; i < 20; i++) {
                driver.executeScript("mobile: dragGesture", Map.of(
                        "startX", centerX,
                        "startY", fastEndY,
                        "endX", centerX,
                        "endY", fastStartY,
                        "speed", 10000
                ));
                Thread.sleep(1);
            }

            // Step 2: Controlled scroll to target
            LogUtils.info("Scrolling down to find day: " + day);
            int slowStartY = (int) (height * 0.52);
            int slowEndY = (int) (height * 0.48);
            int maxSwipes = 60;

            for (int i = 0; i < maxSwipes; i++) {
                try {
                    WebElement dayElement = driver.findElement(
                            AppiumBy.androidUIAutomator("new UiSelector().text(\"" + day + "\")")
                    );
                    dayElement.click();
                    LogUtils.info("Selected day: " + day);
                    return this;
                } catch (Exception e) {
                    // Not found
                }

                driver.executeScript("mobile: dragGesture", Map.of(
                        "startX", centerX,
                        "startY", slowStartY,
                        "endX", centerX,
                        "endY", slowEndY,
                        "speed", 5000
                ));
            }

            throw new RuntimeException("Day not found: " + day);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted", e);
        } catch (Exception e) {
            LogUtils.error("Failed to select day: " + e.getMessage());
            throw new RuntimeException("Failed to select day: " + day, e);
        }
    }

    /**
     * Check the minimum available birth year (should be 2007)
     * Scrolls to top and checks what years are visible
     */
    @Step("Check minimum available birth year")
    public String checkMinimumAvailableYear() {
        AllureUtils.step("Checking minimum available birth year");

        try {
            waitForPageLoad();

            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();
            int centerX = width / 2;

            // Scroll all the way to top
            LogUtils.info("Scrolling to top of year picker");
            int fastStartY = (int) (height * 0.525);
            int fastEndY = (int) (height * 0.465);

            for (int i = 0; i < 10; i++) {
                driver.executeScript("mobile: dragGesture", Map.of(
                        "startX", centerX,
                        "startY", fastEndY,
                        "endX", centerX,
                        "endY", fastStartY,
                        "speed", 10000
                ));
                Thread.sleep(1);
            }

            Thread.sleep(300);

            // Check which years are visible at the top
            // Try to find years from 2010 down to 1990
            for (int year = 2010; year >= 1990; year--) {
                try {
                    driver.findElement(
                            AppiumBy.androidUIAutomator("new UiSelector().text(\"" + year + "\")")
                    );
                    LogUtils.info("Found year: " + year + " at top of picker");
                    return String.valueOf(year);
                } catch (Exception e) {
                    // This year not visible, continue
                }
            }

            LogUtils.warn("Could not determine minimum year");
            return "Unknown";

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted", e);
        } catch (Exception e) {
            LogUtils.error("Failed to check minimum year: " + e.getMessage());
            throw new RuntimeException("Failed to check minimum year", e);
        }
    }


        /**
         * Confirm year selection
         */
    @Step("Confirm year selection")
    public Personal_InformationPage confirmYearSelection() {
        AllureUtils.step("Confirming year selection");
        clickElement(birthdayYearConfirmButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Confirm month selection
     */
    @Step("Confirm month selection")
    public Personal_InformationPage confirmMonthSelection() {
        AllureUtils.step("Confirming month selection");
        clickElement(birthdayMonthConfirmButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Confirm day selection
     */
    @Step("Confirm day selection")
    public Personal_InformationPage confirmDaySelection() {
        AllureUtils.step("Confirming day selection");
        clickElement(birthdayDayConfirmButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Complete full birthday selection (year, month, day)
     */
    @Step("Select complete birthday: {year}/{month}/{day}")
    public Personal_InformationPage selectCompleteBirthday(String year, String month, String day) {
        AllureUtils.step("Selecting complete birthday: " + year + "/" + month + "/" + day);

        // Select year
        selectBirthYear(year);
        confirmYearSelection();

        // Select month
        selectBirthMonth(month);
        confirmMonthSelection();

        // Select day
        selectBirthDay(day);
        confirmDaySelection();

        return this;
    }

    /**
     * Navigate back from year picker
     */
    @Step("Navigate back from year picker")
    public Personal_InformationPage navigateBackFromYearPicker() {
        AllureUtils.step("Navigating back from year picker");
        navigateBack();
        waitForPageLoad();
        return this;
    }

    /**
     * Navigate back from month picker
     */
    @Step("Navigate back from month picker")
    public Personal_InformationPage navigateBackFromMonthPicker() {
        AllureUtils.step("Navigating back from month picker");
        navigateBack();
        waitForPageLoad();
        return this;
    }

    /**
     * Navigate back from day picker
     */
    @Step("Navigate back from day picker")
    public Personal_InformationPage navigateBackFromDayPicker() {
        AllureUtils.step("Navigating back from day picker");
        navigateBack();
        waitForPageLoad();
        return this;
    }
    // ==================== GENDER PICKER METHODS ====================

    /**
     * Check if gender picker is displayed
     */
    @Step("Verify gender picker is displayed")
    public boolean isGenderPickerDisplayed() {
        AllureUtils.step("Checking if gender picker is displayed");
        waitForPageLoad();
        boolean isDisplayed = isElementDisplayed(genderPageTitle);
        LogUtils.info("Gender picker displayed: " + isDisplayed);
        return isDisplayed;
    }

    /**
     * Select Female gender
     */
    @Step("Select gender: Female")
    public Personal_InformationPage selectGenderFemale() {
        AllureUtils.step("Selecting gender: Female");
        clickElement(genderFemaleOption);
        LogUtils.info("Selected gender: Female");
        return this;
    }

    /**
     * Select Male gender
     */
    @Step("Select gender: Male")
    public Personal_InformationPage selectGenderMale() {
        AllureUtils.step("Selecting gender: Male");
        clickElement(genderMaleOption);
        LogUtils.info("Selected gender: Male");
        return this;
    }

    /**
     * Select Prefer Not to Say
     */
    @Step("Select gender: Prefer Not to Say")
    public Personal_InformationPage selectGenderPreferNotToSay() {
        AllureUtils.step("Selecting gender: Prefer Not to Say");
        clickElement(genderPreferNotToSayOption);
        LogUtils.info("Selected gender: Prefer Not to Say");
        return this;
    }

    /**
     * Confirm gender selection
     */
    @Step("Confirm gender selection")
    public Personal_InformationPage confirmGenderSelection() {
        AllureUtils.step("Confirming gender selection");
        clickElement(genderConfirmButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Select gender by name
     */
    @Step("Select gender: {gender}")
    public Personal_InformationPage selectGender(String gender) {
        AllureUtils.step("Selecting gender: " + gender);

        switch (gender.toLowerCase()) {
            case "female":
                selectGenderFemale();
                break;
            case "male":
                selectGenderMale();
                break;
            case "prefer not to say":
                selectGenderPreferNotToSay();
                break;
            default:
                throw new IllegalArgumentException("Invalid gender option: " + gender);
        }

        confirmGenderSelection();
        return this;
    }

    /**
     * Navigate back from gender picker
     */
    @Step("Navigate back from gender picker")
    public Personal_InformationPage navigateBackFromGenderPicker() {
        AllureUtils.step("Navigating back from gender picker");
        navigateBack();
        waitForPageLoad();
        return this;
    }
}