FROM gradle:jdk8


COPY ./src /QIn/src
COPY ./tools /QIn/tools
COPY ./build.gradle /QIn/build.gradle
COPY ./lib /QIn/lib
COPY ./settings.gradle /QIn/settings.gradle
COPY ./PerformanceTests /QIn/PerformanceTests

WORKDIR /QIn

RUN gradle fatJar
RUN apt-get update && apt-get install multitime

CMD /bin/sh
