package fr.eni.projetenchere.dal;

public class DAOFactory {
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}

}
