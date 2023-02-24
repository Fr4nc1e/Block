package com.code.block.core.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.code.block.core.presentation.ui.theme.SpaceSmall

@Composable
@Throws(IllegalArgumentException::class)
fun RowScope.StandardBottomNavItem(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    selected: Boolean = false,
    alertCount: Int? = null,
    selectedColor: Color = MaterialTheme.colors.onPrimary,
    unselectedColor: Color = MaterialTheme.colors.onSurface,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    if (alertCount != null && alertCount < 0) {
        throw IllegalArgumentException("Alert count can not be negative.")
    }

    val lineLength = animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        selectedContentColor = selectedColor,
        unselectedContentColor = unselectedColor,
        icon = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceSmall)
                    .drawBehind {
                        if (lineLength.value > 0f && enabled) {
                            drawLine(
                                color = if (selected) selectedColor else unselectedColor,
                                start = Offset(
                                    x = size.width / 2f - lineLength.value * 15.dp.toPx(),
                                    y = size.height
                                ),
                                end = Offset(
                                    x = size.width / 2f + lineLength.value * 15.dp.toPx(),
                                    y = size.height
                                ),
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        }
                    }
            ) {
                if (icon != null) {
                    if (alertCount != null) {
                        BadgedBox(
                            modifier = Modifier.align(Alignment.Center),
                            badge = {
                                Badge(
                                    backgroundColor = MaterialTheme.colors.onPrimary
                                ) {
                                    val alertText = if (alertCount > 99) {
                                        "99"
                                    } else {
                                        alertCount.toString()
                                    }

                                    Text(
                                        text = alertText,
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colors.primaryVariant,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = contentDescription,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    } else {
                        Icon(
                            imageVector = icon,
                            contentDescription = contentDescription,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    )
}
