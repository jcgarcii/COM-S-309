package coms309.roundtrip.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.*;
import javax.persistence.JoinColumn;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long idNum;

    private String loginname;
    private String displayname;
    private String password;

    @OneToMany
    private Set<FriendRequest> friendRequestSet;


    public User(String loginname, String displayname, String password) {
        this.loginname = loginname;
        this.displayname = displayname;
        this.password = password;
        this.friendRequestSet = new HashSet<>();
    }

    public User() {
    }


    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean denyFriend(FriendRequest request) {
        if (request != null) {
            friendRequestSet.remove(request);
            return true;
        }
        return false;
    }

    public void friendRequest(FriendRequest newRequest) {
        this.friendRequestSet.add(newRequest);
    }

    public String getLoginname() {
        return loginname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public String getPassword() {
        return password;
    }

    public void setIdNum(long id)
    {
        this.idNum = id;
    }
    public boolean addFriendRequest(FriendRequest request) {

        if (!friendRequestSet.isEmpty()) {
            for (FriendRequest req : friendRequestSet) {
                if (request.getId() == req.getId ()) {
                    return false;
                }
            }
        }
            this.friendRequestSet.add(request);
            return true;
        }

}