# Line Tic Tac Toe

## Description
- Algorithm: Minimax
- Database diagram: [here](https://dbdiagram.io/d/Tic-Tac-Toe-666bece5a179551be6df6ac2)
- Flowchart: [here](https://drive.google.com/file/d/1J7C9oeqRAfGU467FdnqvsaW7QwCwRGNc/view?usp=sharing)

## Requirements
- Docker [install](https://docs.docker.com/get-docker/)

## Setup

1. You need to create line messaging API account. You can see how to create
   it [here](https://developers.line.biz/en/docs/messaging-api/getting-started/).
2. Navigate to `src/main/resources`. Make a copy of `application.properties.example` and rename it
   to `application.properties`.
3. Fill the `line.bot.channel-token` and `line.bot.channel-secret` with your own channel token and channel secret.
4. You need to add your server IP to the Line Messaging API's webhook URL. You can see how to do
   it [here](https://developers.line.biz/en/docs/messaging-api/building-bot/#set-webhook-url).
    1. The webhook URL should be `http://<your-server-ip>:8080/callback`.
    2. You can use [ngrok](https://ngrok.com/) to expose your local server to the internet.

## Settings (application.properties)

- `game.max.board.size`: The maximum size of the board. The default value is 3.
- `game.max.depth`: The maximum depth of the minimax algorithm. The default value is 3.

## How to run

1. Run the application by executing
    ```shell
    docker-compose up
    ```
