package coms309.roundtrip.backend.repository;

import coms309.roundtrip.backend.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import coms309.roundtrip.backend.model.SavedDrawing;

public interface SavedDrawingRepository extends JpaRepository<SavedDrawing, Long> {

}
