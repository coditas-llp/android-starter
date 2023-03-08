package com.coditas.example.utils

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


fun AppCompatActivity.showToast(string: String) {
    runOnUiThread {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.showToast(string: String) {
    activity?.runOnUiThread {
        Toast.makeText(this.context, string, Toast.LENGTH_SHORT).show()
    }
}

fun AppCompatActivity.showSnack(view: View ,string: String) {
    runOnUiThread {
        Snackbar.make(view, string, Snackbar.LENGTH_SHORT).show()
    }
}

fun Fragment.showSnack(view: View, string: String) {
    activity?.runOnUiThread {
        Snackbar.make(view, string, Snackbar.LENGTH_SHORT).setBackgroundTint(Color.DKGRAY)
            .setTextColor(Color.WHITE).show()
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}