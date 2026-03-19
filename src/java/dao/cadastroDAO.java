
package dao;

import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.UserModel;

public class cadastroDAO { // Por convenção, nomes de classe em Java começam com letra maiúscula
    
    // 1. Criamos um MÉTODO que recebe o objeto usuario
    public boolean cadastrar(UsuarioModel usuario) {
        
        String sql = "INSERT INTO users (nome, cpf, email, passwords) VALUES (?, ?, ?, ?)";
        
        try (var con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            // 2. Usamos o objeto 'usuario' que chegou como parâmetro
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getPassword()); 
            
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    } // Fim do método
} // Fim da classe