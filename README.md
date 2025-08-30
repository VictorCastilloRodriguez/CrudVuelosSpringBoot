Prueba Técnica Spring Boot
CRUD de Vuelos

- Aplicación desarrollada en Spring Boot que permite gestionar una lista de vuelos sin base de datos. 
- Incluye operaciones CRUD completas, filtros opcionales, ordenamientos y cálculo automático de duración.
- El model tiene incorporados @NotBlank y @NotNull para evitar parametros vacios.
- El dto incluye el campo duracionDias, calculado automáticamente usando LocalDate y la clase Fechas.
- No se permite que fechaLlegada sea anterior a fechaSalida.
- Se lanza excepción si se intenta eliminar un vuelo inexistente.
- La colección con todos los endpoints está exportada y guardada en el repositorio como Collección_insomnia.


---Estructura del Proyecto---

- model: clase Vuelo
- dto: clase VueloDto 
- utils: clase Fechas para calcular duración entre fechas
- repository: clase VueloRepository con 10 vuelos precargados
- service: clase VueloService con lógica de negocio y filtros
- controller: clase VueloController con endpoints REST

---Endpoints disponibles---

- GET	/vuelos	Lista todos los vuelos, ordenados por fecha de salida (Se puede cambiar el ordenamiento por parámetros)
- GET	/vuelos/{id}	Devuelve un vuelo por su ID
- POST	/vuelos	Crea un nuevo vuelo
- PUT	/vuelos/{id}	Actualiza un vuelo existente
- DELETE	/vuelos/{id}	Elimina un vuelo por ID


--- Ordenamiento opcional ---
Está la opción de ordenar la lista por:

- empresa
- lugarLlegada
- nombre
- id
