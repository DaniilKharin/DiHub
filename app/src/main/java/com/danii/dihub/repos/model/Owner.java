
package com.danii.dihub.repos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Owner implements Parcelable {

    @SerializedName("login")
    @Expose
    private String login;


    /**
     * @return The login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login The login
     */
    public void setLogin(String login) {
        this.login = login;
    }



    public Owner() {

    }

    private Owner(Parcel in) {

        login = in.readString();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
    }

    public static final Parcelable.Creator<Owner> CREATOR
            = new Parcelable.Creator<Owner>() {


        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

}
