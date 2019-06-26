FROM oracle/graalvm-ce:19.0.2 as graalvm
COPY . /home/app/clubes
WORKDIR /home/app/clubes
RUN gu install native-image
RUN native-image --no-server -cp build/libs/clubes-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/clubes .
ENTRYPOINT ["./clubes"]
