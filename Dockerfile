FROM maven:3.8.4-eclipse-temurin-17 AS build
RUN mkdir /src

COPY . /src

WORKDIR /src

RUN mvn clean install

FROM quay.io/keycloak/keycloak:21.0 as builder

# Configure a database vendor
ENV KC_DB=mysql

WORKDIR /opt/keycloak

COPY --from=build /src/target/*.jar /opt/keycloak/providers/app.jar

# for demonstration purposes only, please make sure to use proper certificates in production instead
RUN keytool -genkeypair -storepass password -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=server" -alias server -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keystore conf/server.keystore

RUN /opt/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:latest
COPY --from=builder /opt/keycloak/ /opt/keycloak/

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]