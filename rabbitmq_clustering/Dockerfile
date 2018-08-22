FROM rabbitmq:management
COPY rabbitmq.conf /etc/rabbitmq/rabbitmq.conf
#ENV RABBITMQ_CONFIG_FILE=/usr/source/rabbitmq.conf
RUN rabbitmq-plugins enable --offline rabbitmq_peer_discovery_consul