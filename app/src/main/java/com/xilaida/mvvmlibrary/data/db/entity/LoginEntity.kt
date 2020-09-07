package com.xilaida.mvvmlibrary.data.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
@author cjq
@Date 6/9/2020
@Time 10:33 AM
@Describe:
 */
const val CURRENT_USER_ID = 0

@Entity
data class LoginEntity(
    var admin: Boolean? = null,
    var email: String? = null,
    var icon: String? = null,
    var id : String ? = null,
    var nickname:String ? = null,
    var password:String? = null,
    var publicName:String? = null,
    var token:String? = null,
    var type:Int?=  null,
    var username:String?= null,
    @Ignore var chapterTops:List<Any>?= null,
    @Ignore var collectIds:List<Any>?= null
){

    @PrimaryKey(autoGenerate = false)
    var userId = CURRENT_USER_ID
}