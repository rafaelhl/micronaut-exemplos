## API de Partidas

* mn create-app partidas --features=mongo-reactive,swagger-java,discovery-consul,file-watch,graal-native-image
* docker run -d --name=dev-consul -e CONSUL_BIND_INTERFACE=eth0 -e CONSUL_UI_BETA=true -p 8500:8500 consul
* docker run -d --name=dev-mongo -p 27017:27017 mongo
* cd partidas
* ./gradlew run --continuous
* mn create-controller partidas.controller.partidas
* Abrir IDE no diretório atual
    * Habilitar annotation processors caso utilize intellij
* Inserir lombok como dependência do projeto no arquivo build.gradle
  ```groovy
    compileOnly 'org.projectlombok:lombok:1.18.8'
	annotationProcessor 'org.projectlombok:lombok:1.18.8'
  ```
* Comentar dependência do mongo-embed
  ```groovy
  //    testCompile "de.flapdoodle.embed:de.flapdoodle.embed.mongo:2.0.1"
  ```
* Criar classe de dominio Partida com id, clubeCasaId, clubeVisitanteId, pontuacaoClubeCasa, pontuacaoClubeVisitante, dataPartida
* Implementar interface PartidasRepository com os métodos:
  ```java
	Single<Partida> save(@NotNull Partida partida);
	Flowable<Partida> findAll();
	Single<Long> count();
  ```
* Utilizar implementação do método abaixo para auxiliar no acesso ao mongo:
  ```java
    private MongoClient mongoClient;

    public Single<Partida> save(@NotNull Partida partida) {
		return Single.fromPublisher(getCollection().insertOne(partida)).map(success -> partida);
	}

    private MongoCollection<Partida> getCollection() {
		return this.mongoClient.getDatabase("partidasDB").getCollection("partidas", Partida.class);
	}
  ```
* Criar service com delegate para o método que salva partida utilizando o repository
* Criar client da API de clubes para obter nome e estadio do clube via GET /clubes/{id}
  * Acessar API a partir do service discovery
  * Criar circuitbreaker
  * Criar fallback para retornar valores padrões de clubes
* Criar método no service que retorna uma response com os dados da partida incluindo os nomes dos clubes que devem ser obtidos da API de clubes
  * A resposta deverá possuir private clubeCasa, clubeVisitante, estadio, pontuacaoClubeCasa, pontuacaoClubeVisitante, data
* Criar endpoint para salvar uma nova partida via POST /partidas
* Criar endpoint para retornar lista de partidas via GET /partidas
* Criar teste para chamar endpoint POST para salvar nova partida
* Subir aplicação e rodar curl 0:8080/partidas
* Alterar porta da API de Clubes para -1 e subir aplicação
* Rodar novamente curl 0:8080/partidas
* Alterar DataCreator da API de clubes
* Rodar novamente curl 0:8080/partidas até atualizar com novo valor