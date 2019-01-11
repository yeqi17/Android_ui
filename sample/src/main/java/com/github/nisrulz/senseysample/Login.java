package com.github.nisrulz.senseysample;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    private ImageButton btBack;
    LinearLayout llsignin,llsignup;

    private TextInputLayout til_password;//登入
    private TextInputLayout til_oldpassword;//change pw
    private TextInputLayout til_newpassword;
    static private String log_in = "666666";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);
        llSignin.setOnClickListener(this);
        //LinearLayout singnin =(LinearLayout)findViewById(R.id.signin);
        llsignup =(LinearLayout)findViewById(R.id.llSignup);
        llsignup.setOnClickListener(this);
        tvSignupInvoker = (TextView) findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker = (TextView) findViewById(R.id.tvSigninInvoker);

        btnSignup= (Button) findViewById(R.id.btnSignup);
        btnSignin= (Button) findViewById(R.id.btnSignin);
        btBack= findViewById(R.id.imageButton2);//返回button

        llSignup = (LinearLayout) findViewById(R.id.llSignup);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);

        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = false;
                showSignupForm();
            }
        });

        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = true;
                showSigninForm();
            }
        });
        showSigninForm();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
                if(isSigninScreen)
                    btnSignup.startAnimation(clockwise);
            }
        });

        //btnSignup button登录跳转
        //登入之后要对login活动进行销毁.finish().
        til_password = findViewById(R.id.til_password);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String account = til_account.getEditText().getText().toString();
                String password = til_password.getEditText().getText().toString();
//                til_account.setErrorEnabled(false);
                til_password.setErrorEnabled(false);
                //验证用户名和密码
                if(validatePasswordLength(password)){
                    Toast.makeText(Login.this,"登录成功",Toast.LENGTH_LONG).show();
                    finish();//如果登录成功销毁登入页面.
                    Intent intent = new Intent(Login.this,DBface1.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                    startActivity(intent);
                }
            }
        });

        //返回button
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//如果登录成功销毁登入页面.
            }
        });

//修改密码模块
        til_oldpassword = findViewById(R.id.til_oldpassword);
        til_newpassword = findViewById(R.id.til_newpassword);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String account = til_account.getEditText().getText().toString();
                String oldpassword = til_oldpassword.getEditText().getText().toString();
                String newpassword = til_newpassword.getEditText().getText().toString();

                til_oldpassword.setErrorEnabled(false);
                til_newpassword.setErrorEnabled(false);
                //验证用户名和密码
                if(!(validatePasswordLength(oldpassword) || validatePasswordLength(newpassword))){
                    Toast.makeText(Login.this,"密码长度为6-8位",Toast.LENGTH_LONG).show();
                }
                else if(!validatePasswordCorrect(oldpassword)){
                    showError(til_oldpassword, "旧密码错误");
                    }
                    else{
                    log_in = newpassword;
                    Toast.makeText(Login.this,"修改成功",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
        btnSignup.startAnimation(clockwise);

    }

    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left_to_right);
        btnSignin.startAnimation(clockwise);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.llSignin || v.getId() ==R.id.llSignup){
            // Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            //InputMethodManager:软键盘布局
            methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    private boolean validatePasswordLength(String password) {
//        if (StringUtils.isEmpty(password)) {
//            showError(til_password,"密码不能为空");
//            return false;
//        }
        if (password.length() < 6 || password.length() > 8) {
            showError(til_password,"密码长度为6-8位");
            return false;
        }
        return validatePasswordCorrect(password);
    }

    private boolean validatePasswordCorrect(String password){
        if(!password.equals(log_in))
            return false;
        else
            return true;
    }
    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

}