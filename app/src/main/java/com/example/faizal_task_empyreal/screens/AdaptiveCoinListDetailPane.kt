package com.example.faizal_task_empyreal.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.faizal_task_empyreal.ProductViewModel
import com.example.faizal_task_empyreal.utils.RequestNotificationPermission
import com.example.faizal_task_empyreal.screens.products.ProductDetailsScreen
import com.example.faizal_task_empyreal.screens.products.ProductsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalSharedTransitionApi::class)
@Destination
@Composable
fun AdaptiveCoinListDetailPane(
    navigators: DestinationsNavigator? = null,
    modifier: Modifier = Modifier,
) {

    RequestNotificationPermission()

    val viewModel: ProductViewModel = koinViewModel()

    val state by viewModel.state.collectAsStateWithLifecycle()

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                ProductsScreen(
                    navigator = navigators,
                    onClick = { productId ->
                        viewModel.onProductSelected(productId)
                        navigator.navigateTo(pane = ListDetailPaneScaffoldRole.Detail)
                    },
                )
            }

        },
        detailPane = {

            AnimatedPane {
                val selectedId = state.selectedProductId
                if (selectedId != null && selectedId != 0) {
                    ProductDetailsScreen(
                        navigator = navigators,
                        productId = selectedId,
                        onAddClick = { productId ->
                            viewModel.increaseCount(productId)
                        },
                        onRemoveClick = { productId ->
                            viewModel.decreaseCount(productId)
                        },
                        onBackClick = {
                            navigator.navigateBack()
                        }
                    )
                } else {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Select a product to view details",
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }

        },
        modifier = modifier
    )

}