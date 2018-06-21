package com.suxuantech.erpsys.entity

import android.os.Parcel
import android.os.Parcelable

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
 * @Description: todo(用一句话描述该文件做什么)
 */
class StaffSearchEntity : BaseResult() {

    /**
     * code : 200
     * msg :
     * data : [{"id":1,"staffnumber":"G001","staffname":"王晓杰演示1","password":"MDAwMDAwMDAwMIWIoW0","department_id":31,"department_name":"管理部","main_position_id":56,"main_position_name":"店长","main_position_code":"p15197109382616","position_id":"","position_code":null,"zhiji_name":null,"turestaffname":"王晓杰","group_company_name":"北京素玄科技有限公司","brandclass":"艺匠","shop_name":"艺匠北京一店","shop_code":"YJ0001","setkey":null,"group_id":0,"group_name":null,"is_group_admin":0,"sex":"男","entry_date":"2014-02-01","leave_date":null,"birthday":"","IDcard":"","nation":"","place_origin":"","political":"","education":"","graduation_school":"","marital_status":"","contract_endday":"","telephone":"18701389821","QQ":"","email":"","weixin":"","a_discount":"","b_discount":"","a_cost":null,"b_cost":null,"liaison_person":"","liaison_tel":"","salary_money":"","social_security":"","bank_cardnumber":"","addressinfo":"","remarkinfo":"","work_type":null,"vice_work_type":null,"work_shop":"YJ0001","open_id":null,"open_id_name":null,"status":1,"Popedom1":null,"Popedom2":null,"Popedom3":null,"Popedom4":null,"Popedom5":null,"Popedom6":null,"Popedom7":null,"Popedom8":null,"Popedom9":null,"Popedom10":null,"Popedom11":null,"Popedom12":null,"Popedom13":null,"Popedom14":null,"Popedom15":null,"Popedom16":null,"Popedom17":null,"Popedom18":null,"zhiji_id":0,"add_time":"1519714484","sn":"YJY","grade_type":4,"last_login_ip":null,"last_login_time":null,"brandclass_id":3,"work_types":"22","is_jiguang":1,"belong_shop_code":"YJ0001","belong_shop_name":"艺匠北京一店","jg_username":"1411af55e939196dd52eb5c78f06437e"}]
     */

    var data: List<DataBean>? = null

    class DataBean : Parcelable {
        /**
         * id : 1
         * staffnumber : G001
         * staffname : 王晓杰演示1
         * password : MDAwMDAwMDAwMIWIoW0
         * department_id : 31
         * department_name : 管理部
         * main_position_id : 56
         * main_position_name : 店长
         * main_position_code : p15197109382616
         * position_id :
         * position_code : null
         * zhiji_name : null
         * turestaffname : 王晓杰
         * group_company_name : 北京素玄科技有限公司
         * brandclass : 艺匠
         * shop_name : 艺匠北京一店
         * shop_code : YJ0001
         * setkey : null
         * group_id : 0
         * group_name : null
         * is_group_admin : 0
         * sex : 男
         * entry_date : 2014-02-01
         * leave_date : null
         * birthday :
         * IDcard :
         * nation :
         * place_origin :
         * political :
         * education :
         * graduation_school :
         * marital_status :
         * contract_endday :
         * telephone : 18701389821
         * QQ :
         * email :
         * weixin :
         * a_discount :
         * b_discount :
         * a_cost : null
         * b_cost : null
         * liaison_person :
         * liaison_tel :
         * salary_money :
         * social_security :
         * bank_cardnumber :
         * addressinfo :
         * remarkinfo :
         * work_type : null
         * vice_work_type : null
         * work_shop : YJ0001
         * open_id : null
         * open_id_name : null
         * status : 1
         * Popedom1 : null
         * Popedom2 : null
         * Popedom3 : null
         * Popedom4 : null
         * Popedom5 : null
         * Popedom6 : null
         * Popedom7 : null
         * Popedom8 : null
         * Popedom9 : null
         * Popedom10 : null
         * Popedom11 : null
         * Popedom12 : null
         * Popedom13 : null
         * Popedom14 : null
         * Popedom15 : null
         * Popedom16 : null
         * Popedom17 : null
         * Popedom18 : null
         * zhiji_id : 0
         * add_time : 1519714484
         * sn : YJY
         * grade_type : 4
         * last_login_ip : null
         * last_login_time : null
         * brandclass_id : 3
         * work_types : 22
         * is_jiguang : 1
         * belong_shop_code : YJ0001
         * belong_shop_name : 艺匠北京一店
         * jg_username : 1411af55e939196dd52eb5c78f06437e
         */

