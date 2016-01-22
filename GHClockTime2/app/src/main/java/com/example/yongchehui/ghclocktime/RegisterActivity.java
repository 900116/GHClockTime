package com.example.yongchehui.ghclocktime;

import android.view.View;
import android.widget.EditText;

import com.example.yongchehui.ghclocktime.Base.BaseTitleActivity;
import com.example.yongchehui.ghclocktime.Module.GHUser;

/**
 * Created by yongcheHui on 16/1/21.
 */
public class RegisterActivity extends BaseTitleActivity{
    @Override
    protected Integer getContentViewLayout() {
        return R.layout.register_layout;
    }

    private EditText userNameET = null;
    private EditText pwdET = null;

    @Override
    protected void initControlView() {
        setTitle("注册");
        setBackGround(R.mipmap.login_bg);

        userNameET = (EditText)findViewById(R.id.r_userNameET);
        pwdET = (EditText)findViewById(R.id.r_pwdET);
    }

    public void registerClick_event(View view)
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
        GHUser.register(this,userNameET.getText().toString(),pwdET.getText().toString());
        finish();
    }
}
