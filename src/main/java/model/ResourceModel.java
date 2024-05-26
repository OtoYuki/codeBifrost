package model;

public class ResourceModel {
    private int resourceId;
    private String title;
    private String description;
    private String type;
    private String url;
    private String content;
    private String programmingLanguage;

    public ResourceModel() {

    }

    public ResourceModel(int resourceId, String title, String description, String type, String url, String content,
            String programmingLanguage) {
        super();
        this.resourceId = resourceId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.url = url;
        this.content = content;
        this.programmingLanguage = programmingLanguage;
    }

    // Getters and Setters
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

}
