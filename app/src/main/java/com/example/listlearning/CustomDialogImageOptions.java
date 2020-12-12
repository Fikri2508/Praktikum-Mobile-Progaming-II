package com.example.listlearning;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

public class CustomDialogImageOptions extends Dialog {

    private final DialogImageOptionsListerner listerner;

    public CustomDialogImageOptions(@NonNull Context context, DialogImageOptionsListerner listerner) {
        super(context);
        this.listerner = listerner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_image_options);

        findViewById(R.id.open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listerner.onCameraClick();
                dismiss();
            }
        });

        findViewById(R.id.open_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listerner.onGalerryClick();
                dismiss();
            }
        });

    }
}
