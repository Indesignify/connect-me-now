package io.connectmenow.connect.model.entities;

public enum ParticipationStatus {

  ACCEPTED {
    public String toString() {
      return "accepted";
    }
  },
  PENDING {
    public String toString() {
      return "pending";
    }
  },
  DECLINED {
    public String toString() {
      return "declined";
    }
  };

  public abstract String toString();

}
