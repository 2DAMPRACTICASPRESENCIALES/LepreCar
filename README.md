# LEPRECAR.



API desarrollado con el fin de gestionar la reserva de vehículos por usuarios.
En el proyecto se utilizan 3 clases relacionadas entre si.
Car -> Clase vehículos
Booking -> Clase reservas
User -> Clase usuarios.
Ratting -> Clase valoraciones

Por medio de los distintos métodos podemos realizar las siguientes funciones.

Se han controlado los estados 500,404 y 200 emitidos por el servidor.

Directorio	Método	Path	Operación
Car	POST	/cars	Crear una nuevo vehiculo
GET	/cars	Listar todos
GET 	/cars/{brand}	Filtar por marca
PUT	/cars/{id}	Modificación vehículo por id
DEL	/cars/{id}	Eliminación de vehículo por {id


Directorio	Método	Path	Operación
User	POST	/users	Crear una nuevo usuario
GET	/users	Listar todos
PUT	/users/{id}	Modificación usuario por id
DEL	/users/{id}	Eliminación de usuario por {id


Directorio	Método	Path	Operación
Raiting	POST	/raitings	Crear una nuevo valoración
GET	/raitings	Listar todos
PUT	/raitings/{id}	Modificación valoracion por id
DEL	/raitings/{id}	Eliminación de valoración por {id



