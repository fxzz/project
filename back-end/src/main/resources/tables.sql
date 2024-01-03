CREATE TABLE photo
(
    photoId     BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    content     TEXT         NOT NULL,
    accountId    BIGINT  NOT NULL,
    newfilename VARCHAR(255) NOT NULL,
    filename    VARCHAR(255) NOT NULL,
    createdat   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

/*
TODO 나중에 username, email, nickname 에 UNIQUE 추가
*/
CREATE TABLE account
(
    accountId BIGINT PRIMARY KEY AUTO_INCREMENT,
    username  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    nickname  VARCHAR(255),
    role      VARCHAR(255),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ChatRoom (
                          ChatRoomId bigint PRIMARY KEY AUTO_INCREMENT,
                          senderAccountId bigint,
                          accountId bigint,
                          roomName VARCHAR(255),
                          title VARCHAR(255)
)