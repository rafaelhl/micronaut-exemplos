FROM oracle/graalvm-ce:19.0.0 as graalvm
COPY . /home/app/partidas
WORKDIR /home/app/partidas
RUN gu install native-image
RUN native-image --no-server -cp build/libs/partidas-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/partidas .
ENTRYPOINT ["./partidas"]
