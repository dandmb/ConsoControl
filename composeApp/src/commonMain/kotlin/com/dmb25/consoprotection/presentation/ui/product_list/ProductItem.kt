package com.dmb25.consoprotection.presentation.ui.product_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dmb25.consoprotection.domain.model.Product
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.collections.mutableListOf

@Composable
fun ProductItem(
    product: Product,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            AsyncImage(
                model = product.liensVersLesImages,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = product.libelle,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = product.motifRappel,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.datePublication,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
internal fun ProductListScreen() {
    val articles = remember {
        mutableListOf(
            Product(
                categorieProduit = "alimentation",
                conduitesATenirParLeConsommateur = "ne plus consommer|rapporter le produit au point de vente",
                dateDateFinCommercialisation = "2024-11-23",
                dateDeFinDeLaProcedureDeRappel = "2022-09-04",
                dateDebutCommercialisation = "2024-04-24",
                datePublication = "2024-06-25",
                descriptionComplementaireRisque = "eneur en substances chimiques (nitrosamines) dépasse les valeurs autorisées",
                distributeurs = "magasins jouets, puériculture, cadeaux pour enfants",
                gtin = 8904001200662L,
                id = 15620,
                identificationProduits = "tous les lots non concerné |8904001200662 non concerné",
                informationsComplementaires = "forme en fonction du gout",
                libelle = "saucisson sec oignon",
                lienVersAffichettePdf = "https://rappel.conso.gouv.fr/affichettepdf/14561/interne",
                lienVersLaFicheRappel = "https://rappel.conso.gouv.fr/fiche-rappel/14561/interne",
                lienVersLaListeDesDistributeurs = "https://rappel.conso.gouv.fr/document/510504b7-b321-4977-9ed7-67215a0dfe6c/interne/listedesdistributeurs",
                liensVersLesImages = "https://rappel.conso.gouv.fr/image/07273225-1c07-4665-bf40-c1d15f5af7e1.jpg",
                marqueProduit = "le philou normand",
                modalitesDeCompensation = "remboursement",
                modelesOuReferences = "saucisson sec oignon",
                motifRappel = "présence de salmonelle",
                numeroContact = "0231470021",
                preconisationsSanitaires = "les toxi-infections alimentaires causées par les salmonelles se traduisent par des troubles gastro-intestinaux (diarrhée, vomissements) d'apparition brutale souvent accompagnés de fièvre et de maux de tête qui surviennent généralement 6h à 72h après la consommation des produits contaminés. ces symptômes peuvent être plus prononcés chez les jeunes enfants, les femmes enceintes, les sujets immunodéprimés et les personnes âgées. les personnes qui auraient consommé ces produits et qui présenteraient ces symptômes sont invitées à consulter leur médecin traitant en lui signalant cette consommation. en l'absence de symptômes dans les 7 jours après la consommation des produits concernés, il est inutile de s'inquiéter et de consulter un médecin. si le produit doit subir une cuisson avant consommation : la cuisson à cœur des produits (œufs durs, pâtisseries, viandes de volailles…) à +65°c permet de détruire ces bactéries et de prévenir les conséquences d'une telle contamination.",
                rappelGuid = "12a939e6-45dc-4b91-9141-4ab4aa8ea57f",
                risquesEncourus = "salmonella spp (agent responsable de la salmonellose)",
                zoneGeographiqueDeVente = "france entière"
            ),
            Product(
                categorieProduit = "alimentation",
                conduitesATenirParLeConsommateur = "ne plus consommer|rapporter le produit au point de vente",
                dateDateFinCommercialisation = "2024-11-23",
                dateDeFinDeLaProcedureDeRappel = "2022-09-04",
                dateDebutCommercialisation = "2024-04-24",
                datePublication = "2024-06-25",
                descriptionComplementaireRisque = "eneur en substances chimiques (nitrosamines) dépasse les valeurs autorisées",
                distributeurs = "magasins jouets, puériculture, cadeaux pour enfants",
                gtin = 8904001200662L,
                id = 15620,
                identificationProduits = "tous les lots non concerné |8904001200662 non concerné",
                informationsComplementaires = "forme en fonction du gout",
                libelle = "saucisson sec oignon",
                lienVersAffichettePdf = "https://rappel.conso.gouv.fr/affichettepdf/14561/interne",
                lienVersLaFicheRappel = "https://rappel.conso.gouv.fr/fiche-rappel/14561/interne",
                lienVersLaListeDesDistributeurs = "https://rappel.conso.gouv.fr/document/510504b7-b321-4977-9ed7-67215a0dfe6c/interne/listedesdistributeurs",
                liensVersLesImages = "https://rappel.conso.gouv.fr/image/eae995da-1d76-4fe3-ac16-afcf4b65a851.jpg",
                marqueProduit = "le philou normand",
                modalitesDeCompensation = "remboursement",
                modelesOuReferences = "saucisson sec oignon",
                motifRappel = "présence de salmonelle",
                numeroContact = "0231470021",
                preconisationsSanitaires = "les toxi-infections alimentaires causées par les salmonelles se traduisent par des troubles gastro-intestinaux (diarrhée, vomissements) d'apparition brutale souvent accompagnés de fièvre et de maux de tête qui surviennent généralement 6h à 72h après la consommation des produits contaminés. ces symptômes peuvent être plus prononcés chez les jeunes enfants, les femmes enceintes, les sujets immunodéprimés et les personnes âgées. les personnes qui auraient consommé ces produits et qui présenteraient ces symptômes sont invitées à consulter leur médecin traitant en lui signalant cette consommation. en l'absence de symptômes dans les 7 jours après la consommation des produits concernés, il est inutile de s'inquiéter et de consulter un médecin. si le produit doit subir une cuisson avant consommation : la cuisson à cœur des produits (œufs durs, pâtisseries, viandes de volailles…) à +65°c permet de détruire ces bactéries et de prévenir les conséquences d'une telle contamination.",
                rappelGuid = "12a939e6-45dc-4b91-9141-4ab4aa8ea57f",
                risquesEncourus = "salmonella spp (agent responsable de la salmonellose)",
                zoneGeographiqueDeVente = "france entière"
            ),
            Product(
                categorieProduit = "alimentation",
                conduitesATenirParLeConsommateur = "ne plus consommer|rapporter le produit au point de vente",
                dateDateFinCommercialisation = "2024-11-23",
                dateDeFinDeLaProcedureDeRappel = "2022-09-04",
                dateDebutCommercialisation = "2024-04-24",
                datePublication = "2024-06-25",
                descriptionComplementaireRisque = "eneur en substances chimiques (nitrosamines) dépasse les valeurs autorisées",
                distributeurs = "magasins jouets, puériculture, cadeaux pour enfants",
                gtin = 8904001200662L,
                id = 15620,
                identificationProduits = "tous les lots non concerné |8904001200662 non concerné",
                informationsComplementaires = "forme en fonction du gout",
                libelle = "saucisson sec oignon",
                lienVersAffichettePdf = "https://rappel.conso.gouv.fr/affichettepdf/14561/interne",
                lienVersLaFicheRappel = "https://rappel.conso.gouv.fr/fiche-rappel/14561/interne",
                lienVersLaListeDesDistributeurs = "https://rappel.conso.gouv.fr/document/510504b7-b321-4977-9ed7-67215a0dfe6c/interne/listedesdistributeurs",
                liensVersLesImages = "https://rappel.conso.gouv.fr/image/5ba1fa9a-90e9-41ca-8014-5bb02c231f29.jpg",
                marqueProduit = "le philou normand",
                modalitesDeCompensation = "remboursement",
                modelesOuReferences = "saucisson sec oignon",
                motifRappel = "présence de salmonelle",
                numeroContact = "0231470021",
                preconisationsSanitaires = "les toxi-infections alimentaires causées par les salmonelles se traduisent par des troubles gastro-intestinaux (diarrhée, vomissements) d'apparition brutale souvent accompagnés de fièvre et de maux de tête qui surviennent généralement 6h à 72h après la consommation des produits contaminés. ces symptômes peuvent être plus prononcés chez les jeunes enfants, les femmes enceintes, les sujets immunodéprimés et les personnes âgées. les personnes qui auraient consommé ces produits et qui présenteraient ces symptômes sont invitées à consulter leur médecin traitant en lui signalant cette consommation. en l'absence de symptômes dans les 7 jours après la consommation des produits concernés, il est inutile de s'inquiéter et de consulter un médecin. si le produit doit subir une cuisson avant consommation : la cuisson à cœur des produits (œufs durs, pâtisseries, viandes de volailles…) à +65°c permet de détruire ces bactéries et de prévenir les conséquences d'une telle contamination.",
                rappelGuid = "12a939e6-45dc-4b91-9141-4ab4aa8ea57f",
                risquesEncourus = "salmonella spp (agent responsable de la salmonellose)",
                zoneGeographiqueDeVente = "france entière"
            ),
            Product(
                categorieProduit = "alimentation",
                conduitesATenirParLeConsommateur = "ne plus consommer|rapporter le produit au point de vente",
                dateDateFinCommercialisation = "2024-11-23",
                dateDeFinDeLaProcedureDeRappel = "2022-09-04",
                dateDebutCommercialisation = "2024-04-24",
                datePublication = "2024-06-25",
                descriptionComplementaireRisque = "eneur en substances chimiques (nitrosamines) dépasse les valeurs autorisées",
                distributeurs = "magasins jouets, puériculture, cadeaux pour enfants",
                gtin = 8904001200662L,
                id = 15620,
                identificationProduits = "tous les lots non concerné |8904001200662 non concerné",
                informationsComplementaires = "forme en fonction du gout",
                libelle = "saucisson sec oignon",
                lienVersAffichettePdf = "https://rappel.conso.gouv.fr/affichettepdf/14561/interne",
                lienVersLaFicheRappel = "https://rappel.conso.gouv.fr/fiche-rappel/14561/interne",
                lienVersLaListeDesDistributeurs = "https://rappel.conso.gouv.fr/document/510504b7-b321-4977-9ed7-67215a0dfe6c/interne/listedesdistributeurs",
                liensVersLesImages = "https://rappel.conso.gouv.fr/image/986b986f-2902-450e-a023-8d51c5d42c7c.jpg",
                marqueProduit = "le philou normand",
                modalitesDeCompensation = "remboursement",
                modelesOuReferences = "saucisson sec oignon",
                motifRappel = "présence de salmonelle",
                numeroContact = "0231470021",
                preconisationsSanitaires = "les toxi-infections alimentaires causées par les salmonelles se traduisent par des troubles gastro-intestinaux (diarrhée, vomissements) d'apparition brutale souvent accompagnés de fièvre et de maux de tête qui surviennent généralement 6h à 72h après la consommation des produits contaminés. ces symptômes peuvent être plus prononcés chez les jeunes enfants, les femmes enceintes, les sujets immunodéprimés et les personnes âgées. les personnes qui auraient consommé ces produits et qui présenteraient ces symptômes sont invitées à consulter leur médecin traitant en lui signalant cette consommation. en l'absence de symptômes dans les 7 jours après la consommation des produits concernés, il est inutile de s'inquiéter et de consulter un médecin. si le produit doit subir une cuisson avant consommation : la cuisson à cœur des produits (œufs durs, pâtisseries, viandes de volailles…) à +65°c permet de détruire ces bactéries et de prévenir les conséquences d'une telle contamination.",
                rappelGuid = "12a939e6-45dc-4b91-9141-4ab4aa8ea57f",
                risquesEncourus = "salmonella spp (agent responsable de la salmonellose)",
                zoneGeographiqueDeVente = "france entière"
            )

        )
    }
    ProductListContent(
        articles
    )
}
