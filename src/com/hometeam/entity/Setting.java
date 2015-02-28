package com.hometeam.entity;

public class Setting {
    private Integer userId;
    private String name;
    private String value;

    public Setting() {
    }

    public Setting(Integer userId, String name, String value) {
        this.userId = userId;
        this.name = name;
        this.value = value;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Setting setting = (Setting) o;

        if (name != null ? !name.equals(setting.name) : setting.name != null) return false;
        if (userId != null ? !userId.equals(setting.userId) : setting.userId != null) return false;
        if (value != null ? !value.equals(setting.value) : setting.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
