INSERT INTO HONE_COMMON_USER (LOGIN_ID, USER_NAME, PASSWORD, ACTIVE, CREATE_TIME, UPDATE_TIME)
    VALUES ('admin', 'admin', '$2a$10$MneZlOGcEqyZUr9nBhJC0.uFsh9JKPaTIgY5Wkl6u3Yql4ZRY5gzu', 'ACTIVE', sysdate, sysdate);

INSERT INTO HONE_COMMON_USER_ROLE (LOGIN_ID, ROLE, MODULE)
    VALUES ('admin', 'COMMON_ADMINISTRATOR', 'Common');
INSERT INTO HONE_COMMON_USER_ROLE (LOGIN_ID, ROLE, MODULE)
    VALUES ('admin', 'ONLINE_ADMINISTRATOR', 'Online');
INSERT INTO HONE_COMMON_USER_ROLE (LOGIN_ID, ROLE, MODULE)
    VALUES ('admin', 'BATCH_ADMINISTRATOR', 'Batch');

INSERT INTO HONE_COMMON_SYSTEM (SYSTEM_ID, SYSTEM_NAME, CREATE_TIME, UPDATE_TIME)
VALUES ('HMP', '영업포털 시스템', SYSDATE, SYSDATE);