package com.dmb25.consoprotection.presentation.ui.product_detail

import app.cash.turbine.test
import com.dmb25.consoprotection.domain.model.Product
import com.dmb25.consoprotection.domain.repository.RecallRepository
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
class ProductDetailsViewModelTest {

    private val repository: RecallRepository = mockk()
    private lateinit var viewModel: ProductDetailsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProductDetailsViewModel(repository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onFetch should update state to Success when product found`() = runTest {
        val product = createProduct(1)
        coEvery { repository.getProductById(1) } returns product

        viewModel.state.test {
            assertEquals(ProductDetailsViewState.Loading, awaitItem())
            
            viewModel.onFetch(1)
            
            val successItem = awaitItem()
            assertTrue(successItem is ProductDetailsViewState.Success)
            assertEquals(product, (successItem as ProductDetailsViewState.Success).product)
        }
    }

    @Test
    fun `onFetch should update state to Error when product not found`() = runTest {
        coEvery { repository.getProductById(1) } returns null

        viewModel.state.test {
            assertEquals(ProductDetailsViewState.Loading, awaitItem())
            
            viewModel.onFetch(1)
            
            val errorItem = awaitItem()
            assertTrue(errorItem is ProductDetailsViewState.Error)
        }
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