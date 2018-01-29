package com.letoti.kawa.philmaker.view.common.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle

class AlertDialogCustom : DialogFragment() {

    private var message: String = ""
    private var positiveButtonTitle: String = ""
    private var negativeButtonTitle: String = ""

    private var onSubmitClickListener: ((DialogFragment) -> (Unit)) = {}
    private var onCancelClickListener: ((DialogFragment) -> (Unit)) = {}

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {

        if (positiveButtonTitle.isBlank()) {
            positiveButtonTitle = getString(android.R.string.ok)
        }

        if (negativeButtonTitle.isBlank()) {
            negativeButtonTitle = getString(android.R.string.cancel)
        }

        val builder = AlertDialog.Builder(activity)
        builder.setMessage(message)
                .setPositiveButton(positiveButtonTitle, DialogInterface.OnClickListener { dialog, id ->
                    onSubmitClickListener(this@AlertDialogCustom)
                    dismiss()
                })
                .setNegativeButton(negativeButtonTitle, DialogInterface.OnClickListener { dialog, id ->
                    onCancelClickListener(this@AlertDialogCustom)
                    dismiss()
                })
        return builder.create()
    }

    companion object {
        fun createDialog(message: String,
                         positiveButtonTitle: String,
                         negativeButtonTitle: String,
                         onSubmitClickListener: ((DialogFragment) -> (Unit)),
                         onCancelClickListener: ((DialogFragment) -> (Unit))): AlertDialogCustom {
            val dialog = AlertDialogCustom()
            dialog.message = message
            dialog.positiveButtonTitle = positiveButtonTitle
            dialog.negativeButtonTitle = negativeButtonTitle
            dialog.onSubmitClickListener = onSubmitClickListener
            dialog.onCancelClickListener = onCancelClickListener
            return dialog
        }
    }
}
