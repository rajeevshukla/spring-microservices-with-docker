# RabbitMQ Clustering with Consul in docker swarm mode

### Useful Links 

+ [Rabbitmq Clustering fundamental](http://www.rabbitmq.com/clustering.html)<br> 
+ [Cluster formation and peer discovery](https://www.rabbitmq.com/cluster-formation.html)
+ [Peer discovery using consul](https://www.rabbitmq.com/cluster-formation.html#peer-discovery-consul)
+ [Rabbitmq Configuration](http://www.rabbitmq.com/configure.html)
+ [Rabbitmq environment variables](https://www.rabbitmq.com/configure.html#define-environment-variables)
+ [Consul Configuration](https://www.diycode.cc/projects/rabbitmq/rabbitmq-autocluster#consul-configuration)


Rabbitmq service discovery consul plugin is shipped with version rabbitmq 3.7 so no need to install it separately. 
For older versions you need to use [rabbitmq-autclluster](https://github.com/rabbitmq/rabbitmq-autocluster)

All the available plugins can be find under `usr/lib/rabbitmq/lib/rabbitmq_server-${RABBITMQ_VERSION}/plugins`path inside the docker container. 

You can get inside docker running container by using command:
`docker exec -it <containerId> /bin/bash`

To enable consul discovery use: `rabbitmq-plugins enable --offline rabbitmq_peer_discovery_consul` 


So in order to run rabbitmq cluster first you need to have consul server which acts as a service discovery  use below command to spawn up consul in docker environment : <br>
`docker service create \
  --name consul \
  --network testnet \
  -p 8500:8500 \
  -e 'CONSUL_BIND_INTERFACE=eth0' \
  -e 'CONSUL_LOCAL_CONFIG={"skip_leave_on_interrupt": true}' \
  consul agent \
  -server -ui -client=0.0.0.0 \
  -bootstrap-expect=1 \
  -retry-join=consul`

Then run below command to start rabbitmq server :<br> 
`docker service create \
  --name rabbit \
  --network testnet \
  -p 5672:5672 \
  -p 15672:15672 \
  -e "AUTOCLUSTER_TYPE=consul" \
  -e "CONSUL_HOST=consul" \
  -e "CONSUL_PORT=8500" \
  -e "CONSUL_SVC=rabbitmq" \
  -e "CONSUL_SVC_ADDR_AUTO=true" \
  -e "AUTOCLUSTER_CLEANUP=true" \
  -e "CLEANUP_WARN_ONLY=false" \
  -e "RABBITMQ_ERLANG_COOKIE=secrect" \
  rajeevshukla/rabbitmq`
  
  
  Now if you want to spawn up few more rabbit mq containers run below command <br>
  `docker service update rabbit --replicas 3`
  
  and the login http:host-ip:15672  using guest/guest. 
  
### Here the [tutorial link](https://developervisits.wordpress.com/2018/08/28/creating-dynamic-rabbitmq-cluster-with-consul-discovery-in-docker/)  in case you want to learn step by step. 
