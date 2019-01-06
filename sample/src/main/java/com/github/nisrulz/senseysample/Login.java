package com.github.nisrulz.senseysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.text.Editable;
import android.text.TextWatcher;
import android.app.Activity;


public class Login extends AppCompatActivity implements View.OnClickListener{

    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    LinearLayout llsignin,llsignup;

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

        //加入enter跳转的方法
        //tvSignupInvoker.addTextChangedListener(new JumpTextWatcher(tvSignupInvoker,DBface1.class));


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

    /*
    public class TextInputLayout extends Activity implements TextWatcher {
        private android.support.design.widget.TextInputLayout inputlayoutUsename;
        private android.support.design.widget.TextInputLayout inputlayoutPassword;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.text_input_layout);

            //得到用户名输入TextInputLayout
            inputlayoutUsename = (android.support.design.widget.TextInputLayout) findViewById(R.id.usename);
            inputlayoutUsename.getEditText().addTextChangedListener(this);  //继承TextInputLayout的抽象方法

            //得到密码输入TextInputLayout
            inputlayoutPassword = (android.support.design.widget.TextInputLayout) findViewById(R.id.password);
            inputlayoutPassword.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //TODO 输入改变之前做的操作
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //这儿判断操作，如果输入错误可以给用户提示
                    if(s.length()<5){
                        inputlayoutPassword.setErrorEnabled(true);
                        inputlayoutPassword.setError("密码不能小于6位");
                    }else{
                        inputlayoutPassword.setErrorEnabled(false);
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                    //TODO 输入改变之后做的操作
                }
            });
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //这儿判断操作，如果输入错误可以给用户提示
            if(s.length()<5){
                inputlayoutUsename.setErrorEnabled(true);
                inputlayoutUsename.setError("用户名不能小于6位");
            }else{
                inputlayoutUsename.setErrorEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
*/


/*
    public static class JumpTextWatcher implements TextWatcher {
        private EditText mThisView = null;
        private View mNextView = null;

        public JumpTextWatcher(EditText vThis, View vNext) {
            super();
            mThisView = vThis;
            if (vNext != null) {
                mNextView = vNext;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String str = s.toString();
            if (str.indexOf("/r") >= 0 || str.indexOf("\n") >= 0) {
                //如果发现输入回车符或换行符，替换为空字符
                mThisView.setText(str.replace("/r", "").replace("\n", ""));
                if (mNextView != null) {
                    //如果跳转控件不为空，让下一个控件获得焦点，此处可以直接实现登录功能
                    mNextView.requestFocus();//获取焦点
                    if (mNextView instanceof EditText) {
                        EditText et = (EditText) mNextView;
                        //如果跳转控件为EditText，让光标自动移到文本框文字末尾
                        et.setSelection(et.getText().length());
                    }
                }
            }
        }
    }
*/


}