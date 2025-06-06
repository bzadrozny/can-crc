#!/bin/bash

# Check if java is installed and the version is at least 21
if ! command -v java &> /dev/null; then
    echo "Java is not installed. Please install Java 21 or higher."
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
if [[ "${JAVA_VERSION%%.*}" -lt 21 ]]; then
    echo "Java version is $JAVA_VERSION. Please install Java 21 or higher."
    exit 1
fi

# Warmup JVM
java -jar ./build/libs/crc-calculator.jar warmup

###########################
##### Pass the params #####
###########################

# Fetch from sh arguments or set defaults
if [ $# -gt 0 ]; then
  ITERATIONS=$1
else
  ITERATIONS=1000000
fi

if [ $# -gt 1 ]; then
  MESSAGE=$2
else
  MESSAGE="01 10 00 11 00 03 06 1A C4 BA D0"
fi

# Run the CRC calculation with the specified parameters
java \
  -XX:+TieredCompilation \
  -XX:TieredStopAtLevel=1 \
  -XX:+UseSerialGC \
  -Xms8m -Xmx8m \
  -noverify \
  -XX:+AlwaysPreTouch \
  -XX:+DisableExplicitGC \
  -XX:+PerfDisableSharedMem \
  -Xbatch \
  -jar ./build/libs/crc-calculator.jar \
  crc -i "$ITERATIONS" "$MESSAGE"
