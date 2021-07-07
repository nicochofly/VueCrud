package com.yunwen.android.v5configure_s.pojo

class DeviceInfoResult constructor() {

    var code: Int = 0;

    lateinit var data: Array<Deviceinfo>;

    constructor(cd: Int, array: Array<Deviceinfo>) : this() {
        code = cd
        data = array!!
    }



}
