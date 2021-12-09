package coms309.roundtrip.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "friendship")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_1_id_num")
    private User user1;
    @ManyToOne
    @JoinColumn(name = "user_2_id_num")
    private User user2;

    Friendship(){}

    public Friendship(User user1, User user2)
    {
        this.user1 = user1;
        this.user2 = user2;

    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
