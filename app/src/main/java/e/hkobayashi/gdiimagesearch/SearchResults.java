package e.hkobayashi.gdiimagesearch;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import e.hkobayashi.gdiimagesearch.models.ImageList;
import e.hkobayashi.gdiimagesearch.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

//        TextView textView = findViewById(R.id.textView);
//        textView.setText("Loading...");

        String searchText = getIntent().getStringExtra(MainActivity.SEARCH_TEXT_EXTRA);
        String formattedSearchText = searchText.replaceAll("\\s+", "+");
        getData(formattedSearchText);



    }

    private void getData(String searchText) {
        Map<String, String> parameters = new HashMap<>();
//        Log.e("SearchResults", "searchText: " + searchText);
        parameters.put("key", "8676171-014b260fab275b497c9fd8142");
        parameters.put("q" , searchText);
        parameters.put("per_page", "60");
        RetrofitClient
                .getPixabayService()
                .baseApiCall(parameters)
                .enqueue(new Callback<ImageList>() {
                    @Override
                    public void onResponse(Call<ImageList> call, Response<ImageList> response) {
                        if (response != null && response.body() != null && response.body().hits != null) {
                            createRecyclerView(response.body());
                        } else {

                        }
                        Log.i("SearchResults", response.toString());

                    }

                    @Override
                    public void onFailure(Call<ImageList> call, Throwable t) {
                        Log.e("SearchResults", t.getMessage());

                    }
                });

    }

    private void createRecyclerView(ImageList imageList) {
        RecyclerView imagesRecyclerView = findViewById(R.id.images_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,getResources().getInteger(R.integer.num_images_per_row));
        imagesRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new ImageGridAdapter(imageList);
        imagesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.stay, R.anim.slide_to_right);
    }
}