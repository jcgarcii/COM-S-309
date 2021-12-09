package coms309.roundtrip.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class FriendRequest {

    public static final String d = "DENIED";
    public static final String p = "PENDING";
    public static final String a = "ACCEPTED";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    long userFrom;

    long userTo;

    String status;

    public FriendRequest(){}

    public FriendRequest(long userFrom, long userTo) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.status = p;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(long userFrom) {
        this.userFrom = userFrom;
    }

    public long getUserTo() {
        return userTo;
    }

    public void setUserTo(long userTo) {
        this.userTo = userTo;
    }

    public String getStatus() {
        return status;
    }

    public void setIdNum(long id)
    {
        this.id = id;
    }

  public void deny()
  {
      status = d;
  }
  public void accept()
  {
      status = a;
  }

}

