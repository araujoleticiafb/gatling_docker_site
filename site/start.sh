#!/bin/bash
echo "To check Gatling history reports click here -> http://localhost:8080"
while [ true ] ; do
read -t 3 -n 1
if [ $? = 0 ] ; then
exit ;
fi
done