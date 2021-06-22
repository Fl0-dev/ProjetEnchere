package fr.eni.projetenchere.dal;

import java.util.List;

import fr.eni.projetenchere.bo.Enchere;

public interface EnchereDAO {

	/**
	 * selectionne en DB toutes les enchères en cours et les met dans une liste
	 * @return listeEncheres
	 */
	List<Enchere> selectAllEnchere();
	
	
	/**
	 * selectionne en DB toutes les enchères en cours selon
	 * @param noCategorie 
	 * @param nomArticle 
	 * @return listeEncheres
	 */
	//List<Enchere> selectEnchereByCategorie(String nomArticle, int noCategorie);

	

}
