package com.xuzhouhhy.dialog.kidknowledegdialogdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by vive on 2017/7/20.
 */

public class BaseUtil {
    static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    /**
     * 获得当前进程名
     *
     * @param context
     * @return
     */
    public static String curProcessName;


    public static String[] getYDTDayNum() {
        String[] days = new String[3];

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        int index = -1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy:MM:dd");

        for (int i = 0; i < 3; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, index);

            days[i] = simpleDateFormat.format(calendar.getTime());
            index++;
        }
        return days;
    }

    /**
     * 判断当前时间是否为22:00~06:00 夜间时间
     *
     * @return true 当前时间为夜间时间
     */
    public static boolean isNight() {

        SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.getDefault());
        String curTime = sdf.format(System.currentTimeMillis()).trim();
        int hour = Integer.valueOf(curTime);
        // 22:00~06:00
        if (hour == 22 || hour == 23 || (hour >= 0 && hour < 6)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getCurProcessName(Context context) {
        if (context == null) {
            return "";
        }
        if (!TextUtils.isEmpty(curProcessName)) {
            return curProcessName;
        }
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (mActivityManager == null) {
            curProcessName = context.getPackageName();
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = null;
        try {
            runningAppProcesses = mActivityManager.getRunningAppProcesses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (runningAppProcesses == null || runningAppProcesses.isEmpty()) {
            curProcessName = context.getPackageName();
        }

        for (ActivityManager.RunningAppProcessInfo appProcess : runningAppProcesses) {
            if (appProcess.pid == pid) {
                curProcessName = appProcess.processName;
                break;
            }
        }
        return curProcessName;
    }

    static Class<?>[] nullToEmpty(final Class<?>[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_CLASS_ARRAY;
        }
        return array;
    }

    static Object[] nullToEmpty(final Object[] array) {
        if (array == null || array.length == 0) {
            return EMPTY_OBJECT_ARRAY;
        }
        return array;
    }


    public static boolean isProcessRunning(Context context, String processName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = null;
        try {
            appProcesses = activityManager.getRunningAppProcesses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (appProcesses == null) return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
//			Logger.log("process processName:"+appProcess.processName);
            if (appProcess.processName.equals(processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断主进程还在不在系统最近任务栏里面，有可能会用户强行划掉
     */
    public static boolean isProcessInRecentTasks(Context context, String processName) {
        if (context == null || processName == null)
            return true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null)
            return true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            List<ActivityManager.AppTask> tasks = am.getAppTasks();

            if (tasks == null || tasks.isEmpty()) {
                return false;
            }

            for (ActivityManager.AppTask task : tasks) {
                ActivityManager.RecentTaskInfo info = null;
                if (task != null) {
                    info = task.getTaskInfo();
                }

                if (info != null && info.baseIntent != null && info.baseIntent.getComponent() != null
                        && processName.equals(info.baseIntent.getComponent().getPackageName()))
                    return true;

            }
            return false;
        } else {

            List<ActivityManager.RecentTaskInfo> list = am.getRecentTasks(100, ActivityManager.RECENT_IGNORE_UNAVAILABLE);

            if (list == null || list.isEmpty()) {
                return false;
            }

            for (int i = 0; i < list.size(); i++) {
                ActivityManager.RecentTaskInfo info = list.get(i);
                if (info.baseIntent != null && info.baseIntent.getComponent() != null
                        && processName.equals(info.baseIntent.getComponent().getPackageName()))
                    return true;
            }
            return false;
        }
    }


    /**
     * 打印当前线程的调用堆栈
     */
    public static String printTrack() {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        if (st == null) {
            return "无堆栈...";
        }
        StringBuffer sbf = new StringBuffer();
        for (StackTraceElement e : st) {
            if (sbf.length() > 0) {
                sbf.append(" <- ");
                sbf.append(System.getProperty("line.separator"));
            }
            sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}"
                    , e.getClassName()
                    , e.getMethodName()
                    , e.getLineNumber()));
        }
        return sbf.toString();
    }


    public static int dp2px(Context context, float dipValue) {
        if (context == null)
            return (int) (dipValue * 1.5);
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float dipValue) {
        if (context == null)
            return (int) (dipValue * 1.5);
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        if (context == null)
            return (int) (pxValue * 1.5);
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * get the width of the device screen
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (context == null)
            return 1;
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * get the height of the device screen
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (context == null)
            return 1;
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 获取状态栏的高度
     */
    public static int mStatusBarHeight = 0;

    public static int getStatusBarHeight(Context context) {
        if (context == null)
            return 0;
        if (mStatusBarHeight != 0) {
            return mStatusBarHeight;
        }
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int temp = Integer.parseInt(field.get(obj).toString());
            mStatusBarHeight = context.getResources()
                    .getDimensionPixelSize(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mStatusBarHeight;
    }


    /**
     * 是否是平板
     *
     * @param context
     * @return
     */
    public static boolean isTabletDevice(Context context) {
        if (context == null) {
            return false;
        }
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    //字节转换为KB或MB
    public static String byteToMb(double bytes) {
        String result;
        double kb = bytes / 1024;
        if (kb > 1024) {
            double mb = kb / 1024;
            DecimalFormat df = new DecimalFormat("0.0");
            result = df.format(mb) + "MB/s";
            Log.e("byteToMb", "1");
        } else {
            DecimalFormat df = new DecimalFormat("0.0");
            result = df.format(bytes / 1024) + "KB/s";
            Log.e("byteToMb", "2");
        }
        Log.e("byteToMb", result);
        return result;
    }


    /**
     * 获取虚拟按键栏高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        if (isMeizu()) {
            return getSmartBarHeight(context);
        }
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    private static boolean hasNavBar(Context context) {
        if (context == null)
            return false;

        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                return !ViewConfiguration.get(context).hasPermanentMenuKey();
            } else {
                return false;
            }
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    /**
     * 判断是否meizu手机
     *
     * @return
     */
    public static boolean isMeizu() {
        return Build.BRAND.equals("Meizu");
    }

    /**
     * 获取魅族手机底部虚拟键盘高度
     *
     * @param context
     * @return
     */
    public static int getSmartBarHeight(Context context) {
        if (context == null)
            return 0;

        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("mz_action_button_min_height");
            int height = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static PackageInfo getPackageArchiveInfo(String archiveFilePath, int flags) {
        // Workaround for https://code.google.com/p/android/issues/detail?id=9151#c8
        try {
            Class packageParserClass = Class.forName(
                    "android.content.pm.PackageParser");
            Class[] innerClasses = packageParserClass.getDeclaredClasses();
            Class packageParserPackageClass = null;
            for (Class innerClass : innerClasses) {
                if (0 == innerClass.getName().compareTo("android.content.pm.PackageParser$Package")) {
                    packageParserPackageClass = innerClass;
                    break;
                }
            }
            Constructor packageParserConstructor = packageParserClass.getConstructor(
                    String.class);
            Method parsePackageMethod = packageParserClass.getDeclaredMethod(
                    "parsePackage", File.class, String.class, DisplayMetrics.class, int.class);
            Method collectCertificatesMethod = packageParserClass.getDeclaredMethod(
                    "collectCertificates", packageParserPackageClass, int.class);
            Method generatePackageInfoMethod = packageParserClass.getDeclaredMethod(
                    "generatePackageInfo", packageParserPackageClass, int[].class, int.class, long.class, long.class);
            packageParserConstructor.setAccessible(true);
            parsePackageMethod.setAccessible(true);
            collectCertificatesMethod.setAccessible(true);
            generatePackageInfoMethod.setAccessible(true);

            Object packageParser = packageParserConstructor.newInstance(archiveFilePath);

            DisplayMetrics metrics = new DisplayMetrics();
            metrics.setToDefaults();

            final File sourceFile = new File(archiveFilePath);

            Object pkg = parsePackageMethod.invoke(
                    packageParser,
                    sourceFile,
                    archiveFilePath,
                    metrics,
                    0);
            if (pkg == null) {
                return null;
            }

            if ((flags & PackageManager.GET_SIGNATURES) != 0) {
                collectCertificatesMethod.invoke(packageParser, pkg, 0);
            }

            return (PackageInfo) generatePackageInfoMethod.invoke(null, pkg, null, flags, 0, 0);
        } catch (Exception e) {
            Log.e("Signature Monitor",
                    "android.content.pm.PackageParser reflection failed: " + e.toString());
        }

        return null;
    }

    public static boolean verifyPluginFileSignature(Context context, String pluginFilepath) {
        File pluginFile = new File(pluginFilepath);
        if (!pluginFile.exists()) {
            return false;
        }

        try {
            PackageInfo newInfo = getPackageArchiveInfo(pluginFilepath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SIGNATURES);
            PackageInfo mainPkgInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            return checkSignatures(newInfo, mainPkgInfo) == PackageManager.SIGNATURE_MATCH;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    private static int checkSignatures(PackageInfo pluginPackageInfo,
                                       PackageInfo mainPackageInfo) {

        Signature[] pluginSignatures = pluginPackageInfo.signatures;
        Signature[] mainSignatures = mainPackageInfo.signatures;
        boolean pluginSigned = pluginSignatures != null
                && pluginSignatures.length > 0;
        boolean mainSigned = mainSignatures != null
                && mainSignatures.length > 0;

        if (!pluginSigned && !mainSigned) {
            return PackageManager.SIGNATURE_NEITHER_SIGNED;
        } else if (!pluginSigned && mainSigned) {
            return PackageManager.SIGNATURE_FIRST_NOT_SIGNED;
        } else if (pluginSigned && !mainSigned) {
            return PackageManager.SIGNATURE_SECOND_NOT_SIGNED;
        } else {
            if (pluginSignatures.length == mainSignatures.length) {
                for (int i = 0; i < pluginSignatures.length; i++) {
                    Signature s1 = pluginSignatures[i];
                    Signature s2 = mainSignatures[i];
                    if (!Arrays.equals(s1.toByteArray(), s2.toByteArray())) {
                        return PackageManager.SIGNATURE_NO_MATCH;
                    }
                }
                return PackageManager.SIGNATURE_MATCH;
            } else {
                return PackageManager.SIGNATURE_NO_MATCH;
            }
        }
    }

    /**
     * 取消通知
     *
     * @param context
     * @param id
     */
    public static void cancelNotification(Context context, int id) {
        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.cancel(id);
        }
    }

    public static @Nullable
    Notification createNotification(Context context,
                                    String title, String ticker, String msg, int resIconId, PendingIntent pi) {
        Notification notification;
        NotificationCompat.Builder builder = null;

        builder = new NotificationCompat.Builder(
                context).setWhen(System.currentTimeMillis())
                .setSmallIcon(resIconId).setTicker(ticker)
                .setContentTitle(title).setContentText(msg)
                .setContentIntent(pi)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_SOUND)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        try {
            notification = builder.build();
            notification.when = System.currentTimeMillis();
            return notification;
        } catch (NullPointerException e) {  //系统调用会产生空指针，无法修复，只能在此处catch
            e.printStackTrace();
        }

        return null;
    }

}