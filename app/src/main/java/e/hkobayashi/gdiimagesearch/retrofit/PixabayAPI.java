package e.hkobayashi.gdiimagesearch.retrofit;

/**
 * Created by hkobayashi on 4/12/18.
 */

import java.util.Map;

import e.hkobayashi.gdiimagesearch.models.ImageList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface PixabayAPI {
    @GET("api")
    Call<ImageList> baseApiCall(@QueryMap Map<String, String> parameter);
}