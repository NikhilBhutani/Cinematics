package com.github.nikhilbhutani.popularmovies2.Utils;

import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by Nikhil Bhutani on 8/22/2016.
 */
public class AlertDialogUtil {

    private static final String LOG_TAG = AlertDialogUtil.class.getSimpleName();

    public static void createAlert(Context context, String title, String msg,
                                   String positiveButtonText, DialogInterface.OnClickListener positiveButtonListener,
                                   String negativeButtonText, DialogInterface.OnClickListener negativeButtonListener) {

        Log.d(LOG_TAG, "creatAlert called");
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title).setCancelable(false).setMessage(msg)
                .setPositiveButton(positiveButtonText, positiveButtonListener);

        if (!TextUtils.isEmpty(negativeButtonText)) {
            alertDialog.setNegativeButton(negativeButtonText,
                    negativeButtonListener);
        }
        alertDialog.show();
        Log.d(LOG_TAG, "alertTitle: " + title + ", alertMessage: " + msg);

    }

    public static void createSingleChoiceItemsAlert(Context context, String title, String[] items,
                                                    DialogInterface.OnClickListener itemClickListener) {

        Log.d(LOG_TAG, "createSingleChoiceItemsAlert called");
        AlertDialog.Builder alertDialog =
                new AlertDialog.Builder(context).setTitle(title)
                        .setSingleChoiceItems(items, -1, itemClickListener);
        alertDialog.show();

        Log.d(LOG_TAG, "alertTitle: " + title + ", alertItems: " + Arrays.asList(items));

    }

}
