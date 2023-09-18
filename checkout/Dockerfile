FROM openjdk:20-jdk-slim as build
WORKDIR /workspace/checkout

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:20-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/fruitSalad/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /checkout/lib
COPY --from=build ${DEPENDENCY}/META-INF /checkout/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /checkout
ENTRYPOINT ["java","-cp","checkout:checkout/lib/*","com.fruitSalad_backend.checkout.CheckoutApplication"]