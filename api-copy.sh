#!/bin/sh

target=./kotlin-retrofit

rm -rf ./tinkoff-api/src
rm -rf ./tinkoff-api/docs
cp -a $target/src ./tinkoff-api
cp -a $target/docs ./tinkoff-api

rm -rf $target