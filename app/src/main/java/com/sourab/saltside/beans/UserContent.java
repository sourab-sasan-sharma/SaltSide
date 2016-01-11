package com.sourab.saltside.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sourab Sharma (sourab.sharma@live.in)  on 12/29/2015.
 */
public class UserContent implements Parcelable {

    String image;
    String description;
    String title;

    public UserContent() {
    }

    public UserContent(Parcel in) {
        image = in.readString();
        description = in.readString();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(description);
        dest.writeString(title);
    }

    public static final Parcelable.Creator<UserContent> CREATOR = new Parcelable.Creator<UserContent>() {
        @Override
        public UserContent createFromParcel(Parcel source) {
            return new UserContent(source);
        }

        @Override
        public UserContent[] newArray(int size) {
            return new UserContent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
