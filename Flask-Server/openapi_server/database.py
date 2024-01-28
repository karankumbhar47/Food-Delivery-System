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
    sqlConnection.commit()


def check_exists(value: str, field: str, table: str):
    global sqlConnection
    global sqlCursor
    sqlCursor.execute(f"SELECT COUNT(*) FROM {table} where {field} = '{value}'")
    if sqlCursor.fetchone()[0] == 0:
        return False
    return True
