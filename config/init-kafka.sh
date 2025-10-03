#!/bin/bash

set -e

echo "Creating Kafka topics..."

/opt/kafka/bin/kafka-topics.sh --create \
    --bootstrap-server kafka:9092 \
    --replication-factor 1 \
    --partitions 1 \
    --topic device_topic

/opt/kafka/bin/kafka-topics.sh --create \
    --bootstrap-server kafka:9092 \
    --replication-factor 1 \
    --partitions 1 \
    --topic telementry_topic

echo "Done!"