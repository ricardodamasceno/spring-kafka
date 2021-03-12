# Kafka and ksqlDB reference

### Set ksql to search for messages from beginning
SET 'auto.offset.reset'='earliest';

###  List All topics

$ list topics;

###  List messages from topic

$ print '<TOPIC_NAME>' from beginning limit 2; -- Single quotes are mandatory
EX: print 'KAFKA_USER_TOPIC'

###  Create stream
$ create stream <stream_name> (name VARCHAR, email VARCHAR, phone VARCHAR, age INTEGER) WITH (KAFKA_TOPIC='<TOPIC-NAME>', VALUE_FORMAT='JSON');
***VALUE_FORMAT='DELIMITED', it means the records will be splitted using ';'.

ex: create stream users_stream (name VARCHAR, email VARCHAR, phone VARCHAR, age INTEGER) WITH (KAFKA_TOPIC='KAFKA_USER_TOPIC', VALUE_FORMAT='JSON');

###  Describe stream
describe <stream-name>;

###  Delete Stream
$ drop stream <stream_name>;

###  Filter values using streams
select name, email from users_stream emit changes;

**for ksql 5.4 or later, you have to specify 'emit changes' at the end of the query. It means it's a push query.

###  Terminate query
terminate <query-name>;

### Create stream from another stream from a script 

create a file <file-name>.ksql

//File Content
SET 'auto.offset.reset'='earliest';

create stream users_stream_extended as
select name, email, phone, age
from users_stream
where name = 'Ricardo Damasceno';

**** OBS: If you are running ksql on Docker, you'll need to add the file inside the container. Otherwise ksql won't be able to find it.
ex:


Access the container in iterative way
docker exec -it ksqldb-cli /bin/bash

Add the script content inside a file
echo "SET 'auto.offset.reset'='earliest'; create stream users_stream_extended as select name, email, phone, age from users_stream where name = 'Alexandre Damasceno'; " >> stream-creation.ksql

After that, ksql will be able to execute the command.

run script 'path/to/file/inside/container';

###  Create table from topic
CREATE TABLE USER_TB (key VARCHAR primary key, name VARCHAR, email VARCHAR, phone VARCHAR, age INTEGER) WITH (KAFKA_TOPIC='KAFKA_USER_TOPIC', VALUE_FORMAT='JSON');

*** the table needs a primary key, and this key will be the key of the topic event




