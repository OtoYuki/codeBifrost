package model;

import java.util.List;

public class RoadmapModel {
    private int roadmapId;
    private String title;
    private String description;
    private String skillLevel;
    private String imagePath;
    private List<ResourceModel> resources;

    public RoadmapModel() {

    }

    public RoadmapModel(int roadmapId, String title, String description, String skillLevel, String imagePath,
            List<ResourceModel> resources) {
        super();
        this.roadmapId = roadmapId;
        this.title = title;
        this.description = description;
        this.skillLevel = skillLevel;
        this.resources = resources;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(int roadmapId) {
        this.roadmapId = roadmapId;
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

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public List<ResourceModel> getResources() {
        return resources;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setResources(List<ResourceModel> resources) {
        this.resources = resources;
    }

}
