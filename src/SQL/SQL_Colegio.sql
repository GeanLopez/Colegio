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
/*Editar Alumno*/
use colegio;
update Alumno set nombre="Juan", apellido="Cueva",correo="Penes@gmail.com",telefono=981548465,dni=1257456,sexo="Cabro",Fecha_Nacimiento="2000-05-25" where id_alumno=3;
/*Editar Docente*/
use colegio;
update Docente set nombre="Ivan", apellido="Martinez Putin",correo="Penes@gmail.com",telefono=981548465,dni=5465465,sexo="Idefinido",Fecha_Nacimiento="2000-05-25" where id_Docente=3;
/*Cambiar Contraseña Alumno*/
use colegio;
update Alumno set contraseña="penes" where id_Alumno=3;

/*Cambiar Contraseña Docente*/
use colegio;
update Docente set contraseña="gampi" where id_Docente=3;

use colegio;
update Docente set avatar="gampi" where id_Docente=3;