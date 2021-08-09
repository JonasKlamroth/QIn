QIn is a tool that is developed at [FZI](https://www.fzi.de/en/home/), which translates quantum circuits into Java code and makes the accessible to software verification/validation tools aimed at Java code. To build QIn we provide 3 possibilities:
- Build it manually.
- Use our prebuilt docker image (this may reduce performance).
- Use the [prebuilt version](https://github.com/JonasKlamroth/QIn/tree/sefm21/bin/QIn.jar).

## Using the Docker Image
- Install docker if you do not have it already installed (e.g., via ``sudo curl -sSL https://get.docker.com/ | sh``).
- For the following step, you might need to prefix the call with ``sudo `` if you are not a member of the docker group.
- Run the interactive container via ``docker run -it jonasklamroth/qin``.
- Run QIn as shown below in the section **Running QIn**.

## Building QIn Manually 
### Requirements
- Java 8 (both newer and older versions do not work due to incompatibilities with OpenJML)
- Gradle 6.4.1 or higher (only if gradle wrapper does not work on your system)
- Preferrably some Unix OS (tested only on Ubuntu 20.04)

### Compiling QIn
- Make sure that **JAVA_HOME** points to a valid installation of Java 8 (tested for openjdk).
- Checkout the source code via ``git clone git@github.com:JonasKlamroth/QIn.git`` (for a checkout using SSH).
- Build the jar file via ``gradle fatJar`` (**QIn.jar** will appear in the root folder of your project).
- If the previous step does not work, first create a gradle wrapper via ``gradle wrapper``.

## Running QIn
- You can see the available command-line options via ``java -jar QIn.jar``.
- In general, you can run QIn via ``java -jar QIn.jar JAVA_FILE"``, where **JAVA_FILE** should be replaced by the Java file that you want to translate. For examples, see the section below.

## Examples
- To translate the Grover case Study File and store the translation in the root folder of the project run the following command:
```
java -jar QIn.jar src/test/resources/Grover.java -o Grover.java
```

## Running tools on translations
- To run [JJBMC](https://github.com/jonasklamroth/JJBMC) on one of the examples you can either go to the JJBMC-Website or use the precompiled version in the tools folder. To run JJBMC on the grover case study use the following command: 
```
java -jar ./tools/JJBMC.jar Grover.java grover
```

- To run the tests for the translations of the case studies run:
```
gradle testCaseStudies
```
