

关于使用ButterKnife问题说明:
如果在Fragment中使用onCreateView的时候请按照下面的格式使用
	错误示范:
        unbinder = ButterKnife.bind(this, inflater.inflate(R.layout.fragment_take_data_fragment1, container, false));
        return inflater.inflate(R.layout.fragment_take_data_fragment1, container, false);
	正确示范:
		View inflate = inflater.inflate(R.layout.fragment_take_data_fragment1, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
	如果按照错误的示范使用,那么就会导致设置数据设置不上,其实是因为	ButterKnife绑定的view,和返回的View不是同一个对象导致的
		

说明:关于APP模块中compile说明
    compile project(':libzxing') //二维码扫描
    compile files('libs/json-lib-2.4-jdk15.jar')
    compile project(':libswipe') //侧滑关闭页面
    compile project(':libcalendar')//折叠日历
    // compile 'com.android.support:appcompat-v7:25.3.1'
    compile "com.android.support:appcompat-v7:${rootProject.ext.android.support_library_version}"
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.yanzhenjie:permission:1.1.2'//权限管理
    compile 'com.yanzhenjie:recyclerview-swipe:1.1.3'//可以侧滑加载更多,添加headview和footview的recyclerview
    compile 'com.yanzhenjie.nohttp:nohttp:1.1.4'//网络框架
    compile 'com.yanzhenjie.nohttp:okhttp:1.1.4'//网络框架底层
    compile 'com.alibaba:fastjson:1.1.54.android'//解析
    compile 'com.yanzhenjie:durban:1.0.1'//照片裁切
    compile 'com.yanzhenjie:statusview:1.0.3'//状态栏沉浸式
    compile 'com.yanzhenjie:album:2.0.2'//相册选择
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'//不解释
    compile 'io.reactivex.rxjava2:rxjava:2.1.5'
    compile 'org.greenrobot:eventbus:3.0.0'//消息传递
    compile 'com.yanzhenjie.zbar:camera:1.0.0'//相机预览
    compile 'com.yanzhenjie.zbar:zbar:1.0.0'//zbar二维码
    compile 'cat.ereza:customactivityoncrash:2.1.0'//崩溃自定义页面
    compile 'com.contrarywind:Android-PickerView:3.2.6'//选择框(日期,地点等)
    compile 'com.google.code.gson:gson:2.7'//josn
    compile 'in.srain.cube:ultra-ptr:1.0.11'//下拉刷新
    testCompile 'junit:junit:4.12'
    compile 'com.ashokvarma.android:bottom-navigation-bar:2.0.3'//底部table导航栏
    compile 'com.jakewharton:butterknife:8.4.0'//注解
    annotationProcessor 'com.jakewharton:butterknife-compiler:8+'//注解
    compile 'com.coldmoqiuli:banners:1.0.1'
    compile 'com.bigkoo:alertview:1.0.3'//仿ios弹窗http://blog.csdn.net/da_caoyuan/article/details/70216112
     compile 'com.github.donkingliang:GroupedRecyclerViewAdapter:1.2.0' recycleview 分组的适配器  https://github.com/donkingliang/GroupedRecyclerViewAdapter
     其实还有一个网上流传比较好的适配器BaseRecyclerViewAdapterHelper，这个有时间再换

