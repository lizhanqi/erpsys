package com.suxuantech.erpsys.chat.keyboard.weight;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.KeyboardUtils;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.utils.DensityUtils;

import java.io.File;
import java.util.ArrayList;

import cn.jmessage.android.uikit.recordvoice.RecordVoiceButton;


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
 * @author Created by 李站旗 on 2018/3/11 0011 13:10 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */


public class KeyBoardView extends AutoHeightLayout {
    private ImageView rcSwitchToMenu;
    private ImageView rcVoiceToggle;
    private EditText rcEditText;
    private ImageView rcEmoticonToggle;
    private ImageView rcPluginToggle;
    private FrameLayout container;
    private RecordVoiceButton btnAudioInput;
    private LinearLayout llInputText;
    private TextView tvSend;
    private View pluginViews;
    private View emotionPage;
    private ViewPager vpEmotion;
    private ImageView btnFitEmotion;
    private RecyclerView rvEmotionType;
    private BaseRecyclerAdapter typeAdaputer;
    private RelativeLayout input;
    private Editable tempText;

    //语音录入中
    public interface AudioInput {
        void onAudioInputClick();
    }

    //发送按钮
    public interface SendListen {
        void send(Editable s);
    }

    SendListen sendListen;

    public void setSendListen(SendListen sendListen) {
        this.sendListen = sendListen;
    }

    AudioInput audioInput;

    public void setAudioInput(AudioInput audioInput) {
        this.audioInput = audioInput;
    }

    RecordListener recordListener;

    public void setRecordListener(RecordListener recordListener) {
        this.recordListener = recordListener;
    }

    public interface RecordListener {
        void onRecordFinished(int duration, String path);
    }

    public KeyBoardView(Context context) {
        this(context, null);
    }

