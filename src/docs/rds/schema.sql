DROP TABLE IF EXISTS MST_USER CASCADE;
CREATE TABLE MST_USER(
    USER_KEY            BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    ACTIVATE_STATUS     VARCHAR(64)         NOT NULL,

    DELETED             BOOLEAN             NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
    PRIMARY KEY(USER_KEY)
);

DROP TABLE IF EXISTS MST_USER_ROLE CASCADE;
CREATE TABLE MST_USER_ROLE(
    USER_ROLE_KEY       BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    ROLE_TYPE           VARCHAR(64)         NOT NULL,
    USER_KEY            BIGINT              NOT NULL,

    DELETED             BOOLEAN             NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
    PRIMARY KEY(USER_ROLE_KEY)
);

DROP TABLE IF EXISTS SOCIAL_USER_IDENTI CASCADE;
CREATE TABLE SOCIAL_USER_IDENTI(
    SOCIAL_IDENT_KEY    BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    IDENTIFICATION      VARCHAR(1024)       NOT NULL,
    SOCIAL_TYPE         VARCHAR(64)         NOT NULL,
    USER_KEY            BIGINT              NOT NULL,
    EMAIL               VARCHAR(128),

    DELETED             BOOLEAN             NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
    PRIMARY KEY(SOCIAL_IDENT_KEY)
);

DROP TABLE IF EXISTS MST_USER_CREDENTIAL CASCADE;
CREATE TABLE MST_USER_CREDENTIAL(
    CREDENTIAL_KEY          BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    PASSWORD                VARCHAR(256)        NOT NULL,
    USER_KEY                BIGINT              NOT NULL,

    DELETED                 BOOLEAN             NOT NULL,
    CREATED_BY              BIGINT,
    CREATED_AT              TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY        BIGINT,
    LAST_MODIFIED_AT        TIMESTAMP           NOT NULL,
    PRIMARY KEY(CREDENTIAL_KEY)
);

DROP TABLE IF EXISTS MST_USER_DETAILS CASCADE;
CREATE TABLE MST_USER_DETAILS(
    USER_DETAILS_KEY        BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    NICKNAME                VARCHAR(128)        NOT NULL,
    STR_ID                  UUID                NOT NULL,
    USER_KEY                BIGINT              NOT NULL,
    PROFILE                 VARCHAR(512),

    DELETED                 BOOLEAN             NOT NULL,
    CREATED_BY              BIGINT,
    CREATED_AT              TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY        BIGINT,
    LAST_MODIFIED_AT        TIMESTAMP           NOT NULL,
    PRIMARY KEY(USER_DETAILS_KEY)
);

DROP TABLE IF EXISTS EMAIL_SIGNUP_AUTH CASCADE;
CREATE TABLE EMAIL_SIGNUP_AUTH(
    AUTH_KEY                BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    USER_KEY                BIGINT              NOT NULL,
    UUID                    UUID                NOT NULL,

    DELETED                 BOOLEAN             NOT NULL,
    CREATED_BY              BIGINT,
    CREATED_AT              TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY        BIGINT,
    LAST_MODIFIED_AT        TIMESTAMP           NOT NULL,
    PRIMARY KEY(AUTH_KEY)
);

DROP TABLE IF EXISTS EMAIL_TEMPLATE CASCADE;
CREATE TABLE EMAIL_TEMPLATE(
    TEMPLATE_KEY            BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    TITLE                   VARCHAR(128)        NOT NULL,
    TEMPLATE                TEXT                NOT NULL,
    TEMPLATE_TYPE           VARCHAR(64)         NOT NULL,

    DELETED                 BOOLEAN             NOT NULL,
    CREATED_BY              BIGINT,
    CREATED_AT              TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY        BIGINT,
    LAST_MODIFIED_AT        TIMESTAMP           NOT NULL,
    PRIMARY KEY(TEMPLATE_KEY)
);

/* CA-27 */
DROP TABLE IF EXISTS MST_USER_LOGIN_LOG CASCADE;
CREATE TABLE MST_USER_LOGIN_LOG(
    LOG_KEY                 BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    USER_KEY                BIGINT              NOT NULL,
    STATUS                  VARCHAR(128)        NOT NULL,

    DELETED                 BOOLEAN             NOT NULL,
    CREATED_BY              BIGINT,
    CREATED_AT              TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY        BIGINT,
    LAST_MODIFIED_AT        TIMESTAMP           NOT NULL,
    PRIMARY KEY(LOG_KEY)
);

/* CA-26 */
DROP TABLE IF EXISTS CODEPIED_FILE CASCADE;
CREATE TABLE CODEPIED_FILE(
    FILE_KEY                BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    ORIGINAL_NAME           VARCHAR(512)        NOT NULL,
    FILE_ID                 VARCHAR(1024)       NOT NULL,
    MEDIA_TYPE              VARCHAR(128)        NOT NULL,
    IS_PUBLIC_FILE          BOOLEAN             NOT NULL DEFAULT FALSE,

    DELETED                 BOOLEAN             NOT NULL,
    CREATED_BY              BIGINT,
    CREATED_AT              TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY        BIGINT,
    LAST_MODIFIED_AT        TIMESTAMP           NOT NULL,
    PRIMARY KEY(FILE_KEY)
);

