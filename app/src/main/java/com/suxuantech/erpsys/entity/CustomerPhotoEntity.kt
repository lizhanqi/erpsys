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
class CustomerPhotoEntity : BaseResult2<CustomerPhotoEntity.DataBean>() {


    /**
     * Code : 200
     * Data : [{"id":16,"orderId":"SY18012000007","photodate":"2018-01-31 00:00:00.000","phototime":"1300","cameraman":"","photoassistant":"","lighting_engineer":"","designer":"","mentor":"","phototype":"","photostate":"","dresser":"","photonote":"","photocount":null,"locationcount":null,"Interiorcount":null,"shop_name":null,"shop_code":"ZX002","secretaireman":"纪金秀","topic":null,"returndate":null,"getdate":null,"choosedate":"2018-02-24 00:00:00.000","dress_count":null,"photo_shop_code":null,"shexiangshi":null,"jianjishi":null,"zhuozhuangshi":null,"huazhuangzhuli":null,"haijingshu":null,"sheyingshiJB":null,"huazhuangshiJB":null,"dresschoosetime":"1000","dressgettime":"","dressreturtime":"","islast":0,"photostate1":""},{"id":24,"orderId":"SY18012000007","photodate":"2018-01-30 00:00:00.000","phototime":"1300","cameraman":"","photoassistant":"","lighting_engineer":"杨过","designer":"","mentor":"","phototype":"拍照","photostate":"全未拍","dresser":"","photonote":"","photocount":"0","locationcount":0,"Interiorcount":0,"shop_name":"沈阳时尚经典婚纱店","shop_code":"ZX002","secretaireman":"王语嫣","topic":"","returndate":null,"getdate":null,"choosedate":"2018-02-24 00:00:00.000","dress_count":0,"photo_shop_code":"ZX002","shexiangshi":null,"jianjishi":null,"zhuozhuangshi":null,"huazhuangzhuli":null,"haijingshu":null,"sheyingshiJB":null,"huazhuangshiJB":null,"dresschoosetime":"1000","dressgettime":"","dressreturtime":"","islast":1,"photostate1":"全未拍"}]
     */

    class DataBean : Parcelable {
        /**
         * id : 16
         * orderId : SY18012000007
         * photodate : 2018-01-31 00:00:00.000
         * phototime : 1300
         * cameraman :
         * photoassistant :
         * lighting_engineer :
         * designer :
         * mentor :
         * phototype :
         * photostate :
         * dresser :
         * photonote :
         * photocount : null
         * locationcount : null
         * Interiorcount : null
         * shop_name : null
         * shop_code : ZX002
         * secretaireman : 纪金秀
         * topic : null
         * returndate : null
         * getdate : null
         * choosedate : 2018-02-24 00:00:00.000
         * dress_count : null
         * photo_shop_code : null
         * shexiangshi : null
         * jianjishi : null
         * zhuozhuangshi : null
         * huazhuangzhuli : null
         * haijingshu : null
         * sheyingshiJB : null
         * huazhuangshiJB : null
         * dresschoosetime : 1000
         * dressgettime :
         * dressreturtime :
         * islast : 0
         * photostate1 :
         */

        var id: Int = 0
        var orderId: String? = null
        var photodate: String? = null
        var phototime: String? = null
        var cameraman: String? = null
        var photoassistant: String? = null
        var lighting_engineer: String? = null
        var designer: String? = null
        var mentor: String? = null
        var phototype: String? = null
        var photostate: String? = null
        var dresser: String? = null
        var photonote: String? = null
        var photocount: String? = null
        var locationcount: String? = null
        var interiorcount: String? = null
        var shop_name: String? = null
        var shop_code: String? = null
        var secretaireman: String? = null
        var topic: String? = null
        var returndate: String? = null
        var getdate: String? = null
        var choosedate: String? = null
        var dress_count: String? = null
        var photo_shop_code: String? = null
        var shexiangshi: String? = null
        var jianjishi: String? = null
        var zhuozhuangshi: String? = null
        var huazhuangzhuli: String? = null
        var haijingshu: String? = null
        var sheyingshiJB: String? = null
        var huazhuangshiJB: String? = null
        var dresschoosetime: String? = null
        var dressgettime: String? = null
        var dressreturtime: String? = null
        var islast: Int = 0
        var photostate1: String? = null

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(this.id)
            dest.writeString(this.orderId)
            dest.writeString(this.photodate)
            dest.writeString(this.phototime)
            dest.writeString(this.cameraman)
            dest.writeString(this.photoassistant)
            dest.writeString(this.lighting_engineer)
            dest.writeString(this.designer)
            dest.writeString(this.mentor)
            dest.writeString(this.phototype)
            dest.writeString(this.photostate)
            dest.writeString(this.dresser)
            dest.writeString(this.photonote)
            dest.writeString(this.photocount)
            dest.writeString(this.locationcount)
            dest.writeString(this.interiorcount)
            dest.writeString(this.shop_name)
            dest.writeString(this.shop_code)
            dest.writeString(this.secretaireman)
            dest.writeString(this.topic)
            dest.writeString(this.returndate)
            dest.writeString(this.getdate)
            dest.writeString(this.choosedate)
            dest.writeString(this.dress_count)
            dest.writeString(this.photo_shop_code)
            dest.writeString(this.shexiangshi)
            dest.writeString(this.jianjishi)
            dest.writeString(this.zhuozhuangshi)
            dest.writeString(this.huazhuangzhuli)
            dest.writeString(this.haijingshu)
            dest.writeString(this.sheyingshiJB)
            dest.writeString(this.huazhuangshiJB)
            dest.writeString(this.dresschoosetime)
            dest.writeString(this.dressgettime)
            dest.writeString(this.dressreturtime)
            dest.writeInt(this.islast)
            dest.writeString(this.photostate1)
        }

        constructor() {}

        protected constructor(`in`: Parcel) {
            this.id = `in`.readInt()
            this.orderId = `in`.readString()
            this.photodate = `in`.readString()
            this.phototime = `in`.readString()
            this.cameraman = `in`.readString()
            this.photoassistant = `in`.readString()
            this.lighting_engineer = `in`.readString()
            this.designer = `in`.readString()
            this.mentor = `in`.readString()
            this.phototype = `in`.readString()
            this.photostate = `in`.readString()
            this.dresser = `in`.readString()
            this.photonote = `in`.readString()
            this.photocount = `in`.readString()
            this.locationcount = `in`.readString()
            this.interiorcount = `in`.readString()
            this.shop_name = `in`.readString()
            this.shop_code = `in`.readString()
            this.secretaireman = `in`.readString()
            this.topic = `in`.readString()
            this.returndate = `in`.readString()
            this.getdate = `in`.readString()
            this.choosedate = `in`.readString()
            this.dress_count = `in`.readString()
            this.photo_shop_code = `in`.readString()
            this.shexiangshi = `in`.readString()
            this.jianjishi = `in`.readString()
            this.zhuozhuangshi = `in`.readString()
            this.huazhuangzhuli = `in`.readString()
            this.haijingshu = `in`.readString()
            this.sheyingshiJB = `in`.readString()
            this.huazhuangshiJB = `in`.readString()
            this.dresschoosetime = `in`.readString()
            this.dressgettime = `in`.readString()
            this.dressreturtime = `in`.readString()
            this.islast = `in`.readInt()
            this.photostate1 = `in`.readString()
        }

        companion object {

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
