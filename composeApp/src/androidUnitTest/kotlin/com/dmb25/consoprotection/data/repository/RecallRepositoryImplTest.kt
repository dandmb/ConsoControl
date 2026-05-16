package com.dmb25.consoprotection.data.repository

import app.cash.turbine.test
import com.dmb25.consoprotection.data.local.dao.ProductDao
import com.dmb25.consoprotection.data.local.dao.SyncMetadataDao
import com.dmb25.consoprotection.data.local.entity.ProductEntity
import com.dmb25.consoprotection.data.local.entity.SyncMetadataEntity
import com.dmb25.consoprotection.data.remote.ApiService
import com.dmb25.consoprotection.data.remote.dto.RecallResponseDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RecallRepositoryImplTest {

    private lateinit var repository: RecallRepositoryImpl
    private val apiService: ApiService = mockk()
    private val productDao: ProductDao = mockk(relaxed = true)
    private val syncMetadataDao: SyncMetadataDao = mockk(relaxed = true)

    @BeforeTest
    fun setup() {
        repository = RecallRepositoryImpl(apiService, productDao, syncMetadataDao)
    }

    @Test
    fun `getRecalls should return products from dao and trigger refresh`() = runTest {
        val productEntities = listOf(
            createProductEntity(1, "Brand")
        )
        every { productDao.getAll() } returns flowOf(productEntities)
        
        val remoteResponse = RecallResponseDto(
            results = emptyList(),
            totalCount = 10
        )
        coEvery { apiService.getRecalls(any(), any()) } returns remoteResponse
        coEvery { syncMetadataDao.get() } returns null

        repository.getRecalls().test {
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals("Brand", result[0].marqueProduit) 
            assertEquals("LIBELLE", result[0].libelle) // toModel calls .uppercase()
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { apiService.getRecalls(limit = 20, offset = 0) }
        coVerify { productDao.insertAll(any()) }
    }

    @Test
    fun `getProductById should return product from dao`() = runTest {
        val entity = createProductEntity(1, "Brand")
        coEvery { productDao.getById(1) } returns entity

        val result = repository.getProductById(1)

        assertEquals(1, result?.id)
        assertEquals("Brand", result?.marqueProduit)
        assertEquals("LIBELLE", result?.libelle)
    }

    @Test
    fun `canLoadMore should return true when local count is less than remote count`() = runTest {
        coEvery { syncMetadataDao.get() } returns SyncMetadataEntity(id = 1, totalLocalCount = 5, totalRemoteCount = 10)

        val result = repository.canLoadMore()

        assertEquals(true, result)
    }

    @Test
    fun `canLoadMore should return false when local count is equal to remote count`() = runTest {
        coEvery { syncMetadataDao.get() } returns SyncMetadataEntity(id = 1, totalLocalCount = 10, totalRemoteCount = 10)

        val result = repository.canLoadMore()

        assertEquals(false, result)
    }

    private fun createProductEntity(id: Int, marque: String) = ProductEntity(
        id = id,
        gtin = 123L,
        numeroFiche = "F123",
        numeroVersion = 1,
        natureJuridiqueRappel = "Legal",
        categorieProduit = "Category",
        sousCategorieProduit = "Sub",
        marqueProduit = marque,
        libelle = "Libelle",
        modelesOuReferences = "Model",
        identificationProduits = "ID",
        conditionnements = null,
        dateDebutCommercialisation = null,
        dateDateFinCommercialisation = null,
        temperatureConservation = null,
        marqueSalubrite = null,
        informationsComplementaires = null,
        zoneGeographiqueDeVente = "Zone",
        distributeurs = "Distributor",
        motifRappel = "Reason",
        risquesEncourus = "Risks",
        preconisationsSanitaires = null,
        descriptionComplementaireRisque = null,
        conduitesATenirParLeConsommateur = "Conduct",
        numeroContact = null,
        modalitesDeCompensation = "Comp",
        dateDeFinDeLaProcedureDeRappel = null,
        informationsComplementairesPubliques = null,
        liensVersLesImages = "img",
        lienVersLaListeDesProduits = null,
        lienVersLaListeDesDistributeurs = null,
        lienVersAffichettePdf = "pdf",
        lienVersLaFicheRappel = "fiche",
        rappelGuid = "guid",
        datePublication = "2023-01-01T12:00:00+01:00" // Valid ISO format for mapper
    )
}