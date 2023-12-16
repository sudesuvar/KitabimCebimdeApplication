package com.sudedenizsuvar.kitabimcebimde

import android.widget.ImageView

class booksClass {
    var constBookname:String?=null
    var constComment:String?=null
    var constPhoto:Int?=null

    constructor(constBookname:String, constComment:String, constPhoto: Int){
        this.constComment= constComment
        this.constBookname=constBookname
        this.constPhoto=constPhoto

    }
}