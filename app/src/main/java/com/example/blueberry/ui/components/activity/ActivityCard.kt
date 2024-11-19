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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.R
import java.sql.Date
import java.text.SimpleDateFormat

@Composable
fun ActivityCard(
    activityItem: ActivityItem,
    modifier: Modifier = Modifier
) {
    val formattedDate = SimpleDateFormat(stringResource(R.string.date_format), Locale.current.toJavaLocale()).format(activityItem.date)

    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
    ) {

        // Activity row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = activityItem.activityType.icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = activityItem.user,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = stringResource(activityItem.activityType.label),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            val prefix = if (activityItem.activityType == ActivityType.RECEIVED) "+" else "-" // Prefix based on ActivityType
            val amountText = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$prefix$${activityItem.amount}") // Prefix with $ and +/-
                }
            }

            Text(
                text = amountText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// Helper function for locale conversion
fun androidx.compose.ui.text.intl.Locale.toJavaLocale(): java.util.Locale {
    return java.util.Locale(this.language, this.region)
}

// Preview for the composable
@Preview(showBackground = true)
@Composable
fun ActivityPreview() {
    Column {
        ActivityCard(ActivityItem(Date(2020, 10, 10), ActivityType.SENT, "Nicolás Priotto", 100.0))
        ActivityCard(ActivityItem(Date(2020, 10, 10), ActivityType.RECEIVED, "Nicolás Priotto", 222100.0))
        ActivityCard(ActivityItem(Date(2020, 10, 10), ActivityType.PAYMENT, "Nicolás Priotto", 22222.0))

    }
}