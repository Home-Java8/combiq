package ru.atott.combiq.web.bean;

import java.util.Collection;
import java.util.List;

public class PagingBean {
    private List<Page> pages;
    private int currentPage;
    private int from;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public static class Page {
        private String title;
        private String url;
        private boolean omission;
        private boolean active;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isOmission() {
            return omission;
        }

        public void setOmission(boolean omission) {
            this.omission = omission;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
    }
}
