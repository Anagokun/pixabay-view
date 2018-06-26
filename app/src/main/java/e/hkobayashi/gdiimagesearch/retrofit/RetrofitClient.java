package e.hkobayashi.gdiimagesearch.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hkobayashi on 4/12/18.
 */

public class RetrofitClient {
    private static PixabayAPI pixabayAPI;
    private static Retrofit getClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static PixabayAPI getPixabayService() {
        if (pixabayAPI == null) {
            pixabayAPI = getClient("https://pixabay.com").create(PixabayAPI.class);
        }
        return pixabayAPI;
    }
}