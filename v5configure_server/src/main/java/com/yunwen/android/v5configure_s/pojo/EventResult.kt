package com.yunwen.android.v5configure_s.pojo

class EventResult constructor() {

    var code: Int = 0;

    lateinit var data: Array<EventInfo>;

    constructor(cd: Int, array: Array<EventInfo>) : this() {
        code = cd
        data = array!!
    }



}