    public KeyBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LinearLayout linearLayout = new LinearLayout(context);
        ViewGroup.LayoutParams linParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        input = (RelativeLayout) inflate(context, R.layout.rc_ext_extension_bar, null);
        rcSwitchToMenu = input.findViewById(R.id.rc_switch_to_menu);
        rcVoiceToggle = input.findViewById(R.id.rc_voice_toggle);
        rcEditText = input.findViewById(R.id.rc_edit_text);
        rcEmoticonToggle = input.findViewById(R.id.rc_emoticon_toggle);
        rcPluginToggle = input.findViewById(R.id.rc_plugin_toggle);
        btnAudioInput = input.findViewById(R.id.rc_audio_input);
        llInputText = input.findViewById(R.id.ll_input_text);
        tvSend = input.findViewById(R.id.tv_send);
        FrameLayout.LayoutParams headParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DensityUtils.px2dp(getContext(), 800));
        container = new FrameLayout(context);
        container.setVisibility(GONE);
        container.setLayoutParams(headParams);
        //导航
        emotionPage = inflate(getContext(), R.layout.emotion_page, null);
        vpEmotion = emotionPage.findViewById(R.id.vp_emotion);
        btnFitEmotion = emotionPage.findViewById(R.id.btn_fit_emotion);
        rvEmotionType = emotionPage.findViewById(R.id.rv_emotion_type);
        //设置录音文件存放位置
        File rootDir = getContext().getFilesDir();
        String fileDir = rootDir.getAbsolutePath() + "/voice";
        btnAudioInput.setFilePath(fileDir);
        setOnclick();
        setAdaputer();
        linearLayout.addView(input);
        linearLayout.addView(container);
        addView(linearLayout);
        init();
    }
    /**
     * 判断是否显示了键盘
     */
    private boolean isSoftKeyboardShown() {
        return getSoftKeyboardHeight() != 0;
    }


    /**
     * 获取键盘的高度
     */
    private int getSoftKeyboardHeight() {
        Rect rect = new Rect();
        ((Activity)  getContext() ).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //屏幕当前可见高度，不包括状态栏
        int displayHeight = rect.bottom - rect.top;
        //屏幕可用高度
        int availableHeight = getAvailableScreenHeight((Activity) getContext());//ScreenUtils.getAvailableScreenHeight(activity);
        //用于计算键盘高度
        int softInputHeight = availableHeight - displayHeight -getStatusBarHeight((Activity) getContext());// ScreenUtils.getStatusBarHeight(activity);
        return softInputHeight;
    }
    /**
     * 状态栏高度
     */
    public static int getStatusBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }
    /**
     * 返回屏幕可用高度
     * 当显示了虚拟按键时，会自动减去虚拟按键高度
     */
    public static int getAvailableScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
    /**
     * 点击返回键时先隐藏表情布局
     *
     * @return
     */
    public boolean interceptBackPress() {
        if (container.isShown()) {
            hideEmotionLayout(false);
            return true;
        }
        return false;
    }

    public void init() {
        //监听返回按键,前提是  rcEditText.requestFocus();,已经获得到了光标焦点
        rcEditText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == event.getKeyCode()) {
                    return interceptBackPress();
                }
                return false;
            }
        });
        rcPluginToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pluginViews.isShown()) {
                    lockContentHeight();//显示软件盘时，锁定内容高度，防止跳闪。
                    hideEmotionLayout(true);//隐藏表情布局，显示软件盘
                    unlockContentHeightDelayed();//软件盘显示后，释放内容高度
                } else {
                    switchMode(false);
                    if (tempText!=null){
                        rcEditText.setText(tempText);
                        rcEditText.setSelection(rcEditText.getText().length());
                        tempText=null;
                    }
                    if (isSoftKeyboardShown()) {//同上
                        lockContentHeight();
                        showPlugins();
                        unlockContentHeightDelayed();
                    } else {
                        showPlugins();//两者都没显示，直接显示表情布局
                    }
                }
            }
        });
        rcEmoticonToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcEditText.requestFocus();//获取焦点 光标出现
                if (emotionPage.isShown()) {
                    lockContentHeight();//显示软件盘时，锁定内容高度，防止跳闪。
                    hideEmotionLayout(true);//隐藏表情布局，显示软件盘
                    unlockContentHeightDelayed();//软件盘显示后，释放内容高度
                } else {
                    if (isSoftKeyboardShown()) {//同上
                        lockContentHeight();
                        showEmotionKeyBoard();
                        unlockContentHeightDelayed();
                    } else {
                        showEmotionKeyBoard();//两者都没显示，直接显示表情布局
                    }
                }
            }
        });

        rcEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && container.isShown()) {
                    lockContentHeight();//显示软件盘时，锁定内容高度，防止跳闪。
                    hideEmotionLayout(true);//隐藏表情布局，显示软件盘
                    //软件盘显示后，释放内容高度
                    rcEditText.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            unlockContentHeightDelayed();
                        }
                    }, 200L);
                }
                return false;
            }
        });
    }

    /**
     * 隐藏表情布局
     *
     * @param showSoftInput 是否显示软件盘
     */
    private void hideEmotionLayout(boolean showSoftInput) {
        if (container.isShown()) {
            container.setVisibility(View.GONE);
            if (showSoftInput) {
                showSoftInput();
            }
        }
    }


    /**
     * 编辑框获取焦点，并显示软件盘
     */
    private void showSoftInput() {
        rcEditText.requestFocus();
        rcEditText.post(new Runnable() {
            @Override
            public void run() {
                KeyboardUtils.showSoftInput(rcEditText);
            }
        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(size, MeasureSpec.UNSPECIFIED));
    }

    public EditText getRcEditText() {
        return rcEditText;
    }

    private void setAdaputer() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvEmotionType.setLayoutManager(linearLayoutManager);
        DefaultItemDecoration defaultItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.textHint_99));
        rvEmotionType.addItemDecoration(defaultItemDecoration);
        typeAdaputer = new BaseRecyclerAdapter<Drawable>(rvEmotionType, face, R.layout.item_emotion_type) {
            @Override
            public void convert(RecyclerHolder holder, Drawable item, int position, boolean isScrolling) {
                ImageView view = holder.getView(R.id.img_emotion_type);
                view.setImageDrawable(face.get(position));
                if (current == position) {
                    view.setBackgroundColor(getResources().getColor(R.color.translucent_black_90));
                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        };
        typeAdaputer.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                vpEmotion.setCurrentItem(position);
            }
        });
        vpEmotion.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                current = position;
                typeAdaputer.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onSoftKeyboardHeightChanged(int i) {
        keyBoardHeight = i;
    }

    int keyBoardHeight = 850;

    private void setOnclick() {
        btnAudioInput.setRecordListener(new RecordVoiceButton.OnRecordVoiceListener() {
            @Override
            public void onRecordFinished(int duration, String path) {
                if (recordListener != null) {
                    recordListener.onRecordFinished(duration, path);
                }
            }
        });
        btnFitEmotion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "添加表情", Toast.LENGTH_SHORT).show();
            }
        });

        tvSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sendListen != null) {
                    sendListen.send(rcEditText.getText());
                }
                rcEditText.setText("");
            }
        });
        rcEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    tvSend.setVisibility(VISIBLE);
                    rcPluginToggle.setVisibility(GONE);
                } else {
                    tvSend.setVisibility(GONE);
                    rcPluginToggle.setVisibility(VISIBLE);
                }
            }
        });
        btnAudioInput.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (audioInput != null) {
                        audioInput.onAudioInputClick();
                    }
                }
                return false;
            }
        });
        //到文字
        rcSwitchToMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempText!=null){
                    rcEditText.setText(tempText);
                    rcEditText.setSelection(rcEditText.getText().length());
                    tempText=null;
                }
                switchMode(false);
                getRootView().requestLayout();
                KeyboardUtils.showSoftInput(rcEditText);
            }
        });
        //到语音
        rcVoiceToggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tempText = rcEditText.getText();
                rcEditText.setText("");
                switchMode(true);
                hideEmotionKeyBoard();
                KeyboardUtils.hideSoftInput(getRootView());
            }
        });
    }

    private void switchMode(boolean isAudio) {
        if (isAudio) {
            rcSwitchToMenu.setVisibility(VISIBLE);
            llInputText.setVisibility(GONE);
            rcVoiceToggle.setVisibility(GONE);
            btnAudioInput.setVisibility(VISIBLE);
        } else {
            rcSwitchToMenu.setVisibility(GONE);
            llInputText.setVisibility(VISIBLE);
            rcVoiceToggle.setVisibility(VISIBLE);
            btnAudioInput.setVisibility(GONE);
            rcEditText.requestFocus();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ViewGroup parent = (ViewGroup) getParent();
//        EmotionKeyboard.with((Activity) getContext())
//                .setEmotionView(container).bindToEditText(rcEditText).bindToEmotionButton(rcEmoticonToggle)
//                .bindToContent(parent.getChildAt(0)).bu();
    }

    private void hideEmotionKeyBoard() {
        container.setVisibility(GONE);
    }

    /**
     * 设置插件的页面,这里不再管理了
     *
     * @param pluginViews
     */
    public void setPluginViews(View pluginViews) {
        this.pluginViews = pluginViews;
    }

    /**
     *
     */
    private void showPlugins() {
        KeyboardUtils.hideSoftInput(this);
        if (keyBoardHeight != 0) {
            ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
            layoutParams.height = keyBoardHeight;
            container.setLayoutParams(layoutParams);
        }
        container.setVisibility(VISIBLE);
        container.removeAllViews();
        if (pluginViews != null) {
            container.addView(pluginViews);
            container.postInvalidate();
        }
    }


    /**
     *
     */
    private void showEmotionKeyBoard() {
        KeyboardUtils.hideSoftInput(this);
        if (keyBoardHeight != 0) {
            ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
            layoutParams.height = keyBoardHeight;
            container.setLayoutParams(layoutParams);
        }
        container.setVisibility(VISIBLE);
        container.removeAllViews();
        emotionPage.setVisibility(VISIBLE);
        if (emotionPage != null) {
            container.addView(emotionPage);
            container.postInvalidate();
        }
    }

    ArrayList<Drawable> face = new ArrayList<>();
    ArrayList<View> emotionView = new ArrayList<>();
    int current;

    public void addEmotionView(View emotion, Drawable bitmap) {
        face.add(bitmap);
        if (emotion == null) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(bitmap);
            emotionView.add(imageView);
        } else {
            emotionView.add(emotion);
        }
        typeAdaputer.refresh(face);
        VTypeAdapter vTypeAdapter = new VTypeAdapter();
        vpEmotion.setAdapter(vTypeAdapter);
    }

    class VTypeAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return emotionView.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(emotionView.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(emotionView.get(position));
            return emotionView.get(position);
        }
    }

    /**
     * 锁定内容高度，防止跳闪
     */
    private void lockContentHeight() {
        ViewGroup parent = (ViewGroup) getParent();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) parent.getChildAt(0).getLayoutParams();
        params.height = parent.getChildAt(0).getHeight();
        params.weight = 0.0F;
    }

    /**
     * 释放被锁定的内容高度
     */
    private void unlockContentHeightDelayed() {
        rcEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewGroup parent = (ViewGroup) getParent();
                ((LinearLayout.LayoutParams) parent.getChildAt(0).getLayoutParams()).weight = 1.0F;
            }
        }, 100L);
    }

}
