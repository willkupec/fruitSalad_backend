FROM openjdk:20-jdk-slim as build
WORKDIR /workspace/cart

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:20-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/cart/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /cart/lib
COPY --from=build ${DEPENDENCY}/META-INF /cart/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /cart
ENTRYPOINT ["java","-cp","cart:cart/lib/*","com.fruitSalad_backend.cart.CartApplication"]