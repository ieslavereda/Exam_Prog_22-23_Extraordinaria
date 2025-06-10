package es.ieslavereda.exam_prog_2023_extraordinaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class BingoRepository {

    @Autowired
    private DataSource dataSource;

    public boolean removeAll() throws SQLException {
        String sql = "{ CALL clean() }";
        try (Connection c = dataSource.getConnection();
             CallableStatement cs = c.prepareCall(sql)) {

            cs.execute();
            return true;
        }
    }

    public Bola add(Bola bola) throws SQLException {
        String sql = "INSERT INTO bolas (numero,color) VALUES (?,?)";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1,bola.getNumero());
            ps.setInt(2,bola.getColor());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                bola.setId(rs.getInt(1));
            return bola;
        }
    }


}
