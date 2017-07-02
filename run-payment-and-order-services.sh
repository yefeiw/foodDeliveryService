cd payment-service
mvn clean install
java -jar target/payment-service-1.0.0.jar &
cd ..
cd order-service
mvn clean install
java -jar target/order-service-1.0.0.jar &
cd ..