/* CA-20 */
DROP TABLE IF EXISTS BOARD CASCADE;
CREATE TABLE BOARD(
    BOARD_KEY       	 BIGINT              GENERATED BY DEFAULT AS IDENTITY,
    UUID 		         UUID                NOT NULL,
    NAME            	 VARCHAR(64)         NOT NULL UNIQUE,

    DELETED              BOOLEAN             NOT NULL,
    CREATED_BY           BIGINT,
    CREATED_AT           TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY     BIGINT,
    LAST_MODIFIED_AT     TIMESTAMP           NOT NULL,
    PRIMARY KEY(BOARD_KEY)
);

DROP TABLE IF EXISTS THREAD CASCADE;
CREATE TABLE THREAD(
    THREAD_KEY          BIGINT               GENERATED BY DEFAULT AS IDENTITY,
    UUID 		        UUID                 NOT NULL,
    TITLE 		        VARCHAR(128)         NOT NULL,
    CONTENT             VARCHAR(3026)        NOT NULL,
    ANONYMOUS		    BOOLEAN				 NOT NULL,
    BOARD_KEY           BIGINT				 NOT NULL,
    USER_KEY            BIGINT				 NOT NULL,

    DELETED             BOOLEAN              NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP            NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP            NOT NULL,
    PRIMARY KEY(THREAD_KEY)
);

DROP TABLE IF EXISTS THREAD_VIEW CASCADE;
CREATE TABLE THREAD_VIEW(
    THREAD_VIEW_KEY    	BIGINT				GENERATED BY DEFAULT AS IDENTITY,
    THREAD_KEY			BIGINT				NOT NULL,
    USER_KEY			BIGINT				NOT NULL,

    DELETED             BOOLEAN             NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
    PRIMARY KEY(THREAD_VIEW_KEY)
);

DROP TABLE IF EXISTS THREAD_SCORE CASCADE;
CREATE TABLE THREAD_SCORE(
    THREAD_KEY 			BIGINT 				GENERATED BY DEFAULT AS IDENTITY,
    SCORE				BIGINT				NOT NULL,

    DELETED             BOOLEAN             NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
    PRIMARY KEY(THREAD_KEY)
);

DROP TABLE IF EXISTS THREAD_VOTE CASCADE;
CREATE TABLE THREAD_VOTE(
    THREAD_VOTE_KEY     BIGINT				GENERATED BY DEFAULT AS IDENTITY,
    RECOMMEND			BOOLEAN				NOT NULL,
    THREAD_KEY 			BIGINT				NOT NULL,
    USER_KEY			BIGINT				NOT NULL,

    DELETED             BOOLEAN             NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
    PRIMARY KEY(THREAD_VOTE_KEY)
);

DROP TABLE IF EXISTS THREAD_FILE CASCADE;
CREATE TABLE THREAD_FILE(
    THREAD_FILE_KEY    	BIGINT				GENERATED BY DEFAULT AS IDENTITY,
    THREAD_KEY 			BIGINT				NOT NULL,
    CODEFILED_FILE_KEY	BIGINT				NOT NULL,

    DELETED             BOOLEAN             NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
    PRIMARY KEY(THREAD_FILE_KEY)
);

DROP TABLE IF EXISTS THREAD_COMMENT CASCADE;
CREATE TABLE THREAD_COMMENT(
    THREAD_COMMENT_KEY  BIGINT				GENERATED BY DEFAULT AS IDENTITY,
    THREAD_KEY 			BIGINT				NOT NULL,
    PARENT_KEY			BIGINT				NOT NULL,
    USER_KEY			BIGINT				NOT NULL,
    CONTENT             VARCHAR(1024)       NOT NULL,

    DELETED             BOOLEAN             NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
    PRIMARY KEY(THREAD_COMMENT_KEY)
);

DROP TABLE IF EXISTS SUBSCRIBER CASCADE;
CREATE TABLE SUBSCRIBER(
    SUBSCRIBER_KEY  	BIGINT				GENERATED BY DEFAULT AS IDENTITY,
    BOARD_KEY 			BIGINT				NOT NULL,
    USER_KEY			BIGINT				NOT NULL,

    DELETED             BOOLEAN             NOT NULL,
    CREATED_BY          BIGINT,
    CREATED_AT          TIMESTAMP           NOT NULL,
    LAST_MODIFIED_BY    BIGINT,
    LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
    PRIMARY KEY(SUBSCRIBER_KEY)
);

DROP TABLE IF EXISTS BOARD_MANAGER CASCADE;
CREATE TABLE BOARD_MANAGER(
  BOARD_MANAGER_KEY  	BIGINT				GENERATED BY DEFAULT AS IDENTITY,
  BOARD_KEY           BIGINT				NOT NULL,
  USER_KEY			BIGINT				NOT NULL,

  DELETED             BOOLEAN             NOT NULL,
  CREATED_BY          BIGINT,
  CREATED_AT          TIMESTAMP           NOT NULL,
  LAST_MODIFIED_BY    BIGINT,
  LAST_MODIFIED_AT    TIMESTAMP           NOT NULL,
  PRIMARY KEY(BOARD_MANAGER_KEY)
);

