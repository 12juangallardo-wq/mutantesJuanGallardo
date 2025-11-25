🧬 Mutantes – Examen MercadoLibre

API REST que detecta si una secuencia de ADN pertenece a un mutante y expone estadísticas de consultas. Implementado con Spring Boot 3.5, Java 17, H2, SpringDoc OpenAPI, y JUnit 5.

📌 1. Enunciado del Problema

Un mutante se reconoce cuando existen al menos dos secuencias de cuatro letras iguales consecutivas en dirección:

Horizontal

Vertical

Diagonal

Diagonal inversa

Sólo se permiten caracteres: A, T, C, G.

La API debe proveer:

Método	Endpoint	Descripción
POST	/mutant	Determina si un ADN es mutante
GET	/stats	Retorna estadísticas de mutantes vs humanos
GET	/swagger-ui.html	Documentación automática
⚙️ 2. Tecnologías Utilizadas

Java 17

Spring Boot 3.5.8

Spring Web / Spring Data JPA

H2 Database (en memoria)

SpringDoc OpenAPI 2.6.0

JUnit 5 + Mockito

Gradle

🚀 3. Cómo Ejecutar el Proyecto
1️⃣ Clonar el repositorio
git clone https://github.com/12juangallardo-wq/mutantesJuanGallardo.git
cd mutantesJuanGallardo

2️⃣ Ejecutar con Gradle
./gradlew bootRun

3️⃣ La API queda disponible en:
http://localhost:8080

4️⃣ Swagger UI
http://localhost:8080/swagger-ui.html

🧪 4. Cómo Ejecutar los Tests
./gradlew test


Los reportes quedan en:

build/reports/tests/test/index.html

📊 5. Endpoints
🧬 POST /mutant
✔ Request válido
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

✔ Respuestas
Código	Significado
200 OK	El ADN es mutante
403 Forbidden	El ADN es humano
400 Bad Request	Formato ADN inválido
500 Internal Server Error	Error inesperado
📈 GET /stats

Ejemplo de respuesta:

{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}

🛡️ 6. Validaciones Implementadas
✔ Validaciones sintácticas (MutantService)

Matriz no puede ser nula

Matriz no puede ser vacía

Debe ser NxN

Todas las filas deben tener la misma longitud

Solo caracteres válidos: A, T, C, G

Si falla → 400 BAD REQUEST

###
🗂️ 7. Arquitectura del Proyecto
src/
└── main/
    ├── java/
    │   └── org.example.mutantes
    │       ├── controller/ → Endpoints REST
    │       ├── service/ → Lógica de negocio
    │       ├── entity/ → Entidades JPA
    │       ├── repository/ → Repositorio (H2)
    │       └── exception/ → GlobalExceptionHandler
    └── resources/
        └── application.properties
###

🔍 8. Persistencia y Evitación de Duplicados

Cada ADN se guarda una única vez, mediante:

Hash SHA-256 del ADN

Si ya existe, se retorna su resultado sin recalcular

Esto cumple con el requisito de eficiencia.

📦 9. Base de Datos H2

Consola accesible desde:

http://localhost:8080/h2-console


Configuración:

JDBC URL: jdbc:h2:mem:mutantesdb
User: SA
Password: (vacío)


Tablas:

DNA_RECORDS (id, dna_hash, mutant)

📚 10. Documentación Swagger

Disponible en:

http://localhost:8080/swagger-ui.html


Incluye:

Schemas

Ejemplos

Response codes

Descripciones formales

🧪 11. Tests Implementados
✔ MutantDetectorTest

Detección horizontal, vertical, diagonal, inversa, matrices pequeñas y grandes.

✔ MutantServiceTest

Guarda mutante

Guarda humano

Evita duplicados

Manejo de hash

Validaciones mínimas

✔ StatsServiceTest

Cálculo correcto del ratio en todos los casos.

✔ MutantControllerTest

200 mutante

403 humano

400 error de validación

500 error interno

Cobertura estimada:

≈ 90–95% line coverage
≈ 80–90% branch coverage

🏁 12. Decisiones Técnicas Justificadas
✔ No se guarda el ADN crudo

Se guarda hash → rápido, simple, anónimo, optimizado.

✔ Motor propio de detección O(N²)

Eficiente para matrices grandes.

✔ Validaciones mínimas

Sólo para evitar datos corruptos en /stats.

👨‍💻 13. Autor

Juan Gallardo
Estudiante Ingeniería en Sistemas – UTN FRM

🌟 14. Estado del Proyecto

✔ Completamente funcional
✔ Cumple todo lo obligatorio del examen
✔ Documentación profesional
✔ Tests avanzados + Swagger + Handler global
