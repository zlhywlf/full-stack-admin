FROM eclipse-temurin:17-jre
ARG JAR_FILE=subscription-1.0.0.jar
COPY ${JAR_FILE} subscription-1.0.0.jar
COPY start.sh start.sh
ENV TZ=Asia/Shanghai
# java -Dfile.encoding=UTF-8 -jar subscription-1.0.0.jar --ds.clientId={} --ds.secret={} --server.port=8081 --logging.file.path=.
ENTRYPOINT ["bash", "start.sh"]