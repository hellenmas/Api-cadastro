echo "CREATING SQS QUEUE"
awslocal sqs create-queue --queue-name teste.fifo --attributes "FifoQueue=true"

echo "DYNAMO DB TABLE"
awslocal dynamodb create-table --table-name teste \
--attribute-definitions AttributeName=_id,AttributeType=S \
--key-schema AttributeName=_id,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5