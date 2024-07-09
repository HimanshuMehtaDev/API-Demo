package com.live.tasknew.network;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.live.tasknew.R;

import java.util.Objects;

public class ProgressDialog {
    Context context;
    Dialog dialog;

    public ProgressDialog(Context context) {
        this.context = context;
    }

    public void show() {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.lay_progress_dialog);

        ProgressBar progressBar = dialog.findViewById(R.id.progressBar);

        progressBar.setProgressTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.black)));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();

    }

    public void hide() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}