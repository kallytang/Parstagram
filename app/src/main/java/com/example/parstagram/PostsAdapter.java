package com.example.parstagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    public static final String KEY_PROFILE_IMAGE = "profileImage";

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    private Context context;
    private List<Post> posts;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    //clears elements of recyclerview
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> postsList){
        posts.addAll(postsList);
        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvDateCreated;
        private ImageView ivProfileImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvDateCreated = itemView.findViewById(R.id.tvDateCreated);
        }

        public void bind(Post post) {
            //Bind the post data to the view elements;
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            Log.i("testing", post.getCreatedAt().toString());
            ParseFile image = post.getImage();
            tvDateCreated.setText(post.getCreatedAt().toString());

            Glide.with(context).load(R.drawable.default_profile_normal).circleCrop().into(ivProfileImage);


            if (image != null){
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }


        }
    }

}
