package com.suxuantech.erpsys.chat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.ZoomControls
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.search.core.PoiInfo
import com.baidu.mapapi.search.core.SearchResult
import com.baidu.mapapi.search.geocode.*
import com.baidu.mapapi.search.poi.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.chat.location.service.LocationService
import com.suxuantech.erpsys.utils.ToastUtils


class BaiduMapActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    var mapView: MapView? = null
    var myLocationConfiguration: MyLocationConfiguration? = null
    var isFirstLoc = true
    var mGeoCoder: GeoCoder? = null
    var locationService: LocationService? = null
    var recycleLocation: RecyclerView? = null
    var currentLatLng: LatLng = LatLng(0.0, 0.0)
    var progressBar: ProgressBar? = null
    var isOnClick = false
    var isCurrentLocation = false
    var searchView: SearchView? = null
    var rl_map: RelativeLayout? = null
    var mPoiSearch: PoiSearch? = null
    var city = "中国"
    var street = ""
    var  scale=17.0f
    var keyWord: String = ""
    var hd: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            progressBar!!.visibility = View.GONE
            recycleLocation!!.visibility == View.VISIBLE
        }
    }
    // 地图触摸事件监听器
    var touchListener: BaiduMap.OnMapTouchListener = BaiduMap.OnMapTouchListener { event ->
        if (event.action == MotionEvent.ACTION_UP) {
            isOnClick = false
            isCurrentLocation = false
        }
    }
    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private val mListener = object : BDLocationListener {
        override fun onReceiveLocation(location: BDLocation?) {
            if (null != location && location.locType != BDLocation.TypeServerError && isFirstLoc) {
                val data = MyLocationData.Builder()
                        // .direction(mCurrentX)
                        .accuracy(location.radius)
                        .latitude(location.latitude)
                        .longitude(location.longitude)
                        .build()
                mapView!!.map.setMyLocationData(data)
                currentLatLng = LatLng(location.latitude, location.longitude)
                isFirstLoc = false
                isOnClick = false
                isCurrentLocation = true
                // 实现动画跳转(位置)
                val mapStatusUpdate = MapStatusUpdateFactory.newLatLng(currentLatLng)
                ToastUtils.show("" + currentLatLng.longitude + "====" + currentLatLng.latitude)
                mapView!!.map.animateMapStatus(mapStatusUpdate)
            }
        }
    }
    // 地理编码监听器
    var GeoListener: OnGetGeoCoderResultListener = object : OnGetGeoCoderResultListener {
        override fun onGetGeoCodeResult(result: GeoCodeResult?) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                // 没有检索到结果
                //没有找到检索结果
            }
            // 获取地理编码结果
        }

        override fun onGetReverseGeoCodeResult(result: ReverseGeoCodeResult?) {
            hd.sendEmptyMessageDelayed(0, 2000)
            baiduMapAdapter!!.setEnableLoadMore(false)
            baiduMapAdapter!!.setCurrent(isCurrentLocation)
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
                baiduMapAdapter!!.upData(null)
            } else {
                val addressDetail = result.addressDetail
                city = addressDetail.city
                val province =if(addressDetail.city.equals(addressDetail.province) )  "" else addressDetail.province
                street=province +addressDetail.city+addressDetail.district+addressDetail.street+addressDetail.streetNumber
                scale=mapView!!.map.mapStatus.zoom
                val poiList = result.poiList//这是附近的兴趣点信息
                if(poiList!=null){
                    val poiInfo = poiList.get(0)
                    currentLatLng =poiInfo.location
                    city =poiInfo.city
                    street =poiInfo.address+poiInfo.name
                }
                baiduMapAdapter!!.upData(poiList)
            }
        }
    }

    /**
     * 伴生类对象,作为静态存在,与static  类似
     */
    companion object {
        var baiduMapAdapter: BaiduMapAdapter? = null
        var page = 20
        var pageNum = 0
    }

    /**
     * Poi搜索结果
     */
    object ongetPoiSearchResult : OnGetPoiSearchResultListener {
        override fun onGetPoiResult(p0: PoiResult?) {
            if (p0!!.error != SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                baiduMapAdapter!!.setEnableLoadMore(true)
                baiduMapAdapter!!.setCurrent(false)
                if (pageNum == 0) {
                    baiduMapAdapter!!.upData(p0.allPoi)
                    //搜索结果没点击默认就不选中任何
                    if (p0.allPoi != null) {
                        baiduMapAdapter!!.posstionChecked = p0.allPoi.size
                    }
                } else {
                    baiduMapAdapter!!.append(p0.allPoi)
                }
                if (p0.allPoi == null || p0.allPoi.size > page) {
                    baiduMapAdapter!!.loadMoreEnd()
                } else {
                    baiduMapAdapter!!.loadMoreComplete()
                }

            } else {
                baiduMapAdapter!!.loadMoreEnd()
            }
        }

        /**
         *   * 在传入city检索时，返回的结果太多，涉及多个城市，可以将扩大检索的城市list罗列，
         * 让用户点击选择，再针对选定城市进行二次检索（如果只有一个城市，后端直接进行二次检索）。
         */
        override fun onGetPoiDetailResult(p0: PoiDetailResult?) {
            if (p0!!.error != SearchResult.ERRORNO.NO_ERROR) {
                //详情检索失败
                // result.error请参考SearchResult.ERRORNO
            } else {
                //检索成功
            }
        }

        /**
         * 室内结果
         */
        override fun onGetPoiIndoorResult(p0: PoiIndoorResult?) {
        }
    }

    /**
     * 当用户在输入法中点击搜索按钮时,或者输入回车时,调用这个方法，发起实际的搜索功能
     * @param query
     * @return
     */
    override fun onQueryTextSubmit(query: String?): Boolean {
        isCurrentLocation = false
        pageNum = 0
        keyWord = query.toString()
        mPoiSearch!!.searchInCity((PoiCitySearchOption())
                .city(city)
                .keyword(keyWord).pageCapacity(page)
                .pageNum(pageNum))
        searchView!!.clearFocus()
        return true
    }

    /**
     * 每一次输入字符，都会调用这个方法，实现搜索的联想功能
     * @param newText
     * @return
     */
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> this.finish()//真正实现回退功能的代码
            R.id.menu_send -> {
                val intent = Intent()
                intent.putExtra("address", street)
                intent.putExtra("latitude", currentLatLng.latitude)
                intent.putExtra("longitude", currentLatLng.longitude)
                intent.putExtra("scale", scale)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(applicationContext)
        setContentView(R.layout.activity_baidu_map)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)//这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
        title = "位置"
        mapView = findViewById<MapView>(R.id.bmapView)
        rl_map = findViewById<RelativeLayout>(R.id.rl_map)
        progressBar = findViewById<ProgressBar>(R.id.address_loading)
        recycleLocation = findViewById<RecyclerView>(R.id.rv_location)
        recycleLocation!!.layoutManager = LinearLayoutManager(baseContext)
        baiduMapAdapter = BaiduMapAdapter(R.layout.picker_item_place, null)
        baiduMapAdapter!!.setEnableLoadMore(false)
        baiduMapAdapter!!.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            if (!isCurrentLocation) {
                pageNum++
                mPoiSearch!!.searchInCity((PoiCitySearchOption())
                        .city(city)
                        .keyword(keyWord).pageCapacity(page)
                        .pageNum(pageNum))
            }
        }, recycleLocation)
            baiduMapAdapter!!.setOnItemClickListener { adapter, view, position ->
            baiduMapAdapter!!.onChecked(position)
            var poiList = baiduMapAdapter!!.data as List<PoiInfo>
            currentLatLng = poiList.get(position).location
            isOnClick = true
            city = poiList.get(position).city
            street=poiList.get(position).city+poiList.get(position).address+poiList.get(position).name
            scale=mapView!!.map.mapStatus.zoom
                mapView!!.map.animateMapStatus(MapStatusUpdateFactory.newLatLng(currentLatLng))
        }
        recycleLocation!!.adapter = baiduMapAdapter
        findViewById<ImageView>(R.id.img_current_location).setOnClickListener(View.OnClickListener {
            isFirstLoc = true
            locationService!!.stop()
            initLocation()
        })
        initMap()
        initLocation()
    }

    /**
     * Toolbar菜单
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.map_search, menu)
        //设置搜索输入框的步骤
        //1.查找指定的MemuItem
        val menuItem = menu.findItem(R.id.action_search)
        //2.设置SearchView v7包方式
        val view = MenuItemCompat.getActionView(menuItem)
        if (view != null) {
            searchView = view as SearchView
            //4.设置SearchView 的查询回调接口
            searchView!!.setOnQueryTextListener(this)
            //在搜索输入框没有显示的时候 点击Action ,回调这个接口，并且显示输入框
            searchView!!.setOnSearchClickListener(View.OnClickListener { })
            searchView!!.setOnCloseListener(object : SearchView.OnCloseListener {
                override fun onClose(): Boolean {
                    return false
                }
            })
        }
        return true
    }

    /**
     * 初始化地图
     */
    private fun initMap() {
        //地理编码
        mGeoCoder = GeoCoder.newInstance()
        mPoiSearch = PoiSearch.newInstance()
        mPoiSearch!!.setOnGetPoiSearchResultListener(ongetPoiSearchResult)
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
        mapView!!.map.setMapStatus(MapStatusUpdateFactory.zoomTo(scale))
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
                currentLatLng = p0!!.target
                if (!isOnClick) {
                    progressBar!!.visibility = View.VISIBLE
                    recycleLocation!!.visibility == View.GONE
                    //根据经纬度查询当前的附近东西
                    mGeoCoder!!.reverseGeoCode(ReverseGeoCodeOption().location(currentLatLng))
                }
            }
        })
    }

    /**
     * 定位服务
     */
    private fun initLocation() {
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
        mapView = null
    }
}

