/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author 232.004057
 */
     
import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import model.CadastroUsuarioModel;

public class CadastroDAO { // Por convenção, nomes de classe em Java começam com letra maiúscula
    
    // 1. Criamos um MÉTODO que recebe o objeto usuario
    public boolean cadastrar(CadastroUsuarioModel usuario) {
        
        String sql = "INSERT INTO users (nome, cpf, email, passwords) VALUES (?, ?, ?, ?)";
        
        try (var con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            // 2. Usamos o objeto 'usuario' que chegou como parâmetro
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getPasswords()); 
            
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (java.sql.SQLException e) {
        System.err.println("Erro ao salvar no banco: " + e.getMessage());
        return false; 
        }
    } // Fim do método
} // Fim da classe
    

