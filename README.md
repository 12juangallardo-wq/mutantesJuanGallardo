🧬 Mutantes – Examen MercadoLibre

API REST que detecta si una secuencia de ADN pertenece a un mutante y expone estadísticas de consultas.
Implementado con Java 17, Spring Boot 3.3+, H2, SpringDoc OpenAPI, y JUnit 5.

------------------------------------------------------------
1. Enunciado del Problema
------------------------------------------------------------
Un mutante se reconoce cuando existen al menos dos secuencias de cuatro letras iguales consecutivas en:
- Horizontal
- Vertical
- Diagonal ↘
- Diagonal inversa ↗

Caracteres válidos: A, T, C, G.

Endpoints requeridos:
POST /mutant → determina si un ADN es mutante
GET /stats → estadísticas
GET /swagger-ui.html → documentación

------------------------------------------------------------
2. Tecnologías Utilizadas
------------------------------------------------------------
- Java 17
- Spring Boot (Web, JPA, Validation)
- H2 Database
- SpringDoc OpenAPI 2.x
- JUnit 5 + Mockito
- Gradle
- JaCoCo

------------------------------------------------------------
3. Cómo Ejecutar el Proyecto
------------------------------------------------------------
1) Clonar:
git clone https://github.com/12juangallardo-wq/mutantesJuanGallardo.git
cd mutantesJuanGallardo

2) Ejecutar:
./gradlew bootRun

------------------------------------------------------------
4. Cómo Ejecutar los Tests
------------------------------------------------------------
./gradlew test

Reportes:
- Tests: build/reports/tests/test/index.html
- Cobertura: build/reports/jacoco/test/html/index.html

------------------------------------------------------------
5. Endpoints
------------------------------------------------------------
POST /mutant

Ejemplo:
{
  "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGAAGG",
    "CCCCTA",
    "TCACTG"
  ]
}

Respuestas:
200 → mutante
403 → humano
400 → inválido

GET /stats:
{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}

------------------------------------------------------------
6. Validaciones Implementadas
------------------------------------------------------------
- Matriz no puede ser nula
- Matriz no puede ser vacía
- Debe ser NxN
- Filas con igual longitud
- Caracteres válidos: A/T/C/G

------------------------------------------------------------
7. Arquitectura del Proyecto
------------------------------------------------------------
src/
 └── main/
     ├── java/org.example.mutantes
     │    ├── controller/
     │    ├── service/
     │    ├── entity/
     │    ├── repository/
     │    └── exception/
     └── resources/

------------------------------------------------------------
8. Persistencia y Evitación de Duplicados
------------------------------------------------------------
- Hash SHA-256 del ADN
- Si ya existe → no recalcula

------------------------------------------------------------
9. Base de Datos H2
------------------------------------------------------------
Console: http://localhost:8080/h2-console
URL: jdbc:h2:mem:mutantesdb
User: SA

------------------------------------------------------------
10. Documentación Swagger
------------------------------------------------------------
http://localhost:8080/swagger-ui.html

------------------------------------------------------------
11. Tests Implementados
------------------------------------------------------------
MutantDetectorTest:
- Horizontal, vertical, diagonal, inversa
- Matrices 4x4–10x10
- Casos borde

MutantServiceTest:
- Guarda mutante
- Guarda humano
- Evita duplicados
- Hash correcto

StatsServiceTest:
- Ratio en todos los casos

MutantControllerTest:
- 200 mutante
- 403 humano
- 400 inválido
- /stats JSON válido

Cobertura:
- 90–95% líneas
- 80–90% ramas

------------------------------------------------------------
12. Decisiones Técnicas
------------------------------------------------------------
- Hash → eficiente
- Detector O(N²)
- Validaciones robustas
- Manejo global de errores

------------------------------------------------------------
13. Autor
------------------------------------------------------------
Juan Gallardo – UTN FRM

------------------------------------------------------------
14. Estado del Proyecto
------------------------------------------------------------
✔ Funcional
✔ Completo
✔ Documentado
✔ Tests avanzados
