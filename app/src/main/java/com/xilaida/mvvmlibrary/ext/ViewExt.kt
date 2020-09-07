package com.xilaida.mvvmlibrary.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
@author cjq
@Date 5/9/2020
@Time 8:23 PM
@Describe:
 */


fun Context.toast(msg:CharSequence){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.GONE
}

fun View.snackbar(message:String){
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).also {
        snackbar ->
        snackbar.setAction("Ok"){
            snackbar.dismiss()
        }
    }.show()
}




