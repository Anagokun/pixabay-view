package e.hkobayashi.gdiimagesearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;

import e.hkobayashi.gdiimagesearch.models.ImageList;

/**
 * Created by hkobayashi on 4/19/18.
 */

class ImageGridAdapter extends RecyclerView.Adapter {
    private ImageList imageList;

    public ImageGridAdapter(ImageList imageList) {
        this.imageList = imageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_image, parent, false);

        ImageViewHolder viewHolder = new ImageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        String url = imageList.hits.get(position).previewURL;
        Uri uri = Uri.parse(url);
        ((ImageViewHolder) holder).imageView.setImageURI(uri);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageDetailIntent = new Intent(view.getContext(), ImageDetail.class);
                imageDetailIntent.putExtra("large_image_url", imageList.hits.get(position).largeImageURL);
                imageDetailIntent.putExtra("num_likes", imageList.hits.get(position).likes);
                imageDetailIntent.putExtra("num_comments", imageList.hits.get(position).comments);
                imageDetailIntent.putExtra("uploaded_by", imageList.hits.get(position).user);
                view.getContext().startActivity(imageDetailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.hits.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fresco_image);
        }
    }
}
