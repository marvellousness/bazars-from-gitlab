pipeline {
  agent any

  environment {
    APP_NAME = 'Bazar Books'
  }

  options {
    // Stop the build early in case of compile or test failures
    skipStagesAfterUnstable()
  }

  stages {

    // Compile
    stage('Compile') {
      steps {
        // Compile the app and its dependencies
        sh './gradlew clean assembleDebug assembleRelease'
      }
    }
  }
}