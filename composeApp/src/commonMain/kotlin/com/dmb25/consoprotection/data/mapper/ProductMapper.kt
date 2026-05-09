package com.dmb25.consoprotection.data.mapper

import com.dmb25.consoprotection.data.local.entity.ProductEntity
import com.dmb25.consoprotection.domain.model.IdentificationProduit

import com.dmb25.consoprotection.data.remote.dto.ProductDto
import com.dmb25.consoprotection.domain.model.Product

fun String.toIdentificationProduits(): List<IdentificationProduit> {
    return this.split("|").mapNotNull { entry ->
        val parts = entry.split("$")
        if (parts.size >= 4) {
            IdentificationProduit(
                gtin = parts[0],
                numeroLot = parts[1],
                typeDateLimite = parts[2],
                dateDebut = parts[3],
                dateFin = parts.getOrNull(4)?.takeIf { it.isNotBlank() }
            )
        } else null
    }
}

fun String.splitPipe(): List<String> =
    this.split("|").map { it.trim() }.filter { it.isNotBlank() }

fun String.splitAntoine(): List<String> =
    this.split("¤").map { it.trim() }.filter { it.isNotBlank() }

fun String?.splitPipeOrNull(): List<String>? =
    this?.split("|")?.map { it.trim() }?.filter { it.isNotBlank() }?.takeIf { it.isNotEmpty() }

fun String?.splitAntoineOrNull(): List<String>? =
    this?.split("¤")?.map { it.trim() }?.filter { it.isNotBlank() }?.takeIf { it.isNotEmpty() }

fun String.capitalize() = replaceFirstChar { it.uppercase() }

fun String.toFormattedDate(): String {
    val parts = this.split("T")
    val datePart = parts[0].split("-")
    val timePart = parts[1].split("+")[0].split(":")

    val year = datePart[0]
    val month = datePart[1]
    val day = datePart[2]

    val hour = timePart[0]
    val minute = timePart[1]
    val second = timePart[2]

    return "$hour:$minute:$second - $day/$month/$year"
}

fun ProductDto.toEntity(): ProductEntity = ProductEntity(
    id = id,
    gtin = gtin,
    numeroFiche = numeroFiche,
    numeroVersion = numeroVersion,
    natureJuridiqueRappel = natureJuridiqueRappel,
    categorieProduit = categorieProduit,
    sousCategorieProduit = sousCategorieProduit,
    marqueProduit = marqueProduit,
    libelle = libelle,
    modelesOuReferences = modelesOuReferences,
    identificationProduits = identificationProduits,
    conditionnements = conditionnements,
    dateDebutCommercialisation = dateDebutCommercialisation,
    dateDateFinCommercialisation = dateDateFinCommercialisation,
    temperatureConservation = temperatureConservation,
    marqueSalubrite = marqueSalubrite,
    informationsComplementaires = informationsComplementaires,
    zoneGeographiqueDeVente = zoneGeographiqueDeVente,
    distributeurs = distributeurs,
    motifRappel = motifRappel,
    risquesEncourus = risquesEncourus,
    preconisationsSanitaires = preconisationsSanitaires,
    descriptionComplementaireRisque = descriptionComplementaireRisque,
    conduitesATenirParLeConsommateur = conduitesATenirParLeConsommateur,
    numeroContact = numeroContact,
    modalitesDeCompensation = modalitesDeCompensation,
    dateDeFinDeLaProcedureDeRappel = dateDeFinDeLaProcedureDeRappel,
    informationsComplementairesPubliques = informationsComplementairesPubliques,
    liensVersLesImages = liensVersLesImages,
    lienVersLaListeDesProduits = lienVersLaListeDesProduits,
    lienVersLaListeDesDistributeurs = lienVersLaListeDesDistributeurs,
    lienVersAffichettePdf = lienVersAffichettePdf,
    lienVersLaFicheRappel = lienVersLaFicheRappel,
    rappelGuid = rappelGuid,
    datePublication = datePublication,
)

fun ProductEntity.toModel(): Product = Product(
    id = id,
    gtin = gtin,
    numeroFiche = numeroFiche,
    numeroVersion = numeroVersion,
    natureJuridiqueRappel = natureJuridiqueRappel,
    categorieProduit = categorieProduit,
    sousCategorieProduit = sousCategorieProduit,
    marqueProduit = marqueProduit,
    libelle = libelle.uppercase(),
    modelesOuReferences = modelesOuReferences.splitAntoine(),
    identificationProduits = identificationProduits.toIdentificationProduits(),
    conditionnements = conditionnements.splitAntoineOrNull(),
    dateDebutCommercialisation = dateDebutCommercialisation,
    dateDateFinCommercialisation = dateDateFinCommercialisation,
    temperatureConservation = temperatureConservation,
    marqueSalubrite = marqueSalubrite,
    informationsComplementaires = informationsComplementaires,
    zoneGeographiqueDeVente = zoneGeographiqueDeVente.splitPipe(),
    distributeurs = distributeurs.splitPipe().flatMap { it.splitAntoine() },
    motifRappel = motifRappel.capitalize(),
    risquesEncourus = risquesEncourus,
    preconisationsSanitaires = preconisationsSanitaires,
    descriptionComplementaireRisque = descriptionComplementaireRisque,
    conduitesATenirParLeConsommateur = conduitesATenirParLeConsommateur.splitPipe(),
    numeroContact = numeroContact,
    modalitesDeCompensation = modalitesDeCompensation.splitPipe(),
    dateDeFinDeLaProcedureDeRappel = dateDeFinDeLaProcedureDeRappel,
    informationsComplementairesPubliques = informationsComplementairesPubliques,
    images = liensVersLesImages.splitPipe(),
    lienVersLaListeDesProduits = lienVersLaListeDesProduits,
    lienVersLaListeDesDistributeurs = lienVersLaListeDesDistributeurs,
    lienVersAffichettePdf = lienVersAffichettePdf,
    lienVersLaFicheRappel = lienVersLaFicheRappel,
    rappelGuid = rappelGuid,
    datePublication = datePublication.toFormattedDate(),
)