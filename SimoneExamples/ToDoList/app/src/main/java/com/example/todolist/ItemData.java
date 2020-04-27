package com.example.todolist;

public class ItemData {
    private String title;
    private String descr;
    private Boolean checked;

    public ItemData(String title, String descr, Boolean checked) {
        this.title = title;
        this.descr = descr;
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
