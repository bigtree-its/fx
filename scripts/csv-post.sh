#!/bin/bash
FILES=$1
for f in $FILES/*.csv
do
    echo "Processing $f file..."
    content=$(cat $f)
    echo "$content"
    curl -X POST -H 'Content-Type: text/plain' http://localhost:8080/transactions --data-raw "${content}"
done
