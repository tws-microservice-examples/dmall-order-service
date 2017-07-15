FROM java:8
VOLUME /tmp

ADD build/libs/order-service-*.jar /work/app.jar
ADD run.sh /

ENTRYPOINT ["/run.sh"]
