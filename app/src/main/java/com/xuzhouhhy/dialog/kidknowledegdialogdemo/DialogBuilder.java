package com.xuzhouhhy.dialog.kidknowledegdialogdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogBuilder {

    private View mView;

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
        builder.setView(getView(context, positiveStringId, negativeResourceId, titleStringId,
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
        builder.setView(getViewWitoutTitle(context, positiveStringId, negativeResourceId,
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
        builder.setView(getViewWitoutNo(context, positiveStringId,
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
        builder.setView(getViewWithoutNoAndTitle(context, positiveStringId,
                messageStringId, positiveListener));
        return builder;
    }

    private View getViewWithoutNoAndTitle(@NonNull Context context,
                                          int positiveStringId,
                                          int messageStringId,
                                          @NonNull View.OnClickListener positiveListener) {
        if (mView == null) {
            mView = createViewWithoutNoAndTitle(context, positiveStringId, messageStringId,
                    positiveListener);
        }
        return mView;
    }

    private View createViewWithoutNoAndTitle(@NonNull Context context,
                                             int positiveStringId,
                                             int messageStringId,
                                             @NonNull View.OnClickListener positiveListener) {
        mView = LayoutInflater.from(context)
                .inflate(R.layout.layout_material_dialog, null);
        AppCompatButton button = mView.findViewById(R.id.btn_p);
        button.setText(positiveStringId);
        button.setOnClickListener(positiveListener);
        button = mView.findViewById(R.id.btn_n);
        button.setVisibility(View.GONE);
        TextView textView = mView.findViewById(R.id.title);
        textView.setVisibility(View.GONE);
        textView = mView.findViewById(R.id.message);
        textView.setText(messageStringId);
        return mView;
    }

    private View getViewWitoutNo(@NonNull Context context,
                                 int positiveStringId,
                                 int titleStringId, int messageStringId,
                                 @NonNull View.OnClickListener positiveListener) {
        mView = LayoutInflater.from(context)
                .inflate(R.layout.layout_material_dialog, null);
        AppCompatButton button = mView.findViewById(R.id.btn_p);
        button.setText(positiveStringId);
        button.setOnClickListener(positiveListener);
        button = mView.findViewById(R.id.btn_n);
        button.setVisibility(View.GONE);
        TextView textView = mView.findViewById(R.id.title);
        textView.setText(titleStringId);
        textView = mView.findViewById(R.id.message);
        textView.setText(messageStringId);
        return mView;
    }

    private View getViewWitoutTitle(@NonNull Context context,
                                    int positiveStringId, int negativeResourceId,
                                    int messageStringId,
                                    @NonNull View.OnClickListener positiveListener,
                                    @NonNull View.OnClickListener negativeListener) {
        if (mView == null) {
            mView = createViewWithoutTitle(context, positiveStringId, negativeResourceId,
                    messageStringId, positiveListener, negativeListener);
        }
        return mView;
    }

    private View createViewWithoutTitle(@NonNull Context context,
                                        int positiveStringId, int negativeResourceId,
                                        int messageStringId,
                                        @NonNull View.OnClickListener positiveListener,
                                        @NonNull View.OnClickListener negativeListener) {
        mView = LayoutInflater.from(context)
                .inflate(R.layout.layout_material_dialog, null);
        AppCompatButton button = mView.findViewById(R.id.btn_p);
        button.setText(positiveStringId);
        button.setOnClickListener(positiveListener);
        button = mView.findViewById(R.id.btn_n);
        button.setText(negativeResourceId);
        button.setOnClickListener(negativeListener);
        TextView textView = mView.findViewById(R.id.title);
        textView.setVisibility(View.GONE);
        textView = mView.findViewById(R.id.message);
        textView.setText(messageStringId);
        return mView;
    }

    private View getView(@NonNull Context context,
                         int positiveStringId, int negativeResourceId,
                         int titleStringId, int messageStringId,
                         @NonNull View.OnClickListener positiveListener,
                         @NonNull View.OnClickListener negativeListener) {
        if (mView == null) {
            mView = createView(context, positiveStringId, negativeResourceId, titleStringId,
                    messageStringId, positiveListener, negativeListener);
        }
        return mView;
    }

    private View createView(@NonNull Context context,
                            int positiveStringId, int negativeResourceId,
                            int titleStringId, int messageStringId,
                            @NonNull View.OnClickListener positiveListener,
                            @NonNull View.OnClickListener negativeListener) {
        mView = LayoutInflater.from(context)
                .inflate(R.layout.layout_material_dialog, null);
        AppCompatButton button = mView.findViewById(R.id.btn_p);
        button.setText(positiveStringId);
        button.setOnClickListener(positiveListener);
        button = mView.findViewById(R.id.btn_n);
        button.setText(negativeResourceId);
        button.setOnClickListener(negativeListener);
        TextView textView = mView.findViewById(R.id.title);
        textView.setText(titleStringId);
        textView = mView.findViewById(R.id.message);
        textView.setText(messageStringId);
        return mView;
    }

//    private static class DialogView{
//
//        private Context mContext;
//
//        public DialogView(@NonNull Context context,
//                          int positiveStringId, int negativeResourceId,
//                          int titleStringId, int messageStringId,
//                          @NonNull View.OnClickListener positiveListener,
//                          @NonNull View.OnClickListener negativeListener){
//            mContext=context;
//            mView=LinearLayout.inflate(mContext,R.layout.layout_material_dialog,null);
//            Button button = mView.findViewById(R.id.btn_p);
//            button.setText(positiveStringId);
//            button.setOnClickListener(positiveListener);
//            button = mView.findViewById(R.id.btn_n);
//            button.setText(negativeResourceId);
//            button.setOnClickListener(negativeListener);
//            TextView textView = mView.findViewById(R.id.title);
//            textView.setText(titleStringId);
//            textView=mView.findViewById(R.id.message);
//            textView.setText(messageStringId);
//        }
//
//        private View mView;
//
//        public DialogView createView(){
//
//        }
//    }
}
