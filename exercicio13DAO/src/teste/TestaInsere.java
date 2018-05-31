package teste;

import java.sql.SQLException;
import java.util.Calendar;

import modelo.Contato;
import bd.ContatoDAO;

public class TestaInsere {
	public static void main(String[] args) throws SQLException{
		 // pronto para gravar
		 Contato contato = new Contato();
		 contato.setNome("MAC439");
		 contato.setEmail("mac439@ime.usp.br");
		 contato.setEndereco("R. do Matão, 1010");
		 contato.setDataNascimento(Calendar.getInstance());
		 
		 // grave nessa conexão!!!
		 ContatoDAO dao = new ContatoDAO();
		 
		 // método elegante
		 dao.adiciona(contato);
		 
		 System.out.println("Novo contato gravado com sucesso!");
	}
}
