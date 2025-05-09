FROM openjdk:17-oracle

WORKDIR /app
COPY target/eft_s9_katlheen_rodriguez-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_ISXRW8I4NIX4N3IA /app/oracle_wallet
EXPOSE 9090

CMD ["java","-jar","app.jar"]