package io.connectmenow.connect.model.entities;

public enum MeetingStatus {
  SUCCESSFUL {
    public String toString() {
      return "successful";
    }
  },
  ONGOING {
    public String toString() {
      return "ongoing";
    }
  },
  PENDING {
    public String toString() { return "pending"; }
  },
  CANCELLED {
    public String toString() { return "cancelled"; }
  };

  public abstract String toString();
}
