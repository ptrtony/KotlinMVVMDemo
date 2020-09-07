package com.xilaida.mvvmlibrary.utils

import java.io.IOException
import java.net.ConnectException

/**
@author cjq
@Date 6/9/2020
@Time 2:18 PM
@Describe:
 */

class ApiException(message:String) : IOException(message)
class NoInternetException(message:String):ConnectException(message)