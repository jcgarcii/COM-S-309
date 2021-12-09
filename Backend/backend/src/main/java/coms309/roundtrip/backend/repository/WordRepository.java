package coms309.roundtrip.backend.repository;

import coms309.roundtrip.backend.model.User;
import coms309.roundtrip.backend.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface WordRepository extends JpaRepository<Word, Integer> {

    }
