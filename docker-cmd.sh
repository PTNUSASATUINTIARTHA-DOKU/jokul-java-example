#!/bin/bash

echo "CONTAINER_ENV : ${CONTAINER_ENV}"

echo "rungtw-merchant-configuration with profile : ${CONTAINER_ENV}"

if [ "x$XMSLimit" == "x" ]; then
    # default allocation memory minimum
    XMSLimit="128m"
fi

if [ "x$XMXLimit" == "x" ]; then
    # default allocation memory maximum
    XMXLimit="256m"
fi

if [[ -z "$CLOUD_CONFIG_URI" ]]; then
    java -Xms$XMSLimit -Xmx$XMXLimit -jar -Dspring.profiles.active=$CONTAINER_ENV /apps/devex/devex-demo-java-lib/application.jar
else
    java -Xms$XMSLimit -Xmx$XMXLimit -jar -Dspring.profiles.active=$CONTAINER_ENV -Dspring.cloud.config.uri=$CLOUD_CONFIG_URI /apps/devex/devex-demo-java-lib/application.jar
fi
