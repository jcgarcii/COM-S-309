package coms309.roundtrip.backend.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import coms309.roundtrip.backend.model.*;
import coms309.roundtrip.backend.repository.FriendRequestRepository;
import coms309.roundtrip.backend.repository.FriendshipRepository;
import coms309.roundtrip.backend.repository.SavedDrawingRepository;
import coms309.roundtrip.backend.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SavedDrawingController {


    @Autowired
    SavedDrawingRepository drawingRepo;

    /**
        Get all the drawing ids associated with a user.
     **/
    @GetMapping("drawing/all/{userID}")
    List<Long> GetAllDrawings(@PathVariable long userID){
        List<Long> list = new ArrayList<Long>();
        List<SavedDrawing> buffer = drawingRepo.findAll();

        for(SavedDrawing current : buffer)
        {
            if(current.getUserCreator() == userID)
            {
                list.add(current.getIdNum());
            }
        }
        return list;
    }


    /**
        Returns a drawing by id.
     **/
    @GetMapping("drawing/getDrawing/{id}")
    String GetDrawing(@PathVariable long id) throws SQLException {

        SavedDrawing drawing;
        drawing = drawingRepo.getById(id);
        return drawing.getDrawing();
    }

    @RequestMapping(value="drawing/postDrawing/{userId}/{word}", method = RequestMethod.POST)
    String PostDrawing(@PathVariable long userId, @PathVariable String word, @RequestBody String drawing) throws IOException, SQLException {
        if(drawing != null)
        {
            SavedDrawing newDrawing = new SavedDrawing();
            newDrawing.setAssociatedWord(word);
            newDrawing.setIdNum(userId);
            newDrawing.setDrawing(drawing);
            drawingRepo.save(newDrawing);
            return "Succ";
        }
        else{return "Failure";}
    }

    @PutMapping("drawing/delete/{idNum}")
    String DeleteDrawing(@PathVariable long idNum) {
        if (drawingRepo.existsById(idNum)) {
            drawingRepo.deleteById(idNum);
            return "Success";
        } else {
            return "Drawing Not Found";
        }
    }
}
