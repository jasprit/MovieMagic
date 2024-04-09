# MovieMagic-Compose
Welcome to MovieMagic-Compose, an Android app built using Jetpack Compose in Kotlin. This app allows users to discover new movies and search for their favorite titles.


![Screenshot 1](https://github.com/jasprit/MovieMagic/blob/main/screenshot-1.png)

## Features

- Fetches new movies from a remote API.
- Enables users to search for movies based on title or year.
- Displays detailed information about each movie.
- Provides a clean and intuitive user interface.

## Architecture

MovieMagic-Compose follows a modern architecture pattern, including:
- **MVVM**: Model-View-ViewModel architecture for separation of concerns.
- **Clean Architecture**: Clear separation of layers like domain, data, and presentation.
- **Kotlin Coroutines**: Asynchronous programming for handling network requests and background tasks.
- **Dependency Injection**: Utilizes Koin for managing dependencies and improving testability.

## Dependencies

Major dependencies used in this project:
- Retrofit: For making network requests.
- Coil: For loading images from URLs.
- ViewModel & LiveData: For implementing MVVM architecture.
- Koin: For Dependency Injection.

## Getting Started

To run MovieMagic-Compose locally, follow these steps:
1. Clone the repository to your local machine.
2. Obtain an API key from [OMDb API](http://www.omdbapi.com/apikey.aspx).
3. Replace `"YOUR_API_KEY"` with your actual API key in the project.
4. Run the project on an emulator or device using Android Studio.

## Usage

Here are some examples of how to use MovieMagic-Compose:
```kotlin
// Fetch movies from the API
viewModel.getMovies()

// Search for movies
viewModel.searchMovies("Harry Potter")
