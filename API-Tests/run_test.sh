#!/bin/bash

address=$1

python3 ./API-Tests/consumer.py -a $address
python3 ./API-Tests/vendor.py -a $address 
python3 ./API-Tests/catlog.py -a $address 

