package com.awwhome.alertdialogtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 获取当前activity上下文
     *
     * @return
     */
    public Context getContext() {
        return this;
    }

    // 普通弹出框
    public void click1(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("警告");
        builder.setMessage("请您确认操作");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: 点击了取消按钮");
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: 点击了确定按钮");
            }
        });

        // 弹出框一定要调用show方法
        builder.show();

    }

    /**
     * 单选弹出框
     *
     * @param view
     */
    public void click2(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("请选择你喜欢的课程");

        final String[] kc = {"android", "c", "c++", "java", "php", "html", "js"};

        // 第一个参数为单选框要选择的条目内容，第二个参数为选择条目的是否制定检查，-1为不检查，第三个参数为点击事件
        builder.setSingleChoiceItems(kc, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // kc[which] 数据存在哪里，就是那里取
                Toast.makeText(getContext(), "我喜欢的课程为：" + kc[which], Toast.LENGTH_SHORT).show();

                // 关闭当前的弹出框
                dialog.dismiss();

            }
        });

        // 弹出框一定要调用show方法
        builder.show();

    }

    /**
     * 复选弹出框
     *
     * @param view
     */
    public void click3(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("请选择你喜欢的水果");

        final String[] fruits = {"苹果", "香蕉", "火龙果", "葡萄", "荔枝", "榴莲"};

        final boolean[] isCheck = {true, true, false, false, false, true};

        // 第一个参数：选择条目，第二个参数：默认被选中的条目，第三个参数，点击事件
        builder.setMultiChoiceItems(fruits, isCheck, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });

        //点击确定按钮时，把已选中的水果打印在屏幕上
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < isCheck.length; i++) {
                    if (isCheck[i]) {
                        // 将取出的水果存起来
                        stringBuffer.append(fruits[i] + " ");
                    }
                }
                Toast.makeText(getContext(), "我喜欢的水果有：" + stringBuffer, Toast.LENGTH_SHORT).show();
                // 关闭当前弹出框
                dialog.dismiss();
            }
        });


        // 弹出框一定要调用show方法
        builder.show();

    }

    /**
     * 进度条弹出框
     *
     * @param view
     */
    public void click4(View view) {

        // 创建进度条弹出框
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("正在玩命加载中......");
        // 设置弹出框样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置最大进度
        progressDialog.setMax(100);
        progressDialog.show();

        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i <= 100; i++) {
                    // 与进度相关的控件可以在子线程更新
                    // 设置当前的进度
                    progressDialog.setProgress(i);
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 关闭进度条弹出框
                progressDialog.dismiss();
            }
        }.start();
    }
}
