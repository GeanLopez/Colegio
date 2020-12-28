use colegio;
insert into Alumno (nombre, apellido,correo,id_tipo,contraseña) 
values ("Mario","Guzman","Guzman@gmail.com",1,"123456");

use colegio;
select * from Alumno;

/*Verificación de Usuario*/
use colegio;
select contraseña from Alumno where correo="josue@gmail.com";

/*Verificación de Usuario tipo*/
use colegio;
select contraseña, id_tipo from Alumno where correo="josue@gmail.com";

use colegio;
insert into Docente (Id_Especialidad,nombre, apellido,correo,id_tipo,contraseña) 
values (2,"Alberto","Moreno Cueva","Cueva@gmail.com",2,"123456");
