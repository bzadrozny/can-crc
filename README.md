# CRC Calculator

A command-line tool for calculating CRC (Cyclic Redundancy Check) values for byte sequences, written in Kotlin.

## Features

- Calculates CRC for a user-provided hex byte sequence (up to 256 bytes)
- Supports benchmarking with configurable iteration count
- Input validation for hex notation

## Requirements

- Java 21 or higher
- Gradle (for building)

## Usage

### Build

```sh
./gradlew clean build
```

### Run
You can use the provided run.sh script or run the JAR directly.

```shell
./run.sh [ITERATIONS] [HEX_SEQUENCE]
```

- ITERATIONS (optional): Number of times to repeat the calculation (default: 1,000,000)  
- HEX_SEQUENCE (optional): Hex bytes (default: 01 10 00 11 00 03 06 1A C4 BA D0)

Example:
```sh
./run.sh 10000 "01 10 00 11 00 03 06 1A C4 BA D0"
```

Directly via JAR
```sh
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
  -jar build/libs/crc-calculator.jar crc "01 10 00 11 00 03 06 1A C4 BA D0" -i 10000
```