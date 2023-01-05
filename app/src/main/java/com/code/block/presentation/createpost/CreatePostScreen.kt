package com.code.block.presentation.createpost

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.code.block.R
import com.code.block.presentation.components.StandardTopBar
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CreatePostScreen() {
    StandardTopBar(
        title = {
            Text(
                text = stringResource(R.string.make_post),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}
