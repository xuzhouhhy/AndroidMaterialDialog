package com.xuzhouhhy.dialog.kidknowledegdialogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick5(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.dialog);
        dialogBuilder.setTitle(R.string.title);
        dialogBuilder.setMessage(R.string.title);
        dialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialogBuilder.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialogBuilder.show();
    }


    public void onClick1(View view) {
        DialogBuilder dialogBuilder = new DialogBuilder();
        AlertDialog.Builder builder = dialogBuilder.yesNoDialog(
                this, R.string.yes, R.string.no, R.string.title, R.string.messgae,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了积极向按钮", Toast.LENGTH_LONG).show();
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了消极向按钮", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setCancelable(true);
        //设置window背景透明
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
        alertDialog.show();
    }

    public void onClick2(View view) {
        DialogBuilder dialogBuilder = new DialogBuilder();
        AlertDialog.Builder builder = dialogBuilder.yesNoDialogWithoutTitle(
                this, R.string.yes, R.string.no, R.string.messgae,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了积极向按钮", Toast.LENGTH_LONG).show();
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了消极向按钮", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setCancelable(true);
        //设置window背景透明
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
        alertDialog.show();
    }

    public void onClick3(View view) {
        DialogBuilder dialogBuilder = new DialogBuilder();
        AlertDialog.Builder builder = dialogBuilder.yesDialog(
                this, R.string.yes, R.string.title, R.string.messgae,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了积极向按钮", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setCancelable(true);
        //设置window背景透明
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
        alertDialog.show();
    }

    public void onClick4(View view) {
        DialogBuilder dialogBuilder = new DialogBuilder();
        AlertDialog.Builder builder = dialogBuilder.yesDialogWithoutNoAndTitle(
                this, R.string.yes, R.string.messgae,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了积极向按钮", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setCancelable(true);
        //设置window背景透明
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
        alertDialog.show();
    }

}
