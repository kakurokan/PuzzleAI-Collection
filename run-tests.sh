#!/usr/bin/env bash
set -e
mkdir -p out
echo "Compiling..."
javac -d out $(find src -name "*.java") $(find test -name "*.java")

echo "Running tests..."
java -cp out tests.TestRunner
