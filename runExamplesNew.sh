#!/bin/bash
exe() { echo "\$ ${@/eval/}" ; "$@" ; }

echo "Create QIn-jar"
exe ./gradlew fatJar

echo "DownloadJJBMC"
exe ./gradlew downloadJJBMC

echo "Creating example folder"
if [[ ! -d examples ]]
then
    mkdir examples
fi

echo "Translating Shor.java"
exe java -jar QIn.jar ./src/test/resources/Shor3.java -o ./examples/Shor3.java -v PI=3.14159265359 -ndf

echo "Running JJBMC on factorize method as holistic approach"
exe java -jar ./tools/JJBMC.jar ./examples/Shor3.java -t 60000 factorize -fi -u 17

echo "Running JJBMC on all methods (modular approach)"
exe java -jar ./tools/JJBMC.jar ./examples/Shor3.java -t 6000000 -u 17

echo "Translating BB84.java using non-det functions from JJBMC as source of nondeterminism"
exe java -jar QIn.jar ./src/test/resources/BB84.java -o ./examples/BB84.java -ndf 

echo "Running JJBMC on key bit generation method"
exe java -jar ./tools/JJBMC.jar ./examples/BB84.java -t 60000 generateKeyBit

echo "Now extend that to more bits (in this case 5)"
exe java -jar ./tools/JJBMC.jar ./examples/BB84.java -t 60000 generateKeyBits

echo "Translating DeutschJozsa.java using only real valued coefficients (since this is sufficient for this algorithm) and N=3 (costum version)"
exe java -jar QIn.jar ./src/test/resources/DeutschJozsa.java -o ./examples/DeutschJozsa.java -real -v N=3 -ndf

echo "Running JJBMC on the DeutschJozsa algorithm (costum version)"
exe java -jar ./tools/JJBMC.jar ./examples/DeutschJozsa.java -t 60000 isBalanced -u 18 -fi 

echo "Running JJBMC on a broken version of the DeutschJozsa algorithm to see if we can find the error"
exe java -jar ./tools/JJBMC.jar ./examples/DeutschJozsa.java -t 60000 isBalancedBroken -u 18 -fi -tr

echo "Translating DeutschJozsaQiskit.java using only real valued coefficients (since this is sufficient for this algorithm) and N=3 (qiskit version)"
exe java -jar QIn.jar ./src/test/resources/DeutschJozsaQiskit.java -o ./examples/DeutschJozsaQiskit.java -real -v N=3 -ndf

echo "Running JJBMC on the DeutschJozsa algorithm (qiskit version)"
exe java -jar ./tools/JJBMC.jar ./examples/DeutschJozsaQiskit.java -t 60000 dj -fi -u 17

echo "Very similar for the BernsteinVazirani Algorithm. First we translate it"
exe java -jar QIn.jar ./src/test/resources/BernsteinVazirani.java -o ./examples/BernsteinVazirani.java -real -v N=3

echo "Now we can JJBMC on it"
exe java -jar ./tools/JJBMC.jar ./examples/BernsteinVazirani.java -t 60000 findHiddenShift -u 18 -fi 

echo "Again the same with the Qiskit version"
exe java -jar QIn.jar ./src/test/resources/BernsteinVaziraniQiskit.java -o ./examples/BernsteinVaziraniQiskit.java -real -v N=3 -ndf

echo "Now we can JJBMC on it"
exe java -jar ./tools/JJBMC.jar ./examples/BernsteinVaziraniQiskit.java -t 60000 findHiddenShift -u 18

echo "Translating Grover.java using only real valued coefficients again"
exe java -jar QIn.jar ./src/test/resources/Grover.java -o ./examples/Grover.java -real 

echo "Running JJBMC on the grover algorithm"
exe java -jar ./tools/JJBMC.jar ./examples/Grover.java -t 60000 grover2

echo "Running JJBMC on a broken version of the grover algorithm to see if we can find the error"
exe java -jar ./tools/JJBMC.jar ./examples/Grover.java -t 60000 grover2broken -tr

echo "Translating SuperdenseCoding.java"
exe java -jar QIn.jar ./src/test/resources/SuperdenseCoding.java -o ./examples/SuperdenseCoding.java

echo "Running JJBMC on the superdense coding method"
exe java -jar ./tools/JJBMC.jar ./examples/SuperdenseCoding.java sdc
