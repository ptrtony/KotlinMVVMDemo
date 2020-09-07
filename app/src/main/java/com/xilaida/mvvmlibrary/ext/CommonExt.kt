package com.xilaida.mvvmlibrary.ext
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.xilaida.mvvmlibrary.R

fun View.onClick(method:()->Unit){
    this.setOnClickListener { method() }
}

//fun Throwable.netException(mView:BaseView){
//    if (this is BaseException){
//        mView.onError(this.errorMsg)
//    }else{
//        mView.onError(this.message.toString())
//    }
//}

//fun <T>Observable<BaseResp<T>>.convert():Observable<T>{
//    return this.flatMap(BaseSubscriber())
//}

/**
 * @buttonColors 0 button可点击的颜色  1 button不可点击的颜色
 */
fun Button.enable(mEtn:EditText,method:()->Boolean){
    val mBtn = this
    mEtn.addTextChangedListener(object:TextWatcher{
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            mBtn.isEnabled = method()
            if (method()){
                mBtn.setBackgroundResource(R.drawable.common_button_enable_bg)
            }else{
                mBtn.setBackgroundResource(R.drawable.common_button_disenable_bg)
            }
        }

    })
}

/**
 * 隐藏软件盘
 */

fun EditText.hideKeyboard(){
    val manager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(this.windowToken,0)

}

fun View.hideKeyboard(){
    val manager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(this.windowToken,0)
}

