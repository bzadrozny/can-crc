# CRC Calculator

A command-line tool for calculating CRC (Cyclic Redundancy Check) values for byte sequences, written in Kotlin.

## Features

- Calculates CRC for a user-provided bits sequence (up to 96 bytes)
- Supports benchmarking with configurable iteration count
- Input validation for hex notation

## Requirements

- Java 23 or higher
- Gradle (for building)

## Usage

### Build

```sh
./gradlew clean build
```

### Run
You can use the provided run.sh script or run the JAR directly.

```shell
./run.sh [ITERATIONS] [BITS_SEQUENCE]
```

- ITERATIONS (optional): Number of times to repeat the calculation (default: 1,000,000)  
- BITS_SEQUENCE (optional): Bits (default: 0101011100101000101011111100101)

Example:
```sh
./run.sh 10000 "11010101"
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
  -jar build/libs/crc-calculator.jar crc "111100010110 001" -i 10000
```