package com.letoti.kawa.philmaker.view.common.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.content.DialogInterface

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

    fun showAlertMessage(context: Context,
                         title: String,
                         message: String,
                         positiveButtonTitle: String,
                         negativeButtonTitle: String,
                         onSubmitClickListener: (() -> (Unit))?,
                         onCancelClickListener: (() -> (Unit))?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
                .setMessage(message)
        if (onSubmitClickListener != null)
            builder.setPositiveButton(if (positiveButtonTitle.isBlank()) context.getString(android.R.string.ok) else positiveButtonTitle,
                    DialogInterface.OnClickListener { dialog, id ->
                        onSubmitClickListener()
                        dialog.dismiss()
                    })
        if (onCancelClickListener != null)
            builder.setNegativeButton(if (negativeButtonTitle.isBlank()) context.getString(android.R.string.cancel) else negativeButtonTitle,
                    DialogInterface.OnClickListener { dialog, id ->
                        onCancelClickListener()
                        dialog.dismiss()
                    })
        return builder.create()
    }

    fun showAlertMessage(context: Context, title: String, message: String): Dialog {
        return showAlertMessage(context, title, message, "", "", {}, null)
    }

    fun showAlertMessage(context: Context, title: String): Dialog {
        return showAlertMessage(context, title, "", "", "", {}, null)
    }
}