package com.xuzhouhhy.dialog.kidknowledegdialogdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogBuilder {

    /**
     * 带取消、确定、title、message
     *
     * @param context            context
     * @param positiveStringId   确定按钮String资源id
     * @param negativeResourceId 取消按钮String资源id
     * @param titleStringId      title String资源id
     * @param messageStringId    message String资源id
     * @param positiveListener   确定按钮监听事件
     * @param negativeListener   取消按钮监听事件
     * @return builder
     */
    public AlertDialog.Builder yesNoDialog(@NonNull Context context,
                                           int positiveStringId, int negativeResourceId,
                                           int titleStringId, int messageStringId,
                                           @NonNull View.OnClickListener positiveListener,
                                           @NonNull View.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(createView(context, positiveStringId, negativeResourceId, titleStringId,
                messageStringId, positiveListener, negativeListener));
        return builder;
    }


    /**
     * 带取消、确定、message
     *
     * @param context            context
     * @param positiveStringId   确定按钮String资源id
     * @param negativeResourceId 取消按钮String资源id
     * @param messageStringId    message String资源id
     * @param positiveListener   确定按钮监听事件
     * @param negativeListener   取消按钮监听事件
     * @return builder
     */
    public AlertDialog.Builder yesNoDialogWithoutTitle(@NonNull Context context,
                                                       int positiveStringId, int negativeResourceId,
                                                       int messageStringId,
                                                       @NonNull View.OnClickListener positiveListener,
                                                       @NonNull View.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(createViewWithoutTitle(context, positiveStringId, negativeResourceId,
                messageStringId, positiveListener, negativeListener));
        return builder;
    }

    /**
     * 带确定按钮、title、message
     *
     * @param context          context
     * @param positiveStringId 确定按钮String资源id
     * @param titleStringId    titel String资源id
     * @param messageStringId  message String资源id
     * @param positiveListener 确定按钮监听事件
     * @return builder
     */
    public AlertDialog.Builder yesDialog(@NonNull Context context,
                                         int positiveStringId,
                                         int titleStringId, int messageStringId,
                                         @NonNull View.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(createViewWithoutNo(context, positiveStringId,
                titleStringId, messageStringId, positiveListener));
        return builder;
    }

    /**
     * 带确定按钮、message
     *
     * @param context          context
     * @param positiveStringId 确定按钮String资源id
     * @param messageStringId  message String资源id
     * @param positiveListener 确定按钮监听事件
     * @return builder
     */
    public AlertDialog.Builder yesDialogWithoutNoAndTitle(@NonNull Context context,
                                                          int positiveStringId,
                                                          int messageStringId,
                                                          @NonNull View.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(createViewWithoutNoAndTitle(context, positiveStringId, messageStringId,
                positiveListener));
        return builder;
    }

    private View createViewWithoutNoAndTitle(@NonNull Context context,
                                             int positiveStringId,
                                             int messageStringId,
                                             @NonNull View.OnClickListener positiveListener) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_material_dialog, null);
        AppCompatButton button = view.findViewById(R.id.btn_p);
        button.setText(positiveStringId);
        button.setOnClickListener(positiveListener);
        button = view.findViewById(R.id.btn_n);
        button.setVisibility(View.GONE);
        TextView textView = view.findViewById(R.id.title);
        textView.setVisibility(View.GONE);
        textView = view.findViewById(R.id.message);
        textView.setText(messageStringId);
        return view;
    }

    private View createViewWithoutNo(@NonNull Context context,
                                     int positiveStringId,
                                     int titleStringId, int messageStringId,
                                     @NonNull View.OnClickListener positiveListener) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_material_dialog, null);
        AppCompatButton button = view.findViewById(R.id.btn_p);
        button.setText(positiveStringId);
        button.setOnClickListener(positiveListener);
        button = view.findViewById(R.id.btn_n);
        button.setVisibility(View.GONE);
        TextView textView = view.findViewById(R.id.title);
        textView.setText(titleStringId);
        textView = view.findViewById(R.id.message);
        textView.setText(messageStringId);
        return view;
    }

    private View createViewWithoutTitle(@NonNull Context context,
                                        int positiveStringId, int negativeResourceId,
                                        int messageStringId,
                                        @NonNull View.OnClickListener positiveListener,
                                        @NonNull View.OnClickListener negativeListener) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_material_dialog, null);
        AppCompatButton button = view.findViewById(R.id.btn_p);
        button.setText(positiveStringId);
        button.setOnClickListener(positiveListener);
        button = view.findViewById(R.id.btn_n);
        button.setText(negativeResourceId);
        button.setOnClickListener(negativeListener);
        TextView textView = view.findViewById(R.id.title);
        textView.setVisibility(View.GONE);
        textView = view.findViewById(R.id.message);
        textView.setText(messageStringId);
        return view;
    }

    private View createView(@NonNull Context context,
                            int positiveStringId, int negativeResourceId,
                            int titleStringId, int messageStringId,
                            @NonNull View.OnClickListener positiveListener,
                            @NonNull View.OnClickListener negativeListener) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_material_dialog, null);
        AppCompatButton button = view.findViewById(R.id.btn_p);
        button.setText(positiveStringId);
        button.setOnClickListener(positiveListener);
        button = view.findViewById(R.id.btn_n);
        button.setText(negativeResourceId);
        button.setOnClickListener(negativeListener);
        TextView textView = view.findViewById(R.id.title);
        textView.setText(titleStringId);
        textView = view.findViewById(R.id.message);
        textView.setText(messageStringId);
        return view;
    }

}
