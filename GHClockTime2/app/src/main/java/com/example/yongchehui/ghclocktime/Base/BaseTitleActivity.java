package com.example.yongchehui.ghclocktime.Base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yongchehui.ghclocktime.R;

/**
 * Created by yongcheHui on 16/1/20.
 * 带有导航栏的Activity
 */
public abstract class BaseTitleActivity  extends Activity{
    //标题栏
    protected TextView titleView = null;
    //内容
    protected ContentFragment contentView = null;
    //根视图
    protected RelativeLayout rootLayout;

    /**
     * 重写此方法返回内容布局ID
     * @return 布局ID
     * */
    protected abstract Integer getContentViewLayout();
    /**
     * 重写此方法初始化控件
     * */
    protected abstract void  initControlView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_base_title);

        this.rootLayout =(RelativeLayout) findViewById(R.id.root_Layout);

        this.titleView = (TextView) this.findViewById(R.id.titleTV);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        this.contentView = new ContentFragment(this.getContentViewLayout());
        transaction.replace(R.id.id_fragment_content, this.contentView);
        transaction.commit();
    }

    /**
     * 该方法用于设置标题
     * @param title 标题
     * */
    protected  void  setTitle(String title)
    {
        this.titleView.setText(title);
    }

    /**
     * 跳转到其他页面
     * @param act 要跳转的页面
     * */
    protected void push(Class act){
        Intent i = new Intent(this,act);
        startActivity(i);
    }

    /**
     * 设置背景图片
     * @param backGroundID 背景图片id
     **/
    protected void setBackGround(Integer backGroundID){
        rootLayout.setBackgroundResource(backGroundID);
    }

    /**
     * 显示警告窗
     * @param msg 显示内容
     * */
    protected void showWarningMessage(String msg)
    {
        new AlertDialog.Builder(this).setTitle("警告").setMessage(msg).setPositiveButton("好", null).show();
    }
}
