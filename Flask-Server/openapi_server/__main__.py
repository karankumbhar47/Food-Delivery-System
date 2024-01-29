#!/usr/bin/env python3

import connexion

from openapi_server import encoder
from openapi_server import database
from openapi_server import file_storage


def main():
    app = connexion.App(__name__, specification_dir='./openapi/')
    app.app.json_encoder = encoder.JSONEncoder
    app.add_api('openapi.yaml',
                arguments={'title': 'FDS'},
                pythonic_params=True)
    database.init()
    file_storage.init()
    app.run(port=8080)


if __name__ == '__main__':
    main()
