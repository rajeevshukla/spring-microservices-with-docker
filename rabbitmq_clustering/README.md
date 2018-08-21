# RabbitMQ Clustering with Consul in docker swarm mode

### Useful Links 

+ [Rabbitmq Clustering fundamental](http://www.rabbitmq.com/clustering.html)<br> 
+ [Cluster formation and peer discovery](https://www.rabbitmq.com/cluster-formation.html)
+ [Peer discovery using consul](https://www.rabbitmq.com/cluster-formation.html#peer-discovery-consul)
+ [Rabbitmq Configuration](http://www.rabbitmq.com/configure.html)



Rabbitmq service discovery consul plugin is shipped with version rabbitmq 3.7 so no need to install it separately. 
For older versions you need to use [rabbitmq-autclluster](https://github.com/rabbitmq/rabbitmq-autocluster)

All the available plugins can be find under `usr/lib/rabbitmq/lib/rabbitmq_server-${RABBITMQ_VERSION}/plugins`path inside the docker container. 

You can get inside docker running container by using below commands 
`docker exec -it <containerId> /bin/bash`

