FROM openjdk:20-jdk-slim as build
WORKDIR /workspace/fruitSalad

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
# RUN -e MYSQL_ROOT_PASSWORD=1234 -p 3306:3306 -d mysql:latest

FROM openjdk:20-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/fruitSalad/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /fruitSalad/lib
COPY --from=build ${DEPENDENCY}/META-INF /fruitSalad/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /fruitSalad
ENTRYPOINT ["java","-cp","fruitSalad:fruitSalad/lib/*","com.fruitSalad_backend.Backend.BackendApplication"]