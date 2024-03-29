QIn is a tool that is developed at [FZI](https://www.fzi.de/en/home/), which translates quantum circuits into Java code and thus makes quantum circuits accessible to software verification/validation tools aimed at Java code. To build QIn we provide 3 possibilities:
- Build it manually.
- Use our prebuilt docker image (this may reduce performance).
- Use the [prebuilt version](https://github.com/JonasKlamroth/QIn/releases/download/latest/QIn.jar).

## Using the Docker Image
- Install docker if you do not have it already installed (e.g., via ``sudo curl -sSL https://get.docker.com/ | sh``).
- For the following step, you might need to prefix the call with ``sudo `` if you are not a member of the docker group.
- Run the interactive container via ``docker run -it jonasklamroth/qin``.
- Run QIn as shown below in the section **Running QIn** (multitime for the benchmark is already installed).
- to copy a file back to your host system use: docker cp [container-id]:/QIn/grover.java grover.java (for the grover case study)
- get container id with docker ps

## Building QIn Manually 
### Requirements
- Java 8 (both newer and older versions do not work due to incompatibilities with OpenJML)
- Gradle 6.4.1 or higher (only if gradle wrapper does not work on your system)
- Preferrably some Unix OS (tested only on Ubuntu 22.04)

### Compiling QIn
- Make sure that **JAVA_HOME** points to a valid installation of Java 8 (tested for openjdk).
- Checkout the source code via ``git clone git@github.com:JonasKlamroth/QIn.git`` (for a checkout using SSH).
- Build the jar file via ``./gradlew fatJar`` (**QIn.jar** will appear in the root folder of your project).
- If the previous step does not work, first create a gradle wrapper via ``gradle wrapper``.

## Running QIn
- You can see the available command-line options via ``java -jar QIn.jar``.
- In general, you can run QIn via ``java -jar QIn.jar JAVA_FILE"``, where **JAVA_FILE** should be replaced by the Java file that you want to translate. For examples, see the section below.
- Use -o option to print resulting translation to file: ``java -jar QIn.jar JAVA_FILE -o OUT_FILE"``, where **JAVA_FILE** should be replaced by the Java file that you want to translate and **OUT_FILE** with the name of the file the result should be written to.

## Examples
- To translate the Grover case Study File and store the translation in the root folder of the project run the following command:
```
java -jar QIn.jar src/test/resources/Grover.java -o Grover.java
```
- You can also translate .qasm files (OPENQASM 2.0, experimental support only)

## Running tools on translations
- To run [JJBMC](https://github.com/jonasklamroth/JJBMC) on one of the examples you can either go to the JJBMC-Website or download JJBMC using the gradle task "downloadJJBMC" ("./gradlew downloadJJBMC"). To run JJBMC on the grover case study use the following command (make sure everything is installed as necessary in the readme of JJBMC): 
```
java -jar ./tools/JJBMC.jar Grover.java grover2
```

- To run the tests for the translations of the case studies run:
```
./gradlew testCaseStudies
```

## Running Benchmark for qubits and gates
- To run the Benchmarks for different number of qubits and gates we prepared the folder PerformanceTests. 
- To run a single test run JJBMC as described in the previous section on one of the
  Benchmark-Java-files. 
- To run all benchmarks and reproduce the results shown in the paper just run the script "runPerformanceTests.sh". To do so make sure of two things:
    - multitime is installed on your system (for Debian):
    ```
    sudo apt-get install multitime
    ```
    - run the provided scripts
    ```
    ./runGatesTests.sh &> qubitsResults.txt
    ./runQubitsTests.sh &> gatesResults.txt
    ```
    - create the graphs as seen in the paper (assuming the results are stored in gatesResults.txt and qubitsResults.txt, you may need to install missing dependencies see requirements.txt)
    ```
    python3 plotResults.py
    ```
  
## Writing own Circuits
To write your own quantum circuits we provide an interface similar to those of Qiskit and Cirq: [QIn.CircuitMock](https://github.com/JonasKlamroth/QIn/tree/main/src/java/QIn/QIn.CircuitMock.java)):
- Create a circuit object: ```QIn.CircuitMock c = new QIn.CircuitMock(numQubits)``` (numQubits is the number of qubits for that circuit)
- call methods to apply gates: ```c.h(1)``` (to apply a a hadamard gate to qubit 1)
  - supported standard gates for now are: h (Hadamard), x (NOT), z (Z-Rotation), cx (CONTROLLED NOT), CZ (CONTROLLED Z)
  - any arbitrary gate can be applied by providing the corresponding matrix: ```c.u(m, m_i, qbits...)``` where m and m_i are real and complex parts of the matrix for the gate you want to apply and qbits are the bits you want to apply it to (have to be adjacent qubits)
- same for measurements: ```c.measure(0)``` (to measure the 0th qubit, measuremtnes are the probabilistic measurement presented in the paper)
  - measure is the deterministic translation presented in the paper
  - measurePos is a version which considers all possible measurement results (so all measurement results that occur with probability > 0)

