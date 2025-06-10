package es.ieslavereda.exam_prog_2023_extraordinaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BingoServiceImp implements IBingoService{

    @Autowired
    private BingoRepository repository;

    @Override
    public String removeAll() throws SQLException {
        if(repository.removeAll())
            return "OK";
        return "ERROR!!";
    }

    @Override
    public Bola add(Bola bola) throws SQLException {
        return repository.add(bola);
    }
}
