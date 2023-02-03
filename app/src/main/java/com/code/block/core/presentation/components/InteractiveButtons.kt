package com.code.block.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.code.block.R
import com.code.block.core.domain.model.Post
import com.code.block.core.presentation.ui.theme.IconSizeMedium
import com.code.block.core.presentation.ui.theme.SpaceSmall

@Composable
fun InteractiveButtons(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
    post: Post,
    isLiked: Boolean,
    showDeleteIcon: Boolean = true,
    onLikeClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = CenterVertically
        ) {
            IconButton(
                onClick = {
                    onLikeClick()
                },
                modifier = Modifier.size(IconSizeMedium)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = if (isLiked) {
                        stringResource(R.string.unlike)
                    } else {
                        stringResource(R.string.like)
                    },
                    tint = if (isLiked) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.onSurface
                    }
                )
            }

            Spacer(modifier = Modifier.width(SpaceSmall))

            Text(
                text = "${post.likeCount}",
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.clickable {
                    onNavigate(Screen.PersonListScreen.route + "/${post.id}")
                }
            )
        }

        Row(
            verticalAlignment = CenterVertically
        ) {
            IconButton(
                onClick = {
                    onCommentClick()
                },
                modifier = Modifier.size(IconSizeMedium)
            ) {
                Icon(
                    imageVector = Icons.Filled.Comment,
                    contentDescription = stringResource(R.string.comment)
                )
            }

            Spacer(modifier = Modifier.width(SpaceSmall))

            Text(
                text = "${post.commentCount}",
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onSurface
            )
        }

        IconButton(
            onClick = {
                onShareClick()
            },
            modifier = Modifier.size(IconSizeMedium)
                .align(CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(R.string.share)
            )
        }

        if (post.isOwnPost && showDeleteIcon) {
            IconButton(
                onClick = {
                    onDeleteClick()
                },
                modifier = Modifier.size(IconSizeMedium)
                    .align(CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(R.string.delete)
                )
            }
        }
    }
}
