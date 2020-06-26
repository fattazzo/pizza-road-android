# remove all models and api
rm -rf ../app/src/main/java/it/pizzaroad/openapi/models/*
rm -rf ../app/src/main/java/it/pizzaroad/openapi/api/*

# generate all new classes
rm -rf /tmp/openapi/pizzaroad
java -jar swagger-codegen-cli.jar generate -l java -i api.json -c config.json -o /tmp/openapi/pizzaroad --type-mappings Double=java.math.BigDecimal

# copy all new models and api
cp /tmp/openapi/pizzaroad/src/main/java/it/pizzaroad/openapi/models/* ../app/src/main/java/it/pizzaroad/openapi/models
cp /tmp/openapi/pizzaroad/src/main/java/it/pizzaroad/openapi/api/* ../app/src/main/java/it/pizzaroad/openapi/api
cp /tmp/openapi/pizzaroad/src/main/java/it/pizzaroad/openapi/CollectionFormats.java ../app/src/main/java/it/pizzaroad/openapi
cp /tmp/openapi/pizzaroad/src/main/java/it/pizzaroad/openapi/StringUtil.java ../app/src/main/java/it/pizzaroad/openapi