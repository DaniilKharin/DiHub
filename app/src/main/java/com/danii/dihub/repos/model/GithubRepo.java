
package com.danii.dihub.repos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class GithubRepo implements Parcelable {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("stargazers_count")
    @Expose
    private Integer stargazersCount;
    @SerializedName("language")
    @Expose
    private String language;


    public GithubRepo() {
    }

    public GithubRepo(Parcel in) {
        name = in.readString();
        fullName = in.readString();
        htmlUrl = in.readString();
        description = in.readString();
        updatedAt = in.readString();
        createdAt = in.readString();
        stargazersCount = in.readInt();
        language = in.readString();
        owner = in.readParcelable(Owner.class.getClassLoader());
    }


    /**
     * @return The id
     */
    /*public Integer getId() {
        if (id!=null)
            return id;
        else
            return -1;
    }*/

    /**
     * @param id The id
     */
    /*public void setId(Integer id) {
        this.id = id;
    }
    */
    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return The owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * @param owner The owner
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }



    /**
     * @return The htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * @param htmlUrl The html_url
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        if (description == null)
            return "";
        else
            return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    /**
     * @return The stargazersCount
     */
    public Integer getStargazersCount() {
        return stargazersCount;
    }

    /**
     * @param stargazersCount The stargazers_count
     */
    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }



    /**
     * @return The language
     */
    public String getLanguage() {
        if (language == null)
            return "";
        else
            return language;
    }

    /**
     * @param language The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(getName());
        dest.writeString(getFullName());
        dest.writeString(getHtmlUrl());
        dest.writeString(getDescription());
        dest.writeString(getUpdatedAt());
        dest.writeString(getCreatedAt());
        dest.writeInt(getStargazersCount());
        dest.writeString(getLanguage());
        dest.writeParcelable(getOwner(), flags);

    }

    public static final Parcelable.Creator<GithubRepo> CREATOR
            = new Parcelable.Creator<GithubRepo>() {


        @Override
        public GithubRepo createFromParcel(Parcel in) {
            return new GithubRepo(in);
        }

        @Override
        public GithubRepo[] newArray(int size) {
            return new GithubRepo[size];
        }
    };

}