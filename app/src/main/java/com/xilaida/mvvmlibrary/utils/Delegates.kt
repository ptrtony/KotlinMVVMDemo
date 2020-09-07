package com.xilaida.mvvmlibrary.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
@author cjq
@Date 6/9/2020
@Time 10:17 PM
@Describe:
 */

fun <T> lazyDeferred(block:suspend CoroutineScope.() -> T):Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async {
            block.invoke(this)
        }
    }
}