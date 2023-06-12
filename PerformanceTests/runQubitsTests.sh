#!/bin/bash

n=10
java -jar ../QIn.jar ../src/test/resources/Benchmark/QBenchmark3S.java -o QBenchmark3S.java -v N=$num
for i in {1..7}
do
   u=$((2 ** $i + 2))
   multitime -n $n -qq java -jar ../tools/JJBMC.jar QBenchmark3S.java -t 1200000000 qbits_$i -u $u
done
