package com.gipra.vicibshoppy.utlis

import VolleySingleton
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import es.dmoral.toasty.Toasty


fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {

    Toast.makeText(this, message, duration).show()

}

fun Context.showToasty(message: String, duration: Int = Toast.LENGTH_SHORT) {

    Toasty.success(this, message, Toast.LENGTH_SHORT, true).show();
}




fun Context.changeStatusBarColor(window: Window, red: Int, green: Int, blue: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = Color.rgb(red, green, blue)
    }
}
