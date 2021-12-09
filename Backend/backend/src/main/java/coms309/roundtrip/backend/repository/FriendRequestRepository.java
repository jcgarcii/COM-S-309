package coms309.roundtrip.backend.repository;

import coms309.roundtrip.backend.model.FriendRequest;
import coms309.roundtrip.backend.model.Friendship;
import coms309.roundtrip.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    }


