package com.suxuantech.erpsys.chat

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ZoomControls
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.search.core.PoiInfo
import com.baidu.mapapi.search.core.SearchResult
import com.baidu.mapapi.search.geocode.*
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.chat.location.service.LocationService


class BaiduMapActivity : AppCompatActivity() {
    var mapView: MapView? = null
    var myLocationConfiguration: MyLocationConfiguration? = null
    var mLoactionLatLng: Long = 0
    var isFirstLoc = true
    var mGeoCoder: GeoCoder? = null
    var  mLastAaddress:String?=""
    var locationService: LocationService? = null
    var   recycleLocation   :  RecyclerView?=null
    var currentLatLng: LatLng= LatLng(0.0, 0.0)
    var baiduMapAdapter  :BaiduMapAdapter  ?=null
    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private val mListener = object : BDLocationListener {
        override fun onReceiveLocation(location: BDLocation?) {
            if (null != location && location.locType != BDLocation.TypeServerError&&isFirstLoc) {
                val data = MyLocationData.Builder()
                        // .direction(mCurrentX)
                        .accuracy(location.radius)
                        .latitude(location.latitude)
                        .longitude(location.longitude)
                        .build()
                         mapView!!.map.setMyLocationData(data)
                // 是否第一次定位
//                if (isFirstLoc) {
                    currentLatLng = LatLng(location.latitude, location.longitude)
                    isFirstLoc = false
                isOnClick=false
                // 实现动画跳转(位置)
                    val mapStatusUpdate = MapStatusUpdateFactory.newLatLng (currentLatLng)
                    mapView!!.map.animateMapStatus( mapStatusUpdate)

//                }
            }
        }
        fun onConnectHotSpotMessage(s: String, i: Int) {}
    }


    // 地理编码监听器
    internal var GeoListener: OnGetGeoCoderResultListener = object : OnGetGeoCoderResultListener {
        override fun onGetGeoCodeResult(result: GeoCodeResult?) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                // 没有检索到结果
            }
            // 获取地理编码结果
        }

        override fun onGetReverseGeoCodeResult(result: ReverseGeoCodeResult?) {
            hd.sendEmptyMessageDelayed(0,2000)
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
            } else {
                val addressDetail = result.addressDetail
              var  city = addressDetail.city
                val address = result.address
                val poiList = result.poiList//这是附近的兴趣点信息
                baiduMapAdapter!!.upData(poiList)
                if (poiList != null) {
                    // mLastAaddress=address+poiList.get(0).name;
                    mLastAaddress = poiList[0].name
                } else {
                    mLastAaddress = address
                }
            }
        }
    }
    internal var hd: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            progressBar!!.visibility=View.GONE
            recycleLocation!!.visibility==View.VISIBLE
        }
    }
    var  progressBar:ProgressBar?=null
    var  isOnClick=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(applicationContext)
        setContentView(R.layout.activity_baidu_map)
        mapView = findViewById<MapView>(R.id.bmapView)
        progressBar = findViewById<ProgressBar>(R.id.address_loading)
        recycleLocation      = findViewById<RecyclerView>(R.id.rv_location)
        recycleLocation!!.layoutManager= LinearLayoutManager(baseContext)
        baiduMapAdapter = BaiduMapAdapter(R.layout.picker_item_place, null)
        baiduMapAdapter!!.setOnItemClickListener { adapter, view, position ->
            baiduMapAdapter!!.onChecked(position)
            var poiList=   baiduMapAdapter!!.data  as List<PoiInfo>
            currentLatLng =  poiList.get(position).location
            isOnClick=true
            // 实现动画跳转(位置)
            val mapStatusUpdate = MapStatusUpdateFactory.newLatLng (currentLatLng)
            mapView!!.map.animateMapStatus( mapStatusUpdate)
        }
        recycleLocation!!.adapter=baiduMapAdapter
        findViewById<ImageView>(R.id.img_current_location).setOnClickListener(View.OnClickListener {
            isFirstLoc=true
            locationService!!.stop()
            initLocation()
        })
        initMap()
        initLocation()
    }

    // 地图触摸事件监听器
    internal var touchListener: BaiduMap.OnMapTouchListener = BaiduMap.OnMapTouchListener { event ->
        // TODO Auto-generated method stub
        if (event.action == MotionEvent.ACTION_UP) {
            isOnClick=false
//                return false
        }
    }

    private fun initMap() {
        //地理编码
        mGeoCoder = GeoCoder.newInstance()
        mGeoCoder!!.setOnGetGeoCodeResultListener(GeoListener)
        mapView!!.map.setOnMapTouchListener(touchListener)
        //  支持自定义定位图标样式，替换定位icon
//        val mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_current_location)
        val mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.picker_map_geo_icon)
        myLocationConfiguration = MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker)
        // 开启定位图层
        mapView!!.map.isMyLocationEnabled = true
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mapView!!.map.setMyLocationConfiguration(myLocationConfiguration)
        //缩放级别
        mapView!!.map.setMapStatus(MapStatusUpdateFactory.zoomTo(17.0f))
        //隐藏比例尺
        mapView!!.showScaleControl(false)
        //隐藏缩放按钮
        mapView!!.showZoomControls(false)
        // 隐藏百度logo
        val count = mapView!!.childCount
        for (i in 0 until count) {
            val child = mapView!!.getChildAt(i)
            if (child is ImageView || child is ZoomControls) {
                child.visibility = View.INVISIBLE
            }
        }

        mapView!!.map.setOnMapStatusChangeListener(object : BaiduMap.OnMapStatusChangeListener {
            override fun onMapStatusChangeStart(p0: MapStatus?, p1: Int) {
            }
            override fun onMapStatusChange(p0: MapStatus?) {
            }
            override fun onMapStatusChangeStart(p0: MapStatus?) {
            }
            override fun onMapStatusChangeFinish(p0: MapStatus?) {
                currentLatLng= p0!!.target
                if (!isOnClick){
                    progressBar!!.visibility=View.VISIBLE
                    recycleLocation!!.visibility==View.GONE
                    //根据经纬度查询当前的附近东西
                    mGeoCoder!!.reverseGeoCode(ReverseGeoCodeOption().location(currentLatLng))
                }
            }
        })
    }
    private fun initLocation() {
        //定位服务
        locationService = LocationService(applicationContext)// JGApplication.locationService;
        locationService!!.registerListener(mListener)//是否应该在onStart中注册
        locationService!!.setLocationOption(locationService!!.defaultLocationClientOption)
        locationService!!.start()
    }

    /***
     * Stop location service
     */
    override fun onStop() {
        // TODO Auto-generated method stub
        locationService!!.stop() // 停止定位服务
        super.onStop()
    }

    override fun onStart() {
        // TODO Auto-generated method stub
        super.onStart()
        // -----------location config ------------
        // 获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        // 注册监听
        locationService!!.setLocationOption(locationService!!.defaultLocationClientOption)
    }



    override fun onResume() {
        super.onResume()
        locationService!!.start()
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationService!!.unregisterListener(mListener) // 注销掉监听
        locationService!!.stop()
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView!!.onDestroy()
    }
}
