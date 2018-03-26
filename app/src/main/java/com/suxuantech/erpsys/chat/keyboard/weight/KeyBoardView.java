package com.suxuantech.erpsys.chat.keyboard.weight;

import android.content.Context;
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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewGroup parent = (ViewGroup) getParent();
                ViewGroup viewGroup= (ViewGroup) parent.getChildAt(0);
                viewGroup.getChildAt(0)
                        .setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                       // hideEmotionKeyBoard();
                  //      KeyboardUtils.hideSoftInput(rcEditText);
                        return false;
                    }
                });
            }
        });
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
        rcEmoticonToggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmotionKeyBoard();
            }
        });

        rcEditText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                        hideEmotionKeyBoard();
                        return false;
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
        rcPluginToggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlugins();
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
                rcSwitchToMenu.setVisibility(GONE);
                llInputText.setVisibility(VISIBLE);
                rcVoiceToggle.setVisibility(VISIBLE);
                btnAudioInput.setVisibility(GONE);
                getRootView().requestLayout();
                KeyboardUtils.showSoftInput(rcEditText);
            }
        });
        //到语音
        rcVoiceToggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rcSwitchToMenu.setVisibility(VISIBLE);
                llInputText.setVisibility(GONE);
                rcVoiceToggle.setVisibility(GONE);
                btnAudioInput.setVisibility(VISIBLE);
                hideEmotionKeyBoard();
                KeyboardUtils.hideSoftInput(getRootView());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
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
     * 锁定内容View以防止跳闪
     * 直接设置为当前高度(不适用于先点击输入框,也就是表情并没有展示)
     */
    private void lockContentViewHeight() {
        ViewGroup parent = (ViewGroup) getParent();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getChildAt(0).getLayoutParams();
        layoutParams.height = parent.getChildAt(0).getHeight();
        layoutParams.weight = 0;
    }

    /**
     * 释放锁定的内容View
     * 直接占满
     */
    private void unlockContentViewHeight() {
        ViewGroup parent = (ViewGroup) getParent();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getChildAt(0).getLayoutParams();
        layoutParams.weight = 1;
    }
}
