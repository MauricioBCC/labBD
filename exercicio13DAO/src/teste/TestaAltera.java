package teste;

import java.sql.SQLException;

import modelo.Contato;
import bd.ContatoDAO;

public class TestaAltera {
	public static void main(String[] args) throws SQLException{
		 int  id = 1; 
		
		 ContatoDAO dao = new ContatoDAO();
		 Contato contato = dao.obter(id);
		 
		 if (contato != null) {
			 // altera e-mail e endereco
			 contato.setEmail("mac439novo@ime.usp.br");
			 contato.setEndereco("R. do Matão, 1010 -- Cidade Universitária");
			 		 
			 // grava as alteracoes
			 dao.altera(contato);
			 
			 System.out.println("Alterações no contato gravadas com sucesso!");
		 }
		 else
			 System.out.println("Não existe contato com id " + String.valueOf(id) + "!");
	}
}
