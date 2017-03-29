#!/bin/bash

# MongoDB is started with following docker image: https://github.com/tutumcloud/mongodb
echo 'Executing following command: docker run -d -p 20184:27017 -p 21184:28017 --env-file ./env tutum/mongodb'
docker run --network=host -d -p 20184:27017 -p 21184:28017 --env-file ./env tutum/mongodb


sudo /usr/bin/docker run --network=host --restart always --env-file ./env -d --name codelab-auth-mongodb -p=20184:27017 codelab-auth-mongodb