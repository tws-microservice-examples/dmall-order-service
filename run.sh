#!/bin/sh

PROFILE=${ACTIVE_PROFILE:=default}
java -jar /work/app.jar --spring.profiles.active=$PROFILE 