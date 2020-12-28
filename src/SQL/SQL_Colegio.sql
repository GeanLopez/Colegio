use colegio;
insert into Alumno (nombre, apellido,correo,id_tipo,contraseña) 
values ("Mario","Guzman","Guzman@gmail.com",1,"123456");

use colegio;
select * from Alumno;

/*Verificación de Usuario*/
use colegio;
select contraseña from Alumno where correo="josue@gmail.com";
