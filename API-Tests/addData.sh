#!/bin/bash

# run this script from the root directory of project 
# give compulsory the ip address of server 
# example   --> localhost, 192.168.15.12
   

address=$1

python3 ./API-Tests/src/consumer.py -a $address
python3 ./API-Tests/src/vendor.py -a $address 
python3 ./API-Tests/src/catlog.py -a $address 

