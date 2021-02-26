package com.example.pruebaonoff.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebaonoff.R;
import com.example.pruebaonoff.elementos.Post;

import java.util.ArrayList;

public class AdapterPost
        extends RecyclerView.Adapter<AdapterPost.ViewHolderPost>
        implements View.OnClickListener {
    //  @NonNull

    ArrayList<Post> listPosts;
    private View.OnClickListener listener;

    public AdapterPost(ArrayList<Post> listPosts) {
        this.listPosts = listPosts;
    }

    @Override
    public ViewHolderPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, null, false);

        view.setOnClickListener(this);

        return new ViewHolderPost(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPost holder, int position) {

        holder.title.setText(listPosts.get(position).getTitle() + "\n"
                + "User ID: " + listPosts.get(position).getUserId() +
                " | Post ID: " + listPosts.get(position).getIdPost());

        holder.body.setText("\n" + listPosts.get(position).getBody() + "\n");
    }

    @Override
    public int getItemCount() {
        return listPosts.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public static class ViewHolderPost extends RecyclerView.ViewHolder {

        TextView title, body;

        public ViewHolderPost(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.idTitlePost);
            body = (TextView) itemView.findViewById(R.id.idBodyPost);
        }
    }
}
