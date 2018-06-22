签名文件内容:
MD5:
    CD:85:4B:4A:A8:10:61:D2:E5:3B:45:01:04:E5:CA
    CD854B4AA81061D2E53B450104E5CA
SHA1 
    5D:AE:68:52:39:F4:B7:A1:01:28:8A:F3:C8:FE:DB:EF:DB:B4:1E:3F
    5DAE685239F4B7A101288AF3C8FEDBEFDBB41E3F
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
        //另一个刷新  效果爽到爆 https://github.com/scwang90/SmartRefreshLayout
//TextView可以通过html有效加载的标签
  //这里换行标签是<br/>
  //font 的seize 无效 color可以
 //small有效
 //strike删除线有效
 //big有效 ，b也行
 //上标文本sup
  //下表文本sup
  例如：
        textView.setText(
                Html.fromHtml("<font size=\"60\" color='"+getResources().getColor(R.color.myValue_33)+"'>今天天气好吗？</font>" +
                "<font size=\"12\"color='"+getResources().getColor(R.color.textHint_99)+"'><br/>挺好的<sub><small>fadf</small></sub></font>"));


===================安卓空格说明===========================
空格： & #160;
窄空格： & #8201;
一个汉字宽度的空格：& #160;& #160;& #8201;

用两个空格（& #160;& #160;）占一个汉字的宽度时，两个空格比一个汉字略窄，三个空格（& #160;& #160;& #160;）比一个汉字略宽

在实际使用中需要灵活使用 和 的组合。
android:text=”真实姓名:”
android:text=”身& #160;& #160;份& #160;& #160;证:”

TextView实现首行缩进的方法：
•在string资源文件中，在文字的前面加入”\u3000\u3000”即可实现首行缩进
•在Java代码中，使用setText(“\u3000\u3000”+xxxxx);
  /**
  *  看一看menu.add方法的参数：
  *  第一个int类型的group ID参数，代表的是组概念，你可以将几个菜单项归为一组，以便更好的以组的方式管理你的菜单按钮。
  *  第二个int类型的item ID参数，代表的是项目编号。这个参数非常重要，一个item ID对应一个menu中的选项。在后面使用菜单的时候，
  就靠这个item ID来判断你使用的是哪个选项。menu.findItem(1);
  *  第三个int类型的order ID参数，代表的是菜单项的显示顺序。默认是0，表示菜单的显示顺序就是按照add的显示顺序来显示。
  *  第四个String类型的title参数，表示选项中显示的文字。
   */

    /**
     * 华为start
     */
//判断是否是华为刘海屏
    public static boolean hasNotchInScreen(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);

        } catch (ClassNotFoundException e) {
            Log.e("test", "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("test", "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
            Log.e("test", "hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }

    //获取华为刘海的高宽
    public static int[] getNotchSize(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("haha", "getNotchSize ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("haha", "getNotchSize NoSuchMethodException");
        } catch (Exception e) {
            Log.e("haha", "getNotchSize Exception");
        } finally {
            return ret;
        }
    }

//常用隐式意图:
//toolbar菜单中图标显示
((MenuBuilder) mToolbar.getMenu()).setOptionalIconsVisible(true);
