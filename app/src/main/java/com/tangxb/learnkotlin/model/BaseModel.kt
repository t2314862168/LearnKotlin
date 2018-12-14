package com.tangxb.learnkotlin.model

import org.greenrobot.greendao.annotation.Id

open class BaseModel {
    @Id
    var _id: Long = 0
}