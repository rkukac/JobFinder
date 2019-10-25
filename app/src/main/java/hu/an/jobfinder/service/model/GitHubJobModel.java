package hu.an.jobfinder.service.model;

import com.squareup.moshi.Json;

public class GitHubJobModel {

    @Json(name = "id")
    String mId;
    @Json(name = "type")
    String mType;
    @Json(name = "url")
    String mUrl;
    @Json(name = "company")
    String mCompany;
    @Json(name = "company_url")
    String mCompanyUrl;
    @Json(name = "location")
    String mLocation;
    @Json(name = "title")
    String mTitle;
    @Json(name = "description")
    String mDescription;

    public GitHubJobModel() {
    }

    public GitHubJobModel(String mId, String mType, String mUrl, String mCompany, String mCompanyUrl, String mLocation, String mTitle, String mDescription) {
        this.mId = mId;
        this.mType = mType;
        this.mUrl = mUrl;
        this.mCompany = mCompany;
        this.mCompanyUrl = mCompanyUrl;
        this.mLocation = mLocation;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getCompanyUrl() {
        return mCompanyUrl;
    }

    public void setCompanyUrl(String mCompanyUrl) {
        this.mCompanyUrl = mCompanyUrl;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
