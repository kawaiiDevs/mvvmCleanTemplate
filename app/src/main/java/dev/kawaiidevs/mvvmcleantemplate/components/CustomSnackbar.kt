package dev.kawaiidevs.mvvmcleantemplate.components

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import dev.kawaiidevs.mvvmcleantemplate.R


object CustomSnackbar {

    fun createSnackbar(
        view: View,
        message: String,
        backgroundColor: Int = R.color.gray10,
        textColor:  Int = R.color.white,
        textAction: Int? = null,
        centerText: Boolean = false,
        action: (() -> Unit)? = null
    ): Snackbar {
        val snackbar = view.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG) }

        snackbar.setBackgroundTint(ContextCompat.getColor(view.context, backgroundColor))
        snackbar.setTextColor(ContextCompat.getColor(view.context, textColor))

        textAction?.let {
            snackbar.setActionTextColor(ContextCompat.getColor(view.context, R.color.gray00))
            snackbar.setAction(it) { action?.invoke() }
        }

        val snackbarView = snackbar.view
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.maxLines = 3
        if (centerText) textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        return snackbar
    }

}