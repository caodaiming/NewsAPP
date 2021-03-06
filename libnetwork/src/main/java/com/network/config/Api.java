package com.network.config;

import com.network.bean.BaseResponse;
import com.network.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lulei on 2017/1/3.
 * time : 9:26
 * date : 2017/1/3
 * mail to lulei4461@gmail.com
 */
public interface Api {
    //============= API =================//
    @GET(Constants.getNewsByType)
    Observable<ArrayList<NewsBean>> getNewsByType(@Query("type") int type,
                                                  @Query("page") int page);

    @FormUrlEncoded
    @POST(Constants.login)
    Observable<String> login(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse<NewsBean>> postTokenOLD(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse<String>> postToken(@FieldMap Map<String, Object> map);

    @GET(Constants.getNewsByType)
    Observable<BaseResponse<String>> getToken(@Query("serialNumber") String serialNumber);

    @POST(Constants.getNewsByType)
    Observable<BaseResponse> gateway(@Header("token") String token, @Body RequestBody requestBody);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse<Boolean>> locationupload(@Header("token") String token,
                                                     @FieldMap Map<String, Double> map);


    @GET(Constants.getNewsByType)
    Observable<BaseResponse<List<NewsBean>>> firmware(@Query("flag") String flag,
                                                      @Query("version") String version,
                                                      @Header("token") String token);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse> uploadsyslog(@Header("token") String token,
                                          @Path("type") Integer type,
                                          @Field("ossFile") String ossFile);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse> uploadMsgState(@Header("token") String token,
                                            @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Constants.getNewsByType)
    Observable<BaseResponse> uploadDetailDeviceInfo(@Header("token") String token,
                                                    @FieldMap Map<String, String> map);
}
