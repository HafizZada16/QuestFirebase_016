package com.example.questfirebase_016.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questfirebase_016.R
import com.example.questfirebase_016.view.route.DestinasiDetail
import com.example.questfirebase_016.viewmodel.DetailViewModel
import com.example.questfirebase_016.viewmodel.PenyediaViewModel
import com.example.questfirebase_016.viewmodel.StatusUIDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaScreen(
    navigateToEditItem: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiDetail.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            val uiState = viewModel.statusUIDetail
            FloatingActionButton(
                onClick = {
                    when(uiState){ is StatusUIDetail.Success ->
                        navigateToEditItem(uiState.satusiswa!!.id.toInt()) else->{}
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.update)
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        BodyDetailSiswa(
            statusUIDetail = viewModel.statusUIDetail,
            retryAction = viewModel::getSatuSiswa,
            onDeleteClick = {
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}