        var id: Int = 0
        var staffnumber: String? = null
        var staffname: String? = null
        var password: String? = null
        var department_id: Int = 0
        var department_name: String? = null
        var main_position_id: Int = 0
        var main_position_name: String? = null
        var main_position_code: String? = null
        var position_id: String? = null
        var position_code: String? = null
        var zhiji_name: String? = null
        var turestaffname: String? = null
        var group_company_name: String? = null
        var brandclass: String? = null
        var shop_name: String? = null
        var shop_code: String? = null
        var setkey: String? = null
        var group_id: Int = 0
        var group_name: String? = null
        var is_group_admin: Int = 0
        var sex: String? = null
        var entry_date: String? = null
        var leave_date: String? = null
        var birthday: String? = null
        var iDcard: String? = null
        var nation: String? = null
        var place_origin: String? = null
        var political: String? = null
        var education: String? = null
        var graduation_school: String? = null
        var marital_status: String? = null
        var contract_endday: String? = null
        var telephone: String? = null
        var qq: String? = null
        var email: String? = null
        var weixin: String? = null
        var a_discount: String? = null
        var b_discount: String? = null
        var a_cost: String? = null
        var b_cost: String? = null
        var liaison_person: String? = null
        var liaison_tel: String? = null
        var salary_money: String? = null
        var social_security: String? = null
        var bank_cardnumber: String? = null
        var addressinfo: String? = null
        var remarkinfo: String? = null
        var work_type: String? = null
        var vice_work_type: String? = null
        var work_shop: String? = null
        var open_id: String? = null
        var open_id_name: String? = null
        var status: Int = 0
        var popedom1: String? = null
        var popedom2: String? = null
        var popedom3: String? = null
        var popedom4: String? = null
        var popedom5: String? = null
        var popedom6: String? = null
        var popedom7: String? = null
        var popedom8: String? = null
        var popedom9: String? = null
        var popedom10: String? = null
        var popedom11: String? = null
        var popedom12: String? = null
        var popedom13: String? = null
        var popedom14: String? = null
        var popedom15: String? = null
        var popedom16: String? = null
        var popedom17: String? = null
        var popedom18: String? = null
        var zhiji_id: Int = 0
        var add_time: String? = null
        var sn: String? = null
        var grade_type: Int = 0
        var last_login_ip: String? = null
        var last_login_time: String? = null
        var brandclass_id: Int = 0
        var work_types: String? = null
        var is_jiguang: Int = 0
        var belong_shop_code: String? = null
        var belong_shop_name: String? = null
        var jg_username: String? = null

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(this.id)
            dest.writeString(this.staffnumber)
            dest.writeString(this.staffname)
            dest.writeString(this.password)
            dest.writeInt(this.department_id)
            dest.writeString(this.department_name)
            dest.writeInt(this.main_position_id)
            dest.writeString(this.main_position_name)
            dest.writeString(this.main_position_code)
            dest.writeString(this.position_id)
            dest.writeString(this.position_code)
            dest.writeString(this.zhiji_name)
            dest.writeString(this.turestaffname)
            dest.writeString(this.group_company_name)
            dest.writeString(this.brandclass)
            dest.writeString(this.shop_name)
            dest.writeString(this.shop_code)
            dest.writeString(this.setkey)
            dest.writeInt(this.group_id)
            dest.writeString(this.group_name)
            dest.writeInt(this.is_group_admin)
            dest.writeString(this.sex)
            dest.writeString(this.entry_date)
            dest.writeString(this.leave_date)
            dest.writeString(this.birthday)
            dest.writeString(this.iDcard)
            dest.writeString(this.nation)
            dest.writeString(this.place_origin)
            dest.writeString(this.political)
            dest.writeString(this.education)
            dest.writeString(this.graduation_school)
            dest.writeString(this.marital_status)
            dest.writeString(this.contract_endday)
            dest.writeString(this.telephone)
            dest.writeString(this.qq)
            dest.writeString(this.email)
            dest.writeString(this.weixin)
            dest.writeString(this.a_discount)
            dest.writeString(this.b_discount)
            dest.writeString(this.a_cost)
            dest.writeString(this.b_cost)
            dest.writeString(this.liaison_person)
            dest.writeString(this.liaison_tel)
            dest.writeString(this.salary_money)
            dest.writeString(this.social_security)
            dest.writeString(this.bank_cardnumber)
            dest.writeString(this.addressinfo)
            dest.writeString(this.remarkinfo)
            dest.writeString(this.work_type)
            dest.writeString(this.vice_work_type)
            dest.writeString(this.work_shop)
            dest.writeString(this.open_id)
            dest.writeString(this.open_id_name)
            dest.writeInt(this.status)
            dest.writeString(this.popedom1)
            dest.writeString(this.popedom2)
            dest.writeString(this.popedom3)
            dest.writeString(this.popedom4)
            dest.writeString(this.popedom5)
            dest.writeString(this.popedom6)
            dest.writeString(this.popedom7)
            dest.writeString(this.popedom8)
            dest.writeString(this.popedom9)
            dest.writeString(this.popedom10)
            dest.writeString(this.popedom11)
            dest.writeString(this.popedom12)
            dest.writeString(this.popedom13)
            dest.writeString(this.popedom14)
            dest.writeString(this.popedom15)
            dest.writeString(this.popedom16)
            dest.writeString(this.popedom17)
            dest.writeString(this.popedom18)
            dest.writeInt(this.zhiji_id)
            dest.writeString(this.add_time)
            dest.writeString(this.sn)
            dest.writeInt(this.grade_type)
            dest.writeString(this.last_login_ip)
            dest.writeString(this.last_login_time)
            dest.writeInt(this.brandclass_id)
            dest.writeString(this.work_types)
            dest.writeInt(this.is_jiguang)
            dest.writeString(this.belong_shop_code)
            dest.writeString(this.belong_shop_name)
            dest.writeString(this.jg_username)
        }

