package com.letoti.kawa.philmaker.common.dialog

import android.app.Dialog
import android.app.DialogFragment
import android.content.Context

object DialogFactory {

    /**
     * Get progress dialog instance
     */
    fun getProgressDialog(x: Context, title: String, message: String): Dialog {
        val progressDialog = ProgressDialogCustom(x)
        progressDialog.setTitle(title)
        progressDialog.setCancelable(false)
        return progressDialog
    }

    fun showAlertMessage(message: String,
                         positiveButtonTitle: String,
                         negativeButtonTitle: String,
                         onSubmitClickListener: ((DialogFragment) -> (Unit)),
                         onCancelClickListener: ((DialogFragment) -> (Unit))): AlertDialogCustom {

        return AlertDialogCustom.createDialog(message, positiveButtonTitle, negativeButtonTitle, onSubmitClickListener, onCancelClickListener)
    }

    fun showAlertMessage(message: String): AlertDialogCustom {
        return showAlertMessage(message, "", "", {}, {})
    }
}