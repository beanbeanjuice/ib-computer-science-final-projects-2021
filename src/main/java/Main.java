public class Main {

    private static LoginHandler loginHandler;

    public static void main(String[] args) {
        // Creating a new LoginHandler object.
        loginHandler = new LoginHandler();

        // Test "Accounts"
        loginHandler.signUp("testAccount1", "password123");
        loginHandler.signUp("testAccount2", "password321");

        // Trying to login
        String username = "william";
        String password = "testtest123";

        // Simulating a "login" with an account that doesn't exist.
        LoginHandler.LOGIN_INFORMATION loginInformation = loginHandler.logIn(username, password);
        // Since the account doesn't exist, doing System.out.println(loginInformation.getMessage()) should print out
        // that the account doesn't exist
        System.out.println(loginInformation.getMessage());


        // Simulating a "signup" with an account that doesn't exist.
        LoginHandler.SIGNUP_INFORMATION signupInformation = loginHandler.signUp(username, password);
        // This should print out that the signup was successful.
        System.out.println(signupInformation.getMessage());

        // Now if we try to login AGAIN with the same thing, it should say successful login
        loginInformation = loginHandler.logIn(username, password);
        System.out.println(loginInformation.getMessage());

        // And again, if we try to signup with an account that ALREADY exists, it should tell us
        // that the username is already taken.
        signupInformation = loginHandler.signUp(username, password);
        System.out.println(signupInformation.getMessage());

        // Additionally, if we try to login with the incorrect password, it should tell us incorrect password
        loginInformation = loginHandler.logIn(username, "testtest321");
        System.out.println(loginInformation.getMessage());






        // Here is an example of how you could use this to login.
        String username2 = "testUsername";
        String password2 = "testPassword";

        LoginHandler.LOGIN_INFORMATION loginInformation2 = loginHandler.logIn(username2, password2);

        if (loginInformation2.equals(LoginHandler.LOGIN_INFORMATION.SUCCESSFUL_LOGIN)) {
            // Login using your own login method. For example...
            // Here is where you would change the screen on your program
            System.out.println(loginInformation2.getMessage());
        } else {

            // Show the user some sort of error (i.e. changing the text on the GUI to the getMessage();
            System.out.println(loginInformation2.getMessage());
        }


        // Here is an example of how you could use this to signup.

        LoginHandler.SIGNUP_INFORMATION signupInformation2 = loginHandler.signUp(username2, password2);

        if (signupInformation2.equals(LoginHandler.SIGNUP_INFORMATION.SUCCESSFUL_SIGNUP)) {
            // When this passes, that means the user IS signed up, but you should have here
            // what methods should be run AFTER the user is signed up
            // Here is where you would change the screen on your program
            System.out.println(signupInformation2.getMessage());
        } else {
            // Here is again, where you change the text on the GUI to the getMessage() method
            // So the user knows what is wrong.
            System.out.println(signupInformation2.getMessage());
        }
    }

    // This is so you can get the loginHandler from any class.
    public LoginHandler getLoginHandler() {
        return loginHandler;
    }

}
