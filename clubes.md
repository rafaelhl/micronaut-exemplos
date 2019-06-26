## API de Clubes

* mn create-app clubes --features=hibernate-jpa,file-watch,discovery-consul,management,graal-native-image
* docker run -d --name=dev-consul -e CONSUL_BIND_INTERFACE=eth0 -e CONSUL_UI_BETA=true -p 8500:8500 consul
* cd clubes
* ./gradlew run --continuous
* mn create-controller clubes.controller.clubes
* mn create-client clubes.client.clubes
* Abrir IDE no diretório atual
* Alterar classe ClubesController para retornar "Ola, mundo!"
* curl 0:8080/clubes
* Inserir lombok como dependência do projeto no arquivo build.gradle
  ```groovy
    compileOnly 'org.projectlombok:lombok:1.18.8'
	annotationProcessor 'org.projectlombok:lombok:1.18.8'
  ```
* Criar classe de dominio Clube com id, nome, estadio
* Alterar controller para retornar JSON do clube
* curl 0:8080/clubes
* mn create-bean clubes.repository.clubesRepository
* mn create-bean clubes.service.clubesService
* Implementar interface ClubesRepository coms os métodos:
    ```java
        Single<Clube> save(Clube clube);
        Maybe<Clube> findClube(Long id);
        Flowable<Clube> findAll();
        Single<Long> count();
    ```
* Alterar application.yml
    ```yaml
    jpa:
      default:
        packages-to-scan:
          - 'clubes.domain'
        properties:
          hibernate:
            hbm2ddl.auto: update
            show_sql: true
    ```
* Implementar DataCreator:
    ```java
        @Singleton
        @AllArgsConstructor
        public class DataCreator implements ApplicationEventListener<ServerStartupEvent> {

            private ClubesRepository clubesRepository;

            @Override
            public void onApplicationEvent(ServerStartupEvent event) {
                if (this.clubesRepository.count().blockingGet() > 0) {
                    return;
                }
                this.clubesRepository.save(new Clube("America de Natal", "Arena das Dunas"));
                this.clubesRepository.save(new Clube("ABC FC", "Frasqueirão"));
            }
        }
    ```
* Alterar ClubesServices para delegar chamadas aos métodos do repository
* Criar ClubesApi:
    ```java
        public interface ClubesApi {
            @Get
            Flowable<Clube> index();
            @Get("/{id}")
            Maybe<Clube> show(Long id);
        }
    ```
* Alterar ClubesClient para herdar de ClubesApi
* Alterar ClubesController para implementar interface da API
* Para de rodar a aplicação via gradle
* Alterar ClubesControllerTest para validar dois endpoints expostos
* Mostrar Dockerfile com a construção do binário da GraalVM
* Rodar aplicação a partir de binário existente