package com.dmb25.consoprotection.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val gtin: Long,
    val numeroFiche: String,
    val numeroVersion: Int,
    val natureJuridiqueRappel: String,
    val categorieProduit: String,
    val sousCategorieProduit: String,
    val marqueProduit: String,
    val libelle: String,
    val modelesOuReferences: String,
    val identificationProduits: String,
    val conditionnements: String?,
    val dateDebutCommercialisation: String?,
    val dateDateFinCommercialisation: String?,
    val temperatureConservation: String?,
    val marqueSalubrite: String?,
    val informationsComplementaires: String?,
    val zoneGeographiqueDeVente: String,
    val distributeurs: String,
    val motifRappel: String,
    val risquesEncourus: String,
    val preconisationsSanitaires: String?,
    val descriptionComplementaireRisque: String?,
    val conduitesATenirParLeConsommateur: String,
    val numeroContact: String?,
    val modalitesDeCompensation: String,
    val dateDeFinDeLaProcedureDeRappel: String?,
    val informationsComplementairesPubliques: String?,
    val liensVersLesImages: String,
    val lienVersLaListeDesProduits: String?,
    val lienVersLaListeDesDistributeurs: String?,
    val lienVersAffichettePdf: String,
    val lienVersLaFicheRappel: String,
    val rappelGuid: String,
    val datePublication: String,
)