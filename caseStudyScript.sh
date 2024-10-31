echo "BB84"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/BB84.java

echo "DeutschJoszaQiskit"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar -u 18 ./CaseStudies/DeutschJoszaQiskit.java dj

echo "DeutschJozsa"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/DeutschJozsa.java isBalanced -u 18 -t 60000 -tr

echo "BernsteinVaziraniQiskit"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/BernsteinVaziraniQiskit.java -u 18 findHiddenShift

echo "BernsteinVazirani"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/BernsteinVazirani.java -u 18 -t 60000 findHiddenShift

echo "Grover"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Grover.java grover2

echo "GroverQSharpTut"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/GroverQSharpTut.java groverAlternating -u 18

echo "Shor PeriodFinding"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Shor.java -u 130 findPeriodCircuit

echo "Shor factoring"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Shor.java -u 17 factorize
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Shor.java -u 17 findPeriod
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Shor.java shor

echo "ShorQiskit periodfinding"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Shor3.java -u 34 findPeriodCircuits -t 600000
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Shor3.java -u 34 findPeriodCircuit -t 600000

echo "Shor factoring"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Shor3.java -u 17 factorize
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Shor3.java -u 17 findPeriod
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Shor3.java shor

echo "SuperdenseCoding"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/SuperdenseCoding.java sdc

echo "Taffoli"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/Taffoli.java testTaffoli -u 34

echo "GHZ"
multitime -n 10 -qq java -jar ./tools/JJBMC.jar ./CaseStudies/GHZ.java createGHZState -u 18
