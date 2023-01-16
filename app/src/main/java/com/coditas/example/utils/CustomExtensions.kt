package com.coditas.example.utils

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


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

fun AppCompatActivity.showSnack(string: String) {
    runOnUiThread {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
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