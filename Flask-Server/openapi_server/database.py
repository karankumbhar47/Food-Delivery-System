import os
import sqlite3

sqlConnection = None
sqlCursor = None


def init():
    global sqlConnection
    global sqlCursor
    sqlDatabaseFile = os.getenv("FOOD_DELIVERY_DB")
    if sqlDatabaseFile is None:
        print("Env variable `FOOD_DELIVERY_DB` is \
              not set using to /tmp/fds.db")
        sqlDatabaseFile = "/tmp/fds.db"
    sqlConnection = sqlite3.connect(sqlDatabaseFile)
    sqlCursor = sqlConnection.cursor()
    # Create tables if not already present
    sqlCursor.execute('''
        CREATE TABLE IF NOT EXISTS Consumer (
            user_id         CHAR(40) PRIMARY KEY,
            username        VARCHAR(50) UNIQUE NOT NULL,
            password        VARCHAR(255) NOT NULL,
            name            VARCHAR(255),
            phone_number    VARCHAR(20),
            profile_picture CHAR(40),
            email_id        VARCHAR(255),
            gender          VARCHAR(10),
            dob             DATE
        )
    ''')
    sqlCursor.execute('''
        CREATE TABLE IF NOT EXISTS Catalog (
            item_id             CHAR(40) PRIMARY KEY,
            item_name           VARCHAR(100),
            thumbnail_picture   BLOB,
            is_addon            INT,
            tags                VARCHAR(1024),
            price               DECIMAL(10,2),
            vendor              INT,
            max_quantity        INT,
            is_available        INT,
            current_rating      DECIMAL(1,1),
            generic_name        VARCHAR(100),
            FOREIGN KEY(vendor) REFERENCES Vendor(user_id)
        )
    ''')
    # Add login time and logout time to session table
    sqlCursor.execute('''
        CREATE TABLE IF NOT EXISTS Session (
            session_id CHAR(40) PRIMARY KEY,
            user_id    CHAR(40) REFERENCES Consumer(user_id),
            valid      INTEGER
        )
    ''')
    sqlCursor.execute('''
        CREATE TABLE IF NOT EXISTS Vendor (
            user_id             CHAR(40) PRIMARY KEY,
            user_name           VARCHAR(100),
            profile_picture     BLOB,
            is_available        INT,
            password            VARCHAR(255),
            location            VARCHAR(255),
            active_houirs       VARCHAR(255),
            current_status      VARCHAR(10),
            pure_veg            INT
        )
    ''')
    sqlConnection.commit()


def check_exists(value: str, field: str, table: str):
    global sqlCursor
   
    sqlCursor.execute(f"SELECT COUNT(*) FROM {table} where {field} = '{value}'")
    if sqlCursor.fetchone()[0] == 0:
        return False
    return True


def open():
    global sqlConnection
    global sqlCursor
    sqlDatabaseFile = os.getenv("FOOD_DELIVERY_DB")
    if sqlDatabaseFile is None:
        sqlDatabaseFile = "/tmp/fds.db"
    sqlConnection = sqlite3.connect(sqlDatabaseFile)
    sqlCursor = sqlConnection.cursor()


def close():
    global sqlConnection
    global sqlCursor
    sqlCursor.close()
    sqlConnection.close()