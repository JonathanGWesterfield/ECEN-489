package com.mp8.jonathanwesterfield.machineproblem8;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

/**
 * Author: Jonathan Westerfield
 * Summary: This class holds various alert messages that may be used by other classes
 *
 * THIS CLASS IS BAD BECAUSE I DIDN'T IMPLEMENT IT CORRECTLY. USING IT WILL CRASH YOUR APP!!!
 */
public class VariousAlerts
{

    public VariousAlerts() { /* Empty Constructor */}

    /**
     * Alert to show that the email or password field is empty
     * @param view
     */
    public static void showUserPasswdEmptyAlert(View view, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Email or Password Field Empty").
                setMessage("Please enter a email and password.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Alert to show that the email or password field is empty
     * @param view
     */
    public static void showFieldIsNumericAlert(View view, Context context, String textField)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(textField + " Is Wrong Type").
                setMessage("Please enter a number in the " + textField + " field.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Alert to show that the email or password was wrong and could not be
     * authenticated. User can't login
     * @param view
     */
    public static void showLoginFailAlert(View view, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Failed Login").
                setMessage("Email and/or password is incorrect.\nPlease try again.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Alert to show that there was in error in using the given email and password
     * to create a new account
     * @param view
     */
    public static void showAcctCreateFailAlert(View view, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Account Could Not Be Created").
                setMessage("Account creation using the given email and password " +
                        "pair failed.\nPlease try again.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Alert to show that a query could not be executed.
     * @param view
     */
    public static void showQueryFailAlert(View view, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Query Failed").
                setMessage("Failed to execute query. Please make sure you have good internet" +
                        "connection.\nPlease try again.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Alert to show that user could not sign out
     * @param view
     */
    public static void showSignOutFailAlert(View view, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sign Out Failed").
                setMessage("Failed to sign out of account. Please make sure you have good " +
                        "internet connection.\nPlease try again.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Alert to show that data could not be pushed to the database
     * @param view
     */
    public static void showPushFailAlert(View view, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Push Failed").
                setMessage("Failed to push data to the database. Please make sure you have good " +
                        "internet connection.\nPlease try again.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
