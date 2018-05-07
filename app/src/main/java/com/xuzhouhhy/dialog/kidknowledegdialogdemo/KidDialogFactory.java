package com.xuzhouhhy.dialog.kidknowledegdialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


public class KidDialogFactory extends Dialog {

    public KidDialogFactory(@NonNull Context context) {
        super(context);
    }

    public KidDialogFactory(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = BaseUtil.getScreenWidth(getContext()) * 4 / 5;
        getWindow().setAttributes(layoutParams);
    }

    public static class Builder {

        private Context mContext;
        private int mThemeId;
        private View mView;
        private boolean mCancelable;
        private boolean mCancelableTouchPutside;

        public Builder(Context context) {
            mContext = context;
            mThemeId = R.style.dialog;
        }

        public Builder(Context context, int themeId) {
            mContext = context;
            mThemeId = themeId;
        }

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
        public Builder(@NonNull Context context,
                       int positiveStringId, int negativeResourceId,
                       int titleStringId, int messageStringId,
                       @NonNull View.OnClickListener positiveListener,
                       @NonNull View.OnClickListener negativeListener,
                       boolean cancelable,
                       boolean cancalTouchOutside) {
            mView = createView(context, positiveStringId, negativeResourceId, titleStringId,
                    messageStringId, positiveListener, negativeListener);
            mContext = context;
            mCancelable = cancelable;
            mCancelableTouchPutside = cancalTouchOutside;
            mThemeId = R.style.dialog;
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
        public Builder(@NonNull Context context,
                       int positiveStringId, int negativeResourceId,
                       int messageStringId,
                       @NonNull View.OnClickListener positiveListener,
                       @NonNull View.OnClickListener negativeListener,
                       boolean cancelable,
                       boolean cancalTouchOutside) {
            mView = createViewWithoutTitle(context, positiveStringId, negativeResourceId,
                    messageStringId, positiveListener, negativeListener);
            mContext = context;
            mCancelable = cancelable;
            mCancelableTouchPutside = cancalTouchOutside;
            mThemeId = R.style.dialog;
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
        public Builder(@NonNull Context context,
                       int positiveStringId,
                       int titleStringId, int messageStringId,
                       @NonNull View.OnClickListener positiveListener,
                       boolean cancelable,
                       boolean cancalTouchOutside) {
            mView = createViewWithoutNo(context, positiveStringId,
                    titleStringId, messageStringId, positiveListener);
            mContext = context;
            mCancelable = cancelable;
            mCancelableTouchPutside = cancalTouchOutside;
            mThemeId = R.style.dialog;
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
        public Builder(@NonNull Context context,
                       int positiveStringId,
                       int messageStringId,
                       @NonNull View.OnClickListener positiveListener,
                       boolean cancelable,
                       boolean cancalTouchOutside) {
            mView = createViewWithoutNoAndTitle(context, positiveStringId, messageStringId,
                    positiveListener);
            mContext = context;
            mCancelable = cancelable;
            mCancelableTouchPutside = cancalTouchOutside;
            mThemeId = R.style.dialog;
        }

        public KidDialogFactory show() {
            final KidDialogFactory dialog = new KidDialogFactory(mContext, mThemeId);
            dialog.setContentView(mView);
            dialog.setCancelable(mCancelable);
            dialog.setCanceledOnTouchOutside(mCancelableTouchPutside);
            dialog.show();
            return dialog;
        }
    }

    public static View createViewWithoutNoAndTitle(@NonNull Context context,
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

    public static View createViewWithoutNo(@NonNull Context context,
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

    public static View createViewWithoutTitle(@NonNull Context context,
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

    public static View createView(@NonNull Context context,
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
