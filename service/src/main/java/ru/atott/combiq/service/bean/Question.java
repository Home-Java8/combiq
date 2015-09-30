package ru.atott.combiq.service.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Question {
    private String id;
    private String title;
    private List<String> tags;
    private String level;
    private long reputation;
    private QuestionAttrs attrs;
    private String tip;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }

    public QuestionAttrs getAttrs() {
        return attrs;
    }

    public void setAttrs(QuestionAttrs attrs) {
        this.attrs = attrs;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("tags", tags)
                .append("level", level)
                .append("reputation", reputation)
                .append("attrs", attrs)
                .append("tip", tip)
                .toString();
    }
}
