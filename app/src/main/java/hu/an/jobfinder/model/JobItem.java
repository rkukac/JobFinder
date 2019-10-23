package hu.an.jobfinder.model;

public class JobItem {

    private String mId;
    private String mCompany;
    private String mCompanyUrl;
    private String mLocation;
    private String mTitle;
    private String mDescription;
    private boolean mIsFavorite;

    public JobItem() {}

    public JobItem(String mId, String mCompany, String mCompanyUrl, String mLocation, String mTitle, String mDescription) {
        this.mId = mId;
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

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public void setFavorite(boolean mIsFavorite) {
        this.mIsFavorite = mIsFavorite;
    }
}
