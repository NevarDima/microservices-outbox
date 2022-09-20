#!/bin/bash

cd orders
sh orders-build.sh
cd ..

cd delivery
sh delivery-build.sh
cd ..