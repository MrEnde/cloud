package org.cloud.common.requests;

public enum RequestTypes {
    CREATE_FILE(),
    CREATE_DIR(),
    GET_LIST_FILES_AND_DIRS(),
    GET_LIST_FILES(),
    GET_LIST_DIRS(),
    READ_FILE(),
    MOVE(),
    DELETE_FILE(),
    DELETE_DIR(),
    RENAME_FILE(),
    RENAME_DIR(),
    GET_PATH(),
    EXIT(),
    AUTHENTICATION(),
    REGISTRATION()
}
