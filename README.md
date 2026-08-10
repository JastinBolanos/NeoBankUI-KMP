<div align="center">
  <img src="docs/auranova_logo.png" alt="AuraNova Logo" width="120"/>

  <h1>NeoBank UI KMP | Enterprise-Grade Fintech Architecture</h1>
  <h3>AuraNova Showcase</h3>

  <p><strong>Redefining the financial experience: high-end design and flawless performance.</strong></p>

[![Kotlin](https://img.shields.io/badge/Kotlin-2.x-blue.svg?style=for-the-badge&logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose-Multiplatform-purple.svg?style=for-the-badge&logo=android)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![iOS Compatible](https://img.shields.io/badge/iOS-18.2%2B-black.svg?style=for-the-badge&logo=apple)]()
[![CI/CD](https://img.shields.io/badge/Build-Passing-brightgreen.svg?style=for-the-badge&logo=githubactions)]()

<p align="center">
  <a href="https://github.com/JastinBolanos/NeoBankUI-KMP/releases/download/v1.1.0/NeoBankUI.apk">
    <img src="https://img.shields.io/badge/Descargar-APK%20Android-green?style=for-the-badge&logo=android&logoColor=white" alt="Descargar APK">
  </a>
</p>
</div>

---

## The Vision

An ultra-premium financial interface prototype built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform**. Designed to deliver a high-end native experience on both Android and iOS from a single codebase.

**AuraNova** goes beyond traditional UI. It establishes a competitive **"visual moat"** through immersive aesthetics and a frictionless user flow (such as the biometric *daily portal*), demonstrating that visual complexity need not come at the expense of performance fluidity.

## Tech Stack & Technical Excellence

The project is structured for scalability, prioritizing separation of concerns, testability, and efficient cross-platform rendering.

* **Core & UI Framework:** Kotlin Multiplatform + Compose Multiplatform, sharing 100% of the visual logic.
* **Architecture:** Clean Architecture (*Feature-driven*) with rigorous state management (*State Hoisting*).
* **UI/UX Design:** *Dark Mode* interface featuring complex *Glassmorphism* elements, layered components, and custom typography.
* **Performance:** Extreme asset optimization (smart rasterization) to ensure 60fps+ transitions, with explicit support for **120Hz ProMotion on iOS** (`CADisableMinimumFrameDurationOnPhone`).
* **Infinite Carousel:** Advanced technical implementation using `HorizontalPager`, managing continuous, seamless scrolling state.
* **Automated CI/CD:** GitHub Actions workflow for continuous validation and remote compilation of the iOS scheme via `xcodebuild` on macOS virtual machines.
* **Native Multiplatform Navigation:** Custom intelligent back-stack management utilizing the `expect/actual` pattern to flawlessly intercept Android hardware back presses (`KmpBackHandler`) without breaking iOS gesture flows.
* **Granular Security UX:** Dynamic, state-driven privacy masking for sensitive financial data (Total Balance, CCV, Card Numbers) featuring auto-hide triggers upon user scrolling.
* **Smart Interactions:** Native-feeling gesture detection (`detectHorizontalDragGestures`) for swipe-to-dismiss side menus, coupled with staggered entrance animations (Fade-in + Slide-up) to eliminate UI jarring.

---
### <img src="https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExcHp0bDAxNXk1bG56OHp6MHU5NWp3aG95Zm9ndzNjNmh2amxpNTZmNiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/F0VCptrJteVWDeLBHD/giphy.gif" width="70" align="absmiddle" /> Live Demo: NeoBank KMP in Action
> ** Observe every detail:** appreciate the fluidity of the native animations, the *glassmorphism* effects, the infinite carousel, and the visual consistency across screens. This video showcases the complete experience of NeoBank KMP, a high-end financial prototype developed entirely using Kotlin Multiplatform and Compose Multiplatform.

https://github.com/user-attachments/assets/a09c2770-8fb7-442e-836d-fa7cbc82ac0f

---

## Case Study: UI/UX Design & Multiplatform Engineering

AuraNova is not just a visual showcase; it is a masterclass in frontend engineering using **Kotlin Multiplatform (KMP) and Compose**. What makes this frontend truly fascinating is its obsession with native fluidity, deep UX psychology, and architectural robustness.

Every interaction has been meticulously crafted: from the **"breathing" pulse animations** on the home screen and **staggered slide-up transitions**, to a fully custom **Intelligent Back-Stack Navigation** that perfectly intercepts hardware buttons across platforms. We've pushed the boundaries of *Glassmorphism* (real-time blurring) while maintaining strict accessibility standards and granular data privacy (dynamic UI masking).

> **Technical Note:** All views rendered below are 100% native code (Compose Multiplatform), without the use of WebViews or hybrid frameworks. The fluidity of the native animations and the seamless multiplatform back-navigation are best experienced when compiling the project on a physical device.

---

### 1. Biometric Access & Contextual Navigation
The user journey begins with a friction-free authentication screen utilizing Z-index depth blurring. Once inside, global navigation is handled by a custom `KmpBackHandler` stack and an interactive side menu that can be dismissed with fluid, native swipe-to-right gestures, revealing the blurred dashboard behind it.

<p align="center">
  <img src="docs/screen_biometric.png" width="300" alt="Pantalla de inicio biométrico"/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="docs/screen_menu_profile.png" width="300" alt="Menú Perfil Deslizable"/>
</p>

---

### 2. Main Dashboard & Infinite Promotional Carousel
The core of the application features an advanced `HorizontalPager`, enabling cyclical auto-scrolling navigation between various financial offers. Each banner utilizes layer rendering (shadows and blurs) to achieve metallic, glass, and titanium textures set against an adaptive gradient background.

<p align="center">
  <img src="docs/screen_home_banner1.png" width="250" alt="Banner Mundo"/>
  &nbsp;&nbsp;
  <img src="docs/screen_home_banner2.png" width="250" alt="Banner Crédito"/>
  &nbsp;&nbsp;
  <img src="docs/screen_home_banner3.png" width="250" alt="Banner Inversiones"/>
</p>

---

### 3. Granular Privacy, History & Smart Transfers
Financial data readability and security are paramount. The UI features dynamic "eye" toggles to mask sensitive data, which auto-hide upon swiping. Transfers boast an interactive *Glassmorphism* recipient pill with rotating arrows, and a "Smart Send Button" that only illuminates when valid input is detected via the custom Compose Numpad.

<p align="center">
  <img src="docs/screen_cards.png" width="250" alt="Detalle de Tarjetas y Privacidad"/>
  &nbsp;&nbsp;
  <img src="docs/screen_history.png" width="250" alt="Historial de Transacciones"/>
  &nbsp;&nbsp;
  <img src="docs/screen_send_money.png" width="250" alt="Transferencias Inteligentes"/>
</p>

---

### 4. Premium Offers & Profile Personalization
The experience is rounded out by highly personalized spaces. The profile section features perfect circular cropping (`ContentScale.Crop`) for real user avatars, backed by an infinite energy pulse animation. The premium banners continue the immersive aesthetic with deep dark themes and glowing accents.

<p align="center">
  <img src="docs/screen_home_banner4.png" width="250" alt="Banner Metal"/>
  &nbsp;&nbsp;
  <img src="docs/screen_home_banner5.png" width="250" alt="Banner Tecnología"/>
  &nbsp;&nbsp;
  <img src="docs/screen_profile.png" width="250" alt="Configuración de Perfil"/>
</p>

---

## How to run the project

### Prerequisites
* **Android:** Android Studio Ladybug (or higher) with the Kotlin Multiplatform plugin.
* **iOS:** Mac with Xcode 16+ installed.

### Instructions
1. Clone this repository:
   ```bash
   git clone [https://github.com/JastinBolanos/NeoBankUI-KMP.git](https://github.com/JastinBolanos/NeoBankUI-KMP.git)
   cd NeoBankUI-KMP

2. **For Android:** Open the project in Android Studio, select the `composeApp` configuration, and press *Run*.
3. **For iOS:**
   * Open the `iosApp` folder in Xcode.
   * Wait for *Swift* and *Assets* indexing to complete.
   * Select a simulator (e.g., iPhone 16/17) and press `Cmd + R`. Xcode will automatically delegate the compilation of the Kotlin framework to Gradle.

---

## License and Intellectual Property

This repository is protected under a **Proprietary Restricted-Use License**.

* The source code is provided strictly for purposes of **study, learning, and technical evaluation**.
* Plagiarism of the visual structure (box arrangement, programmatic gradients, color palettes, and layouts)—as well as unauthorized commercial use or partial republication on third-party platforms—is **strictly prohibited**.
* Please read the [LICENSE](./LICENSE) file for the full legal terms and commercial conditions.
* For information regarding the attribution of third-party graphic assets (3D renders legitimately obtained from the Figma community), please consult the [ASSETS_LICENSE](./ASSETS_LICENSE.md) statement.
