FROM eclipse-temurin:11.0.18_10-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:11

VOLUME /Desktop/Universidad/Semestre 9/Arquitectura de Software/Proyecto Final/logsProy

ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENV USER_NAME ${POSTGRES_USER}
ENV PASSWORD ${POSTGRES_PASSWORD}
ENV PGURL ${POSTGRES_URL}

ENV SERVER_URI ${SERVER_URI}
ENV DISCOVERY_URI ${DISCOVERY_URL}
ENV PROFILE_PROD ${PROFILE_PROD}

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-cp","app:app/lib/*","com.example.msorders.MsOrdersApplicationKt"]