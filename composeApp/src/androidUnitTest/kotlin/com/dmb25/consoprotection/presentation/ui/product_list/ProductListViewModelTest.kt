package com.dmb25.consoprotection.presentation.ui.product_list

import app.cash.turbine.test
import com.dmb25.consoprotection.domain.model.Product
import com.dmb25.consoprotection.domain.repository.RecallRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class ProductListViewModelTest {

    private val repository: RecallRepository = mockk()
    private lateinit var viewModel: ProductListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { repository.getRecalls() } returns flowOf(emptyList())
        viewModel = ProductListViewModel(repository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be Loading then Success if data present`() = runTest {
        val products = listOf(createProduct(1, "Cat 1"))
        every { repository.getRecalls() } returns flowOf(products)
        
        viewModel = ProductListViewModel(repository)

        viewModel.uiState.test {
            val item = awaitItem()
            if (item is UiState.Loading) {
                val nextItem = awaitItem()
                assertTrue(nextItem is UiState.Success)
                assertEquals(1, (nextItem as UiState.Success).data.size)
            } else if (item is UiState.Success) {
                assertEquals(1, item.data.size)
            }
        }
    }

    @Test
    fun `search should update searchResults`() = runTest {
        val products = listOf(createProduct(1, "Cat 1"))
        coEvery { repository.searchRecalls("query") } returns products

        viewModel.search("query")
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.searchResults.test {
            assertEquals(products, awaitItem())
        }
    }

    @Test
    fun `onCategorySelected should update selectedCategory`() = runTest {
        viewModel.onCategorySelected("Cat 1")
        assertEquals("Cat 1", viewModel.selectedCategory.value)

        viewModel.onCategorySelected("Cat 1")
        assertEquals(null, viewModel.selectedCategory.value)
    }

    private fun createProduct(id: Int, category: String) = Product(
        id = id,
        gtin = 123L,
        numeroFiche = "F123",
        numeroVersion = 1,
        natureJuridiqueRappel = "Legal",
        categorieProduit = category,
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