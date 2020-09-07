package com.xilaida.mvvmlibrary.data.protocal.base

/**
@author cjq
@Date 5/9/2020
@Time 10:46 PM
@Describe:
 */

data class BaseResp<T>(var errorCode:Int,var errorMsg:String?,var data:T)