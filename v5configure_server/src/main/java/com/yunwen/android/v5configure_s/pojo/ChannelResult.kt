package com.yunwen.android.v5configure_s.pojo

class ChannelResult constructor() {

    var code: Int = 0;

    lateinit var data: Array<ChannelInfo>;

    constructor(cd: Int, array: Array<ChannelInfo>) : this() {
        code = cd
        data = array!!
    }



}