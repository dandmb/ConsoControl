package com.dmb25.consoprotection.presentation.ui.codescan

import app.cash.turbine.test
import com.dmb25.consoprotection.domain.model.Product
import com.dmb25.consoprotection.domain.repository.RecallRepository
import com.dmb25.consoprotection.scanner.BarcodeScanner
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ScannerViewModelTest {

    private val scanner: BarcodeScanner = mockk()
    private val repository: RecallRepository = mockk()
    private lateinit var viewModel: ScannerViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ScannerViewModel(scanner, repository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `startScan should update state to Error when no code detected`() = runTest {
        coEvery { scanner.scan() } returns null

        viewModel.uiState.test {
            assertEquals(ScannerUiState.Loading, awaitItem())
            
            viewModel.startScan {}
            
            val errorItem = awaitItem()
            assertTrue(errorItem is ScannerUiState.Error)
            assertEquals("Aucun code détecté", (errorItem as ScannerUiState.Error).error)
        }
    }

    @Test
    fun `startScan should call onProductFound when product retrieved`() = runTest {
        val product = createProduct(1)
        coEvery { scanner.scan() } returns "123"
        coEvery { repository.getProductByGtin(123L) } returns product

        var foundId: String? = null
        viewModel.startScan { foundId = it }
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals("1", foundId)
    }

    private fun createProduct(id: Int) = Product(
        id = id,
        gtin = 123L,
        numeroFiche = "F123",
        numeroVersion = 1,
        natureJuridiqueRappel = "Legal",
        categorieProduit = "Cat",
        sousCategorieProduit = "Sub",
        marqueProduit = "Brand",
        libelle = "Libelle",
        modelesOuReferences = emptyList(),
        identificationProduits = emptyList(),
        conditionnements = null,
        dateDebutCommercialisation = null,
        dateDateFinCommercialisation = null,
        temperatureConservation = null,
        marqueSalubrite = null,
        informationsComplementaires = null,
        zoneGeographiqueDeVente = emptyList(),
        distributeurs = emptyList(),
        motifRappel = "Reason",
        risquesEncourus = "Risks",
        preconisationsSanitaires = null,
        descriptionComplementaireRisque = null,
        conduitesATenirParLeConsommateur = emptyList(),
        numeroContact = null,
        modalitesDeCompensation = emptyList(),
        dateDeFinDeLaProcedureDeRappel = null,
        informationsComplementairesPubliques = null,
        images = emptyList(),
        lienVersLaListeDesProduits = null,
        lienVersLaListeDesDistributeurs = null,
        lienVersAffichettePdf = "pdf",
        lienVersLaFicheRappel = "fiche",
        rappelGuid = "guid",
        datePublication = "2023-01-01"
    )
}