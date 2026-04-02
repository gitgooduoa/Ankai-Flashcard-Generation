# AnkAI Flashcard Generation

AnkAI Flashcard Generation is a backend service that uses AI to transform notes into flashcards.

## Features

- **AI-Powered Flashcard Generation**: Automatically create flashcards (title, question, answer) from simple notes.

## Prerequisites

- **JDK 21+**
- **OpenRouter API Key**: Obtain an API key from [OpenRouter](https://openrouter.ai/).

## Setup & Configuration

1. **Environment Variable**: Set your OpenRouter API key as an environment variable
2. **Run the Server**:
   ```bash
   ./gradlew run
   ```
   The server will start at `http://localhost:8080`.

## API Documentation

**Available At**: `/api/openapi`

## Building

To build or run the project, use one of the following tasks:

| Task                                    | Description                                                          |
|-----------------------------------------|----------------------------------------------------------------------|
| `./gradlew test`                        | Run the tests                                                        |
| `./gradlew build`                       | Build everything                                                     |
| `./gradlew buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `./gradlew buildImage`                  | Build the docker image to use with the fat JAR                       |
| `./gradlew run`                         | Run the server                                                       |
