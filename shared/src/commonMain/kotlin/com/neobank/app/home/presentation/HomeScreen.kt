package com.neobank.app.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import com.neobank.app.core.navigation.NavTab
import com.neobank.app.core.navigation.NeoBottomNavigationBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.neobank.app.menu.presentation.MenuPanel
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.bg_neobank
import neobankui.shared.generated.resources.ic_more
import neobankui.shared.generated.resources.ic_bonuses
import neobankui.shared.generated.resources.ic_cards
import neobankui.shared.generated.resources.ic_transfer
import neobankui.shared.generated.resources.ic_bell
import neobankui.shared.generated.resources.ic_google
import neobankui.shared.generated.resources.ic_youtube
import neobankui.shared.generated.resources.ic_facebook
import neobankui.shared.generated.resources.ic_visa
import neobankui.shared.generated.resources.ic_claro
import neobankui.shared.generated.resources.ic_scanner
import neobankui.shared.generated.resources.bg_credit_banner
import neobankui.shared.generated.resources.bg_iphone_banner
import neobankui.shared.generated.resources.bg_metal_banner
import neobankui.shared.generated.resources.bg_travel_banner
import neobankui.shared.generated.resources.img_card_metal
import neobankui.shared.generated.resources.img_credit_lock
import neobankui.shared.generated.resources.img_invest_coins
import neobankui.shared.generated.resources.img_iphone_titanium
import neobankui.shared.generated.resources.img_travel_miles
import org.jetbrains.compose.resources.DrawableResource

data class PromoBanner(
    val title: String,
    val subtitle: String,
    val backgroundColor: Color,
    val imageRes: DrawableResource? = null
)

@Composable
fun HomeScreen(
    balance: Double,
    onNavigateToCards: () -> Unit = {},
    onNavigateToSendMoney: () -> Unit = {},
    onNavigateToHistory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    selectedTab: NavTab = NavTab.Home,
    onTabSelected: (NavTab) -> Unit = {}
) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        // BACKGROUND
        Image(
            painter = painterResource(Res.drawable.bg_neobank),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // MAIN CONTENT
        Column(modifier = Modifier.fillMaxSize()) {

            // --- TOP HALF ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.45f)
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                TopBarSection(modifier = Modifier.padding(horizontal = 24.dp))

                Spacer(modifier = Modifier.height(40.dp))

                BalanceSection(balance = balance)

                Spacer(modifier = Modifier.height(40.dp))

                QuickActionsSection(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onCardsClick = onNavigateToCards,
                    onTransferClick = onNavigateToSendMoney,
                    onMoreClick = { isMenuOpen = true }
                )

                Spacer(modifier = Modifier.height(32.dp))

                PromoCardsSection()
            }

            // --- BOTTOM HALF ---
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color(0xFFF8F9FA),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                TransactionsSection(bottomPadding = 90.dp)
            }
        }

        // BOTTOM NAVIGATION BAR
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            NeoBottomNavigationBar(
                selectedTab = NavTab.Home,
                onTabSelected = { tab ->
                    when (tab) {
                        NavTab.Cards -> onNavigateToCards()
                        NavTab.History -> onNavigateToHistory()
                        NavTab.Profile -> onNavigateToProfile()
                        NavTab.Home -> { /* We are already here */ }
                        NavTab.Transfer -> { /* Only used from the top button */ }
                    }
                }
            )
        }

        // --- SIDE MENU ANIMATIONS ---

        // 3. Dark background layer (Dimmer)
        AnimatedVisibility(
            visible = isMenuOpen,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { isMenuOpen = false }
            )
        }

        // 4. Sliding Panel
        AnimatedVisibility(
            visible = isMenuOpen,
            enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
            exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            MenuPanel(
                onClose = { isMenuOpen = false },
                onNavigateToProfile = onNavigateToProfile
            )
        }
    }
}


// --- SUB-COMPONENTS ---
@Composable
private fun TopBarSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hi Saurabh Kumar",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
            Text(
                text = "Welcome Back",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // SCANNER Icon
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_scanner),
                    contentDescription = "Scan code",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

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
    }
}

