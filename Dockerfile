# Cart
FROM openjdk:20-jdk-slim as build
WORKDIR /workspace/cart

COPY /cart/mvnw /cart
COPY /cart/.mvn .mvn
COPY /cart/pom.xml .
COPY /cart/src src

RUN /cart clean install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:20-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/cart/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /cart/lib
COPY --from=build ${DEPENDENCY}/META-INF /cart/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /cart
ENTRYPOINT ["java","-cp","cart:cart/lib/*","com.fruitSalad_backend.cart.CartApplication"]

# Checkout

FROM openjdk:20-jdk-slim as build
WORKDIR /workspace/checkout

COPY /checkout/mvnw /checkout
COPY /checkout/.mvn .mvn
COPY /checkout/pom.xml .
COPY /checkout/src src

RUN /checkout clean install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:20-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/checkout/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /checkout/lib
COPY --from=build ${DEPENDENCY}/META-INF /checkout/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /checkout
ENTRYPOINT ["java","-cp","checkout:checkout/lib/*","com.fruitSalad_backend.checkout.CheckoutApplication"]

# Payment

FROM openjdk:20-jdk-slim as build
WORKDIR /workspace/payment

COPY /payment/mvnw /payment
COPY /payment/.mvn .mvn
COPY /payment/pom.xml .
COPY /payment/src src

RUN /payment clean install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:20-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/payment/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /payment/lib
COPY --from=build ${DEPENDENCY}/META-INF /payment/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /payment
ENTRYPOINT ["java","-cp","payment:payment/lib/*","com.fruitSalad_backend.payment.PaymentApplication"]