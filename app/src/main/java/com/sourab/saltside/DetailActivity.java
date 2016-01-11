package com.sourab.saltside;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.sourab.saltside.CONSTANTS.CONSTANTS;
import com.sourab.saltside.beans.UserContent;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.img_detail_content)
    ImageView imgDetailContent;

    @Bind(R.id.txt_desc_detail)
    TextView txtDescDetail;

    @Bind(R.id.txt_title_detail)
    TextView txtTitleDetail;

    private UserContent userContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initialize();
    }

    private void initialize() {
        Intent intent = getIntent();
        if (intent.hasExtra(CONSTANTS.KEY_USER_CONTENT)) {
            userContent = intent.getParcelableExtra(CONSTANTS.KEY_USER_CONTENT);
        }
        if (userContent == null) {
            finish();
            return;
        }
        txtTitleDetail.setText(userContent.getTitle());
        txtDescDetail.setText(userContent.getDescription());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        ImageLoader.getInstance().displayImage(userContent.getImage(), imgDetailContent, options);

    }

}
