package com.hometeam.entity;

public class Right {
    private Integer userId;
    private Integer permissionId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Right right = (Right) o;

        if (permissionId != null ? !permissionId.equals(right.permissionId) : right.permissionId != null) return false;
        if (userId != null ? !userId.equals(right.userId) : right.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Right{" +
                "userId=" + userId +
                ", permissionId=" + permissionId +
                '}';
    }
}
