CREATE TABLE photo
(
    photoId     BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    content     TEXT         NOT NULL,
    nickname    VARCHAR(50)  NOT NULL,
    newfilename VARCHAR(255) NOT NULL,
    filename    VARCHAR(255) NOT NULL,
    createdat   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

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