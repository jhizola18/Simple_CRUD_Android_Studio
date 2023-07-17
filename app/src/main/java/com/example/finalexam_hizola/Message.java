package com.example.finalexam_hizola;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
public class Message {
    public static void message(Context context, String message) {
        Toast.makeText((Context) context, message, Toast.LENGTH_LONG).show();
    }

    public static void message(View.OnClickListener onClickListener, String data) {
    }
}

