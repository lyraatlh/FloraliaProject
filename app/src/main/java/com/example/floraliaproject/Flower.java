package com.example.floraliaproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Flower implements Parcelable {
    private int photo;
    private String name;
    private String latinName;
    private String meaning;
    private String origin;

    public Flower(String name, String latinName, String origin, String meaning, Integer photo) {
        this.photo = photo;
        this.name = name;
        this.latinName = latinName;
        this.meaning = meaning;
        this.origin = origin;
    }

    public Flower() {}

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.latinName);
        dest.writeString(this.meaning);
        dest.writeString(this.origin);
    }

    protected Flower(Parcel in) {
        this.photo = in.readInt();
        this.name = in.readString();
        this.latinName = in.readString();
        this.meaning = in.readString();
        this.origin = in.readString();
    }

    public static final Parcelable.Creator<Flower> CREATOR = new Parcelable.Creator<Flower>() {
        @Override
        public Flower createFromParcel(Parcel source) {
            return new Flower(source);
        }

        @Override
        public Flower[] newArray(int size) {
            return new Flower[size];
        }
    };
}