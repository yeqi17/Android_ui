package com.github.nisrulz.senseysample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.TouchTypeDetector;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;


public class DBface2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Init Sensey
        Sensey.getInstance().init(this);
        // Start Touch
        startTouchTypeDetection();
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged(mDataList);
    }

    @Override
    protected boolean displayHomeAsUpEnabled() {
        return false;
    }

    @Override
    protected List<FaceRegistrant> createDataList() {
        FaceRegistrant faceRegistrant;

        List<FaceRegistrant> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            faceRegistrant = new FaceRegistrant("yeqi",R.drawable.bg_people);
            dataList.add(faceRegistrant);
        }
        return dataList;
    }
    //List类型要对应List<FaceRegistrant>

    @Override
    protected BaseAdapter createAdapter() {
        return new RegistrantFaceAdapter(this);
    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(DBface2.this).setBackground(R.drawable.selector_green)
                        .setImage(R.mipmap.ic_action_add)
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(addItem); // 添加菜单到左侧。

                SwipeMenuItem closeItem = new SwipeMenuItem(DBface2.this).setBackground(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(closeItem); // 添加菜单到左侧。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(DBface2.this).setBackground(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

                SwipeMenuItem addItem = new SwipeMenuItem(DBface2.this).setBackground(R.drawable.selector_green)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(DBface2.this, "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(DBface2.this, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };
    //重写touch的操作
    private void startTouchTypeDetection() {
        Sensey.getInstance()
                .startTouchTypeDetection(DBface2.this, new TouchTypeDetector.TouchTypListener() {
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
                                //setResultTextView("Scrolling Up");
                                Intent intent = new Intent(DBface2.this, enter_code.class);
                                startActivity(intent);
                                break;
                            case TouchTypeDetector.SCROLL_DIR_DOWN:
                                //setResultTextView("Scrolling Down");
                                break;
                            case TouchTypeDetector.SCROLL_DIR_LEFT:
                                //setResultTextView("Scrolling Left");
                                break;
                            case TouchTypeDetector.SCROLL_DIR_RIGHT:
                                //setResultTextView("Scrolling Right");
                                break;
                            default:
                                // Do nothing
                                break;
                        }
                    }

                    @Override
                    public void onSingleTap() {
                        //setResultTextView("Single Tap");
                    }

                    @Override
                    public void onSwipe(int swipeDirection) {
                        switch (swipeDirection) {
                            case TouchTypeDetector.SWIPE_DIR_UP:
                                //setResultTextView("Swipe Up");
                                break;
                            case TouchTypeDetector.SWIPE_DIR_DOWN:
                                //setResultTextView("Swipe Down");
                                break;
                            case TouchTypeDetector.SWIPE_DIR_LEFT:
                                //setResultTextView("Swipe Left");
                                break;
                            case TouchTypeDetector.SWIPE_DIR_RIGHT:
                                //setResultTextView("Swipe Right");
                                break;
                            default:
                                //do nothing
                                break;
                        }
                    }

                    @Override
                    public void onThreeFingerSingleTap() {
                        //setResultTextView("Three Finger Tap");
                    }

                    @Override
                    public void onTwoFingerSingleTap() {
                        //setResultTextView("Two Finger Tap");
                    }
                });
    }


}
