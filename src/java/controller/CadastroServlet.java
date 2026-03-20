package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importando as suas classes recém-criadas!
import model.CadastroUsuarioModel;
import dao.CadastroDAO;

@WebServlet("/cadastrar")
public class CadastroServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Pegando os dados do formulário
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("passwords");
        
        // 2. Colocando os dados dentro da "caixa" (O seu Model)
        CadastroUsuarioModel usuario = new CadastroUsuarioModel();
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setPasswords(senha); // Lembrete: em um projeto real, faríamos o hash da senha aqui!
        
        // 3. Chamando o DAO para salvar no banco de dados
        CadastroDAO dao = new CadastroDAO();
        boolean sucesso = dao.cadastrar(usuario);
        
        // 4. Configurando a resposta de acordo com o resultado do banco
        response.setContentType("text/html;charset=UTF-8");
        var out = response.getWriter();
        
        out.println("<!DOCTYPE html><html><head><title>Resultado</title></head><body>");
        
        if (sucesso) {
            out.println("<h2>Cadastro realizado com sucesso!</h2>");
            out.println("<p>Bem-vindo(a), <strong>" + nome + "</strong>!</p>");
        } else {
            out.println("<h2>Erro ao realizar o cadastro.</h2>");
            out.println("<p>Verifique os dados ou tente novamente mais tarde.</p>");
        }
        
        out.println("<br><a href='index.html'>Voltar</a>");
        out.println("</body></html>");
    }
}