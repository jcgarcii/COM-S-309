package coms309.roundtrip.backend.repository;
import coms309.roundtrip.backend.model.MessageAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<MessageAction, Long> {


}
