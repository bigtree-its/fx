cd ..
./gradlew clean build -x test
docker build -t fx-app:latest .
docker run --name fx-app --net fx-network -p 8080:8080 --rm fx-app:latest 