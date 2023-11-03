package com.coin.ui.account

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.coin.R
import com.coin.ui.navigation.CoinRoute
import com.coin.ui.navigation.CoinTopLevelDestination
import com.coin.ui.navigation.LayoutType
import com.coin.ui.navigation.TOP_LEVEL_DESTINATIONS
import com.coin.ui.navigation.navigationMeasurePolicy
import com.coin.ui.theme.CoinClientAndroidTheme
import com.coin.ui.utils.CoinNavigationContentPosition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDrawerContent(
    selectedDestination: String,
    navigationContentPosition: CoinNavigationContentPosition,
    navigateToTopLevelDestination: (CoinTopLevelDestination) -> Unit,
    navigatePageDestination: (String) -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://pic.lvmama.com/uploads/pc/place2/2017-05-23/c362e80d-eadf-4b26-807c-629eec13bb4f.jpg")
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    ModalDrawerSheet {
        //头像
        Column(
            modifier = Modifier
                .layoutId(LayoutType.HEADER)
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                model = "https://pic.lvmama.com/uploads/pc/place2/2017-05-23/c362e80d-eadf-4b26-807c-629eec13bb4f.jpg",
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "名字",
                modifier = Modifier.padding(top = 10.dp),
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            //选项
            AccountDrawerItem(
                title = "选项",
                icon = Icons.Default.ArrowForward,
                contentDescription = "点击",
                onClick = {},
            )
            AccountDrawerItem(
                title = "选项",
                icon = Icons.Default.ArrowForward,
                contentDescription = "点击",
                onClick = {},
            )
            AccountDrawerItem(
                title = "选项",
                icon = Icons.Default.ArrowForward,
                contentDescription = "点击",
                onClick = {},
            )
        }

    }
}


@Composable
fun AccountDrawerItem(
    title: String,
    contentDescription: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize)
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(15.dp)
        )
    }
}