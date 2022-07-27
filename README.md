# GithubClosedPRs

A native Android app that displays a list of pull requests that were closed for a Github repository:

​![alt text](https://github.com/tushar40/GithubPRs/blob/master/readme-images/githubclosedprs.png)


This project is built using Kotlin and AndroidX.

# Contents at a Glance
* [Getting Started](#getting-started)
  * [Clone the Repository](#clone-the-repository)
  * [Open and Run Project in Android Studio](#open-and-run-project-in-android-studio)
  * [Generating APK](#generating-apk)
  * [Components](#components)
* [On Device Caching](#on-device-caching)
* [Dependencies](#dependencies)
  * [Libraries Used](#libraries-used)
  * [Licenses](#licenses)
## Getting Started

### Clone the Repository

```
$ git clone https://github.com/tushar40/StackOverFlowQuestions.git
```

### Open and Run Project in Android Studio

- Open the project up in Android Studio.
- Click on the `Run App` (Green play button in top bar)

At this point, you *should* be able to build and run the project in the Android device or emulator.

### Generating APK

From Android Studio:

1. ***Build*** menu
2. ***Build Bundle(s) / APK(s)***
3. ***Build APK***

### Components
- Activities
  - ***PullRequestsActivity*** - The activity which is presented to the user when the app is launched. It shows a list of closed Pull requests of a repository.


- Adaptors
  - ***PullRequestAdapter*** - The adaptor for Questions ListView in Questions Screen.

- API
  - Interfaces & Wrappers for Endpoints.

- Utils
  1. DateTimeManager - Class for managing date and time related changes.
  2. Constants - Contains constant strings used throughout the app.
  3. PullRequestDiffCallback - Gives the callback used to differentiate between old and new items in the ListView.

- ViewModels
  - ***PullRequestViewModel*** - ViewModel for PullRequestsActivity.

## Dependencies

### Libraries Used

  1. [Retrofit](https://square.github.io/retrofit/)
  4. [Glide](https://github.com/bumptech/glide)

### Licenses

- Android native dependencies (Kotlin, Testing etc) - Apache 2.0
- Glide - BSD, part MIT and Apache 2.0
- RetroFit - Apache 2.0
- gson - Apache 2.0
- material-components-android - Apache 2.0
