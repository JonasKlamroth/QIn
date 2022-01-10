n=100
for i in {1..7}
do
    u=$((2 ** $i + 2))
    multitime -n $n -qq java -jar ../tools/JJBMC.jar QBenchmark3S.java -t 1200000000 qbits_$i -u $u
done
for j in 1 3 5 
do
    u=$((2 ** $j + 2))
    for i in {1..7}
    do
        multitime -n $n -qq java -jar ../tools/JJBMC.jar QBenchmark2S.java -t 1200000000 gatesTest_${j}_$i -u $u
    done
done
