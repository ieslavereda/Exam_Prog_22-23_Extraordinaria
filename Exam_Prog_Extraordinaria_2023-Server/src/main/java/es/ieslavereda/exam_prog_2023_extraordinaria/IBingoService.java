package es.ieslavereda.exam_prog_2023_extraordinaria;

import java.sql.SQLException;

public interface IBingoService {
     String removeAll() throws SQLException;

     Object add(Bola bola) throws SQLException;
}
