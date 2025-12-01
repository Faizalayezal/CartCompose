package com.example.faizal_task_empyreal.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer


@Composable
fun ShimmerProductGrid(modifier: Modifier = Modifier) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(8) {
            ShimmerProductCard(shimmer = shimmer)
        }
    }
}

@Composable
fun ShimmerProductCard(shimmer: Shimmer) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shimmer(shimmer)
                .background(Color.LightGray.copy(alpha = 0.4f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Color.Gray.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(16.dp)
                    .fillMaxWidth(0.5f)
                    .background(Color.Gray.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.height(6.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(14.dp)
                    .fillMaxWidth(0.8f)
                    .background(Color.Gray.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
