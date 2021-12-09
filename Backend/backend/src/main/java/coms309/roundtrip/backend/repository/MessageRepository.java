package coms309.roundtrip.backend.repository;

import coms309.roundtrip.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
