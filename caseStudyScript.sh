echo "BB84"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar BB84.java

echo "DeutschJoszaQiskit"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar -u 18 DeutschJoszaQiskit.java dj

echo "DeutschJozsa"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar DeutschJozsa.java isBalanced -u 18 -t 60000 -tr

echo "BernsteinVaziraniQiskit"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar BernsteinVaziraniQiskit.java -u 18 findHiddenShift

echo "BernsteinVazirani"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar BernsteinVazirani.java -u 18 -t 60000 findHiddenShift

echo "Grover"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Grover.java grover2

echo "GroverQSharpTut"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar GroverQSharpTut.java groverAlternating -u 18

echo "Shor PeriodFinding"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Shor.java -u 130 findPeriodCircuit

echo "Shor factoring"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Shor.java -u 17 factorize
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Shor.java -u 17 findPeriod
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Shor.java shor

echo "ShorQiskit periodfinding"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Shor3.java -u 34 findPeriodCircuits -t 600000
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Shor3.java -u 34 findPeriodCircuit -t 600000

echo "Shor factoring"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Shor3.java -u 17 factorize
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Shor3.java -u 17 findPeriod
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Shor3.java shor

echo "SuperdenseCoding"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar SuperdenseCoding.java sdc

echo "Taffoli"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar Taffoli.java testTaffoli -u 34

echo "GHZ"
multitime -n 1 -qq java -jar ./tools/JJBMC.jar GHZ.java createGHZState -u 18
