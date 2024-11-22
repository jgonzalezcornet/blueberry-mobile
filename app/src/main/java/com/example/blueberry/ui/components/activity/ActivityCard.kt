package com.example.blueberry.ui.components.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.data.model.Activity
import com.example.blueberry.data.model.User
import com.example.blueberry.R


@Composable
fun ActivityCard(
    activity: Activity,
    currentUser: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        // Activity row
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val activityType = if (activity.payer.id == currentUser.id) ActivityType.SENT else ActivityType.RECEIVED
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Icon(
                        painter = painterResource(id = activityType.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if(activityType == ActivityType.SENT) "${activity.receiver.firstName} ${activity.receiver.lastName}" else "${activity.payer.firstName} ${activity.payer.lastName}",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(){
                    Text(
                        text = activity.createdAt,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    if(activityType == ActivityType.SENT){
                        Text(
                            text = when(activity.type){
                                "CARD" -> stringResource(R.string.activity_card_type)
                                "BALANCE" -> stringResource(R.string.activity_balance_type)
                                else -> ""
                            },
                            fontSize = 12.sp,
                            color = Color.Gray,
                            fontStyle = FontStyle.Italic,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            val prefix = if (activity.payer.id == currentUser.id) "-" else "+"

            Text(
                text = "$prefix \$${activity.amount}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

