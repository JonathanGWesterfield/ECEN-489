package com.mp8.jonathanwesterfield.machineproblem8;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

/**
 * Author: Jonathan Westerfield
 * Summary: This class holds various alert messages that may be used by other classes
 */
public class VariousAlerts
{
    private Context context;

    public VariousAlerts() { /* Empty Constructor */}

    public VariousAlerts(Context context)
    {
        this.context = context;
    }

    /**
     * Alert to show that the username or password field is empty
     * @param view
     */
    public void showUserPasswdEmptyAlert(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Username or Password Field Empty").
                setMessage("Please enter a username and password.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Alert to show that the username or password field is empty
     * @param view
     */
    public void showFieldIsNumericAlert(View view, String textField)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle(textField + " Is Wrong Type").
                setMessage("Please enter a number in the " + textField + " field.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Alert to show that the username or password was wrong and could not be
     * authenticated. User can't login
     * @param view
     */
    public void showLoginFailAlert(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Failed Login").
                setMessage("Username and/or password is incorrect.\nPlease try again.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Alert to show that there was in error in using the given username and password
     * to create a new account
     * @param view
     */
    public void showAcctCreateFailAlert(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Account Could Not Be Created").
                setMessage("Account creation using the given sername and password " +
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
    public void showQueryFailAlert(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
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
    public void showSignOutFailAlert(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
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
    public void showPushFailAlert(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Push Failed").
                setMessage("Failed to push data to the database. Please make sure you have good " +
                        "internet connection.\nPlease try again.")
                .setNeutralButton("OK", null);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
