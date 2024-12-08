#!/bin/bash

n=10

for num in 2 4 6 8
do
    java -jar ../QIn.jar ../src/test/resources/Benchmark/QBenchmark2S.java -o QBenchmark2S.java -v N=$num
    echo $num
    for j in 2 3
    do
        u=$((2 ** $j + 2))
        multitime -n $n -qq java -jar ../tools/JJBMC.jar QBenchmark2S.java -t 1200000000 gatesTest${j}single -u $u #-j "--external-sat-solver kissat"
        multitime -n $n -qq java -jar ../tools/JJBMC.jar QBenchmark2S.java -t 1200000000 gatesTest${j}singleX -u $u #-j "--external-sat-solver kissat"
        multitime -n $n -qq java -jar ../tools/JJBMC.jar QBenchmark2S.java -t 1200000000 gatesTest${j} -u $u #-j "--external-sat-solver kissat"
    done
done
