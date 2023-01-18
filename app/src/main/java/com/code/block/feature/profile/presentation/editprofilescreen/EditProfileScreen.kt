package com.code.block.feature.profile.presentation.editprofilescreen

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.code.block.R
import com.code.block.core.presentation.components.StandardTopBar
import com.code.block.core.presentation.ui.theme.SpaceLarge
import com.code.block.core.presentation.ui.theme.SpaceMedium
import com.code.block.core.presentation.ui.theme.SpaceSmall
import com.code.block.feature.profile.presentation.editprofilescreen.components.BannerEditSection
import com.code.block.feature.profile.presentation.editprofilescreen.components.ChipContent
import com.code.block.feature.profile.presentation.editprofilescreen.components.EditTextSection
import kotlin.random.Random

@Composable
fun EditProfileScreen(
    onNavigateUp: () -> Unit = {},
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardTopBar(
            navActions = {
                IconButton(
                    onClick = {
                        viewModel.onEvent(EditProfileEvent.EditionCompleted)
                        if (
                            state.value.usernameError == null && state.value.qqError == null && state.value.weChatError == null && state.value.gitHubError == null &&
                            state.value.bioError == null
                        ) {
                            onNavigateUp()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(id = R.string.save_changes),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.edit_your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            BannerEditSection(
                bannerImage = painterResource(id = R.drawable.hd_batman),
                profileImage = painterResource(id = R.drawable.batman_profile_image)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceLarge)
            ) {
                Spacer(modifier = Modifier.height(SpaceSmall))

                EditTextSection()

                Spacer(modifier = Modifier.height(SpaceMedium))

                ChipContent(
                    list = listOf(
                        "Movie",
                        "Basketball",
                        "Music",
                        "Rock",
                        "Classic",
                        "80's Music",
                        "Comics"
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    selected = Random.nextInt(2) == 0
                )
            }
        }
    }
}
