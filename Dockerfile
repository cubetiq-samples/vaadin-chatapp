FROM ghcr.io/graalvm/jdk:java17-22.3.1 as builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./
RUN ./gradlew --no-daemon --no-build-cache --no-parallel --no-configure-on-demand --offline dependencies

COPY . ./
RUN ./gradlew --no-daemon -x test nativeCompile

FROM scratch
COPY --from=builder /app/build/native/main /app
ENTRYPOINT ["/app"]