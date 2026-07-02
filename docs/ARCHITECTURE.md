# Vyl Player Architecture

## Tech Stack
- Kotlin
- Jetpack Compose
- ExoPlayer
- Room Database
- MVVM
- Coroutines + Flow

---

## Folder Structure

android-app/
├── app/
│
├── ui/
│   ├── screens/
│   ├── components/
│   ├── theme/
│   └── navigation/
│
├── player/
│   ├── service/
│   ├── controller/
│   └── session/
│
├── data/
│   ├── repository/
│   ├── datastore/
│   └── mediastore/
│
├── database/
│   ├── dao/
│   ├── entity/
│   └── room/
│
├── viewmodel/
│
├── animations/
│
├── visualizer/
│
├── notifications/
│
└── utils/