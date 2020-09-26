#!/bin/sh

target=./kotlin-retrofit
rm -rf $target
mkdir $target
cd $target
openapi-generator generate \
-g kotlin \
--additional-properties=library=jvm-retrofit2,useCoroutines=true \
-i https://tinkoffcreditsystems.github.io/invest-openapi/swagger-ui/swagger.yaml
