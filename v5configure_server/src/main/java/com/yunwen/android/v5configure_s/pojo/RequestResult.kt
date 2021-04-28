package com.yunwen.android.v5configure_s.pojo

class RequestResult constructor() {


    constructor(cd: Int) : this() {
        this.code = cd
    }

    var code: Int = 0
}