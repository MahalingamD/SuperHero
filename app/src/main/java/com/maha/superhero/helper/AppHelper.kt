package com.maha.superhero.helper

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.maha.superhero.R


fun Context.toast(aMsg: String) {
    Toast.makeText(this, aMsg, Toast.LENGTH_SHORT).show()
}


fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}


fun checkProgressBar(aProgressbar: ProgressBar) {
    if (!aProgressbar.isShown) aProgressbar.show()
}

fun isInternetOn(aContext: Context): Boolean {
    val connectivityManager =
            aContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun showAlert(aContext: Context, aMessage: String) {
    try {
        val builder = AlertDialog.Builder(aContext)
        builder.setMessage(aMessage).setTitle(aContext.getString(R.string.app_name))
                .setCancelable(false).setPositiveButton("OK") { dialog, id ->
                    dialog.dismiss()
                }
        val alert = builder.create()
        alert.show()
        // Change the buttons color in dialog
        val pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE)
        pbutton.setTextColor(ContextCompat.getColor(aContext, R.color.purple_500))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun loadFromUrlGlide(url: String, aImageView: ImageView, mContext: Context) {

    Glide.with(mContext).load(url).fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.ic_superhero).error(R.drawable.ic_superhero).into(aImageView)

}

val aReg = Regex("^[0-9]+\$")

fun checkString(aText: String): Boolean {

    return aText.matches(aReg)
}
