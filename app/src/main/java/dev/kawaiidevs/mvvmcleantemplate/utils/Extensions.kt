package dev.kawaiidevs.mvvmcleantemplate.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_STRING
import java.text.SimpleDateFormat
import java.util.*


fun View.hideKeyboard() {
    context.hideKeyboard(this)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.setSingleClickListener(action: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(action))
}

fun View.toggleVisibility(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

fun String.formatDate(format: String): String {
    return try {
        val sourceSdf = SimpleDateFormat(format, Locale.getDefault())
        val requiredSdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        requiredSdf.format(sourceSdf.parse(this))
    } catch (e: Exception) {
        return EMPTY_STRING
    }
}