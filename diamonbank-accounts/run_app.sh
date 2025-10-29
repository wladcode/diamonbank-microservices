#!/bin/sh

# Debug support
if [ "$DEBUG_ENABLED" = "true" ]; then
  export JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 $JAVA_OPTS"
fi

#
# Adding java.security.egd
#
export JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"


echo "Starting Java with the arguments $JAVA_OPTS"
java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher