from typing import Optional, Tuple

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
    else:
        print(f"Using database at {sqlDatabaseFile}")

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
            username            VARCHAR(100) NOT NULL,
            name                VARCHAR(255) NOT NULL,
            profile_picture     CHAR(40),
            is_available        INT,
            password            VARCHAR(255) NOT NULL,
            phone_number        VARCHAR(20) NOT NULL,
            email_id            VARCHAR(255),
            location            VARCHAR(255) NOT NULL,
            active_hours        VARCHAR(255),
            current_status      VARCHAR(10),
            pure_veg            INT
        )
    ''')
    sqlCursor.execute('''
        CREATE TABLE IF NOT EXISTS DeliveryPerson (
            user_id             CHAR(40) PRIMARY KEY,
            user_name           VARCHAR(100),
            phone_number        VARCHAR(20) NOT NULL,
            email_id            VARCHAR(255), 
            profile_picture     BLOB,
            name                VARCHAR(100),
            is_available        INT,
            password            VARCHAR(255),
            vehicle_details     VARCHAR(255),
            picked_up_items     TEXT
        )
    ''')
    sqlCursor.execute('''
        CREATE TABLE IF NOT EXISTS FoodOrder (
            order_id            CHAR(40) PRIMARY KEY, 
            items               TEXT,
            total_price         DECIMAL(10, 2),
            delivery_location   VARCHAR(255),
            pickup_location     VARCHAR(255),
            consumer_id         INT REFERENCES Consumer(user_id),
            vendor_id           INT REFERENCES Vendor(user_id), 
            delivery_person_id  INT REFERENCES DeliveryPerson(user_id), 
            status              VARCHAR(50),
            otp                 INT
        )
    ''')
    sqlConnection.commit()


def check_exists(value: str, field: str, table: str):
    global sqlCursor
    sqlCursor.execute(f"SELECT COUNT(*) FROM {table} where {field} = '{value}'")
    if sqlCursor.fetchone()[0] == 0:
        return False
    return True


def verify_session_id(session_id: str) -> Optional[Tuple[str, str]]:
    sqlCursor.execute(f"SELECT user_id, valid FROM 'Session' WHERE session_id = '{session_id}'")
    data = sqlCursor.fetchone()
    if data is not None:
        user_id = data[0]
        valid = data[1]
        if not valid:
            return None
        if check_exists(user_id, "user_id", "Consumer"):
            return user_id, "Consumer"
        if check_exists(user_id, "user_id", "Vendor"):
            return user_id, "Vendor"
        # Add similar check for delivery person
        return user_id, "Other"
    return None


def open():
    global sqlConnection
    global sqlCursor

    init()

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
