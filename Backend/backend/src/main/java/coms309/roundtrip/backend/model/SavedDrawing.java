package coms309.roundtrip.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Blob;

@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev")
@Entity
@Table(name = "drawings")
public class SavedDrawing{

    public SavedDrawing(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idNum;

    private long userCreator;

    private String associatedWord;

    @Lob
    @JsonIgnore
    private String drawing;


    public long getIdNum() {
        return idNum;
    }

    public void setIdNum(long idNum) {
        this.idNum = idNum;
    }

    public long getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(long userCreator) {
        this.userCreator = userCreator;
    }

    public String getAssociatedWord() {
        return associatedWord;
    }

    public void setAssociatedWord(String associatedWord) {
        this.associatedWord = associatedWord;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public SavedDrawing(long userCreator, String associatedWord, String drawing) {
        this.userCreator = userCreator;
        this.associatedWord = associatedWord;
      //  this.drawing = drawing;
    }
}
