
package com.danii.dihub;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class GithubRepo implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("fork")
    @Expose
    private Boolean fork;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("pushed_at")
    @Expose
    private String pushedAt;
    @SerializedName("homepage")
    @Expose
    private Object homepage;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("stargazers_count")
    @Expose
    private Integer stargazersCount;
    @SerializedName("watchers_count")
    @Expose
    private Integer watchersCount;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("has_issues")
    @Expose
    private Boolean hasIssues;
    @SerializedName("has_downloads")
    @Expose
    private Boolean hasDownloads;
    @SerializedName("has_wiki")
    @Expose
    private Boolean hasWiki;
    @SerializedName("has_pages")
    @Expose
    private Boolean hasPages;
    @SerializedName("forks_count")
    @Expose
    private Integer forksCount;
    @SerializedName("mirror_url")
    @Expose
    private Object mirrorUrl;
    @SerializedName("open_issues_count")
    @Expose
    private Integer openIssuesCount;
    @SerializedName("forks")
    @Expose
    private Integer forks;
    @SerializedName("open_issues")
    @Expose
    private Integer openIssues;
    @SerializedName("watchers")
    @Expose
    private Integer watchers;
    @SerializedName("default_branch")
    @Expose
    private String defaultBranch;

    public GithubRepo() {
    }

    public GithubRepo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        fullName = in.readString();
        _private = (Boolean.parseBoolean(in.readString()));
        htmlUrl = in.readString();
        description = in.readString();
        fork = (Boolean.parseBoolean(in.readString()));
        url = in.readString();
        pushedAt = in.readString();
        //homepage=in.readString();
        size = in.readInt();
        stargazersCount = in.readInt();
        watchersCount = in.readInt();
        language = in.readString();
        hasIssues = (Boolean.parseBoolean(in.readString()));
        hasDownloads = (Boolean.parseBoolean(in.readString()));
        hasWiki = (Boolean.parseBoolean(in.readString()));
        hasPages = (Boolean.parseBoolean(in.readString()));
        forksCount = in.readInt();
        // mirrorUrl=in.readString();
        openIssuesCount = in.readInt();
        forks = in.readInt();
        openIssues = in.readInt();
        watchers = in.readInt();
        defaultBranch = in.readString();
        owner = in.readParcelable(Owner.class.getClassLoader());
    }


    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * @return The _private
     */
    public Boolean getPrivate() {
        return _private;
    }

    /**
     * @param _private The private
     */
    public void setPrivate(Boolean _private) {
        this._private = _private;
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
     * @return The fork
     */
    public Boolean getFork() {
        return fork;
    }

    /**
     * @param fork The fork
     */
    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @return The pushedAt
     */
    public String getPushedAt() {
        return pushedAt;
    }

    /**
     * @param pushedAt The pushed_at
     */
    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    /**
     * @return The homepage
     */
    public Object getHomepage() {
        return homepage;
    }

    /**
     * @param homepage The homepage
     */
    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    /**
     * @return The size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size The size
     */
    public void setSize(Integer size) {
        this.size = size;
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
     * @return The watchersCount
     */
    public Integer getWatchersCount() {
        return watchersCount;
    }

    /**
     * @param watchersCount The watchers_count
     */
    public void setWatchersCount(Integer watchersCount) {
        this.watchersCount = watchersCount;
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

    /**
     * @return The hasIssues
     */
    public Boolean getHasIssues() {
        return hasIssues;
    }

    /**
     * @param hasIssues The has_issues
     */
    public void setHasIssues(Boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    /**
     * @return The hasDownloads
     */
    public Boolean getHasDownloads() {
        return hasDownloads;
    }

    /**
     * @param hasDownloads The has_downloads
     */
    public void setHasDownloads(Boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    /**
     * @return The hasWiki
     */
    public Boolean getHasWiki() {
        return hasWiki;
    }

    /**
     * @param hasWiki The has_wiki
     */
    public void setHasWiki(Boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    /**
     * @return The hasPages
     */
    public Boolean getHasPages() {
        return hasPages;
    }

    /**
     * @param hasPages The has_pages
     */
    public void setHasPages(Boolean hasPages) {
        this.hasPages = hasPages;
    }

    /**
     * @return The forksCount
     */
    public Integer getForksCount() {
        return forksCount;
    }

    /**
     * @param forksCount The forks_count
     */
    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    /**
     * @return The mirrorUrl
     */
    public Object getMirrorUrl() {
        return mirrorUrl;
    }

    /**
     * @param mirrorUrl The mirror_url
     */
    public void setMirrorUrl(Object mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
    }

    /**
     * @return The openIssuesCount
     */
    public Integer getOpenIssuesCount() {
        return openIssuesCount;
    }

    /**
     * @param openIssuesCount The open_issues_count
     */
    public void setOpenIssuesCount(Integer openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    /**
     * @return The forks
     */
    public Integer getForks() {
        return forks;
    }

    /**
     * @param forks The forks
     */
    public void setForks(Integer forks) {
        this.forks = forks;
    }

    /**
     * @return The openIssues
     */
    public Integer getOpenIssues() {
        return openIssues;
    }

    /**
     * @param openIssues The open_issues
     */
    public void setOpenIssues(Integer openIssues) {
        this.openIssues = openIssues;
    }

    /**
     * @return The watchers
     */
    public Integer getWatchers() {
        return watchers;
    }

    /**
     * @param watchers The watchers
     */
    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    /**
     * @return The defaultBranch
     */
    public String getDefaultBranch() {
        return defaultBranch;
    }

    /**
     * @param defaultBranch The default_branch
     */
    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(String.valueOf(_private));
        dest.writeString(htmlUrl);
        dest.writeString(description);
        dest.writeString(String.valueOf(fork));
        dest.writeString(url);
        dest.writeString(pushedAt);
        //dest.writeString(homepage.toString());
        dest.writeInt(size);
        dest.writeInt(stargazersCount);
        dest.writeInt(watchersCount);
        dest.writeString(language);
        dest.writeString(String.valueOf(hasIssues));
        dest.writeString(String.valueOf(hasDownloads));
        dest.writeString(String.valueOf(hasWiki));
        dest.writeString(String.valueOf(hasPages));
        dest.writeInt(forksCount);
        // dest.writeString(mirrorUrl.toString());
        dest.writeInt(openIssuesCount);
        dest.writeInt(forks);
        dest.writeInt(openIssues);
        dest.writeInt(watchers);
        dest.writeString(defaultBranch);
        dest.writeParcelable(owner, flags);

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