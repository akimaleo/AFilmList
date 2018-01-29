package com.letoti.kawa.philmaker.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.letoti.kawa.philmaker.R;

public class ProgressDialogCustom extends Dialog {

    public ProgressDialogCustom(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.modal_dialog_progress);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
}
