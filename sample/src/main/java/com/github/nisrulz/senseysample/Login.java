package com.github.nisrulz.senseysample;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.view.MotionEvent;
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
import android.widget.Toast;

import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.PinchScaleDetector;
import com.github.nisrulz.sensey.TouchTypeDetector;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    LinearLayout llsignin,llsignup;

    private TextInputLayout til_password;//登入
    private TextInputLayout til_oldpassword;//change pw
    private TextInputLayout til_newpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Init Sensey
        Sensey.getInstance().init(this);
        // Start Touch 直接崩溃掉,为啥呢?
        //重写一个函数,解决设定login为主页面的手势操作的加载问题.但是touch()到login还是直接崩掉?
        startTouchTypeDetection();

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
                if(validatePassword(password)){
                    Toast.makeText(Login.this,"登录成功",Toast.LENGTH_LONG).show();
                    finish();//如果登录成功销毁登入页面.
                    Intent intent = new Intent(Login.this,DBface1.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                    startActivity(intent);
                }
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
                if(validatePassword(oldpassword)&&validatePassword(newpassword)){
                    Toast.makeText(Login.this,"修改成功",Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(Login.this,DBface1.class);
//                    startActivity(intent);
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
    private boolean validatePassword(String password) {
//        if (StringUtils.isEmpty(password)) {
//            showError(til_password,"密码不能为空");
//            return false;
//        }
        if (password.length() < 6 || password.length() > 18) {
            showError(til_password,"密码长度为6-18位");
            return false;
        }
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

    @Override
    protected void onPause() {
        super.onPause();
        // Stop Detections
        Sensey.getInstance().stopTouchTypeDetection();
//        Sensey.getInstance().stopPinchScaleDetection();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // *** IMPORTANT ***
        // Stop Sensey and release the context held by it
        Sensey.getInstance().stop();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // Setup onTouchEvent for detecting type of touch gesture
//        Sensey.getInstance().setupDispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }
    private void startTouchTypeDetection() {
        Sensey.getInstance()
                .startTouchTypeDetection(Login.this, new TouchTypeDetector.TouchTypListener() {
                    @Override
                    public void onDoubleTap() {
                    }
                    @Override
                    public void onLongPress() {
                    }
                    @Override
                    public void onScroll(int scrollDirection) {
                        switch (scrollDirection) {
                            case TouchTypeDetector.SCROLL_DIR_UP:
                                break;
                            case TouchTypeDetector.SCROLL_DIR_DOWN:
                                Toast.makeText(Login.this,"up down",Toast.LENGTH_LONG).show();
                                Intent intent2 = new Intent(Login.this,TouchActivity.class);
                                startActivity(intent2);
                                break;
                            case TouchTypeDetector.SCROLL_DIR_LEFT:
                                break;
                            case TouchTypeDetector.SCROLL_DIR_RIGHT:
                                break;
                            default:
                                // Do nothing
                                break;
                        }
                    }

                    @Override
                    public void onSingleTap() {
                    }

                    @Override
                    public void onSwipe(int swipeDirection) {
//                        switch (swipeDirection) {
//                            case TouchTypeDetector.SWIPE_DIR_UP:
//                                break;
//                            case TouchTypeDetector.SWIPE_DIR_DOWN:
//                                break;
//                            case TouchTypeDetector.SWIPE_DIR_LEFT:
//                                break;
//                            case TouchTypeDetector.SWIPE_DIR_RIGHT:
//                                break;
//                            default:
//                                //do nothing
//                                break;
//                        }
                    }

                    @Override
                    public void onThreeFingerSingleTap() {
                    }
                    @Override
                    public void onTwoFingerSingleTap() {
                    }
                });
    }
}