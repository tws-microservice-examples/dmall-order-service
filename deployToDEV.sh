#!/usr/bin/env bash

rancher-compose --url $DEV_RANCHER_SERVER --access-key $KEY --secret-key $SECRET rm
rancher-compose --url $DEV_RANCHER_SERVER --access-key $KEY --secret-key $SECRET up -d