FROM anapsix/alpine-java

RUN mkdir /opt/app

ENV S2W_OPTS "-Dserver.address=0.0.0.0"

COPY container/run_app.sh /opt/app
COPY target/s2w-*-SNAPSHOT.jar /opt/app/s2w.jar

RUN chmod +x /opt/app/run_app.sh

EXPOSE 8081

ENTRYPOINT ["/opt/app/run_app.sh"]

