<div align="center">
  <img src="docs/auranova_logo.png" alt="AuraNova Logo" width="120"/>

  <h1>NeoBank UI KMP | Enterprise-Grade Fintech Architecture</h1>
  <h3>AuraNova Showcase</h3>

  <p><strong>A modern financial client interface exploring declarative UI, adaptive gestures, and smooth cross-platform rendering.</strong></p>

[![Kotlin](https://img.shields.io/badge/Kotlin-2.x-blue.svg?style=for-the-badge&logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose-Multiplatform-purple.svg?style=for-the-badge&logo=android)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![iOS Compatible](https://img.shields.io/badge/iOS-18.2%2B-black.svg?style=for-the-badge&logo=apple)]()
[![CI/CD](https://img.shields.io/badge/Build-Passing-brightgreen.svg?style=for-the-badge&logo=githubactions)]()

<p align="center">
  <a href="https://github.com/JastinBolanos/NeoBankUI-KMP/releases/download/v1.2.0/NeoBankUI.apk">
    <img src="https://img.shields.io/badge/Descargar-APK%20Android-green?style=for-the-badge&logo=android&logoColor=white" alt="Descargar APK">
  </a>
</p>
</div>

---

## The Vision

An exploratory financial client interface built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform**. The project demonstrates how modern declarative UI principles and clean component hierarchies can deliver a responsive native Android experience while maintaining a shared visual architecture ready to extend to iOS.

**AuraNova** focuses on client-side craftsmanship, pairing thoughtful interaction design (such as a simulated biometric authentication flow) with fluid animations to showcase that rich visual styling can coexist with smooth, consistent rendering.

## Tech Stack & Technical Foundations

The project is structured for scalability, prioritizing separation of concerns, testability, and efficient cross-platform rendering.

* **Core & UI Framework:** Kotlin Multiplatform and Compose Multiplatform, sharing presentation logic and design system components across targets.
* **Architecture:** Clean Architecture (*Feature-driven*) with structured state management (*State Hoisting*) across client screens.
* **UI/UX Design:** Dark mode theme incorporating layered translucent surfaces, soft shadows, and clean typographic hierarchy.
* **Display Optimization:** Asset preparation and layout measurement structured for smooth native frame delivery on Android, with configuration readiness for **120Hz ProMotion on iOS** (`CADisableMinimumFrameDurationOnPhone`).
* **Continuous Carousel:** Responsive card carousel implemented with `HorizontalPager`, supporting looped paging and smooth page snapping.
* **Continuous Integration:** GitHub Actions workflow running on macOS virtual environments to validate Kotlin modules and verify iOS schema compilation with `xcodebuild`.
* **Cross-Platform Navigation:** Back-stack handling implemented via Kotlin's `expect/actual` pattern (`KmpBackHandler`) to manage Android system back events consistently alongside navigation gestures.
* **Privacy Controls:** Dynamic state-driven privacy toggles to obscure sensitive financial details (balance amounts, card numbers) with automatic masking on scroll.
* **Gesture Interactions:** Smooth gesture handling (`detectHorizontalDragGestures`) for dismissing side drawers, paired with coordinated entrance animations (fade-in and slide-up) for stable transitions.

---
### <img src="https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExcHp0bDAxNXk1bG56OHp6MHU5NWp3aG95Zm9ndzNjNmh2amxpNTZmNiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/F0VCptrJteVWDeLBHD/giphy.gif" width="70" align="absmiddle" /> Live Demo: NeoBank KMP in Action
> **Interface Walkthrough:** observe the motion design, layered surface components, carousel navigation, and visual consistency across the client flow. This video demonstrates the user experience in NeoBank KMP, highlighting declarative Compose animations and multiplatform layout composition.

https://github.com/user-attachments/assets/f5365b66-90d8-4dde-bfa4-d68650a14556

---

## Case Study: UI/UX Design & Multiplatform Engineering

AuraNova serves as an exploration of client-side engineering using **Kotlin Multiplatform (KMP) and Compose Multiplatform**. The design focuses on responsive touch interactions, structured state management, and declarative layout patterns tailored for modern mobile banking experiences.

The interface pairs subtle visual effects—such as ambient pulse indicators and staggered content entry animations—with practical UX patterns like card masking and responsive navigation drawers. Translucent surfaces and layered cards are implemented directly using native Compose canvas and layout modifiers while keeping components modular and accessible.

> **Technical Note:** All screens shown below are rendered with 100% native UI code (Compose Multiplatform), without embedded WebViews or hybrid wrappers. For optimal animation fidelity and gesture responsiveness, running the project on a physical device or hardware-accelerated emulator is recommended.

---

### 1. Biometric Access & Contextual Navigation
The user flow opens with a simulated biometric authentication screen that pairs subtle depth effects with clean typography to introduce the account view. Navigation transitions into an interactive side menu with gradient styling, dismissible via native drag gestures (`detectHorizontalDragGestures`) to return smoothly to the main dashboard.

<p align="center">
  <img src="docs/01_biometric_login.png" width="280" alt="Pantalla de inicio biométrico"/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="docs/02_profile_menu.png" width="280" alt="Menú Lateral de Navegación"/>
</p>

---

### 2. Main Dashboard & Infinite Promotional Carousel
The home dashboard centers on account summaries and a promotional banner carousel built with `HorizontalPager`. The carousel supports cyclical scrolling across visual offer cards, rendering subtle shadows, light gradients, and translucent overlays directly within declarative Compose components.

<p align="center">
  <img src="docs/03_home_titanium_banner.png" width="250" alt="Banner Titanium"/>
  &nbsp;&nbsp;
  <img src="docs/04_home_credit_banner.png" width="250" alt="Banner Crédito"/>
  &nbsp;&nbsp;
  <img src="docs/05_home_invest_banner.png" width="250" alt="Banner Inversiones"/>
</p>

---

### 3. Granular Privacy, History & Smart Transfers
The interface incorporates interactive visibility toggles that obscure card numbers and account balances on demand. Recent transactions are displayed in structured, semi-transparent card containers. The transfer screen features a custom numeric keypad built in Compose, paired with an action button that activates dynamically when a valid amount is entered.

<p align="center">
  <img src="docs/06_cards_privacy.png" width="250" alt="Detalle de Tarjetas y Privacidad"/>
  &nbsp;&nbsp;
  <img src="docs/07_transaction_history.png" width="250" alt="Historial de Transacciones"/>
  &nbsp;&nbsp;
  <img src="docs/08_send_money_transfer.png" width="250" alt="Transferencias Inteligentes"/>
</p>

---

### 4. Premium Offers & High-End Personalization
Supplementary views include tier benefit cards showcasing dark palette variations with accent colors. The profile settings screen provides a clean account management layout with avatar cropping, organized preference rows, and balanced padding to maintain clear visual hierarchy.

<p align="center">
  <img src="docs/09_home_metal_banner.png" width="250" alt="Banner Metal"/>
  &nbsp;&nbsp;
  <img src="docs/10_home_travel_banner.png" width="250" alt="Banner Viajes"/>
  &nbsp;&nbsp;
  <img src="docs/11_profile_settings.png" width="250" alt="Configuración de Perfil"/>
</p>

---

## How to run the project

### Prerequisites
* **Android:** Android Studio Ladybug (or higher) with the Kotlin Multiplatform plugin.
* **iOS:** Mac with Xcode 16+ installed.

### Instructions
1. Clone this repository:
   ```bash
   git clone https://github.com/JastinBolanos/NeoBankUI-KMP.git
   cd NeoBankUI-KMP
   ```

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
