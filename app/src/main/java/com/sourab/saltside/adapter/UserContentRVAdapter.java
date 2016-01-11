package com.sourab.saltside.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sourab.saltside.CONSTANTS.CONSTANTS;
import com.sourab.saltside.DetailActivity;
import com.sourab.saltside.R;
import com.sourab.saltside.beans.UserContent;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sourab Sharma (sourab.sharma@live.in)  on 1/9/2016.
 */
public class UserContentRVAdapter extends RecyclerView.Adapter<UserContentRVAdapter.ViewHolder> {

    List<UserContent> contentList;
    Context context;
    ImageLoader imageLoader;

    public UserContentRVAdapter(Context context, List<UserContent> contentList, ImageLoader imageLoader) {
        this.context = context;
        this.contentList = contentList;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_user_content_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(CONSTANTS.KEY_USER_CONTENT, contentList.get(viewType));
                context.startActivity(intent);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (contentList != null) {
            UserContent userContent = contentList.get(position);
            if(userContent != null) {
                imageLoader.displayImage(userContent.getImage(), holder.imgUserContent);
                holder.txtTitleUserContent.setText(userContent.getTitle() != null ? userContent.getTitle() : "");
                holder.txtDescUserContent.setText(userContent.getDescription() != null ? userContent.getDescription() : "");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (contentList != null)
            return contentList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_user_content)
        ImageView imgUserContent;

        @Bind(R.id.txt_title_user_content)
        TextView txtTitleUserContent;

        @Bind(R.id.txt_desc_user_content)
        TextView txtDescUserContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
