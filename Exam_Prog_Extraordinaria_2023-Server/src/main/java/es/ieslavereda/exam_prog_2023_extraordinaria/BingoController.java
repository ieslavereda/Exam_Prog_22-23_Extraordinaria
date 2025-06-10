package es.ieslavereda.exam_prog_2023_extraordinaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class BingoController {

    Logger LOGGER = Logger.getLogger(getClass().getName());

    @Autowired
    private IBingoService service;

    @DeleteMapping("/bingo")
    public ResponseEntity<?> removeAll(){
        LOGGER.log(Level.INFO,"Removing data");
        try{
            return new ResponseEntity<>(service.removeAll(), HttpStatus.OK);
        } catch (SQLException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bingo")
    public ResponseEntity<?> addBola(@RequestBody Bola bola){
        LOGGER.log(Level.INFO,"Adding ball " + bola.getNumero());
        try{
            return new ResponseEntity<>(service.add(bola), HttpStatus.OK);
        } catch (SQLException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