fun Double.toFormattedCurrency(): String {
    val parts = this.toString().split(".")
    val integerPart = parts[0]
    val decimalPart = if (parts.size > 1) parts[1].padEnd(2, '0').take(2) else "00"
    val formattedInteger = integerPart.reversed().chunked(3).joinToString(",").reversed()
    return "$formattedInteger.$decimalPart"
}

// 2. BALANCE SECTION
@Composable
private fun BalanceSection(balance: Double) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$${balance.toFormattedCurrency()}",
            color = Color.White,
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 1.sp
        )
        Text(
            text = "Total Balance",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun QuickActionsSection(
    modifier: Modifier = Modifier,
    onCardsClick: () -> Unit,
    onTransferClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionItemPainter(
            painter = painterResource(Res.drawable.ic_transfer),
            label = "Transfer",
            onClick = onTransferClick
        )

        ActionItemPainter(
            painter = painterResource(Res.drawable.ic_cards),
            label = "Cards",
            onClick = onCardsClick
        )

        ActionItemPainter(
            painter = painterResource(Res.drawable.ic_bonuses),
            label = "My bonuses",
            onClick = { /* Future logic */ }
        )

        ActionItemPainter(
            painter = painterResource(Res.drawable.ic_more),
            label = "More",
            onClick = onMoreClick
        )
    }
}

@Composable
private fun ActionItemPainter(
    painter: androidx.compose.ui.graphics.painter.Painter,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(76.dp)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.1f))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = label,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
// =========================================================================
// SECTION: INFINITE CAROUSEL WITH REAL BANNERS AND 3D IMAGES
// =========================================================================
@Composable
private fun PromoCardsSection() {
    // 1. 5 BANNERS
    val banners = listOf(
        PromoBanner(
            title = "Upgrade to Metal",
            subtitle = "Request your NeoBank Metal card and get 5% cashback.",
            backgroundColor = Color(0xFF181A20),
            imageRes = Res.drawable.img_card_metal
        ),
        PromoBanner(
            title = "iPhone 15 Pro Titanium",
            subtitle = "0% interest and 24 installments with your NeoBank card.",
            backgroundColor = Color(0xFF232529),
            imageRes = Res.drawable.img_iphone_titanium
        ),
        PromoBanner(
            title = "The world awaits you",
            subtitle = "Earn double miles and get VIP Lounge access.",
            backgroundColor = Color(0xFF6A11CB),
            imageRes = Res.drawable.img_travel_miles
        ),
        PromoBanner(
            title = "Make your money grow",
            subtitle = "Invest in the top 500 US companies starting from $10.",
            backgroundColor = Color(0xFF15171E),
            imageRes = Res.drawable.img_invest_coins
        ),
        PromoBanner(
            title = "Credit Line Approved",
            subtitle = "You have $15,000 available. Instant disbursement.",
            backgroundColor = Color(0xFFFF9580),
            imageRes = Res.drawable.img_credit_lock
        ),
    )

    val actualPageCount = banners.size
    val initialPage = (Int.MAX_VALUE / 2) - ((Int.MAX_VALUE / 2) % actualPageCount)
    val pagerState = androidx.compose.foundation.pager.rememberPagerState(
        initialPage = initialPage,
        pageCount = { Int.MAX_VALUE }
    )

    // AUTO-SCROLL
    androidx.compose.runtime.LaunchedEffect(pagerState.settledPage) {
        kotlinx.coroutines.delay(3000)
        pagerState.animateScrollToPage(
            page = pagerState.currentPage + 1,
            animationSpec = tween(
                durationMillis = 800,
                easing = androidx.compose.animation.core.FastOutSlowInEasing
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // The card carousel
        androidx.compose.foundation.pager.HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 24.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->

            val actualPage = page % actualPageCount
            val currentBanner = banners[actualPage]

            // BANNER DESIGN
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(currentBanner.backgroundColor)
            ) {
                // --------------------------------------------------------
                // 1. EXCLUSIVE BACKGROUNDS (iPhone, Credit, Metal, and Travel)
                // --------------------------------------------------------
                if (currentBanner.title.contains("iPhone")) {
                    Image(
                        painter = painterResource(Res.drawable.bg_iphone_banner),
                        contentDescription = "Premium iPhone Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else if (currentBanner.title.contains("Credit")) {
                    Image(
                        painter = painterResource(Res.drawable.bg_credit_banner),
                        contentDescription = "Premium Credit Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else if (currentBanner.title.contains("Metal")) {
                    Image(
                        painter = painterResource(Res.drawable.bg_metal_banner),
                        contentDescription = "Premium Metal Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else if (currentBanner.title.contains("awaits")) {
                    Image(
                        painter = painterResource(Res.drawable.bg_travel_banner),
                        contentDescription = "Premium Travel Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // --------------------------------------------------------
                // 2. THE FAMOUS TRANSPARENT CIRCLE
                // --------------------------------------------------------
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 20.dp, y = (-20).dp)
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.05f))
                )

                // --------------------------------------------------------
                // 3. Banner Texts
                // --------------------------------------------------------
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 18.dp, top = 16.dp, bottom = 16.dp, end = if (currentBanner.imageRes != null) 110.dp else 18.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentBanner.title,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = currentBanner.subtitle,
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        maxLines = 2,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }

                // --------------------------------------------------------
                // 4. THE 3D RENDER
                // --------------------------------------------------------
                if (currentBanner.imageRes != null) {
                    Image(
                        painter = painterResource(currentBanner.imageRes),
                        contentDescription = "3D Illustration",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 12.dp)
                            .size(90.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // The indicator dots
        val currentActualPage = pagerState.currentPage % actualPageCount

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(actualPageCount) { index ->
                LoadingDot(isActive = index == currentActualPage)
            }
        }
    }
}

@Composable
private fun LoadingDot(isActive: Boolean) {
    val animatedWidth by androidx.compose.animation.core.animateDpAsState(
        targetValue = if (isActive) 32.dp else 6.dp,
        animationSpec = tween(
            durationMillis = 300,
            easing = androidx.compose.animation.core.FastOutSlowInEasing
        ),
        label = "dotWidthAnimation"
    )

    val progress = remember { Animatable(0f) }

    LaunchedEffect(isActive) {
        if (isActive) {
            progress.snapTo(0f)
            progress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 3000, easing = LinearEasing)
            )
        } else {
            progress.snapTo(0f)
        }
    }

    // Gray background of the dot or pill
    Box(
        modifier = Modifier
            .height(6.dp)
            .width(animatedWidth)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.3f))
    ) {
        if (isActive && progress.value > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress.value)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
    }
}

