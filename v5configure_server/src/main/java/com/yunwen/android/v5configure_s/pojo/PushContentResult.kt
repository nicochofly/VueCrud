package com.yunwen.android.v5configure_s.pojo

class PushContentResult constructor() {

    var code: Int = 0;

    lateinit var data: Array<Pushcontent>;

    constructor(cd: Int, array: Array<Pushcontent>) : this() {
        code = cd
        data = array!!
    }



}
