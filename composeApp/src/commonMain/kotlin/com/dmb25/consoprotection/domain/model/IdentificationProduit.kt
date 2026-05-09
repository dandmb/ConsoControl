package com.dmb25.consoprotection.domain.model

data class IdentificationProduit(
    val gtin: String,
    val numeroLot: String,
    val typeDateLimite: String,
    val dateDebut: String,
    val dateFin: String?,
)