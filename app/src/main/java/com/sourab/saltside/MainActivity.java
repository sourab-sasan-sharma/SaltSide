package com.sourab.saltside;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sourab.saltside.CONSTANTS.CONSTANTS;
import com.sourab.saltside.adapter.UserContentRVAdapter;
import com.sourab.saltside.beans.UserContent;
import com.sourab.saltside.network.RetroWrapper;
import com.sourab.saltside.util.RetainedFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends BaseActivity implements Callback<List<UserContent>> {

    @Bind(R.id.rv_user_content)
    RecyclerView rvUserContent;

    @Bind(R.id.empty_view)
    TextView emptyView;

    private RetainedFragment retainedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialize();
    }

    private void initialize() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvUserContent.setLayoutManager(layoutManager);

        rvUserContent.setHasFixedSize(true);
        rvUserContent.setAdapter(new UserContentRVAdapter(MainActivity.this, null, imageLoader));

        android.app.FragmentManager fragmentManager = getFragmentManager();
        retainedFragment = (RetainedFragment) fragmentManager.findFragmentByTag(CONSTANTS.TAG_USER_CONTENT);

        if (retainedFragment == null) {
            retainedFragment = new RetainedFragment();
            fragmentManager.beginTransaction().add(retainedFragment, CONSTANTS.TAG_USER_CONTENT).commit();
            RetroWrapper retroWrapper = new RetroWrapper(MainActivity.this, MainActivity.this);
            retroWrapper.getUserContentList();
        } else if (retainedFragment.getContentList() != null && retainedFragment.getContentList().size() > 0) {
            rvUserContent.setAdapter(new UserContentRVAdapter(MainActivity.this, retainedFragment.getContentList(), imageLoader));
            emptyView.setVisibility(View.GONE);
            rvUserContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResponse(Response<List<UserContent>> response, Retrofit retrofit) {
        if (response != null && response.body() != null && response.body().size() > 0) {
            Toast.makeText(MainActivity.this, response.body().size() + "", Toast.LENGTH_SHORT).show();
            if (retainedFragment != null) {
                retainedFragment.setContentList(response.body());
            }
            if (rvUserContent != null) {
                rvUserContent.setAdapter(new UserContentRVAdapter(MainActivity.this, response.body(), imageLoader));
                rvUserContent.setVisibility(View.VISIBLE);
            }
            if (emptyView != null) {
                emptyView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (emptyView != null) {
            emptyView.setText(t.getMessage());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (imageLoader != null) {
            imageLoader.clearDiskCache();
            imageLoader.clearMemoryCache();
        }
    }
}
