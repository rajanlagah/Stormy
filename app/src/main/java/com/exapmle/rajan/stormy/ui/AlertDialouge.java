package com.exapmle.rajan.stormy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;

/**
 * Created by rajan on 05-08-2017.
 */

public class AlertDialouge extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // to get comtext of avtivity which call this for alertBox
        Context mContext=getActivity();



        AlertDialog.Builder builder =new AlertDialog.Builder(mContext)
                .setTitle("thier was error")
                .setMessage("Please check the connection")
                .setPositiveButton("Ok",null);
        AlertDialog dialog=builder.create();
        return dialog;
    }
}
