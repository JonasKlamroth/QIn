FROM gradle:jdk8


COPY ./src /QIn/src
COPY ./tools /QIn/tools
COPY ./build.gradle /QIn/build.gradle
COPY ./lib /QIn/lib
COPY ./settings.gradle /QIn/settings.gradle
COPY ./PerformanceTests /QIn/PerformanceTests
COPY ./runExamples.sh /QIn/runExamples.sh

WORKDIR /QIn

RUN gradle fatJar
RUN gradle wrapper
RUN apt-get update && apt-get install multitime curl -y 
RUN curl https://github.com/diffblue/cbmc/releases/download/cbmc-5.84.0/ubuntu-22.04-cbmc-5.84.0-Linux.deb -o cbmc.deb -L
RUN apt-get install ./cbmc.deb -y

CMD /bin/sh