        constructor() {}

        protected constructor(`in`: Parcel) {
            this.id = `in`.readInt()
            this.staffnumber = `in`.readString()
            this.staffname = `in`.readString()
            this.password = `in`.readString()
            this.department_id = `in`.readInt()
            this.department_name = `in`.readString()
            this.main_position_id = `in`.readInt()
            this.main_position_name = `in`.readString()
            this.main_position_code = `in`.readString()
            this.position_id = `in`.readString()
            this.position_code = `in`.readString()
            this.zhiji_name = `in`.readString()
            this.turestaffname = `in`.readString()
            this.group_company_name = `in`.readString()
            this.brandclass = `in`.readString()
            this.shop_name = `in`.readString()
            this.shop_code = `in`.readString()
            this.setkey = `in`.readString()
            this.group_id = `in`.readInt()
            this.group_name = `in`.readString()
            this.is_group_admin = `in`.readInt()
            this.sex = `in`.readString()
            this.entry_date = `in`.readString()
            this.leave_date = `in`.readString()
            this.birthday = `in`.readString()
            this.iDcard = `in`.readString()
            this.nation = `in`.readString()
            this.place_origin = `in`.readString()
            this.political = `in`.readString()
            this.education = `in`.readString()
            this.graduation_school = `in`.readString()
            this.marital_status = `in`.readString()
            this.contract_endday = `in`.readString()
            this.telephone = `in`.readString()
            this.qq = `in`.readString()
            this.email = `in`.readString()
            this.weixin = `in`.readString()
            this.a_discount = `in`.readString()
            this.b_discount = `in`.readString()
            this.a_cost = `in`.readString()
            this.b_cost = `in`.readString()
            this.liaison_person = `in`.readString()
            this.liaison_tel = `in`.readString()
            this.salary_money = `in`.readString()
            this.social_security = `in`.readString()
            this.bank_cardnumber = `in`.readString()
            this.addressinfo = `in`.readString()
            this.remarkinfo = `in`.readString()
            this.work_type = `in`.readString()
            this.vice_work_type = `in`.readString()
            this.work_shop = `in`.readString()
            this.open_id = `in`.readString()
            this.open_id_name = `in`.readString()
            this.status = `in`.readInt()
            this.popedom1 = `in`.readString()
            this.popedom2 = `in`.readString()
            this.popedom3 = `in`.readString()
            this.popedom4 = `in`.readString()
            this.popedom5 = `in`.readString()
            this.popedom6 = `in`.readString()
            this.popedom7 = `in`.readString()
            this.popedom8 = `in`.readString()
            this.popedom9 = `in`.readString()
            this.popedom10 = `in`.readString()
            this.popedom11 = `in`.readString()
            this.popedom12 = `in`.readString()
            this.popedom13 = `in`.readString()
            this.popedom14 = `in`.readString()
            this.popedom15 = `in`.readString()
            this.popedom16 = `in`.readString()
            this.popedom17 = `in`.readString()
            this.popedom18 = `in`.readString()
            this.zhiji_id = `in`.readInt()
            this.add_time = `in`.readString()
            this.sn = `in`.readString()
            this.grade_type = `in`.readInt()
            this.last_login_ip = `in`.readString()
            this.last_login_time = `in`.readString()
            this.brandclass_id = `in`.readInt()
            this.work_types = `in`.readString()
            this.is_jiguang = `in`.readInt()
            this.belong_shop_code = `in`.readString()
            this.belong_shop_name = `in`.readString()
            this.jg_username = `in`.readString()
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<DataBean> = object : Parcelable.Creator<DataBean> {
                override fun createFromParcel(source: Parcel): DataBean {
                    return DataBean(source)
                }

                override fun newArray(size: Int): Array<DataBean?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}
