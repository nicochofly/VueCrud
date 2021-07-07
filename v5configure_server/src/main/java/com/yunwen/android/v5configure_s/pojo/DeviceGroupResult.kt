package com.yunwen.android.v5configure_s.pojo

class DeviceGroupResult constructor() {

    var code: Int = 0;

    lateinit var data: Array<DeviceGroup>;

    constructor(cd: Int, array: Array<DeviceGroup>) : this() {
        code = cd
        data = array!!
    }



}
