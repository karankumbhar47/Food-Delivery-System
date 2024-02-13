#!/bin/bash

# Function to get the IPv4 address of a network interface
get_interface_ipv4_address() {
    interface_name="$1"
    ipv4_address=$(ifconfig $interface_name | awk '/inet / {print $2}')
    echo "$ipv4_address"
}

# Network interface name
interface_name="wlp3s0"

# Get the IPv4 address of the network interface
ipv4_address=$(get_interface_ipv4_address $interface_name)

if [ -n "$ipv4_address" ]; then
    echo "The IPv4 address of interface $interface_name is: $ipv4_address"
else
    echo "No IPv4 address found for interface $interface_name"
fi

