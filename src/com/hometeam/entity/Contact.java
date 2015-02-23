package com.hometeam.entity;

public class Contact {
    private Integer senderId;
    private Integer receiverId;

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (receiverId != null ? !receiverId.equals(contact.receiverId) : contact.receiverId != null) return false;
        if (senderId != null ? !senderId.equals(contact.senderId) : contact.senderId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = senderId != null ? senderId.hashCode() : 0;
        result = 31 * result + (receiverId != null ? receiverId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                '}';
    }
}
