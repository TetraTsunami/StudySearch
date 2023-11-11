git pull
docker compose down spring-boot
./gradlew :backend:bootBuildImage
docker compose up -d spring-boot