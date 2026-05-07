package com.dmb25.consoprotection.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("categorie_produit")
    val categorieProduit: String,
    @SerialName("conditionnements")
    val conditionnements: String?,
    @SerialName("conduites_a_tenir_par_le_consommateur")
    val conduitesATenirParLeConsommateur: String,
    @SerialName("date_date_fin_commercialisation")
    val dateDateFinCommercialisation: String?,
    @SerialName("date_de_fin_de_la_procedure_de_rappel")
    val dateDeFinDeLaProcedureDeRappel: String?,
    @SerialName("date_debut_commercialisation")
    val dateDebutCommercialisation: String?,
    @SerialName("date_publication")
    val datePublication: String,
    @SerialName("description_complementaire_risque")
    val descriptionComplementaireRisque: String?,
    @SerialName("distributeurs")
    val distributeurs: String,
    @SerialName("gtin")
    val gtin: Long,
    @SerialName("id")
    val id: Int,
    @SerialName("identification_produits")
    val identificationProduits: String,
    @SerialName("informations_complementaires")
    val informationsComplementaires: String?,
    @SerialName("informations_complementaires_publiques")
    val informationsComplementairesPubliques: String?,
    @SerialName("libelle")
    val libelle: String,
    @SerialName("lien_vers_affichette_pdf")
    val lienVersAffichettePdf: String,
    @SerialName("lien_vers_la_fiche_rappel")
    val lienVersLaFicheRappel: String,
    @SerialName("lien_vers_la_liste_des_distributeurs")
    val lienVersLaListeDesDistributeurs: String?,
    @SerialName("lien_vers_la_liste_des_produits")
    val lienVersLaListeDesProduits: String?,
    @SerialName("liens_vers_les_images")
    val liensVersLesImages: String,
    @SerialName("marque_produit")
    val marqueProduit: String,
    @SerialName("marque_salubrite")
    val marqueSalubrite: String?,
    @SerialName("modalites_de_compensation")
    val modalitesDeCompensation: String,
    @SerialName("modeles_ou_references")
    val modelesOuReferences: String,
    @SerialName("motif_rappel")
    val motifRappel: String,
    @SerialName("nature_juridique_rappel")
    val natureJuridiqueRappel: String,
    @SerialName("numero_contact")
    val numeroContact: String?,
    @SerialName("numero_fiche")
    val numeroFiche: String,
    @SerialName("numero_version")
    val numeroVersion: Int,
    @SerialName("preconisations_sanitaires")
    val preconisationsSanitaires: String?,
    @SerialName("rappel_guid")
    val rappelGuid: String,
    @SerialName("risques_encourus")
    val risquesEncourus: String,
    @SerialName("sous_categorie_produit")
    val sousCategorieProduit: String,
    @SerialName("temperature_conservation")
    val temperatureConservation: String?,
    @SerialName("zone_geographique_de_vente")
    val zoneGeographiqueDeVente: String
)