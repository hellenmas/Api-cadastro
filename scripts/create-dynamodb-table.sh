#!/usr/bin/env bash

set -e

aws dynamodb create-table \
  --table-name posts \
  --attribute-definitions \
  AttributeName=post_id,AttributeType=S \
  AttributeName=user_id,AttributeType=S \
  AttributeName=created_at,AttributeType=S \
  AttributeName=title,AttributeType=S \
  --key-schema \
  AttributeName=post_id,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=10 \
  --global-secondary-indexes file://scripts/gsi.json \
  --endpoint-url http://localhost:54000 \
  --region ap-northeast-2 || true | cat