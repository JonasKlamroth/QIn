FROM gradle:jdk8


COPY ./src /QIn/src
COPY ./tools /QIn/tools
COPY ./build.gradle /QIn/build.gradle
COPY ./lib /QIn/lib
COPY ./settings.gradle /QIn/settings.gradle

WORKDIR /QIn

RUN gradle fatJar

CMD /bin/sh