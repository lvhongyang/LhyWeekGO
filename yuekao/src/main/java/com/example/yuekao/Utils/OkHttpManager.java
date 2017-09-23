package com.example.yuekao.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author lvhongyang.
 * @time 2017/9/22.
 * @desc:
 */

public class OkHttpManager {

    //    定义成员变量
    private static OkHttpClient mClient;

    private static Handler mHandler;

    private volatile static OkHttpManager mManager;//volatile 防止多个线程同时访问
    //--------------------  使用构造方法完成初始化

    private OkHttpManager() {
        mClient = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        mHandler = new Handler();
    }


    // ----------------------   使用单例模式,通过获取的方式拿到对象

    public static OkHttpManager getInstance() {
/**
 * 这个不叫懒汉式，而是双检锁的Lazy Loading Singleton模式。
 这个办法在Java5之前是肯定不好使的。
 所谓的懒汉式，可以利用内部static类来实现。
 */
//        OkHttpManager instance = null;
//        if (mManager == null) {
//            synchronized (OkHttpManager.class) {
//                if (instance == null) {
//                    instance = new OkHttpManager();
//                    mManager = instance;
//                }
//            }
//        }
//        return instance;
        if (mManager == null) {
            mManager = new OkHttpManager();
        }
        return mManager;
    }

    // -----------------------   定义接口
    public interface Function1 {
        void onResponse(String result);
    }

    interface Function2 {
        void onResponse(byte[] result);
    }

    interface Function3 {
        void onResponse(JSONObject jsonObject);
    }

    interface Function4 {
        void onResponse(Bitmap result);
    }

    // ----------------------- 使用handler,接口,保证处理数据的逻辑在主线程
    //处理网络请求的方法,返回的结果是Json字符串
    private static void onSuccessJsonStringMethod(final String jsonValue, final Function1 callBack) {
        //这里使用Handler.post 把数据放到主线程,也可以使用EventBus或RxJava的线程调度器去完成
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onResponse(jsonValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static void onSuccessJsonStringMethod(final Bitmap jsonValue, final Function4 callBack) {
        //这里使用Handler.post 把数据放到主线程,也可以使用EventBus或RxJava的线程调度器去完成
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onResponse(jsonValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * 请求数据
     */
    public void asyncJsonStringByURL(String url, final Function1 callBack) {
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //先判断response不为null
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonStringMethod(response.body().string(), callBack);
                }
            }
        });

    }

    /**
     * 提交表单的方法
     */
    public void sendComplexForm(String url, Map<String, String> params, final Function1 callBack) {
        FormBody.Builder form = new FormBody.Builder();
        if (!params.isEmpty() && params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                form.add(entry.getKey(), entry.getValue());
            }
        }

        FormBody build = form.build();

        Request request = new Request.Builder().url(url).post(build).build();

        mClient.newCall(request).enqueue(new Callback()  {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //判空
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonStringMethod(response.body().string(), callBack);
                }
            }
        });

    }

    public void downLoadImage(String url, final Function4 callBack) {
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //先判断response不为null
                if (response != null && response.isSuccessful()) {
                    InputStream inputStream = response.body().byteStream();
                    if (inputStream != null) {
                        onSuccessJsonStringMethod(BitmapFactory.decodeStream(inputStream), callBack);
                    }

                }
            }
        });

    }

    //---------------------------- 暴露提供给外界调用的方法


}
