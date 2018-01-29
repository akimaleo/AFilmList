package com.letoti.kawa.philmaker.view.common

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.letoti.kawa.philmaker.R
import com.letoti.kawa.philmaker.view.common.dialog.DialogFactory

/**
 * Created by kawa on 29.01.2018.
 */

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), IView {

    private var progressDialog: Dialog? = null
        get() {
            if (field == null)
                field = DialogFactory.getProgressDialog(this, getString(R.string.loading_title), getString(R.string.loading_message))
            return field
        }

    override fun showLoadingProgress() {
        progressDialog!!.show()
    }

    override fun hideLoadingProgress() {
        progressDialog!!.dismiss()
    }

    override fun showErrorMessage() {
        DialogFactory.showAlertMessage(this, getString(R.string.error_message))
    }


    fun hideSoftwareKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}