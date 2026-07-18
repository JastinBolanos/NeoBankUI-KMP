package com.neobank.app.cards.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.neobank.app.cards.presentation.components.CardItem
import com.neobank.app.core.navigation.NavTab
import com.neobank.app.core.navigation.NeoBottomNavigationBar
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.bg_neobank
import neobankui.shared.generated.resources.ic_bell
import neobankui.shared.generated.resources.ic_scanner
import neobankui.shared.generated.resources.ic_visa
import org.jetbrains.compose.resources.painterResource
import kotlin.math.absoluteValue

@Composable
fun CardsScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToHistory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    selectedTab: NavTab = NavTab.Cards,
    onTabSelected: (NavTab) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // BACKGROUND
        Image(
            painter = painterResource(Res.drawable.bg_neobank),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        val cards = remember {
            listOf(
                mapOf(
                    "holder" to "Saurabh Kumar",
                    "number" to "5764 9968 6789 1289",
                    "masked" to "**** **** **** 1289",
                    "expiry" to "09 / 25",
                    "cvv" to "654",
                    "balance" to "$5,750.20"
                ),
                mapOf(
                    "holder" to "Saurabh Kumar",
                    "number" to "5764 9968 6789 1388",
                    "masked" to "**** **** **** 1388",
                    "expiry" to "11 / 26",
                    "cvv" to "721",
                    "balance" to "$10,985.84"
                ),
                mapOf(
                    "holder" to "Saurabh Kumar",
                    "number" to "5764 9968 6789 4923",
                    "masked" to "**** **** **** 4923",
                    "expiry" to "01 / 27",
                    "cvv" to "903",
                    "balance" to "$2,340.00"
                )
            )
        }

        val pagerState = rememberPagerState(pageCount = { cards.size })

        Column(modifier = Modifier.fillMaxSize()) {
            // --- HEADER ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 64.dp, bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cards",
                    color = Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // SCANNER Icon
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_scanner),
                        contentDescription = "Scan card",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // NOTIFICATIONS Icon
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_bell),
                        contentDescription = "Notifications",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // --- CAROUSEL ---
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 48.dp),
                pageSpacing = 16.dp,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                val scale = lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )

                val alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )

                val card = cards[page]
                CardItem(
                    balance = card["balance"]!!,
                    cardNumber = card["masked"]!!,
                    expiryDate = card["expiry"]!!,
                    modifier = Modifier.graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                )
            }

            // --- INDICATORS ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(cards.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color.White else Color.White.copy(alpha = 0.4f))
                    )
                    if (index < cards.size - 1) {
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                }
            }
        }

        // --- CARD DETAILS ---
        Surface(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.58f)
                .align(Alignment.BottomCenter),
            color = Color(0xFFE8E9EB),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            val currentCard = cards[pagerState.currentPage]
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
                    .padding(bottom = 90.dp)
            ) {
                Text(
                    text = "CARD DETAILS",
                    color = Color(0xFF1A1A1A),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Serif,
                    letterSpacing = 1.2.sp,
                    modifier = Modifier.padding(bottom = 18.dp)
                )

                // Cardholder
                Text(
                    text = "Cardholder Name",
                    color = Color(0xFF222222),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFDCDDE0))
                        .padding(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = currentCard["holder"]!!,
                        color = Color(0xFF666666),
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card Number
                Text(
                    text = "Card Number",
                    color = Color(0xFF222222),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFDCDDE0))
                        .padding(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.width(34.dp).height(20.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.CenterStart)
                                    .clip(CircleShape)
                                    .background(Color(0xFFEB001B))
                            )
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.CenterEnd)
                                    .clip(CircleShape)
                                    .background(Color(0xFFF79E1B).copy(alpha = 0.9f))
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = currentCard["number"]!!,
                            color = Color(0xFF666666),
                            fontSize = 15.sp,
                            letterSpacing = 0.4.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Account Number
                Text(
                    text = "Account Number",
                    color = Color(0xFF222222),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFDCDDE0))
                        .padding(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(Res.drawable.ic_visa),
                            contentDescription = "Visa Logo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .width(40.dp)
                                .height(21.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = currentCard["account"] as? String ?: "0011-0815-0741312654",
                            color = Color(0xFF666666),
                            fontSize = 15.sp,
                            letterSpacing = 0.2.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Expiration + CVV
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Expire Date",
                            color = Color(0xFF222222),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFDCDDE0))
                                .padding(horizontal = 12.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = currentCard["expiry"]!!,
                                color = Color(0xFF666666),
                                fontSize = 15.sp
                            )
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "CVV / CVC",
                            color = Color(0xFF222222),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFDCDDE0))
                                .padding(horizontal = 12.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = currentCard["cvv"]!!,
                                color = Color(0xFF666666),
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }

        // --- BOTTOM NAVIGATION ---
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            NeoBottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { tab ->
                    when (tab) {
                        NavTab.Home     -> onNavigateToHome()
                        NavTab.History  -> onNavigateToHistory()
                        NavTab.Cards    -> { /* We are already here */ }
                        NavTab.Profile  -> onNavigateToProfile()
                        NavTab.Transfer -> { /* Currently unused */ }
                    }
                }
            )
        }
    }
}