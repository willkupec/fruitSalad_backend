FROM openjdk:20-jdk-slim as build
WORKDIR /workspace/payment

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:20-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/payment/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /payment/lib
COPY --from=build ${DEPENDENCY}/META-INF /payment/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /payment
ENTRYPOINT ["java","-cp","payment:payment/lib/*","com.fruitSalad_backend.payment.PaymentApplication"]