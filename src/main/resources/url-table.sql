CREATE TABLE DIGITAL_URL (
SHORT_URL VARCHAR(125) IDENTITY PRIMARY KEY,
LONG_URL VARCHAR(275) NOT NULL,
EXPIRY_DATE DATE,
DESCRIPTION VARCHAR(225),
CATEGORY VARCHAR(70),
TRIBE VARCHAR(70),
APPLICATION VARCHAR(70),
USER_MAIL VARCHAR(300),
BOOKMARK_TITLE VARCHAR(100)
);
