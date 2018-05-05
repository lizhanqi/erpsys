package com.suxuantech.erpsys.entity

/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * ..................佛祖开光 ,永无BUG................
 *
 * @author Created by 李站旗 on 2018/4/27 0027 15:05 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 表单类型的校验
 */
class FormEntity {
    var icon: Int = 0
    var marginTop: Int = 0
    var edit: Boolean = false
    var key: String? = ""
    var value: String? = ""
    var hint: String? = ""
    var mustFill: Boolean = false
    var option: Boolean = false
    var flag: Any? = null
    /**
     * 校验必填项的值是否填入了
     */
    public fun valueNotNull(): Boolean {
        if (mustFill) {
            return value.isNullOrEmpty()
        } else {
            return true
        }
    }

    constructor(key: String, value: String) {
        this.key = key
        this.value = value
    }

    constructor(icon: Int, key: String, value: String) {
        this.icon = icon
        this.key = key
        this.value = value
    }

    constructor(icon: Int, key: String, value: String, hint: String) {
        this.icon = icon
        this.key = key
        this.value = value
        this.hint = hint
    }


    constructor(icon: Int, key: String, value: String, hint: String, mustFill: Boolean) {
        this.icon = icon
        this.key = key
        this.value = value
        this.hint = hint
        this.mustFill = mustFill
    }

    constructor(icon: Int, edit: Boolean, key: String, value: String) {
        this.icon = icon
        this.edit = edit
        this.key = key
        this.value = value
    }

    constructor(icon: Int, edit: Boolean, key: String, value: String, hint: String, mustFill: Boolean, option: Boolean) {
        this.icon = icon
        this.edit = edit
        this.key = key
        this.value = value
        this.hint = hint
        this.mustFill = mustFill
        this.option = option
    }

    constructor(edit: Boolean, key: String, value: String, hint: String, marginTop: Int) {
        this.icon = icon
        this.edit = edit
        this.key = key
        this.value = value
        this.hint = hint
        this.mustFill = mustFill
        this.option = option
        this.marginTop = marginTop
    }

    constructor(icon: Int, key: String, value: String, marginTop: Int){
        this.icon = icon
        this.key = key
        this.value = value
        this.marginTop = marginTop
    }


}