@Composable
private fun TransactionsSection(bottomPadding: androidx.compose.ui.unit.Dp = 0.dp) {
    androidx.compose.foundation.lazy.LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = 24.dp,
            bottom = bottomPadding
        )
    ) {
        item {
            Text(
                text = "TRANSACTIONS",
                color = Color(0xFF243355),
                fontSize = 16.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "October 24, 2023",
                color = Color(0xFF1A1A1A),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_google),
                title = "Google",
                subtitle = "Google Workspace Subscription",
                amount = "- $12.00"
            )
        }
        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_youtube),
                title = "YouTube",
                subtitle = "YouTube Premium Family",
                amount = "- $11.99"
            )
        }
        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_facebook),
                title = "Facebook",
                subtitle = "Ads Campaign Billing",
                amount = "- $50.00"
            )
        }
        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_visa),
                title = "Visa",
                subtitle = "Credit Card Payment",
                amount = "- $150.00"
            )
        }
        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_claro),
                title = "Claro",
                subtitle = "Monthly Internet Bill",
                amount = "- $35.00"
            )
        }
    }
}

@Composable
private fun TransactionItem(
    painter: androidx.compose.ui.graphics.painter.Painter,
    title: String,
    subtitle: String,
    amount: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. THE CONTAINER
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            // 2. THE LOGO
            Image(
                painter = painter,
                contentDescription = title,
                modifier = Modifier.fillMaxSize(0.65f),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Center texts
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color(0xFF1A1A1A),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                color = Color.Gray,
                fontSize = 13.sp,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
        }

        // Amount on the right
        Text(
            text = amount,
            color = Color(0xFF1A1A1A),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}