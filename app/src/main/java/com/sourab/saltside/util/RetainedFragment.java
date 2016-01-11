package com.sourab.saltside.util;

import android.app.Fragment;
import android.os.Bundle;

import com.sourab.saltside.beans.UserContent;

import java.util.List;

/**
 * Created by Sourab Sharma (sourab.sharma@live.in)  on 1/11/2016.
 */
public class RetainedFragment extends Fragment {

    private List<UserContent> contentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public List<UserContent> getContentList() {
        return contentList;
    }

    public void setContentList(List<UserContent> contentList) {
        this.contentList = contentList;
    }
}