package com.techuvalogin.utility

import android.content.Context
import android.widget.Toast

fun Context.showShortToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}