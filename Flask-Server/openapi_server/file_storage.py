from typing import Optional
import os
import openapi_server.utils.basic as basicUtils

STORAGE_LOCATION = "/tmp/store/"


def init():
    STORAGE_LOCATION = os.getenv("FOOD_DELIVERY_STORE")
    if STORAGE_LOCATION is None:
        print("Env variable `FOOD_DELIVERY_STORE` is \
              not set using to /tmp/store")
        STORAGE_LOCATION = "/tmp/store/"


def store_file(data: bytes) -> str:
    filename = new_file_name()
    filepath = os.path.join(STORAGE_LOCATION, filename)
    os.makedirs(STORAGE_LOCATION, exist_ok=True)

    with open(filepath, 'wb') as f:
        f.write(data)
    return filename


def get_file(filename: str) -> Optional[bytes]:
    if not is_file(filename):
        return None
    filename = os.path.join(STORAGE_LOCATION, filename)
    data = None
    with open(filename, 'rb') as f:
        data = f.read()
    return data


def new_file_name() -> str:
    name = ''
    while True:
        name = basicUtils.generate_uid(40)
        if not is_file(name):
            break
    return name


def is_file(filename: str) -> bool:
    return os.path.isfile(os.path.join(STORAGE_LOCATION, filename))
