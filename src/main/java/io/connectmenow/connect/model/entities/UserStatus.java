package io.connectmenow.connect.model.entities;

public enum UserStatus {
  ONLINE {
    public String toString() {
      return "online";
    }
  },
  OFFLINE {
    public String toString() {
      return "offline";
    }
  },
  DND {
    public String toString() {
      return "dnd";
    }
  },
  INACTIVE {
    public String toString() {
      return "inactive";
    }
  };

  public abstract String toString();

}
