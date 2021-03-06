package com.newsjd.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.maxi.chatdemo.db.base.BaseManager;
import com.meituan.android.walle.WalleChannelReader;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import pub.cpp.news.BuildConfig;

public class MainApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        //初始化chatdemo的数据库
        BaseManager.initOpenHelper(this);

        //友盟 初始化
        /*
            注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，UMConfigure.init调用中appkey和channel参数请置为null）。
        */
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        Log.e("SJD", "onCreate: " + channel);
        UMConfigure.init(context, BuildConfig.umeng_appkey, channel, 1, BuildConfig.APPLICATION_ID + "SJD");

        //微信和QQ开放平台对应的AppId和Appkey
        PlatformConfig.setWeixin(BuildConfig.wechat_appid, BuildConfig.wechat_appsecret);
        PlatformConfig.setQQZone(BuildConfig.qqzone_appid, BuildConfig.qqzone_appsecret);
//        PlatformConfig.setSinaWeibo();

        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        if (BuildConfig.DEBUG) {
            UMConfigure.setLogEnabled(true);
        }
    }
}
