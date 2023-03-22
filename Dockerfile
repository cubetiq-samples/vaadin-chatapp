FROM cubetiq/graalvm as builder

RUN echo $PATH

COPY gradlew ./
COPY gradle/wrapper/ gradle/wrapper/
COPY build.gradle.kts settings.gradle.kts ./
RUN ./gradlew -x test

COPY . ./
RUN ./gradlew --no-daemon -x test nativeCompile

FROM scratch
COPY --from=builder /home/cubetiq/build/native-image/application /app
ENTRYPOINT ["/app"]