package com.example.mvparchitecturedemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class EssentialMethods {

  public static void showView(View view) {
    try {
      if (view != null) {
        view.setVisibility(View.VISIBLE);
      }
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  public static void hideView(View view) {
    try {
      if (view != null) {
        view.setVisibility(View.GONE);
      }
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  public static void showShortToast(Context context, String message) {
    try {
      if (!TextUtils.isEmpty(message)) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
      }
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}
