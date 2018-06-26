package e.hkobayashi.gdiimagesearch;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;

/**
 * Created by hkobayashi on 5/10/18.
 */

public class ImageDetail extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        final String url = getIntent().getStringExtra("large_image_url");
        Uri uri = Uri.parse(url);
        getIntent().getStringExtra("uploaded_by");
        final Integer numLikes = getIntent().getIntExtra("num_likes", 0);
        final Integer numComments = getIntent().getIntExtra("num_comments", 0);

        final DraweeView imageDetail = findViewById(R.id.image_detail);
        imageDetail.setImageURI(uri);
        final TextView favoritesNumber = findViewById(R.id.favorites_number);
        favoritesNumber.setText(numLikes + " likes");
        final TextView commentsNumber = findViewById(R.id.comments_number);
        commentsNumber.setText(numComments + " comments");

    }
}
