package testdata;

/**
 * Test Data Constants
 * Contains all test data used across test cases
 *
 * @author Ciye Test Team
 */
public class TestData {

    // Valid User Credentials
    public static class ValidUser {
        public static final String EMAIL = "maani@gmail.com";
        public static final String PASSWORD = "11111111";
        public static final String PROFILE_INITIALS = "MM";
    }

    // Test User for Account Deletion
    public static class DeleteUser {
        public static final String EMAIL = "1a72313202@emaily.pro";
        public static final String PASSWORD = "11111111";
        public static final String PROFILE_INITIALS = "MT";
    }

    // Invalid Test Data
    public static class InvalidData {
        public static final String INVALID_EMAIL = "wrongEmail@test.com";
        public static final String INVALID_PASSWORD = "WrongPassword123";
        public static final String SHORT_PASSWORD = "12345";
        public static final String EMPTY_STRING = "";
    }

    // Error Messages
    public static class ErrorMessages {
        public static final String INVALID_EMAIL_ERROR = "Please enter a valid email address";
        public static final String EMPTY_FIELDS_ERROR = "Please fill in all fields!";
        public static final String WRONG_CREDENTIALS_ERROR = "Could not login. Wrong password or username.";
    }

    // App Navigation
    public static class Navigation {
        public static final String WELCOME_TO_LOGIN_TEXT = "Have an account?,  Log in!";
    }

    // Test Categories
    public static class TestCategories {
        public static final String LOGIN_TESTS = "Login Tests";
        public static final String PROFILE_TESTS = "Profile Tests";
        public static final String NAVIGATION_TESTS = "Navigation Tests";
    }

    // Test Priorities
    public static class TestPriority {
        public static final int CRITICAL = 1;
        public static final int HIGH = 2;
        public static final int MEDIUM = 3;
        public static final int LOW = 4;
    }
}