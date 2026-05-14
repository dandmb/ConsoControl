package com.dmb25.consoprotection.presentation.ui.product_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dmb25.consoprotection.domain.model.Product
import com.dmb25.consoprotection.presentation.utils.openUrl
import consoprotection.composeapp.generated.resources.Res
import consoprotection.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductDetailsSuccessContent(
    product: Product,
) {
    var currentImageIndex by remember { mutableIntStateOf(0) }
    var isImageVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (isImageVisible) "Masquer l'image" else "Afficher l'image",
                style = MaterialTheme.typography.bodyMedium
            )
            Switch(
                checked = isImageVisible,
                onCheckedChange = { isImageVisible = it }
            )
        }

        if (isImageVisible) {
            Box(modifier = Modifier.fillMaxWidth()) {
                if (product.images.isEmpty()) {
                    Image(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 250.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                } else {
                    AsyncImage(
                        model = product.images[currentImageIndex],
                        placeholder = painterResource(Res.drawable.compose_multiplatform),
                        error = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 250.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )

                    if (currentImageIndex > 0) {
                        IconButton(
                            onClick = { currentImageIndex-- },
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(4.dp)
                                .background(Color.Black.copy(alpha = 0.4f), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Image précédente",
                                tint = Color.White
                            )
                        }
                    }

                    if (product.images.size > 1 && currentImageIndex < product.images.size - 1) {
                        IconButton(
                            onClick = { currentImageIndex++ },
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(4.dp)
                                .background(Color.Black.copy(alpha = 0.4f), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Image suivante",
                                tint = Color.White
                            )
                        }
                    }

                    if (product.images.size > 1) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(8.dp)
                        ) {
                            product.images.forEachIndexed { index, _ ->
                                Box(
                                    modifier = Modifier
                                        .size(if (index == currentImageIndex) 10.dp else 6.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (index == currentImageIndex) Color.White
                                            else Color.White.copy(alpha = 0.5f)
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = product.libelle.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${product.marqueProduit.replaceFirstChar { it.uppercase() }} • ${product.categorieProduit.replaceFirstChar { it.uppercase() }}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = product.sousCategorieProduit.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )
        }

        InfoSection(
            title = "Danger",
            icon = Icons.Default.Warning
        ) {
            InfoRow(
                label = "Motif du rappel",
                value = product.motifRappel.replaceFirstChar { it.uppercase() })
            InfoRow(
                label = "Risques encourus",
                value = product.risquesEncourus.replaceFirstChar { it.uppercase() })
            product.descriptionComplementaireRisque?.let {
                InfoRow(
                    label = "Description complémentaire",
                    value = it.replaceFirstChar { c -> c.uppercase() })
            }
            product.preconisationsSanitaires?.let {
                InfoRow(
                    label = "Préconisations sanitaires",
                    value = it.replaceFirstChar { c -> c.uppercase() })
            }
        }

        InfoSection(
            title = "Identification",
            icon = Icons.Default.Info
        ) {
            InfoRow(
                label = "Modèles ou références",
                value = product.modelesOuReferences.joinToString(", ") { it.replaceFirstChar { c -> c.uppercase() } }
            )
            product.conditionnements?.let {
                InfoRow(
                    label = "Conditionnements",
                    value = it.joinToString(", ") { c -> c.replaceFirstChar { ch -> ch.uppercase() } }
                )
            }
            product.dateDebutCommercialisation?.let {
                InfoRow(label = "Date début commercialisation", value = it)
            }
            product.dateDateFinCommercialisation?.let {
                InfoRow(label = "Date fin commercialisation", value = it)
            }
        }

        InfoSection(
            title = "Distribution",
            icon = Icons.Default.ShoppingCart
        ) {
            InfoChipGroup(
                label = "Distributeurs",
                values = product.distributeurs
            )
            Spacer(modifier = Modifier.height(8.dp))
            InfoChipGroup(
                label = "Zone géographique de vente",
                values = product.zoneGeographiqueDeVente
            )
        }

        InfoSection(
            title = "Que faire ?",
            icon = Icons.Default.LocationOn
        ) {
            InfoChipGroup(
                label = "Conduites à tenir",
                values = product.conduitesATenirParLeConsommateur
            )
            Spacer(modifier = Modifier.height(8.dp))
            InfoChipGroup(
                label = "Modalités de compensation",
                values = product.modalitesDeCompensation
            )
            product.numeroContact?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        InfoSection(
            title = "Dates",
            icon = Icons.Default.DateRange
        ) {
            InfoRow(
                label = "Date de publication",
                value = product.datePublication
            )
            product.dateDeFinDeLaProcedureDeRappel?.let {
                InfoRow(label = "Date de fin de procédure", value = it)
            }
        }

        InfoSection(
            title = "Liens utiles",
            icon = Icons.Default.Link
        ) {
            Button(
                onClick = { openUrl(product.lienVersAffichettePdf) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.PictureAsPdf,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Télécharger le PDF")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = { openUrl(product.lienVersLaFicheRappel) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.OpenInBrowser,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Voir la fiche officielle")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}