package com.yunwen.android.v5configure_s.pojo

class CategoryResult constructor() {

    var code: Int = 0;

    lateinit var data: Array<Category>;

    constructor(cd: Int, array: Array<Category>) : this() {
        code = cd
        data = array!!
    }



}
