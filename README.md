The system is built based on Java Springboot framework, which can handle high concurrency situation like flash sale or breaking news activities.

•	Data querying by using MySQL database and mybatis object-relational mapping technique.

•	Applying redis database handle and filter massive requests to avoid over-sell or database crush.

•	Utilizing rocketMQ allow database to handle the requests coming from redis asynchronously.

•	Applying Snowflake algorithm to create unique order number in a distributed system.
