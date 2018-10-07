package com.starwarcasting.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * This is entity class to store details of Star War cast
 *
 * @author Shripadj
 */
public class DataStarWarCast implements Parcelable {

    /**
     * This is used to store Name
     */
    @SerializedName("name")
    public String name;

    /**
     * This is used to store Height
     */
    @SerializedName("height")
    public String height;

    /**
     * This is used to store Weight
     */
    @SerializedName("mass")
    public String mass;

    /**
     * This is used to store character created date
     */
    @SerializedName("created")
    public String created;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.height);
        dest.writeString(this.mass);
        dest.writeString(this.created);
    }

    public DataStarWarCast() {
    }

    protected DataStarWarCast(Parcel in) {
        this.name = in.readString();
        this.height = in.readString();
        this.mass = in.readString();
        this.created = in.readString();
    }

    public static final Parcelable.Creator<DataStarWarCast> CREATOR = new Parcelable.Creator<DataStarWarCast>() {
        @Override
        public DataStarWarCast createFromParcel(Parcel source) {
            return new DataStarWarCast(source);
        }

        @Override
        public DataStarWarCast[] newArray(int size) {
            return new DataStarWarCast[size];
        }
    };
}
