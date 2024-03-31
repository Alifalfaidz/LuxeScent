package org.d3if0145.luxescent.ui.screen

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0145.luxescent.R
import org.d3if0145.luxescent.model.parfum
import org.d3if0145.luxescent.navigasi.Screen
import org.d3if0145.luxescent.ui.theme.LuxeScentTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.About.route)}) {
                        Icon(
                            imageVector = Icons.Outlined.Info ,
                            contentDescription = stringResource(id = R.string.about_application),
                            tint = MaterialTheme.colorScheme.primary
                            )
                    }
                }
            )
        },
        floatingActionButton = {
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.StartActivityForResult() ){

            }
            FloatingActionButton(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6285156819723"))
                launcher.launch(intent)
            }
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "WhatsApp" )

            }
        }
    )
    {
            padding -> ScreenContent(Modifier.padding(padding))
    }
}

fun ActivityResultLauncher<Intent>.launch(intent: Intent) {
    this.launch(intent)
}

@Composable
fun ListItem(parfum: parfum, onClick: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = parfum.brand)
        Text(text = parfum.nama_parfum)
        Text(text = "IDR ${ parfum.harga.toString() }" )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    LuxeScentTheme {
        MainScreen(rememberNavController())
    }
}
@Composable
fun ScreenContent(modifier: Modifier){
    val viewModel: MainViewModel = viewModel()
    val data = viewModel.data

    if (data.isEmpty()){
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = stringResource(id = R.string.list_kosong))
    }

    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 84.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(data) { parfum ->
                Column(modifier = Modifier.padding(8.dp)) {
                    ListItem(parfum = parfum){

                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 8.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )
            }
        }
    }
}
