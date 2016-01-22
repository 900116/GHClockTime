package com.example.yongchehui.ghclocktime;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import com.example.yongchehui.ghclocktime.Base.BaseTitleActivity;
import com.example.yongchehui.ghclocktime.Module.GHUser;

public class MainActivity extends BaseTitleActivity {

    @Override
    protected Integer getContentViewLayout()
    {
        return R.layout.login_content;
    }

    //用户名输入框
    private EditText userNameET = null;
    //密码输入框
    private EditText pwdET = null;

    @Override
    protected void  initControlView(){
        SharedPreferences settings = this.getSharedPreferences("loginState", 0);
        boolean isLogin = settings.getBoolean("isLogin",false);
        if (isLogin){
            push(HomeActivity.class);
        }

        setTitle("登录");
        setBackGround(R.mipmap.login_bg);

        userNameET = (EditText) findViewById(R.id.userNameET);
        pwdET = (EditText) findViewById(R.id.pwdET);
    }

    public void loginClick_event(View view)
    {
        if (userNameET.getText().toString() == null || userNameET.getText().toString().equals(""))
        {
            showWarningMessage("用户名不能为空");
            return;
        }
        if (pwdET.getText().toString() == null || pwdET.getText().toString().equals(""))
        {
            showWarningMessage("密码不能为空");
            return;
        }
        boolean result = GHUser.login(this,userNameET.getText().toString(),pwdET.getText().toString());
        if (result) push(HomeActivity.class);
    }

    public void registerClick_event(View view)
    {
        push(RegisterActivity.class);
    }
}
