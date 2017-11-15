package com.suxuantech.erpsys.test;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.StatusImmersedBaseActivity;

import java.util.List;

public class ToolbarTestActivity extends StatusImmersedBaseActivity {

    private Toolbar toolbar;

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_test);
        idSetOnClick(R.id.title);
        idSetOnClick(R.id.Subtitle);
        idSetOnClick(R.id.logo);
        idSetOnClick(R.id.NavigationIcon);
        idSetOnClick(R.id.menu);
        idSetOnClick(R.id.addMenu);
        idSetOnClick(R.id.addView);
        idSetOnClick(R.id.inflateMenu);
        toolbar = setSupportToolbar();
        toolbar.setNavigationOnClickListener(this);
  /*      toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
           public boolean onMenuItemClick(MenuItem item) {
            toast(item.toString());
                return false;
            }
        });
*/
   }

    /**
     * 给Toolbar初始化菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.testmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //导航返回按钮
  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
       if(item.getItemId() == android.R.id.home)
        {
           toast("返回按钮");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.title:
                toolbar.setTitle("标题");
                break;
            case R.id.Subtitle:
                toolbar.setSubtitle("副标题");
                break;
            case R.id.logo:
                toolbar.setLogo(R.mipmap.ic_launcher);
                toolbar.setLogoDescription("logo说明");
                break;
            case R.id.NavigationIcon:
                toolbar.setNavigationIcon(R.drawable.back_red);
                break;
            case R.id.inflateMenu:
                toolbar.inflateMenu(R.menu.testmenu);
                toolbar.setTitleMarginEnd(15);
                break;
            case R.id.menu:
                toolbar.setTitleMargin(150,40,20,30);
                toolbar.setPopupTheme(R.style.album_TransparentActivity);
                toolbar.setOverflowIcon(ContextCompat.getDrawable(this,android.R.drawable.ic_menu_more));
                toolbar.showOverflowMenu();
                break;
            case R.id.addMenu:
                Menu menu = toolbar.getMenu();
                menu.add("代码添加");
                break;
            case R.id.addView:
                TextView textView = new TextView(this);
                textView.setText("new Textview");
                toolbar.addView(textView);
                break;
        }
    }
}
